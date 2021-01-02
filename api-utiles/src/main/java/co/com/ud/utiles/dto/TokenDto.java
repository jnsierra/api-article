package co.com.ud.utiles.dto;

import co.com.ud.utiles.enumeracion.LOGIN_ACTION;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class TokenDto {
	
	private String token;
	private Integer time;
	private String mensaje;
	private LOGIN_ACTION loginAction;
	
	
	public static TokenDto of(String token, Integer time) {
		return new TokenDto(token, time);
	}
	
	public static TokenDto of(String mensaje) {
		return new TokenDto(mensaje);
	}


	private TokenDto(String token, Integer time) {
		super();
		this.token = token;
		this.time = time;
	}


	public TokenDto(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
}
