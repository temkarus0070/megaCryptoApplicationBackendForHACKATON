package org.neoflex.megacryptoapplicationbackend.Persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    @Id
    private String figi;

    @Column(name = "last_price")
    private double lastPrice;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
