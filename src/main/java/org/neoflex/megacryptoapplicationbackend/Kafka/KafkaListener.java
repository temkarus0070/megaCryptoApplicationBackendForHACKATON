package org.neoflex.megacryptoapplicationbackend.Kafka;


import org.neoflex.megacryptoapplicationbackend.Models.KafkaMessage;


public class KafkaListener {
    @org.springframework.kafka.annotation.KafkaListener(topics = "${spring.kafka.topic}")
    public void listen(KafkaMessage kafkaMessage) {

    }
}
