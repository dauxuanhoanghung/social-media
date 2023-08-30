package com.social.dto.request;

import com.social.enums.Action;
import com.social.pojo.Post;
import lombok.Data;

@Data
public class PostActionRequest {

    Post post;
    Action action;
}
