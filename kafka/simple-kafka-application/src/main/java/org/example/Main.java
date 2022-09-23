package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Main {
    public static void main(String[] args) {
        sendMessageToProducer("new_with_keys", "is_this_working?");
    }

    public static void sendMessageToProducer(String topic, String message) {
        KafkaProducer<String, String> producer = Producer.getInstance();
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        producer.send(record);
        producer.flush();
        producer.close();
    }
}