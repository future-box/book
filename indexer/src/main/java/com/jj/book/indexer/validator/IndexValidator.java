package com.jj.book.indexer.validator;

import com.jj.book.indexer.model.ISBNRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IndexValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ISBNRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ISBNRequest isbnRequest = (ISBNRequest) target;
    }

}


