package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ComentarioEntity;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;
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
public class IComentarioRepositoryTest {

    @Autowired
    private IComentarioRepository comentarioRepository;

    @Test
    public void testFindByLlaveAndTipoComentario(){
        ComentarioEntity comentario = ComentarioEntity.builder()
                .comentario("Esto es una prueba")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_IDEA)
                .llave(1l)
                .id_usuario(1l)
                .build();

        ComentarioEntity comentarioRta = comentarioRepository.save(comentario);
        Assert.assertNotNull(comentarioRta);
        Assert.assertNotNull(comentarioRta.getId());

        List<ComentarioEntity> comentarios = comentarioRepository.findByLlaveAndTipoComentario(1L, TYPE_COMMENTS.RECHAZO_IDEA);
        Assert.assertNotNull(comentarios);
    }

}