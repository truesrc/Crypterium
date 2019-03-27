package com.crypterium.repository;

import org.springframework.data.repository.CrudRepository;
import com.crypterium.domain.Person;

/**
 *
 *
 * @author truesrc
 * @since 27.03.2019
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
