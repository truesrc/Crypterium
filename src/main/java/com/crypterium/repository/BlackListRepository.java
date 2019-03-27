package com.crypterium.repository;

import com.crypterium.domain.BlackList;
import org.springframework.data.repository.CrudRepository;

/**
 * @author truesrc
 * @since 27.03.2019
 */
public interface BlackListRepository extends CrudRepository<BlackList, Integer> {
    BlackList findByPersonId(int id);
}
