package com.crypterium.service;

import com.crypterium.domain.Country;
import com.crypterium.repository.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LimitServiceTest {
    @Autowired
    private LimitService service;
    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void whenLimitNotExceedThenFalse() {
        boolean result = service.isLimit(new Country("RUS"));
        assertFalse(result);
    }

    @Test
    public void whenLimitExceedThenFalse() {
        boolean result = service.isLimit(new Country("RUS",0,5L));
        assertFalse(result);
    }
}