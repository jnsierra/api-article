package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.utiles.dto.CountStateDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IArticuloRepositoryTest {

    @Autowired
    private IArticuloRepository articuloRepository;

    @Autowired
    private IIdeaRepository ideaRepository;

    @Test
    public void testFindByIdIdeaEMPTY(){

        Optional<ArticuloEntity> response = articuloRepository.findByIdIdea(0L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void testGetArticulosByUser(){
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

        ArticuloEntity entityArt = ArticuloEntity.builder()
                .resumen("Este es un resumen")
                .resumen_ingles("this is in english")
                .titulo("Este es el titulo")
                .contenido("Este es el contenido")
                .idea(responseIdea)
                .estado("ARTICULO_EN_PROCESO")
                .build();
        ArticuloEntity responseArt = articuloRepository.save(entityArt);
        Assert.assertNotNull(responseArt);
        Assert.assertNotNull(responseArt.getId());

        List response = articuloRepository.getArticulosByUser(1L);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());

    }
    @Test
    public void testConteoByEstado(){
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

        ArticuloEntity entityArt = ArticuloEntity.builder()
                .resumen("Este es un resumen")
                .resumen_ingles("this is in english")
                .titulo("Este es el titulo")
                .contenido("Este es el contenido")
                .idea(responseIdea)
                .estado("ARTICULO_EN_PROCESO")
                .build();
        ArticuloEntity responseArt = articuloRepository.save(entityArt);
        Assert.assertNotNull(responseArt);
        Assert.assertNotNull(responseArt.getId());

        List<CountStateDto> response = articuloRepository.conteoByEstado();
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());

    }
}
