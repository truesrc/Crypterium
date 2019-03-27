package com.crypterium.service;

import com.crypterium.domain.Loan;
import com.crypterium.repository.LoanRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository repository;

    @Autowired
    public LoanServiceImpl(final LoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loan apply(final Loan loan) {
        return repository.save(loan);
    }

    @Override
    public List<Loan> getAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public List<Loan> getByPersonId(int personId) {
        return repository.findByPersonId(personId);
    }
}
