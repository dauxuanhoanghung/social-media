package com.social.apis;

import com.social.dto.request.CommentRequest;
import com.social.dto.request.ReplyRequest;
import com.social.pojo.Comment;
import com.social.pojo.SubComment;
import com.social.services.CommentService;
import com.social.services.SubCommentService;
import com.social.services.UserService;
import java.security.Principal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping(path = "/api/comments/")
public class CommentAPI {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SubCommentService subCommentService;

    @Autowired
    private UserService userService;

    /**
     * ENDPOINT: [POST][/api/comments/]
     *
     * @commentRequest commentRequest: { content, postId}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CommentRequest commentRequest) {
        this.commentService.save(commentRequest);
    }

    /**
     * ENDPOINT: [POST][/api/comments/addReply]
     *
     * @comment comment: { content, comment (commentId á)}
     */
    @PostMapping(path = "/addReply/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSubComment(@RequestBody ReplyRequest reply) {
        this.subCommentService.save(reply);
    }

    @PostMapping(path = "/actionOnComment/")
    @ResponseStatus(HttpStatus.CREATED)
    public void actionOnComment(Principal principal) {

    }

    @DeleteMapping(path = "/{id}/delete/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int id, Principal principal) {
        this.commentService.delete(id);
    }

    @DeleteMapping(path = "/{id}/deleteSub/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubComment(@PathVariable int id, Principal principal) {
        this.subCommentService.delete(id);
    }
}
