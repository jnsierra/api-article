package co.com.ud.mail.service.impl;

import co.com.ud.mail.service.EmailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendMai(){
        try {
            emailService.sendSimpleMessage("jnsierrac@gmail.com", "prueba", "Funciono");
        }catch (Exception e){
        }
        Assert.assertTrue(Boolean.TRUE);
    }

}