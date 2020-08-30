package co.com.ud.datos.audit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    private static final Logger logger = LoggerFactory.getLogger(AuditorAwareImpl.class);

    @Override
    public Optional<String> getCurrentAuditor() {
        //String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        String usuario = "jnsierra";
        logger.debug("Usuario Loggeado: ".concat(usuario));
        return Optional.of(usuario);
    }

}
