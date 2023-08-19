package com.social.formatters;

import com.social.pojo.Action;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LENOVO
 */
public class ActionFormatter implements Formatter<Action> {

    @Override
    public String print(Action object, Locale locale) {
        return String.valueOf(object);
    }

    @Override
    public Action parse(String text, Locale locale) throws ParseException {
        return new Action(Integer.valueOf(text));
    }
    
}
