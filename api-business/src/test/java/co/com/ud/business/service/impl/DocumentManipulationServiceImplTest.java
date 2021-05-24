package co.com.ud.business.service.impl;


import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.service.IdeaService;
import co.com.ud.business.service.UsuarioService;
import co.com.ud.utiles.dto.ArticuloDto;
import co.com.ud.utiles.dto.IdeaDto;
import co.com.ud.utiles.dto.PersonaDto;
import co.com.ud.utiles.dto.UsuarioDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest
public class DocumentManipulationServiceImplTest {

    private DocumentManipulationServiceImpl documentManipulationService;
    @Mock
    private ArticuloCliente articuloCliente;
    @Mock
    private IdeaService ideaService;
    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.documentManipulationService = new DocumentManipulationServiceImpl("src/test/resources/document/"
                ,"/repository/documentos/articulos/"
                , ideaService
                , usuarioService
                , articuloCliente);
    }

    @Test
    public void testGenerateFormatArtSUCCESS() throws IOException {
        ArticuloDto entity = ArticuloDto.builder()
                .id(1L)
                .titulo("Titulo del articulo")
                .introduccion("Por los lados del partido de ‘la U’, incluso, se conoció una proposición o propuesta para archivar la iniciativa firmada por el senador José Ritter López, presidente de la Comisión Séptima, donde se tramita la norma.")
                .resumen("ESte es el resumen")
                .resumen_ingles("This is the abstract")
                .conclusion("El ministro de Salud, Fernando Ruíz, manifestó hace algunos días que se han recogido sugerencias, anotaciones e intervenciones surgidas en audiencias de consulta en cada una de las instancias del sistema sanitario.\n" +
                        "\n" +
                        "El funcionario también ha dicho que además de componentes que tienen que ver con la gestión integral del riesgo en salud, la territorialización y la forma como se prestan los servicios, se recogen los aprendizajes de la pandemia con el objeto de preparar al país bajo el concepto de una salubridad completa.\n" +
                        "\n" +
                        "El proyecto, además, tiene los tiempos contados, ya que si no se le da al menos un debate antes del próximo 20 de junio será archivada en el Congreso.")
                .build();
        Mockito.doReturn(new ResponseEntity<>(entity, HttpStatus.OK)).when(articuloCliente).getById(Mockito.any(), Mockito.any());

        IdeaDto idea = IdeaDto.builder()
                .id(1L)
                .nombreAlumno("Jesús Nicolas Sierra Chaparro")
                .usuarioId(1L)
                .idProfesorAutoriza(1L)
                .build();
        Mockito.doReturn(Optional.of(idea)).when(ideaService).findById(Mockito.any(), Mockito.any());

        UsuarioDto usuario = UsuarioDto.builder()
                .id(1L)
                .codigo("2017278021")
                .persona(PersonaDto.builder()
                        .nombres("JAIRO ")
                        .apellidos("HERNANDEZ GUITIERREZ")
                        .build())
                .build();

        Mockito.doReturn(Optional.of(usuario)).when(usuarioService).getUserById(Mockito.any());

        Optional<String> response = documentManipulationService.generateFormatArt("13216584baf",1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}