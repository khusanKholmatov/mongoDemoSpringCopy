package com.example.mongodemospring.repository;

import com.example.mongodemospring.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {

    Optional<Expense> findByExpenseName(String expenseName);

    @Query("{'expenseAmount': ?0, 'expenseCategory' : ?1}")
    Iterable<Expense> findByCategoryAndAmount(Double amount, String categoryName);

}
