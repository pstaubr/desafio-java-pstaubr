package cl.desafio.java.pstaubr.auth.jwt;


import cl.desafio.java.pstaubr.services.impl.UserDetailsServiceImpl;
import cl.desafio.java.pstaubr.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Pablo Staub R
 */
@Component
public class JwtAuthRequestFilter extends OncePerRequestFilter {

    private static final Logger log = LogManager.getLogger(JwtAuthRequestFilter.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtils;

    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(jwt);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error(Constants.CANNOT_USER_AUTHENTICATION, e.getLocalizedMessage());
        }
        filterChain.doFilter(request, response);
    }

    /**
     * @param request
     * @return
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(Constants.JWT_AUTHORIZATION);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(Constants.JWT_EARER)) {
            return headerAuth.substring(7);
        }
        return null;
    }
}