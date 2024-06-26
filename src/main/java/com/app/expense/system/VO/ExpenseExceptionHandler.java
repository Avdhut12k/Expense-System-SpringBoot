package com.app.expense.system.VO;

import com.app.expense.system.customExceptions.ExpenseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class ExpenseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ExpenseNotFoundException.class)
    public ResponseEntity<ExpenseVO> ExpenseObjectNotFoundHandler(ExpenseNotFoundException exception,
                                                                  WebRequest request) {
        ExpenseVO vo = new ExpenseVO(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<ExpenseVO>(vo, HttpStatus.NOT_FOUND);
    }
}
