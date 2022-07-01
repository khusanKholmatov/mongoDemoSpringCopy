package com.example.mongodemospring.service;

import com.example.mongodemospring.model.Expense;
import com.example.mongodemospring.model.ExpenseCategory;
import com.example.mongodemospring.model.ExpenseInput;
import com.example.mongodemospring.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(ExpenseInput expense) {
        Expense insertingExpense = new Expense();
        insertingExpense.setExpenseName(expense.getExpenseName());
        insertingExpense.setExpenseAmount(expense.getExpenseAmount());
        insertingExpense.setExpenseCategory(ExpenseCategory.valueOf(expense.getExpenseCategory()));
        return expenseRepository.insert(insertingExpense);
    }
    public void updateExpense(Expense expense, String id) {
        Expense savedExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find Expense by ID %s", expense.getId())
                ));

        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseName(expense.getExpenseName());

        expenseRepository.save(savedExpense);
    }
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }
    public Expense getExpenseByName(String name) {
        return expenseRepository.findByExpenseName(name)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find Expense by name %s", name)
                ));
    }
    public String deleteExpense(String id) {

        Optional<Expense> byId = expenseRepository.findById(id);

        if(byId.isEmpty()) {
            return "couldn't find expense";
        }

        expenseRepository.deleteById(id);
        return "successfully deleted";
    }

    public List<Expense> getExpenseByAmountAndCategoryName(Double amount, String categoryName) {
        Iterable<Expense> expenseIterable = expenseRepository.findByCategoryAndAmount(amount, categoryName);
        return (List<Expense>) expenseIterable;
    }

    public List<Expense> getAllExpensesInPage(int pageNo, int pageSize, String sortBy) {

        Sort sort = Sort.by(sortBy);

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);

        Page<Expense> expensePage = expenseRepository.findAll(pageRequest);
        return expensePage.getContent();
    }

    public void addAllExpenses(List<Expense> expenseList) {
        expenseRepository.saveAll(expenseList);
    }
}
