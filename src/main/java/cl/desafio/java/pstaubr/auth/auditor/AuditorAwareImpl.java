package cl.desafio.java.pstaubr.auth.auditor;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author Pablo Staub R
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}