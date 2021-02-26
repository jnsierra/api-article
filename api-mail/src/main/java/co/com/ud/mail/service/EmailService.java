package co.com.ud.mail.service;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
