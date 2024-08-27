package com.example.sendmail.exception;

public class NotFoundUserException extends RuntimeException{
    private static final long  serialVerisionUID = 1;

    public NotFoundUserException(String message){
        super(message);
    }
}
