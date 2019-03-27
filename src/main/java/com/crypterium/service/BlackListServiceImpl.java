package com.crypterium.service;

import com.crypterium.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@Service
public class BlackListServiceImpl implements BlackListService {
    private final BlackListRepository repository;

    @Autowired
    public BlackListServiceImpl(final BlackListRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isBlackListPerson(int personId) {
        return repository.findByPersonId(personId) != null;
    }
}
