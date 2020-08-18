package co.com.ud.datos.repository;

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
public class IUsuarioRepositoryTest_IT {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Test
    public void testSave(){
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .correo("jnsierrac@gmail.com")
                .contrasena("12345678")
                .nombre("Jesus Nicolas")
                .cambioContra("S")
                .build();
        UsuarioEntity response = usuarioRepository.save(usuarioEntity);
        Assert.assertNotNull(response);

    }
}
