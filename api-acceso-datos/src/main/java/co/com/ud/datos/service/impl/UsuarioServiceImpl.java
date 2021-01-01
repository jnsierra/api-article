package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.repository.IUsuarioRepository;
import co.com.ud.datos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioEntity save(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<UsuarioEntity> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioEntity> getUserFindEmailAndPass(String email, String pass) {
        return usuarioRepository.findByCorreoAndContrasenaAllIgnoreCase(email,pass);
    }

    @Override
    public Optional<UsuarioEntity> getFiltersUniques(String email, String pass) {
        if( Objects.nonNull(email)  &&  Objects.nonNull(pass) ){
            if( !email.isEmpty() && !pass.isEmpty()){
                return usuarioRepository.findByCorreoAndContrasenaAllIgnoreCase(email,pass);
            }
        }else if ( Objects.nonNull(email)  &&  Objects.isNull(pass) ){
            if( !email.isEmpty() ){
                Optional<UsuarioEntity> respuesta = usuarioRepository.findByCorreoAllIgnoreCase(email);
                return respuesta;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> updateIntentosLoginUsuario(String email) {
        Optional<UsuarioEntity> respuesta = usuarioRepository.findByCorreoAllIgnoreCase(email);
        if(respuesta.isPresent()){
            Integer intentoActual = respuesta.get().getIntentos();
            usuarioRepository.updateIntentos(respuesta.get().getId(), respuesta.get().getIntentos() + 1 );
            if(intentoActual >= 3){
                usuarioRepository.inactivarUsuario(respuesta.get().getId());
            }
            return Optional.of(Boolean.TRUE);
        }
        return Optional.empty();
    }


}