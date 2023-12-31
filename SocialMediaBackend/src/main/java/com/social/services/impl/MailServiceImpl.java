package com.social.services.impl;

import com.social.services.MailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author LENOVO
 */
@Lazy
@Service("mailService")
@PropertySource("classpath:mail.properties")
public class MailServiceImpl implements MailService {

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freemarkerConfiguration;

    @Override
    public void sendMail(String mailTo, String subject, String title, String mailTemplate) {
        MimeMessagePreparator preparator = getMessagePreparator(mailTo, subject, title, mailTemplate, "");

        try {
            mailSender.send(preparator);
            System.out.println("Message Sent...Hurrey");
        } catch (MailException exe) {
            System.out.println(exe);
        }
    }

    private MimeMessagePreparator getMessagePreparator(String mailTo, String subject, String title, String mailTemplate, String content) {
        MimeMessagePreparator preparator = (MimeMessage mimeMessage) -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(env.getProperty("mail.username"));
            helper.setTo(mailTo);
            helper.setSubject(subject);
            if (!mailTemplate.isBlank()) {
                Map<String, Object> model = new HashMap<>();
//            model.put("user", user);
                model.put("title", title);
                String text = getFreeMarkerTemplateContent(mailTemplate, model);
//            helper.getMimeMessage().setContent(text, "text/html;charset=utf-8");
                helper.setText(text, true);
//            Add a resource as an attachment
//            helper.addAttachment("chong.jpg", new ClassPathResource("chong.jpg"));
            } else {
                helper.setText(content, true);
            }
        };
        return preparator;
    }

    private String getFreeMarkerTemplateContent(String mailTemplate, Map<String, Object> model) {
        StringBuilder content = new StringBuilder();
        try {
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(mailTemplate.strip() + ".html"),
                    model);
            content.append(text);
            return content.toString();
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void sendBulkWithFreeMarkerTemplate(List<String> mailTos, String subject,
            String template, Map<String, Object> model) {
        mailTos.parallelStream().forEach(mailTo -> {
            MimeMessagePreparator preparator = getMessagePreparator(mailTo, subject, "", template, "");
            try {
                mailSender.send(preparator);
            } catch (MailException exe) {
                System.out.println(exe);
            }
        });
    }

    @Override
    public void sendBulk(List<String> mailTos, String subject, String content) {
        mailTos.parallelStream().forEach(mailTo -> {
            MimeMessagePreparator preparator = getMessagePreparator(mailTo, subject, "", "", content);
            try {
                mailSender.send(preparator);
            } catch (MailException exe) {
                System.out.println(exe);
            }
        });
    }
}
