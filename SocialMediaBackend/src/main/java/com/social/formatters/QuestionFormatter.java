package com.social.formatters;

import com.social.pojo.Question;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author DinhChuong
 */
public class QuestionFormatter implements Formatter<Question> {

    @Override
    public String print(Question question, Locale locale) {
        return String.valueOf(question.getId());
    }

    @Override
    public Question parse(String text, Locale locale) throws ParseException {
        return new Question(Integer.valueOf(text));
    }
    
}
