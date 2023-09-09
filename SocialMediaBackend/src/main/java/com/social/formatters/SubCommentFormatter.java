package com.social.formatters;

import com.social.pojo.SubComment;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class SubCommentFormatter implements Formatter<SubComment> {

    @Override
    public String print(SubComment comment, Locale locale) {
        return String.valueOf(comment.getId());
    }

    @Override
    public SubComment parse(String text, Locale locale) throws ParseException {
        return new SubComment(Integer.valueOf(text));
    }

}

