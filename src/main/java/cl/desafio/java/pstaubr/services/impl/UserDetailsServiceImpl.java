package cl.desafio.java.pstaubr.services.impl;


import cl.desafio.java.pstaubr.entity.UserEntity;
import cl.desafio.java.pstaubr.repository.UserRepository;
import cl.desafio.java.pstaubr.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Pablo Staub R
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByToken(token)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USER_NOT_FOUND_TOKEN + token));
        return UserDetailsImpl.build(userEntity);
    }
}