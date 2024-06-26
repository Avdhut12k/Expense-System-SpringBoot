package com.app.expense.system.repository;

import com.app.expense.system.entity.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @BeforeEach
    void setUp() {

        Expense expense = Expense.builder()
                .expenseName("PhonePay")
                .expenseAmount(510.00).build();
        testEntityManager.persist(expense);
    }

    @Test
    public void giveValidExpenseIdWhenFindByExpenseIdThenSuccessFulExpenseReturn() {
        Expense expense1 = expenseRepository.findByExpenseId(1l);

        assertEquals(expense1.getExpenseName(),"PhonePay");
        assertEquals(expense1.getExpenseAmount(),510.00);
    }
}