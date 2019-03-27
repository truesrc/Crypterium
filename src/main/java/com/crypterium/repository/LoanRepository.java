package com.crypterium.repository;

import com.crypterium.domain.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author truesrc
 * @since 27.03.2019
 */
public interface LoanRepository extends CrudRepository<Loan, Integer> {
    List<Loan> findByPersonId(int id);
}
