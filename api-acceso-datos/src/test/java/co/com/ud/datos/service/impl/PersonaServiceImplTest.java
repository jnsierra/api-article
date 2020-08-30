package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.PersonaEntity;
import co.com.ud.datos.repository.IPersonaRepository;
import co.com.ud.datos.service.PersonaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class PersonaServiceImplTest {

    private PersonaService personaService;

    @Mock
    private IPersonaRepository personaRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        personaService = new PersonaServiceImpl(personaRepository);
    }

    @Test
    public void testSave(){
        PersonaEntity personaRequest = PersonaEntity.builder()
                .apellidos("Sierra Chaparro")
                .nombres("Jesus Nicolas")
                .fechaNacimiento(new Date())
                .build();

        PersonaEntity personaResponse = PersonaEntity.builder()
                .id(1L)
                .apellidos("Sierra Chaparro")
                .nombres("Jesus Nicolas")
                .fechaNacimiento(new Date())
                .build();

        Mockito.doReturn(personaResponse).when(personaRepository).save(Mockito.any());

        PersonaEntity response = personaService.save(personaRequest);

        Assert.assertNotNull(response);

    }

}