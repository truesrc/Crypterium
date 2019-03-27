package com.crypterium.service;

import com.crypterium.domain.Country;
import com.crypterium.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(final CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }
}
