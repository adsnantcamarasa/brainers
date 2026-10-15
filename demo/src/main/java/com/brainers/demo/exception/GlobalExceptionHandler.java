package com.brainers.demo.exception;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<?> NoteNotFoundException(NoteNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NoteAlreadyExistsException.class)
    public ResponseEntity<?> NoteAlreadyExistsException(NoteAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}