package co.com.ud.business.service;

import co.com.ud.utiles.dto.TokenDto;

import java.util.Optional;

public interface TokenService {

    Optional<TokenDto> generateTokenUser(String user);

}
