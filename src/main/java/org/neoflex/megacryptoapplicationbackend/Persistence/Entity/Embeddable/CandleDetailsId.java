package org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class CandleDetailsId implements Serializable {
    private String currency;
    private LocalDateTime openTime;

}
