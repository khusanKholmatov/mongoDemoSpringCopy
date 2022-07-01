package com.example.mongodemospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpenseInput{

    private String expenseName;
    private String expenseCategory;
    private Double expenseAmount;
}
