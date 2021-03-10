package co.com.ud.datos.repository;

import co.com.ud.datos.entity.ArticuloEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IArticuloRepositoryTest {

    @Autowired
    private IArticuloRepository articuloRepository;

    @Test
    public void testFindByIdIdeaEMPTY(){
        Optional<ArticuloEntity> response = articuloRepository.findByIdIdea(0L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());
    }
}
