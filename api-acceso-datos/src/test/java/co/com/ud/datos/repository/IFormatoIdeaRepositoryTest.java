package co.com.ud.datos.repository;

import co.com.ud.datos.entity.FormatoIdeaEntity;
import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IFormatoIdeaRepositoryTest {

    @Autowired
    private IFormatoIdeaRepository formatoIdeaRepository;
    @Autowired
    private IIdeaRepository ideaRepository;

    @Test
    public void testGetFormatosByIdea(){

        IdeaEntity ideaEntity = IdeaEntity.builder()
                .contenido("Esta es una prueba de contendio")
                .titulo("Titulo de prueba")
                .id_profesor(3L)
                .estado("CREADA")
                .usuario(UsuarioEntity.builder()
                        .id(1L)
                        .build())
                .build();
        IdeaEntity responseIdea = ideaRepository.save(ideaEntity);
        Assert.assertNotNull(responseIdea);
        Assert.assertNotNull(responseIdea.getId());
        FormatoIdeaEntity entity  = FormatoIdeaEntity.builder()
                .idea(IdeaEntity.builder().id(responseIdea.getId()).build())
                .formato("FORMATO_IDEA")
                .nombre("idea.pdf")
                .ubicacion("/repository/documentos/usuario/1_idea.pdf")
                .build();

        FormatoIdeaEntity formatoResponse = formatoIdeaRepository.save(entity);

        Assert.assertNotNull(formatoResponse);
        Assert.assertNotNull(formatoResponse.getId());

        List<FormatoIdeaEntity> response = formatoIdeaRepository.getFormatosByIdea(responseIdea.getId());
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());

    }

}
