package cl.desafio.java.pstaubr.services.impl;


import cl.desafio.java.pstaubr.auth.jwt.JwtUtil;
import cl.desafio.java.pstaubr.dto.UserDto;
import cl.desafio.java.pstaubr.dto.UserPhonesDto;
import cl.desafio.java.pstaubr.entity.PhonesEntity;
import cl.desafio.java.pstaubr.entity.UserEntity;
import cl.desafio.java.pstaubr.exceptions.BadRequestException;
import cl.desafio.java.pstaubr.repository.UserRepository;
import cl.desafio.java.pstaubr.services.UserServiceInterface;
import cl.desafio.java.pstaubr.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Pablo Staub R
 */
@Service
public class UserServiceInterfaceImpl implements UserServiceInterface {
    private static final Logger log = LogManager.getLogger(UserServiceInterfaceImpl.class);

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1523240978261506184L;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtils;

    /**
     * @param userRequest
     * @return
     * @throws Exception
     */
    @Override
    public UserDto createUser(UserDto userRequest) {


        /**
         * Validación si existe email en nuestra bd
         */
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            log.info(Constants.EXIST_EMAIL);
            throw new BadRequestException(Constants.EXIST_EMAIL);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setDisabled(Boolean.FALSE);
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setName(userRequest.getName());
        userEntity.setPassword(encoder.encode(userRequest.getPassword()));
        userEntity.setToken(Constants.EMPTY);
        userEntity.setTypeToken(Constants.VALUE_BEARER);

        List<PhonesEntity> phones = new ArrayList<>();
        for (UserPhonesDto phone : userRequest.getPhones()) {
            phones.add(new PhonesEntity(phone.getNumber(), phone.getCitycode(), phone.getContrycode()));
        }
        userEntity.setPhones(phones);
        userEntity = userRepository.save(userEntity);
        /**Usuario registrado correctamente en H2*/

        /**Se genera token */
        String jwt = jwtUtils.generateTokenLimited(userEntity, userEntity.getEmail());
        /**Seteo de token usuario */
        userEntity.setToken(jwt);
        userEntity = userRepository.save(userEntity);
        /**Usuario registrado correctamente en H2*/
        log.info(Constants.USER_OK);
        return new UserEntity().normalizeObj(userEntity);
    }

    /**
     * @param auth
     * @return
     */
    @Override
    public UserDto getUser(Authentication auth) {
        UserDetailsImpl userAuth = (UserDetailsImpl) auth.getPrincipal();

        Optional<UserEntity> user = userRepository.findById(userAuth.getId());
        /**
         * Usuario id existe
         */
        if (user.isPresent()) {
            return new UserEntity().normalizeObj(user.get());
        } else {
            return new UserDto();
        }
    }
}