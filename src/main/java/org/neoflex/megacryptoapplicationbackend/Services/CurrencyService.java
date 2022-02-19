package org.neoflex.megacryptoapplicationbackend.Services;

import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Currency;
import org.neoflex.megacryptoapplicationbackend.Persistence.Repository.CurrenciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    private CurrenciesRepository currenciesRepository;

    @Autowired
    public CurrencyService(CurrenciesRepository currenciesRepository) {

        this.currenciesRepository = currenciesRepository;
    }

    public List<Currency> list() {
        List<Currency> currencyList = currenciesRepository.findAll();
        return currencyList;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void update(Currency currency) {
        Optional<Currency> currencyOptional = currenciesRepository.findById(currency.getFigi());
        if (currencyOptional.isPresent()) {
            if (currencyOptional.get().getUpdateDate().isBefore(currency.getUpdateDate())) {
                currencyOptional.get().setLastPrice(currency.getLastPrice());
                currenciesRepository.save(currency);
            }
        } else
            currenciesRepository.save(currency);
    }
}
