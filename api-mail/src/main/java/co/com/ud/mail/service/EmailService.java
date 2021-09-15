package co.com.ud.mail.service;

public interface EmailService {

    Boolean sendSimpleMessage(String to, String subject, String text);
}
