package com.example.mongodemospring.controller;

import com.example.mongodemospring.model.Expense;
import com.example.mongodemospring.model.ExpenseInput;
import com.example.mongodemospring.service.ExpenseService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @MutationMapping
    public Expense addExpense(@Argument String expenseName,
                              @Argument String expenseCategory,
                              @Argument Double expenseAmount) {

        ExpenseInput expense = new ExpenseInput(expenseName, expenseCategory, expenseAmount);
        return expenseService.addExpense(expense);
    }


    @QueryMapping
    public List<Expense> getAllExpense() {
        return expenseService.getAllExpense();
    }

    @QueryMapping
    public Expense getExpenseByName(String name) {
        return expenseService.getExpenseByName(name);
    }

    @QueryMapping
    public List<Expense> getExpenseByCategoryNameAndAmount(Double amount,String categoryName) {
        return expenseService.getExpenseByAmountAndCategoryName(amount, categoryName);
    }

    @MutationMapping()
    public String deleteExpense(@Argument String id) {
        return expenseService.deleteExpense(id);
    }

    @QueryMapping
    public List<Expense> getAllExpensesInPage(@Argument int pageNo,
                                              @Argument int pageSize,
                                              @Argument String sortBy) {
        return expenseService.getAllExpensesInPage(pageNo, pageSize, sortBy);
    }


}
