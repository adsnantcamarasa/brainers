package com.brainers.demo.exception;

public class NoteNotFoundException extends RunTimeException{
    
    public NoteNotFoundException(String message){
        super(message);
    }
    
}