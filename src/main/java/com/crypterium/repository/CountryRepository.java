package com.crypterium.repository;

import com.crypterium.domain.Country;
import org.springframework.data.repository.CrudRepository;

/**
 * @author truesrc
 * @since 27.03.2019
 */
public interface CountryRepository extends CrudRepository<Country, Integer> {
    Country findByName(String name);

}
