package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.repository.IUsuarioRepository;
import co.com.ud.datos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

}