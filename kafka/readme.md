# Apache Kafka

#### resources:
1. [udemy course](https://www.udemy.com/course/apache-kafka/)
2. [Kafka: The Definitive Guide](https://www.amazon.com/Kafka-Definitive-Real-Time-Stream-Processing/dp/1491936169)
3. [data streaming with kafka](https://github.com/ziritrion/dataeng-zoomcamp/blob/main/notes/6_streaming.md)

## kafka system design

![np](static/big-picture.png)

* **message**: unit of data to store on kafka (orderId:123) like rows in SQL db
  * messages are immutable after write
* **key**: optional metadata of message
  * are being used to write messages in to partitions in a more controlled manner
* **topic**: the name or label of messages (order:create) like tables in SQL db
* **partition**: each topic has multiple partitions
  * message assignments to partitions is handled by producer and is round-robin
  * if the messages have key all messages with same key go to the same partition
* **batch**: collection of messages on same topic and same partition
  * for efficiency, messages are writen into kafka in batches
  * batches are compressed for better data transfer
* **offset**: each message in a partition has a _unique_ and incremental id named offset
  * the incremental order is within each partition (not topic)
  * kafka keeps data for limited time (default 1 week). after delete offset will not get reset
* **stream**: single topic of data, regardless of number of partitions

![np](static/topic-partition-offset.png)

* **broker**: servers (distributed systems)
  * each broker is a bootstrap server (for broker discovery)
  * each broker know about all brokers, topics and partitions (have metadata)
* **kafka cluster**: multiple brokers
* **logs**: data (messages) which stores in kafka brokers' storage disk.

![np](static/broker-discovery.png)

* **replication factor**: number of partitions with same data
  * 3 is safe and 2 is a bit risky
* **leader partition**: the only partition that can receive and serve data (master)
  * only one broker can be a leader for a given partition
* **ISR partition**: in-sync replica (slave)
  * other brokers are ISR

![np](static/cluster-broker-2.png)

* **producer**: write data on topics
  * also known as publisher, writers
  * know which partition and broker to use
  * load balancer
    * round-robin for messages without key
    * key-based: all keys go to same partition (key-hashing)
  * producers get acknowledgement of data writes:
    * acks = 0: producer won't wait for acknowledgement (possible data loss)
    * acks = 1: producer wait for leader's acknowledgement (limited data loss)
    * acks = all: producer wait for leader + replicas acknowledgement (no data loss)

internals of sending message from producer to kafka broker:
```mermaid
flowchart TB
    p(producer)
    k{{kafka broker}}
    subgraph logs[logs for topic 'abc']
        m1[message 1]
        m2[message 2]
    end
    p-->|1. producer declares the topic it wants to talk about to Kafka|k
    p-->|2. producer sends messages to Kafka|k
    k -->|3. Kafka assigns an ID to the messages and writes them to the logs|logs
    k-.->|4. Kafka sends an acknowledgement to the producer|p
```

---
* **consumer**: read data from topic
  * also known as subscribers, readers
  * know which broker to read from
  * read on order in each partition

internals of getting message from kafka broker by consumer:
```mermaid
flowchart BT
    c(consumer)
    k{{kafka broker}}
    subgraph logs[logs for topic 'abc']
        m1[messages]
        m2[message 2]
    end
    c-->|1. consumer declares to Kafka that it wants to read from a particular topic|k
    k<-->|2. Kafka checks logs and get read and unread messages|logs
    k-->|3. Kafka sends unread messages to consumer|c
    c-.->|4. The consumer sends an acknowledgement to Kafka|k
```

* **consumer group**: each consumer group can have multiple consumers
  * each consumer group can read all data from all brokers
  * when a consumer inside a group reads a message, that message will NOT be delivered to any other consumer in the group
  * therefore consumer will not have to deal with duplicated or redundant messages.
  * each partition is assigned to one consumer and only that consumer within that consumer group can read that data
  * if #consumers > #partitions then some consumers are inactive (not recommended but use for backup) 
* **ownership**: the mapping of a customer to a partition

![np](static/consumer-groups.png)

* **consumer offset**: the offset which the consumer group was reading. `__consumer_offset`
  * 3 types of committing offsets:
    1. at most once: commit when data is received
       * if process fails, message will be lost
    2. at least once: commit when data is received and process is done
       * if process fails, it will read the message again
       * process should be idempotent
    3. exactly once:

how consumer offset works?

`__consumer_offsets` is a special topic that keeps track of messages read by each consumer and topic. When a consumer reads messages and Kafka receives the ack, Kafka posts a message to `__consumer_offsets` with the consumer ID, the topic and the message IDs that the consumer has read.

* **zookeeper**: kafka manager
  * manage brokers (keeps a list of them)
  * perform leader election of partitions
  * notify kafka in case of new topic, topic deletion, broker dies, broker comes up
  * have odd number of servers, one leader (which handles write) and the rest followers (which handles reads)

![np](static/zookeeper-kafka.png)

---

![np](static/guarantees.png)

## kafka CLI commands
### topics:
create
```commandline
kafka-topics.sh --bootstrap-server localhost:9092 --topic topic_name --create
```
options:
* `--partitions 3`
* `--replication-factor 2`

see existing topics:
```commandline
kafka-topics.sh --bootstrap-server localhost:9092 --list
kafka-topics.sh --bootstrap-server localhost:9092 --describe
kafka-topics.sh --bootstrap-server localhost:9092 --topic topic_name --describe
```
delete
```commandline
kafka-topics.sh --bootstrap-server localhost:9092 --topic topic_name --delete
```
### producers:
```commandline
kafka-console-producer --bootstrap-server localhost:9092 --topic topic_name
```
options:
* `--producer-property acks=all`
* `--property parse.key=true --property key.separator=,`
### consumers:
```commandline
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic shayan_test
```
options:
* `--from-beginning`
* `--group group_name`
* `--property print.key=true --property key.separator=,`

consumer groups:
```commandline
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --groupd group_name
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --execute --reset-offsets --to-earliest --topic topic_name
```
execute options
* `--topic topic_name` and `--all-topics`
* `--to-earliest`,  `--to-datetime`, `--to-latest`, `--shift-by` and `--to-current`