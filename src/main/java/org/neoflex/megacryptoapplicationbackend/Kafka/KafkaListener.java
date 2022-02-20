package org.neoflex.megacryptoapplicationbackend.Kafka;


import org.neoflex.megacryptoapplicationbackend.Models.KafkaMessage;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Currency;
import org.neoflex.megacryptoapplicationbackend.Services.CurrencyService;
import org.neoflex.megacryptoapplicationbackend.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaListener {
    private CurrencyService currencyService;
    private NotificationService notificationService;

    @Autowired
    public KafkaListener(CurrencyService currencyService, NotificationService notificationService) {
        this.currencyService = currencyService;
        this.notificationService = notificationService;
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "${spring.kafka.topic}")
    public void listen(KafkaMessage kafkaMessage) {
        kafkaMessage.getCandle().getDetails().setCurrency(kafkaMessage.getCandle().getFigi());
        notificationService.addNotify(kafkaMessage.getCandle().getDetails());
        this.currencyService.update(new Currency(kafkaMessage.getCandle().getFigi(), kafkaMessage.getCandle().getDetails().getClose(), kafkaMessage.getCandle().getDetails().getOpenTime()));
    }
}
