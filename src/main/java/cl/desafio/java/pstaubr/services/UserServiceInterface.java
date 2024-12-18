package cl.desafio.java.pstaubr.services;


import cl.desafio.java.pstaubr.dto.UserDto;
import org.springframework.security.core.Authentication;

/**
 * @author Pablo Staub R
 */
public interface UserServiceInterface {
    UserDto createUser(UserDto userRequest) throws Exception;

    UserDto getUser(Authentication auth);
}
