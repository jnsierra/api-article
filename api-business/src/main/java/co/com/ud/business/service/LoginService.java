package co.com.ud.business.service;

import co.com.ud.utiles.enumeracion.LOGIN_ACTION;

public interface LoginService {

    LOGIN_ACTION validaLogin(String correo, String pass);
}
