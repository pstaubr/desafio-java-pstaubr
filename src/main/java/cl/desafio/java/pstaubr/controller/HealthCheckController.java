package cl.desafio.java.pstaubr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pablo Staub R
 */
@RestController
@RequestMapping("/")
public class HealthCheckController {

    private static final Logger log = LogManager.getLogger(HealthCheckController.class);

    /**
     * Validar healthcheck
     *
     * @return
     */
    @GetMapping(value = "/healthcheck")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> healthcheck() {
        log.info("Llamada a healthcheck OK.");
        return ResponseEntity.ok("healthcheck OK.");
    }
}