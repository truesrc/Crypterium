package com.crypterium.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.crypterium.domain.Country;
import com.crypterium.domain.Loan;
import com.crypterium.domain.Person;
import com.crypterium.repository.CountryRepository;
import com.crypterium.repository.PersonRepository;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanServiceTest {

    @Autowired
    private PersonRepository persons;

    @Autowired
    private CountryRepository countries;

    @Autowired
    private LoanService service;

    @Test
    public void whenApplyLoadThenSaveInDb() {
        Person person = new Person("Алёна", "Надеждина");
        Country country = countries.save(new Country("RUS"));
        Loan loan = service.apply(new Loan("", 0D, country, person));
        List<Loan> result = service.getAll();
        assertTrue(result.contains(loan));
    }

    @Test
    public void whenFindByPersonThenReturnListOnlyForRerson() {
        Person person = new Person("Алёна", "Надеждина");
        Country country = countries.save(new Country("RUS"));
        Loan loan = service.apply(new Loan("", 0D, country, person));
        List<Loan> result = service.getByPersonId(person.getId());
        assertThat(result.iterator().next(), is(loan));
    }

}