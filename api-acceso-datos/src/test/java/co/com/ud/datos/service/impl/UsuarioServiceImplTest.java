package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.repository.IUsuarioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UsuarioServiceImplTest {

    private UsuarioServiceImpl usuarioService;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    @Test
    public void testSave(){
        UsuarioEntity usuarioRequest = UsuarioEntity.builder()
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
        Mockito.doReturn(usuarioResponse).when(usuarioRepository).save(Mockito.any());
        UsuarioEntity response = usuarioService.save(usuarioRequest);
        Assert.assertNotNull(response);
    }
    @Test
    public void testUserFindEmailAndPassSUCCESS(){
        UsuarioEntity usuarioResponse = UsuarioEntity.builder()
                .id(1L)
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .build();
        Mockito.doReturn(Optional.of(usuarioResponse)).when(usuarioRepository).findByCorreoAndContrasenaAllIgnoreCase(Mockito.any(), Mockito.any());

        Optional<UsuarioEntity> response = usuarioService.getUserFindEmailAndPass("jnsierrac@gmail.com", "12345678");

        Assert.assertNotNull(response);

    }

    @Test
    public void testGetFiltersUniquesSUCCESS(){
        UsuarioEntity usuarioResponse = UsuarioEntity.builder()
                .id(1L)
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .build();
        Mockito.doReturn(Optional.of(usuarioResponse)).when(usuarioRepository).findByCorreoAndContrasenaAllIgnoreCase(Mockito.any(), Mockito.any());

        Optional<UsuarioEntity> response = usuarioService.getFiltersUniques("jnsierrac@gmail.com", "12345678");

        Assert.assertNotNull(response);

    }

    @Test
    public void testGetFiltersUniquesEMAIL(){
        UsuarioEntity usuarioResponse = UsuarioEntity.builder()
                .id(1L)
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .build();
        Mockito.doReturn(Optional.of(usuarioResponse)).when(usuarioRepository).findByCorreoAllIgnoreCase(Mockito.any());

        Optional<UsuarioEntity> response = usuarioService.getFiltersUniques("jnsierrac@gmail.com", null);

        Assert.assertNotNull(response);

        Assert.assertTrue(response.isPresent());

    }

}