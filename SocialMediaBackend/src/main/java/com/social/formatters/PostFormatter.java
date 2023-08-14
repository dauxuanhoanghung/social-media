package com.social.formatters;

import com.social.pojo.Post;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LENOVO
 */
public class PostFormatter implements Formatter<Post> {

    @Override
    public String print(Post post, Locale locale) {
        return String.valueOf(post.getId());
    }

    @Override
    public Post parse(String text, Locale locale) throws ParseException {
        return new Post(Integer.valueOf(text));
    }

}
