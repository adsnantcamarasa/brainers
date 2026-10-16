package com.brainers.demo.exception;

public class NoteAlreadyExistsException extends RuntimeException{
    public NoteAlreadyExistsException(String message){
        super(message);
    }
}