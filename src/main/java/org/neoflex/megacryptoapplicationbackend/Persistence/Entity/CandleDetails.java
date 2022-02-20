package org.neoflex.megacryptoapplicationbackend.Persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Embeddable.CandleDetailsId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandleDetails {
    @EmbeddedId
    private CandleDetailsId id;
    private double low;
    private double high;
    private double open;
    private double close;


}
