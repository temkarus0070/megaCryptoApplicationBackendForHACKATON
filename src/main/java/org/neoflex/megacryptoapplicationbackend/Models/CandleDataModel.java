package org.neoflex.megacryptoapplicationbackend.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CandleDataModel {
    private int interval;
    private String figi;
    private CandleDetailsDTO details;
}
