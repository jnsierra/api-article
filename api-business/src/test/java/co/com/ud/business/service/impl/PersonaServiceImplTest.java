package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.PersonaCliente;
import co.com.ud.utiles.dto.PersonaDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class PersonaServiceImplTest {

    private PersonaServiceImpl personaService;
    @Mock
    private PersonaCliente personaCliente;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.personaService = new PersonaServiceImpl(personaCliente);
    }

    @Test
    public void save(){
        PersonaDto response = PersonaDto.builder()
                .id(1L)
                .nombres("Nicolas")
                .apellidos("Sierra")
                .build();

        PersonaDto persona = PersonaDto.builder()
                .id(1L)
                .nombres("Nicolas")
                .apellidos("Sierra")
                .build();

        Mockito.doReturn(new ResponseEntity<>( response,  HttpStatus.CREATED))
                .when(personaCliente)
                .save(Mockito.any());

        Optional<PersonaDto> respuesta = personaService.save(persona);
        Assert.assertNotNull(respuesta);
        Assert.assertTrue(respuesta.isPresent());
    }
}