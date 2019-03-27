package com.crypterium.service;

import com.crypterium.domain.Country;

/**
 * @author truesrc
 * @since 27.03.2019
 */
public interface CountryService {
    Country findByName(String name);

    Country save(Country country);

}
