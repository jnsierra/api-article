package co.com.ud.service;

import co.com.ud.utiles.dto.LogRequestDto;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

public interface IMapperToLog {

    LogRequestDto fromRequest(ServerHttpRequest request);

    LogRequestDto fromRespose(ServerHttpResponse response);

}
