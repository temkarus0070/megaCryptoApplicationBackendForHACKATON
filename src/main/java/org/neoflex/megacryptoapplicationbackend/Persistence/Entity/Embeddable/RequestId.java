package org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public
class RequestId implements Serializable {
    private String username;

    private String currency;
}