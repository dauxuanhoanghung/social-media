package com.social.services;

/**
 *
 * @author LENOVO
 */
public interface MailService {
    void sendMail(String mailTo, String subject,String title, String mailTemplate);
}

