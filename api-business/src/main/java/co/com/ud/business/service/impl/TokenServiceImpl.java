package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.business.service.TokenService;
import co.com.ud.utiles.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private final UsuarioCliente usuarioCliente;

    @Autowired
    public TokenServiceImpl(UsuarioCliente usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    @Override
    public Optional<TokenDto> generateTokenUser(String user) {

        return Optional.empty();
    }
}
