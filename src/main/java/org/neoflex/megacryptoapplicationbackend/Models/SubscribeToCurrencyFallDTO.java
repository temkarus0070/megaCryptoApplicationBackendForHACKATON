package org.neoflex.megacryptoapplicationbackend.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeToCurrencyFallDTO extends SubscribeRequestDTO {
    private double currentPrice;
    private double necessaryPercent;
}
