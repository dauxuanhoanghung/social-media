package com.social.services;

import com.social.dto.request.ReplyRequest;
import com.social.pojo.SubComment;
import java.util.List;
import java.util.Map;

public interface SubCommentService {
    
    List<SubComment> getReplies(Map<String, String> params);

    SubComment save(ReplyRequest replyRequest);

    boolean delete(Integer id);
}
