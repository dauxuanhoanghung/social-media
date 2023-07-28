package com.social.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author LENOVO
 */
@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
@JsonIgnoreProperties(value = {"stackTrace", "cause"})
public class NotFoundException extends RuntimeException {

    private final Date datetime;
    private final String username;
    private final String requestUrl;
    
    public NotFoundException() {
        this.datetime = null;
        this.username = null;
        this.requestUrl = null;
    }

    public NotFoundException(String message, String username, String requestUrl) {
        super(message);
        this.datetime = Date.from(Instant.now());
        this.username = username;
        this.requestUrl = requestUrl;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public Date getDatetime() {
        return this.datetime;
    }
    
    @Override
    public String getLocalizedMessage() {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle("messages/messages", locale);
        return messages.getString("notfound.message");
    }
}
