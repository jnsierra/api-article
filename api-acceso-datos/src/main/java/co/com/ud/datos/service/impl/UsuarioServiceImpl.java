package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.TipoUsuarioEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.repository.IPersonaRepository;
import co.com.ud.datos.repository.IUsuarioRepository;
import co.com.ud.datos.service.UsuarioService;
import co.com.ud.utiles.enumeracion.USER_STATE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IPersonaRepository personaRepository;

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository,IPersonaRepository personaRepository ) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
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

    @Override
    public Optional<UsuarioEntity> getUserById(Long id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        return usuario;
    }

    @Override
    public Optional<Boolean> modifyEstadoTipoUsuario(Long id, USER_STATE estado, Long idTipoUsuario) {
        Integer actulizados = usuarioRepository.modificarEstadoUsuario(id, estado);
        if(actulizados == 0){
            return Optional.of(Boolean.FALSE);
        }
        //Buscamos el usuario
        Optional<UsuarioEntity> usuario = getUserById(id);
        if (usuario.isPresent()){
            usuario.get().setTipoUsuario(TipoUsuarioEntity.builder().id(idTipoUsuario).build());
        }
        usuarioRepository.saveAndFlush(usuario.get());
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<UsuarioEntity> getUserByTipoUsuario(String tipoUsuario) {
        return usuarioRepository.findByTipoUsuario(tipoUsuario);
    }
}