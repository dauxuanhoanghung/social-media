package com.social.dto.request;

import com.social.enums.Action;
import com.social.pojo.SubComment;
import java.io.Serializable;
import lombok.Data;

@Data
public class ReplyActionRequest implements Serializable {

    SubComment subComment;
    Action action;
}
