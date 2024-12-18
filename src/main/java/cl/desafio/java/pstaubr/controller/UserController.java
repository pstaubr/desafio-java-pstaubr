package cl.desafio.java.pstaubr.controller;


import cl.desafio.java.pstaubr.dto.UserDto;
import cl.desafio.java.pstaubr.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Pablo Staub R
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    /**
     * Permite crear usuario con su token correspondiente
     *
     * @param userRequest
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/registerUser")

    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userRequest) throws Exception {

        UserDto user = userServiceInterface.createUser(userRequest);

        final ResponseEntity<UserDto> ok = ResponseEntity.ok(user);
        return ok;
    }

    /**
     * Consuta por token con información
     *
     * @param auth
     * @return
     */
    @GetMapping(value = "/byTokenUser")
    public ResponseEntity<?> getUserByToken(Authentication auth) {

        UserDto user = userServiceInterface.getUser(auth);
        return ResponseEntity.ok(user);
    }
}
