package com.social.formatters;

import com.social.pojo.User;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LENOVO
 */
public class UserFormatter implements Formatter<User> {
    
    @Override
    public String print(User object, Locale locale) {
        return String.valueOf(object.getId());
    }
    
    @Override
    public User parse(String text, Locale locale) throws ParseException {
        return new User(Integer.valueOf(text));
    }
    
}
