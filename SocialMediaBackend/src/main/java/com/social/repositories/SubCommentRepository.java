package com.social.repositories;

import com.social.pojo.SubComment;
import com.social.pojo.SubCommentAction;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface SubCommentRepository {

    SubComment getById(Integer id);
    
    List<SubComment> getRepliesByCommentId(int commentId);
    
    List<SubComment> getReplies(Map<String, String> params);

    long countReplyActionById(int replyId);

    SubComment save(SubComment subComment);

    SubCommentAction save(SubCommentAction subCommentAction);

    SubComment update(SubComment subComment);

    boolean delete(SubComment subComment);
    
    boolean deleteById(Integer subCommentId);

}
