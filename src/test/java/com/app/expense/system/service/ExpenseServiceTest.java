package com.app.expense.system.service;

import com.app.expense.system.customExceptions.ExpenseNotFoundException;
import com.app.expense.system.entity.Expense;
import com.app.expense.system.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @InjectMocks
    private ExpenseService expenseService;


    @Mock
    private ExpenseRepository expenseRepository;


    private Expense expense;

    @BeforeEach
    void setUp() {
        expense =  new Expense(101l,"Gpay",500.00);
    }

    @Test
    public void givenValidExpenseWhenSaveExpenseThenExpenseSavedSuccessfully() {

        Mockito.when(expenseRepository.save(any())).thenReturn(expense);

        Expense savedExpense = expenseService.saveExpense(expense);

        assertEquals(savedExpense.getExpenseName(),expense.getExpenseName());
    }

    @Test
    public void givenInvalidExpenseIdWhenGetExpenseByIdThenThrowException() {
        Mockito.when(expenseRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        assertThrows(ExpenseNotFoundException.class
                ,() -> expenseService.getExpenseById(105l));
    }

}