package com.practice.spsecurityjwt.exception;

public class NotFoundException extends RuntimeException{
    private static final long  serialVerisionUID = 1;

    public NotFoundException(String message){
        super(message);
    }
}
