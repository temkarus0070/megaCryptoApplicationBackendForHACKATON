package org.neoflex.megacryptoapplicationbackend.Persistence.Repository;

import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesRepository extends JpaRepository<Currency, String> {
}
