package org.neoflex.megacryptoapplicationbackend.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CandleDetails {
    private double low;
    private double high;
    private double open;
    private double close;
    private LocalDateTime openTime;
}
