package com.tiagojesus.dev.business;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg, Exception e) {
        super(msg, e);
    }
}
