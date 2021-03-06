package com.xerox.friends.controllers.exceptionHandlers;

import com.xerox.friends.utils.ErrorMessage;
import com.xerox.friends.utils.FieldErrorMessage;
import com.xerox.friends.utils.FriendNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FriendNotFoundException.class)
    ErrorMessage exceptionHandler(FriendNotFoundException e)
    {
        return new ErrorMessage("404", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e)
    {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return fieldErrors.stream()
                .map(
                        fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())
                )
                .collect(Collectors.toList());
    }

}
