package com.social.formatters;

import com.social.pojo.Comment;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LENOVO
 */
public class CommentFormatter implements Formatter<Comment> {

    @Override
    public String print(Comment comment, Locale locale) {
        return String.valueOf(comment.getId());
    }

    @Override
    public Comment parse(String text, Locale locale) throws ParseException {
        return new Comment(Integer.valueOf(text));
    }

}
