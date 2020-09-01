package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.TipoUsuarioEntity;
import co.com.ud.datos.repository.ITipoUsuarioRepository;
import co.com.ud.datos.service.TipoUsuarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TipoUsuarioServiceImplTest {

    private TipoUsuarioService tipoUsuarioService;
    @Mock
    private ITipoUsuarioRepository tipoUsuarioRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.tipoUsuarioService = new TipoUsuarioServiceImpl(tipoUsuarioRepository);
    }

    @Test
    public void testSave(){

        TipoUsuarioEntity request = TipoUsuarioEntity.builder()
                .tipo("PROFESOR")
                .descripcion("Docente de la facultad")
                .build();

        TipoUsuarioEntity response = TipoUsuarioEntity.builder()
                .id(1L)
                .tipo("PROFESOR")
                .descripcion("Docente de la facultad")
                .build();

        Mockito.doReturn(response).when(tipoUsuarioRepository).save(request);

        TipoUsuarioEntity respuesta = tipoUsuarioService.save(request);

        Assert.assertNotNull(respuesta);

    }
}