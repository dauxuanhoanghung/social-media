package com.social.services;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface MailService {

    void sendMail(String mailTo, String subject, String title, String mailTemplate);

    void sendBulk(List<String> mailTo, String subject, String content);
}
