package com.app.expense.system.controller;

import com.app.expense.system.customExceptions.ExpenseNotFoundException;
import com.app.expense.system.entity.Expense;
import com.app.expense.system.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class ExpenseControllerTest {


    private static final String baseURL = "http://localhost:9003/expense";

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ExpenseController expenseController;


    @MockBean
    private ExpenseService expenseService;

    private Expense expense;

    @BeforeEach
    void setUp() {
        expense = Expense.builder()
                .expenseId(101L)
                .expenseName("PhonePay")
                .expenseAmount(510.00).build();
    }

    @Test
    public void givenValidExpenseWhenSaveExpenseThenNoError() throws Exception {
        Mockito.when(expenseService.saveExpense(ArgumentMatchers.any())).thenReturn(expense);
        mockMvc.perform(MockMvcRequestBuilders.post( baseURL + "/")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"expenseName\": \"PhonePay\",\n" +
                                "    \"expenseAmount\": 510.00\n" +
                                "}")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.expenseName")
                        .value("PhonePay"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.expenseAmount")
                        .value(510.00));

    }
    @Test
    public void givenInvalidExpenseIdWhenGetByExpenseIdThenThrowsException() throws Exception {
        Mockito.when(expenseService.getExpenseById(ArgumentMatchers.anyLong()))
                .thenThrow(new ExpenseNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get(baseURL + "/101")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    /**
     * Sometimes there are checked exceptions compulsory we should handle it either by try catch or
     * by the throws keyword.
     * throws -> handover the responsibility to caller method and caller method will
     * be responsible to have a handling code for the exception
     *
     * throw  ->
     * sometimes we can create a exception object manually and
     * handover that object to the JVM manually by using throw keyword.
     *
     *
     */

}