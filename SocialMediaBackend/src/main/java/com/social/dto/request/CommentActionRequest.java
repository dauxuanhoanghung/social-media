package com.social.dto.request;

import com.social.enums.Action;
import com.social.pojo.Comment;
import java.io.Serializable;
import lombok.Data;

@Data
public class CommentActionRequest implements Serializable {

    Comment comment;
    Action action;
}
