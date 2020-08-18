package co.com.ud.datos.controller;


import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.service.UsuarioService;
import co.com.ud.utiles.dto.UsuarioDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class UsuarioControllerTest {

    private UsuarioController usuarioController;
    private ModelMapper mapper;
    @Mock
    private UsuarioService usuarioService;



    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mapper = new ModelMapper();
        this.usuarioController = new UsuarioController(mapper, usuarioService);
    }

    @Test
    public void testSave() {
        UsuarioDto usarioDto = UsuarioDto.builder()
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .build();
        UsuarioEntity usuarioResponse = UsuarioEntity.builder()
                .id(1L)
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .build();
        Mockito.doReturn(usuarioResponse).when(usuarioService).save(Mockito.any());

        ResponseEntity<UsuarioDto> response = usuarioController.save(usarioDto);

        Assert.assertNotNull(response);

    }
}