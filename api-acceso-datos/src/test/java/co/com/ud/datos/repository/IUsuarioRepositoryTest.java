package co.com.ud.datos.repository;

import co.com.ud.datos.entity.PersonaEntity;
import co.com.ud.datos.entity.TipoUsuarioEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.utiles.enumeracion.USER_STATE;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IUsuarioRepositoryTest {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Test
    public void testInsert(){
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .correo("jnsierrac@outlook.com")
                .contrasena("12345678")
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .persona(PersonaEntity.builder().id(0L).build())
                .tipoUsuario(TipoUsuarioEntity.builder().id(0L).build())
                .intentos(0)
                .estado(USER_STATE.ACTIVO)
                .build();
        UsuarioEntity response = usuarioRepository.save(usuarioEntity);
        Assert.assertNotNull(response);
        Assert.assertTrue(Boolean.TRUE);
    }
    @Test
    public void testUpdateIntentos(){
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .correo("jnsierrac@outlook.co")
                .contrasena("12345678")
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .persona(PersonaEntity.builder().id(0L).build())
                .tipoUsuario(TipoUsuarioEntity.builder().id(0L).build())
                .intentos(0)
                .estado(USER_STATE.ACTIVO)
                .build();
        //Inserto el usuario
        UsuarioEntity response = usuarioRepository.save(usuarioEntity);
        Assert.assertNotNull(response);
        Assert.assertTrue(Boolean.TRUE);
        Integer actualizados = usuarioRepository.updateIntentos(response.getId(), 2);
        Assert.assertEquals(actualizados, Integer.valueOf(1));
    }
}
