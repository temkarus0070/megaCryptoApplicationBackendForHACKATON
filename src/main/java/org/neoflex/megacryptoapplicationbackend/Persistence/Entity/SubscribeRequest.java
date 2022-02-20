package org.neoflex.megacryptoapplicationbackend.Persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Embeddable.RequestId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SubscribeRequest {
    @EmbeddedId
    private RequestId id;


}


