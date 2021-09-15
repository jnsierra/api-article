package co.com.ud.mail.controller;

import co.com.ud.mail.service.EmailService;
import co.com.ud.utiles.dto.EmailDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class MailControllerTest {

    @Mock
    private EmailService emailService;

    private MailController mailController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mailController = new MailController(emailService);
    }

    @Test
    public void enviarMailSUCCESS(){
        Mockito.doReturn(Boolean.TRUE).when(emailService).sendSimpleMessage(Mockito.any(), Mockito.any(), Mockito.any());
        ResponseEntity<Boolean> response = mailController.enviarMail(EmailDto.builder()
                .to("jnsierrac@gmail.com")
                .subject("contra")
                .text("aaaa")
                .build());
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getBody());


    }
}