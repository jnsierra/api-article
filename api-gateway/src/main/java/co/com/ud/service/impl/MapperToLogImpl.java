package co.com.ud.service.impl;

import co.com.ud.service.IMapperToLog;
import co.com.ud.utiles.dto.LogRequestDto;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MapperToLogImpl implements IMapperToLog {
    @Override
    public LogRequestDto fromRequest(ServerHttpRequest request) {
        Flux<DataBuffer> body = request.getBody();

        return null;
    }

    @Override
    public LogRequestDto fromRespose(ServerHttpResponse response) {
        return null;
    }
}
