package com.social.dto.request;

import com.social.enums.Action;
import com.social.pojo.Post;
import java.io.Serializable;
import lombok.Data;

@Data
public class PostActionRequest implements Serializable {

    Post post;
    Action action;
}
