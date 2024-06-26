package com.app.expense.system.service;

import com.app.expense.system.customExceptions.ExpenseNotDeletedException;
import com.app.expense.system.customExceptions.ExpenseNotFoundException;
import com.app.expense.system.entity.Expense;
import com.app.expense.system.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(Long expenseId) throws ExpenseNotFoundException {
        Optional<Expense> expense = expenseRepository.findById(expenseId);
        if(expense.isEmpty()) {
            throw new ExpenseNotFoundException("Expense Id Not Found : " + expenseId);
        } else {
            return expense.get();
        }
        //return expense.orElseThrow(() -> new ExpenseNotFoundException("Expense Id Not Found : " + expenseId));
    }


    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense updateExpense(Long expenseId, Expense updatedExpense) {

        Expense existingExpense = expenseRepository.getReferenceById(expenseId);

        if(Objects.nonNull(updatedExpense.getExpenseName()) &&
        !"".equals(updatedExpense.getExpenseName())) {
            existingExpense.setExpenseName(updatedExpense.getExpenseName());
        }

        if(0.0 != updatedExpense.getExpenseAmount()) {
            existingExpense.setExpenseAmount(updatedExpense.getExpenseAmount());

        }
        return expenseRepository.save(existingExpense);

    }

    public Expense deleteExpense(Long expenseId) {
        Expense expense =  getExpenseById(expenseId);
        expenseRepository.delete(expense);
        if(Objects.isNull(getExpenseById(expense.getExpenseId()))){
            return expense;
        } else throw new ExpenseNotDeletedException("Please check the ExpenseId is present in the Database or not!!");
    }
}
