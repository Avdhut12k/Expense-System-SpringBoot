package com.app.expense.system.controller;

import com.app.expense.system.customExceptions.ExpenseNotFoundException;
import com.app.expense.system.entity.Expense;
import com.app.expense.system.service.ExpenseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@Slf4j
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger =
            LoggerFactory.getLogger(
                    ExpenseController.class);



    @PostMapping("/save/")
    public Expense saveExpense(@RequestBody Expense expense){
        return expenseService.saveExpense(expense);
    }


    @PostMapping("/")
    public ResponseEntity<?> saveUnknownExpense(@RequestBody String unknownExpense) throws JsonProcessingException {
        Expense expense = objectMapper.readValue(unknownExpense, Expense.class);

        expenseService.saveExpense(expense);
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable("id") Long expenseId) throws ExpenseNotFoundException {
        logger.info("In a ExpenseController of method getExpenseById : " + expenseId);
        return expenseService.getExpenseById(expenseId);
    }

    @GetMapping("/")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable("id") Long expenseId,
                                 @RequestBody Expense updatedExpense) {
        return expenseService.updateExpense(expenseId,updatedExpense);
    }

    @DeleteMapping("/{id}")
    public Expense deleteExpense(@PathVariable("id") Long expenseId) {
        return expenseService.deleteExpense(expenseId);
    }

}
