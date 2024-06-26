package com.app.expense.system.customExceptions;

public class ExpenseNotDeletedException extends RuntimeException{

    public ExpenseNotDeletedException() {
        super();
    }

    public ExpenseNotDeletedException(String message) {
        super(message);
    }

    public ExpenseNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpenseNotDeletedException(Throwable cause) {
        super(cause);
    }

    protected ExpenseNotDeletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
