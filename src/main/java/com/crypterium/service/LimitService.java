package com.crypterium.service;

import com.crypterium.domain.Country;

/**
 * @author truesrc
 * @since 27.03.2019
 */
public interface LimitService {
    boolean isLimit(Country country);

    Country addToTotalLoansFromCountry(Country country);
}
