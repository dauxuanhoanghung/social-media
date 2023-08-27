package com.social.services.impl;

import com.social.pojo.Comment;
import com.social.pojo.SubComment;
import com.social.repositories.CommentRepository;
import com.social.repositories.SubCommentRepository;
import com.social.services.CommentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private SubCommentRepository subCommentRepository;

    @Override
    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public SubComment save(SubComment subComment) {
        return this.subCommentRepository.save(subComment);
    }

    @Override
    public List<Comment> getByPostId(Map<String, String> params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Integer id) {
        Comment comment = this.commentRepository.getCommentById(id);
        if (comment == null) {
            return false;
        } else {
            // Delete inside repo (subAction, subComment, commentAction)
            return this.commentRepository.delete(comment);
        }
    }

    @Override
    public boolean deleteSub(Integer id) {
        SubComment subComment = this.subCommentRepository.getById(id);
        if (subComment == null) {
            return false;
        } else {
            // Must delete all actions of reply here before delete reply (inside repo)
            return this.subCommentRepository.delete(subComment);
        }
    }

    @Override
    public List<SubComment> getRepliesByCommentId(Integer id) {
        return null;
    }
}
