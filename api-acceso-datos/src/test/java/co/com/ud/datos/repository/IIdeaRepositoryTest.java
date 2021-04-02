package co.com.ud.datos.repository;

import co.com.ud.datos.entity.IdeaEntity;
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
public class IIdeaRepositoryTest {

    @Autowired
    private IIdeaRepository ideaRepository;

    @Test
    public void saveIdea(){
        IdeaEntity ideaEntity = IdeaEntity.builder()
                .contenido("Esta es una prueba de contendio")
                .titulo("Titulo de prueba")
                .id_profesor(3L)
                .estado("CREADA")
                .usuario(UsuarioEntity.builder()
                        .id(1L)
                        .build())
                .build();
        IdeaEntity response = ideaRepository.save(ideaEntity);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getId());
    }
    @Test
    public void modificarIdeaSUCCESS(){
        IdeaEntity ideaEntity = IdeaEntity.builder()
                .contenido("Esta es una prueba de contendio")
                .titulo("Titulo de prueba")
                .id_profesor(3L)
                .estado("CREADA")
                .usuario(UsuarioEntity.builder()
                        .id(1L)
                        .build())
                .build();
        IdeaEntity response = ideaRepository.save(ideaEntity);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getId());
        //Hacemos la actualizacion sobre la entidad
        Integer update = ideaRepository.modificarIdea(response.getId(), "Titulo nuevo", "contenido actualizado", "CREADA", 3L);
        Assert.assertNotNull(update);
        Assert.assertTrue(update>0);

    }
}
