package co.com.ud.utiles.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordUtilesTest {

    @Test
    public void testGenerateRandomPassword(){
        String password = PasswordUtiles.builder().build().generateRandomPassword(10);

        Assert.assertNotNull(password);
        Assert.assertNotEquals("", password);
    }

}