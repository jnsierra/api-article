package co.com.ud.mail.controller;

import co.com.ud.mail.service.EmailService;
import co.com.ud.utiles.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v.1/mail")
public class MailController {

    private final EmailService emailService;

    @Autowired
    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/send/")
    public ResponseEntity<Boolean> enviarMail(@RequestBody EmailDto emailDto){
        emailService.sendSimpleMessage(emailDto.getTo(), emailDto.getSubject(), emailDto.getText());
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
