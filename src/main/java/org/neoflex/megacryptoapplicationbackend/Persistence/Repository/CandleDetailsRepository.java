package org.neoflex.megacryptoapplicationbackend.Persistence.Repository;

import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.CandleDetails;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Embeddable.CandleDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandleDetailsRepository extends JpaRepository<CandleDetails, CandleDetailsId> {
    public List<CandleDetails> findAllByIdCurrency(String currency);

    public List<CandleDetails> findByIdCurrencyIn(List<String> currencies);
}
