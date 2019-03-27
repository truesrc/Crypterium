package com.crypterium.service;

import com.crypterium.domain.Loan;

import java.util.List;

/**
 *
 * @author truesrc
 * @since 27.03.2019
 */
public interface LoanService {
    Loan apply(Loan loan);

    List<Loan> getAll();

    List<Loan> getByPersonId(int personId);
}
