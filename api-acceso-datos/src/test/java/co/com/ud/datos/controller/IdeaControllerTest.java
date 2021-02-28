package co.com.ud.datos.controller;

import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.service.impl.IdeaServiceImpl;
import co.com.ud.utiles.dto.IdeaDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class IdeaControllerTest {

    private IdeaControler ideaControler;
    @Mock
    private IdeaServiceImpl ideaServiceImpl;

    private ModelMapper mapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mapper = new ModelMapper();
        this.ideaControler = new IdeaControler(ideaServiceImpl, mapper);
    }

    @Test
    public void testSave(){
        IdeaEntity response = IdeaEntity.builder()
                .id(1L)
                .titulo("Este es un titulo")
                .contenido("Este es el contenido")
                .estado("CREADO")
                .id_profesor(1L)
                .usuario(UsuarioEntity.builder()
                        .id(1L)
                        .build())
                .build();

        IdeaDto idea = IdeaDto.builder()
                .titulo("Este es un titulo")
                .contenido("Este es el contenido")
                .estado("CREADO")
                .id_profesor(1L)
                .usuarioId(1L)
                .build();
        Mockito.doReturn(Optional.of(response)).when(ideaServiceImpl).save(Mockito.any());

        ResponseEntity<IdeaDto> rta = ideaControler.save(idea);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.CREATED, rta.getStatusCode());
    }
}