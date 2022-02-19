package org.neoflex.megacryptoapplicationbackend.security.Persistence.Repositories;

import org.neoflex.megacryptoapplicationbackend.security.Persistence.Entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesRepository extends JpaRepository<Currency, String> {
}
