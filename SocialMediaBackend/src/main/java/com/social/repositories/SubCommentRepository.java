package com.social.repositories;

import com.social.pojo.SubComment;
import com.social.pojo.SubCommentAction;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface SubCommentRepository {

    SubComment getById(Integer id);
    
    List<SubComment> getRepliesByCommentId(int commentId);

    long countReplyActionById(int replyId);

    SubComment save(SubComment subComment);

    SubCommentAction save(SubCommentAction subCommentAction);

    SubComment update(SubComment subComment);

    boolean delete(SubComment subComment);
    
    boolean deleteById(Integer subCommentId);

}
