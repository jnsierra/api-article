package co.com.ud.datos.controller;

import co.com.ud.datos.entity.TipoUsuarioEntity;
import co.com.ud.datos.service.TipoUsuarioService;
import co.com.ud.utiles.dto.TipoUsuarioDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class TipoUsuarioControllerTest {

    private TipoUsuarioController tipoUsuarioController;

    @Mock
    private TipoUsuarioService tipoUsuarioService;

    private ModelMapper map;


    @Before
    public void setUp(){
        map = new ModelMapper();
        MockitoAnnotations.initMocks(this);
        tipoUsuarioController = new TipoUsuarioController(map, tipoUsuarioService);
    }

    @Test
    public void testSave(){
        TipoUsuarioDto request = TipoUsuarioDto.builder()
                .descripcion("Docente de la facultad")
                .tipo("PROFESOR")
                .build();

        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .id(1L)
                .descripcion("Docente de la facultad")
                .tipo("PROFESOR")
                .build();

        Mockito.doReturn(tipoUsuarioEntity).when(tipoUsuarioService).save(Mockito.any());

        ResponseEntity<TipoUsuarioDto> response = tipoUsuarioController.save(request);

        Assert.assertNotNull(response);
    }
}