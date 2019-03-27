package com.crypterium.web;

import com.crypterium.domain.Country;
import com.crypterium.domain.Loan;
import com.crypterium.service.BlackListService;
import com.crypterium.service.CountryService;
import com.crypterium.service.LimitService;
import com.crypterium.service.LoanService;
import com.crypterium.web.forms.Error;
import com.crypterium.web.forms.Result;
import com.crypterium.web.forms.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.isNull;

/**
 * @author truesrc
 * @since 27.03.2019
 */

@RestController
public class LoanController {
    private final LoanService loans;
    private final BlackListService blacklists;
    private final CountryService countryService;
    private LimitService limitService;

    @Autowired
    public LoanController(final LoanService loans, final BlackListService blacklists, CountryService countryService, LimitService limitService) {
        this.loans = loans;
        this.blacklists = blacklists;
        this.countryService = countryService;
        this.limitService = limitService;
    }

    @PostMapping("/")
    public Result apply(@RequestBody Loan loan) {
        final Result result;
        if (!blacklists.isBlackListPerson(loan.getPerson().getId())) {
            if (isNull(loan.getCountry().getName())) { // Умолчание если нет страны
                loan.getCountry().setName("RUS");  //https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3
            }
            // Если еще нет такой страны в БД то сохраняем
            Country country = countryService.findByName(loan.getCountry().getName());
            if (isNull(country)) country = countryService.save(loan.getCountry());
            // Добавляем в страну из заявки, ее ID из нашей БД
            loan.getCountry().setId(country.getId());
            // Проверка сколько на стране заявок
            if (limitService.isLimit(country)) {
                result = new Error(format("Too many loans from the country %s", loan.getCountry().getName()));
            } else {
                result = new Success<>(loans.apply(loan));
            }
        } else
            result = new Error(format("User %s in blacklist", loan.getPerson().getId()));

        return result;
    }

    @GetMapping("/")
    public List<Loan> getAll() {
        return loans.getAll();
    }

    @GetMapping("/{personId}")
    public List<Loan> findByPersonId(@PathVariable int personId) {
        return loans.getByPersonId(personId);
    }
}
