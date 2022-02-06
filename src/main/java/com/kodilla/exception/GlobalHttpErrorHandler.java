package com.kodilla.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<Object> handleTitleNotFoundException() {
        return new ResponseEntity<>("Title with given id doesn't exist",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleNotFoundException2.class)
    public ResponseEntity<Object> handleTitleNotFoundException2() {
        return new ResponseEntity<>("There is no such title in the database",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CopyNotFoundException.class)
    public ResponseEntity<Object> handleCopyNotFoundException() {
        return new ResponseEntity<>("Copy with given id doesn't exist",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReaderNotFoundException.class)
    public ResponseEntity<Object> handleReaderNotFoundException() {
        return new ResponseEntity<>("Reader with given id doesn't exist",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BorrowNotFoundException.class)
    public ResponseEntity<Object> handleBorrowNotFoundException() {
        return new ResponseEntity<>("Borrow with given id doesn't exist",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalOperation.class)
    public ResponseEntity<Object> handleIllegalException() {
        return new ResponseEntity<>("Illegal operation. Invalid status copy selected",
                HttpStatus.BAD_REQUEST);
    }
}
