package com.app.expense.system.repository;

import com.app.expense.system.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Expense findByExpenseId(Long expenseId);
}
