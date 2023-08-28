package com.social.services;

import com.social.dto.request.ReplyRequest;
import com.social.pojo.SubComment;

public interface SubCommentService {

    SubComment save(ReplyRequest replyRequest);

    boolean delete(Integer id);
}
