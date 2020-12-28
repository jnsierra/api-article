package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.business.service.TokenService;
import co.com.ud.utiles.dto.TokenDto;
import co.com.ud.utiles.dto.UsuarioDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService {

    private final UsuarioCliente usuarioCliente;
    public static Integer seconds = Integer.valueOf("3600") ;
    private final String secret;



    @Autowired
    public TokenServiceImpl(UsuarioCliente usuarioCliente,@Value("${jwt.secret}")String secret) {
        this.usuarioCliente = usuarioCliente;
        this.secret = secret;
    }

    @Override
    public Optional<TokenDto> generateTokenUser(String email) {
        Optional<UsuarioDto> user = getUser(email);
        if(user.isPresent()){
            String jwt = "";
            Instant now = Instant.now();


            jwt = Jwts.builder().setSubject(email)
                    .claim("authorities",
                            getRolesByUser(email).stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(seconds, ChronoUnit.SECONDS)))
                    .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(secret)).compact();

            return Optional.of(TokenDto.builder()
                    .token(jwt)
                    .mensaje("Autenticacion exitosa")
                    .time(seconds)
                    .build());
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
        String roleStr = "ROLE_ADMIN";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleStr);
        return grantedAuthorities;
    }
}
