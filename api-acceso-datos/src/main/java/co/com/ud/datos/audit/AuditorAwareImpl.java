package co.com.ud.datos.audit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    private static final Logger logger = LoggerFactory.getLogger(AuditorAwareImpl.class);

    @Override
    public Optional<String> getCurrentAuditor() {
        String usuario = "";
        try {
            usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch (Exception e){
            usuario = "jnsierrac";
        }
        return Optional.of(usuario);
    }

}
