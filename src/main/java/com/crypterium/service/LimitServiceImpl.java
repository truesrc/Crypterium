package com.crypterium.service;

import com.crypterium.domain.Country;
import com.crypterium.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.lang.System.currentTimeMillis;
import static java.util.Objects.isNull;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@Service
public class LimitServiceImpl implements LimitService {
    private final CountryRepository countryRepository;
    @Value("${limit.period}")
    private int period;

    @Value("${limit.total}")
    private int total;

    @Autowired
    public LimitServiceImpl(final CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /*
     *Проверяем сколько на стране заявок.
     * Если меньше установленного ограничения, то ложь.
     *
     *
     *
     */
    @Override
    public boolean isLimit(Country country) {
        if (isNull(country) || isNull(country.getCount())) {
            addToTotalLoansFromCountry(country);
            return false;
        } else {
            if ((currentTimeMillis() - country.getTimeStart()) / 1000 > period) {
                addToTotalLoansFromCountry(country);
                return false;
            } else {
                if (country.getCount() < total) {
                    addToTotalLoansFromCountry(country);
                    return false;
                } else return true;
            }
        }
    }

    /*
     *
     * Обновляем запись страны в БД. Записываем новое значение в count
     * т.е. число заявок в разрешенном периоде и timeStart т.е. начало времени отсчета.
     *
        ID  	COUNT       NAME          TIME_START
        1	    1	            RUS              1553706345058
        2	    1	            BOL	            1553706609528
     *
     *Если дата сейчас минус TIME_START больше периода то TIME_START
     * ставим сейчас и заявку одну. Если меньше то смотрим сколько заявок.
     * Если заявок меньше разрешенного то увеличиваем COUNT на 1.
     * Если больше то отказываем в выдаче.
     *
     *
     */
    @Override
    public Country addToTotalLoansFromCountry(Country country) {
        if (isNull(country.getCount())) {
            country.setCount(1L);
            country.setTimeStart(currentTimeMillis());
        } else {
            if ((currentTimeMillis() - country.getTimeStart()) / 1000 > period) {
                country.setCount(1L);
                country.setTimeStart(currentTimeMillis());
            } else {
                country.setCount(country.getCount() + 1);
            }
        }
        return countryRepository.save(country);
    }
}
