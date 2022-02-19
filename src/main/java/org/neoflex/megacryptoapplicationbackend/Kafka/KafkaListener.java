package org.neoflex.megacryptoapplicationbackend.Kafka;


import org.neoflex.megacryptoapplicationbackend.Models.KafkaMessage;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Currency;
import org.neoflex.megacryptoapplicationbackend.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaListener {
    private CurrencyService currencyService;

    @Autowired
    public KafkaListener(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "${spring.kafka.topic}")
    public void listen(KafkaMessage kafkaMessage) {
        this.currencyService.update(new Currency(kafkaMessage.getCandle().getFigi(), kafkaMessage.getCandle().getDetails().getClose()));
    }
}
