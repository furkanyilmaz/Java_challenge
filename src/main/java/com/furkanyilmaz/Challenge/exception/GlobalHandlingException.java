package com.furkanyilmaz.Challenge.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlingException {

    @ExceptionHandler({FurkanYilmazException.class})
    public String handlingNotFoundException(){
        return "böyle bir data yok.";

    }
    @ExceptionHandler({NullPointerException.class})
    public String handlingNullPointerException(){  //null değer olduğunda yakalar.
        return "null deger girildi...";
    }
}
