package co.com.ud.utiles.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootTest
public class UtilesBase64Test {

    private String path;

    @Before
    public void setUp(){
        this.path = "/opt/";
    }
    @Test
    public void testSaveFileSUCCESS()throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("base64Text.txt").getFile());
        Scanner sc = new Scanner(file);
        String valor = sc.next();
        Boolean response = UtilesBase64.builder().build().saveFile(valor, this.path, "prueba.pdf");
        Assert.assertEquals(Boolean.TRUE, response);
    }

    @Test
    public void testSaveFileNOT_EXISTS_PATH()throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("base64Text.txt").getFile());
        Scanner sc = new Scanner(file);
        String valor = sc.next();
        //Altero el path original
        Boolean response = UtilesBase64.builder().build().saveFile(valor, this.path + "s", "");
        Assert.assertEquals(Boolean.FALSE, response);
    }

    @Test
    public void testBorraTipoBase64WITHOUTIDENTIFICADOR(){
        String base64 = UtilesBase64.builder().build().borraTipoBase64("asdf");
        Assert.assertNotNull(base64);
        Assert.assertEquals(base64, "asdf");
    }

    @Test
    public void testBorraTipoBase64PDF(){
        String base64 = UtilesBase64.builder().build().borraTipoBase64("data:application/pdf;base64,asdf");
        Assert.assertNotNull(base64);
        Assert.assertEquals(base64, "asdf");
    }

    @Test
    public void testBorraTipoBase64WORD(){
        String base64 = UtilesBase64.builder().build().borraTipoBase64("data:application/octet-stream;base64,asdf");
        Assert.assertNotNull(base64);
        Assert.assertEquals(base64, "asdf");
    }

    @Test
    public void testBorraTipoBase64WORDXML(){
        String base64 = UtilesBase64.builder().build().borraTipoBase64("data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,UEsDBBQABgA");
        Assert.assertNotNull(base64);
        Assert.assertEquals(base64, "UEsDBBQABgA");
    }
}