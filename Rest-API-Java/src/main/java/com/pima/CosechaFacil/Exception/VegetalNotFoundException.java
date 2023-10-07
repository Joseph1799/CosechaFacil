package com.pima.CosechaFacil.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VegetalNotFoundException extends RuntimeException {

    public VegetalNotFoundException(String message) {
        super(message);
    }
}

