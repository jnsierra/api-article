package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.business.service.TokenService;
import co.com.ud.utiles.dto.TokenDto;
import co.com.ud.utiles.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private final UsuarioCliente usuarioCliente;

    @Autowired
    public TokenServiceImpl(UsuarioCliente usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    @Override
    public Optional<TokenDto> generateTokenUser(String email) {
        Optional<UsuarioDto> user = getUser(email);
        if(user.isPresent()){
            String jwt = "";
            Instant now = Instant.now();


            /*jwt = Jwts.builder().setSubject(email)
                    .claim("authorities",
                            grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(seconds, ChronoUnit.SECONDS)))
                    .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(secret)).compact();
            return jwt;*/

        }
        return Optional.empty();
    }

    /**
     * Metodo con el cual obtengo el usuario por medio su correo
     * @return
     */
    private Optional<UsuarioDto> getUser(String email){
        ResponseEntity<UsuarioDto> user = usuarioCliente.getUserByEmailAndPass(email, null);
        if(HttpStatus.OK.equals(user.getStatusCode())){
            return Optional.of(user.getBody());
        }

        return Optional.empty();
    }

    private List<GrantedAuthority> getRolesByUser(String email){
        return null;
    }
}
