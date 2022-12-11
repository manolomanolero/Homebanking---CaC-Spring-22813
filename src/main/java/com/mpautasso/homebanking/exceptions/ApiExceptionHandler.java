package com.mpautasso.homebanking.exceptions;

import com.mpautasso.homebanking.exceptions.custom.EmptyElementException;
import com.mpautasso.homebanking.exceptions.custom.EntityNotFoundException;
import com.mpautasso.homebanking.exceptions.custom.InvalidElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            EntityNotFoundException.class,
            EmptyElementException.class,
            InvalidElementException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }
}
