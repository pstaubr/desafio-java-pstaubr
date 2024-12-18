package cl.desafio.java.pstaubr.auth;

import cl.desafio.java.pstaubr.auth.auditor.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Pablo Staub R
 */
@Configuration
@EnableJpaAuditing
public class PersistenceConfig {

    /**
     * @return
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }


}
