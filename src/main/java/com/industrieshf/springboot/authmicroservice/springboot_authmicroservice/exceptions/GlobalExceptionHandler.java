package com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {

        StackTraceElement[] stackTrace = ex.getStackTrace();
    
        // Obtener la primera línea relevante (la línea donde se originó la excepción)
        StackTraceElement element = stackTrace[0]; 
        int lineNumber = element.getLineNumber();   // Obtiene el número de la línea
        String fileName = element.getFileName();    // Obtiene el nombre del archivo
        String className = element.getClassName();  // Obtiene el nombre de la clase
        String methodName = element.getMethodName(); // Obtiene el nombre del método
    

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error=> " + ex.getMessage()+"Excepción en el archivo: " + fileName + 
                           ", clase: " + className +
                           ", método: " + methodName + 
                           ", en la línea: " + lineNumber);
    }
}
