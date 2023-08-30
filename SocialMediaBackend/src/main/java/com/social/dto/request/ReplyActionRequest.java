package com.social.dto.request;

import com.social.enums.Action;
import com.social.pojo.SubComment;
import lombok.Data;

@Data
public class ReplyActionRequest {

    SubComment subComment;
    Action action;
}
