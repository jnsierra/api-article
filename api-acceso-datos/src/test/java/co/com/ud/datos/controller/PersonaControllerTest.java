package co.com.ud.datos.controller;

import co.com.ud.datos.entity.PersonaEntity;
import co.com.ud.datos.service.PersonaService;
import co.com.ud.utiles.dto.PersonaDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest
public class PersonaControllerTest {

    private PersonaController personaController;

    @Mock
    private PersonaService personaService;
    private ModelMapper modelMapper;

    @Before
    public void setUp(){
        modelMapper = new ModelMapper();
        MockitoAnnotations.initMocks(this);
        this.personaController = new PersonaController(modelMapper, personaService);
    }

    @Test
    public void testSave(){
        PersonaDto personaDto = PersonaDto.builder()
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

        Mockito.doReturn(personaResponse).when(personaService).save(Mockito.any());

        ResponseEntity responseEntity = personaController.save(personaDto);

        Assert.assertNotNull(responseEntity);
    }
}