package co.com.ud.datos.repository;

import co.com.ud.datos.entity.PersonaEntity;
import co.com.ud.datos.entity.TipoUsuarioEntity;
import co.com.ud.datos.entity.UsuarioEntity;
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
    public void test(){
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .correo("jnsierrac@outlook.com")
                .contrasena("12345678")
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .persona(PersonaEntity.builder().id(0L).build())
                .tipoUsuario(TipoUsuarioEntity.builder().id(0L).build())
                .build();
        UsuarioEntity response = usuarioRepository.save(usuarioEntity);
        Assert.assertNotNull(response);
        Assert.assertTrue(Boolean.TRUE);
    }
}
