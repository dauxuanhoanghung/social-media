package com.social.dto.request;

import com.social.enums.Action;
import com.social.pojo.Comment;
import lombok.Data;

@Data
public class CommentActionRequest {

    Comment comment;
    Action action;
}
