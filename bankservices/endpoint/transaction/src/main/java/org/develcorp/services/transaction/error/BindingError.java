package org.develcorp.services.transaction.error;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class BindingError {

    public String getMessage(BindingResult bindingResult){
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<String> message = new ArrayList<>();

        for (FieldError e : errors){
            message.add("\n" + "Field: " + e.getField() + " -> " + e.getDefaultMessage()+ "\n");
        }

        return message.toString();
    }
}
