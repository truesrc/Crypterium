package com.crypterium.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.crypterium.domain.BlackList;
import com.crypterium.domain.Person;
import com.crypterium.repository.BlackListRepository;
import com.crypterium.repository.PersonRepository;

import static org.junit.Assert.*;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlackListServiceTest {

    @Autowired
    private PersonRepository persons;

    @Autowired
    private BlackListRepository blacklists;

    @Autowired
    private BlackListService service;

    @Test
    public void whenPersonInBlackListThenReturnTrue() {
        Person person = persons.save(new Person("Алёна", "Надеждина"));
        blacklists.save(new BlackList(person));
        boolean result = service.isBlackListPerson(person.getId());
        assertTrue(result);
    }

    @Test
    public void whenBlackListEmptyThenAnyPersonNotIn() {
        Person person = this.persons.save(new Person("Алёна", "Надеждина"));
        boolean result = this.service.isBlackListPerson(person.getId());
        assertFalse(result);
    }
}