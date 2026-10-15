package com.brainers.demo.exception;

public enum MessageException{
    NOTE_NOT_FOUND("Note not found"),
    NOTE_ALREADY_EXIST("Note already exist");

    String messageException;
    
    MessageException(String messageException){
        this.messageException = messageException;
    }

    // Get message
    String getMessageExeption(){
        return this.messageException;
    }
}