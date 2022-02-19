package org.neoflex.megacryptoapplicationbackend.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KafkaMessage implements Serializable {
    private String eventId;
    private CandleDataModel candle;
}
