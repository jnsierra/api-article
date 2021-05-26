package co.com.ud.datos.controller;

import co.com.ud.datos.entity.FormatoEntity;
import co.com.ud.datos.service.FormatoService;
import co.com.ud.utiles.dto.FormatoDto;
import co.com.ud.utiles.enumeracion.TYPE_FORMATO_ARTICULO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class FormatoControllerTest {

    private FormatoController formatoController;
    @Mock
    private FormatoService formatoService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        formatoController = new FormatoController(formatoService, new ModelMapper());
    }

    @Test
    public void testSave(){
        FormatoEntity entity = FormatoEntity.builder()
                .id(1L)
                .formato(TYPE_FORMATO_ARTICULO.ARTICULO_POR_CORREGIR)
                .estado("ACTIVO")
                .nombre("ARTICULO.docx")
                .build();

        FormatoDto dto = FormatoDto.builder()
                .id(1L)
                .formato(TYPE_FORMATO_ARTICULO.ARTICULO_POR_CORREGIR)
                .estado("ACTIVO")
                .nombre("ARTICULO.docx")
                .idArticulo(1L)
                .build();

        Mockito.doReturn(Optional.of(entity)).when(formatoService).save(Mockito.any());

        ResponseEntity<FormatoDto> response = formatoController.save(dto);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}