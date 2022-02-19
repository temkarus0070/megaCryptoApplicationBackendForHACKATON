package org.neoflex.megacryptoapplicationbackend.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeToCurrencyFall extends SubscribeRequest {
    private double currentPrice;
    private double necessaryPercent;
}
