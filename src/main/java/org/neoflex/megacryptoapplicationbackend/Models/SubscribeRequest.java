package org.neoflex.megacryptoapplicationbackend.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class SubscribeRequest {
    private String currency;
    private boolean endSubscribe;
}
