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

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IUsuarioRepositoryTest {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IPersonaRepository personaRepository;

    @Test
    public void testInsert(){
        PersonaEntity personaEntity =  PersonaEntity.builder()
                .documento(1010203L)
                .apellidos("Perez")
                .nombres("Pepito")
                .fechaNacimiento(new Date())
                .build();
        PersonaEntity persona = personaRepository.save(personaEntity);

        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .correo("jnsierrac@outlook.com")
                .contrasena("12345678")
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .persona(persona)
                .tipoUsuario(TipoUsuarioEntity.builder().id(1L).build())
                .intentos(0)
                .estado(USER_STATE.ACTIVO)
                .codigo("2017267780")
                .build();
        UsuarioEntity response = usuarioRepository.save(usuarioEntity);
        Assert.assertNotNull(response);
        Assert.assertTrue(Boolean.TRUE);
    }
    @Test
    public void testUpdateIntentos(){
        PersonaEntity personaEntity =  PersonaEntity.builder()
                .documento(1010204L)
                .apellidos("Perez")
                .nombres("Pepito")
                .fechaNacimiento(new Date())
                .build();
        PersonaEntity persona = personaRepository.save(personaEntity);

        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .correo("jnsierrac@outlook.co")
                .contrasena("12345678")
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .persona(persona)
                .tipoUsuario(TipoUsuarioEntity.builder().id(1L).build())
                .intentos(0)
                .estado(USER_STATE.ACTIVO)
                .codigo("2017267781")
                .build();
        //Inserto el usuario
        UsuarioEntity response = usuarioRepository.save(usuarioEntity);
        Assert.assertNotNull(response);
        Assert.assertTrue(Boolean.TRUE);
        Integer actualizados = usuarioRepository.updateIntentos(response.getId(), 2);
        Assert.assertEquals(actualizados, Integer.valueOf(1));
    }
    @Test
    public void testModificarEstado(){
        PersonaEntity personaEntity =  PersonaEntity.builder()
                .documento(1010205L)
                .apellidos("Perez")
                .nombres("Pepito")
                .fechaNacimiento(new Date())
                .build();
        PersonaEntity persona = personaRepository.save(personaEntity);

        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .correo("jnsierrac@outlook.com.co")
                .contrasena("12345678")
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .persona(persona)
                .tipoUsuario(TipoUsuarioEntity.builder().id(1L).build())
                .intentos(0)
                .estado(USER_STATE.ACTIVO)
                .codigo("2017267783")
                .build();
        //Inserto el usuario
        UsuarioEntity response = usuarioRepository.save(usuarioEntity);
        Assert.assertNotNull(response);
        Assert.assertTrue(Boolean.TRUE);
        Integer actualizados = usuarioRepository.modificarEstadoUsuario(response.getId(), USER_STATE.INACTIVO);
        Assert.assertEquals(actualizados, Integer.valueOf(1));
    }

    @Test
    public void testFindByTipoUsuario(){
        PersonaEntity personaEntity =  PersonaEntity.builder()
                .documento(1010206L)
                .apellidos("Perez")
                .nombres("Pepito")
                .fechaNacimiento(new Date())
                .build();
        PersonaEntity persona = personaRepository.save(personaEntity);


        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .correo("usuario1@outlook.com")
                .contrasena("12345678")
                .nombre("Pepito Perez")
                .cambioContra("S")
                .persona(persona)
                .tipoUsuario(TipoUsuarioEntity.builder().id(1L).build())
                .intentos(0)
                .estado(USER_STATE.ACTIVO)
                .codigo("2017267784")
                .build();
        UsuarioEntity response = usuarioRepository.save(usuarioEntity);
        Assert.assertNotNull(response);
        Assert.assertTrue(Boolean.TRUE);
        List<UsuarioEntity> responseService = usuarioRepository.findByTipoUsuario("PROFESOR");
        Assert.assertNotNull(responseService);
    }
}
