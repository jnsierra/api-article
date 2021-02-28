package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.TipoUsuarioEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.repository.IPersonaRepository;
import co.com.ud.datos.repository.IUsuarioRepository;
import co.com.ud.utiles.enumeracion.USER_STATE;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceImplTest {

    private UsuarioServiceImpl usuarioService;

    @Mock
    private IUsuarioRepository usuarioRepository;
    @Mock
    private IPersonaRepository personaRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioServiceImpl(usuarioRepository, personaRepository);
    }

    @Test
    public void testSave(){
        UsuarioEntity usuarioRequest = UsuarioEntity.builder()
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .estado(USER_STATE.ACTIVO)
                .build();
        UsuarioEntity usuarioResponse = UsuarioEntity.builder()
                .id(1L)
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .estado(USER_STATE.ACTIVO)
                .build();
        Mockito.doReturn(usuarioResponse).when(usuarioRepository).save(Mockito.any());
        UsuarioEntity response = usuarioService.save(usuarioRequest);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetAll(){
        UsuarioEntity usuarioResponse = UsuarioEntity.builder()
                .id(1L)
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .build();
        List<UsuarioEntity> rta = new ArrayList<>();
        rta.add(usuarioResponse);
        Mockito.doReturn(rta).when(usuarioRepository).findAll();

        List<UsuarioEntity> rtaService = usuarioService.getAll();
        Assert.assertNotNull(rtaService);

    }

    @Test
    public void testUserFindEmailAndPassEMPTY(){
        Optional<UsuarioEntity> response = usuarioService.getFiltersUniques(null, null);
        Assert.assertEquals(Boolean.FALSE, response.isPresent());
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
    @Test
    public void testUpdateIntentosLoginUsuarioEMPTY(){
        Mockito.doReturn(Optional.empty()).when(usuarioRepository).findByCorreoAllIgnoreCase("jnsierrac@gmail.com");
        Optional<Boolean> rta = usuarioService.updateIntentosLoginUsuario("jnsierrac@gmail.com");
        Assert.assertEquals(Boolean.FALSE, rta.isPresent());
    }

    @Test
    public void testUpdateIntentosLoginUsuarioSUCCESS(){
        UsuarioEntity usuarioResponse = UsuarioEntity.builder()
                .id(1L)
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .intentos(0)
                .build();


        Mockito.doReturn(Optional.of(usuarioResponse)).when(usuarioRepository).findByCorreoAllIgnoreCase("jnsierrac@gmail.com");
        Optional<Boolean> rta = usuarioService.updateIntentosLoginUsuario("jnsierrac@gmail.com");
        Assert.assertEquals(Boolean.TRUE, rta.isPresent());
    }

    @Test
    public void testmodifyEstadoUsuarioSUCCESS(){
        UsuarioEntity usuarioResponse = UsuarioEntity.builder()
                .id(1L)
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .contrasena("12345678")
                .correo("jnsierrac@gmail.com")
                .tipoUsuario(TipoUsuarioEntity.builder().build())
                .intentos(0)
                .build();
        Mockito.doReturn(1).when(usuarioRepository).modificarEstadoUsuario(Mockito.any(), Mockito.any());
        Mockito.doReturn(Optional.of(usuarioResponse)).when(usuarioRepository).findById(1L);


        Optional<Boolean> response = usuarioService.modifyEstadoUsuario(1L, USER_STATE.INACTIVO, 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertTrue(response.get());
    }

    @Test
    public void testmodifyEstadoUsuarioFAILED(){
        Mockito.doReturn(0).when(usuarioRepository).modificarEstadoUsuario(Mockito.any(), Mockito.any());

        Optional<Boolean> response = usuarioService.modifyEstadoUsuario(1L, USER_STATE.INACTIVO, 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertFalse(response.get());
    }
}