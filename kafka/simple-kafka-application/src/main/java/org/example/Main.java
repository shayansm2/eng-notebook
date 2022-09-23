package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        sendMessageToProducer("new_with_keys", "is_this_working?");
        sendMessageToProducerWithCallback("new_with_keys", "is_this_working_again?");
    }

    //    actual code of this part
    //    https://github.com/conduktor/kafka-beginners-course/blob/main/kafka-basics/src/main/java/io/conduktor/demos/kafka/ProducerDemo.java
    public static void sendMessageToProducer(String topic, String message) {
        KafkaProducer<String, String> producer = Producer.getInstance();
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        producer.send(record);
        producer.flush();
        producer.close();
    }

    //    actual code of this part
    //    https://github.com/conduktor/kafka-beginners-course/blob/main/kafka-basics/src/main/java/io/conduktor/demos/kafka/ProducerDemoWithCallback.java
    public static void sendMessageToProducerWithCallback(String topic, String message) {
        Logger logger = LoggerFactory.getLogger(Producer.class.getSimpleName());

        KafkaProducer<String, String> producer = Producer.getInstance();
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        producer.send(record, (recordMetadata, e) -> {
            if (e == null) {
                logger.info("topic:partition " + recordMetadata.topic() + ":" + recordMetadata.partition());
            } else {
                logger.error("exception", e);
            }
        });
        producer.flush();
        producer.close();
    }
}