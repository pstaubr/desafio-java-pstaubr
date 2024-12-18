package cl.desafio.java.pstaubr.exceptions.handler;


import cl.desafio.java.pstaubr.controller.HealthCheckController;
import cl.desafio.java.pstaubr.exceptions.BadRequestException;
import cl.desafio.java.pstaubr.exceptions.ErrorResponse;
import cl.desafio.java.pstaubr.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

/**
 * @author Pablo Staub R
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log;

    static {
        log = LogManager.getLogger(HealthCheckController.class);
    }

    /**
     * @param exc
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException exc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, exc);
    }

    /**
     * @param httpStatus
     * @param exc
     * @return
     */
    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
        return buildResponseEntity(httpStatus, exc, null);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exc, List<String> errors) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(Constants.GENERIC_MSG + exc.getLocalizedMessage());
        log.info(Constants.GENERIC_MSG + exc.getLocalizedMessage());
        error.setStatus(httpStatus.value());
        error.setTimestamp(new Date());
        error.setErrors(errors);
        return new ResponseEntity<>(error, httpStatus);
    }
}