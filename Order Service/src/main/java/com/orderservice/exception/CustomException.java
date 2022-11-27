package com.orderservice.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private String errorCode;
    private int status;

    public CustomException(String messsage, int status) {
        super(messsage);
        this.errorCode = errorCode;
        this.status = status;
    }
}
