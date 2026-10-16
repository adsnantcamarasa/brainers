package com.brainers.demo.exception;

public class NoteNotFoundException extends RuntimeException{
    
    public NoteNotFoundException(String message){
        super(message);
    }
    
}