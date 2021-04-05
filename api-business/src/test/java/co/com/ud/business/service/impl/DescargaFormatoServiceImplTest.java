package co.com.ud.business.service.impl;

import co.com.ud.utiles.dto.DocumentDownloadDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class DescargaFormatoServiceImplTest {

    private DescargaFormatoServiceImpl descargaFormatoService;

    @Before
    public void setUp(){
        this.descargaFormatoService = new DescargaFormatoServiceImpl("C:\\repository\\documentos\\formatos\\formato_001.docx");
    }

    @Test
    public void testDescargarFormatoIdea(){

        Optional<DocumentDownloadDto> down = descargaFormatoService.descargarFormatoIdea();
        Assert.assertNotNull(down);
        Assert.assertTrue(down.isPresent());
    }

}