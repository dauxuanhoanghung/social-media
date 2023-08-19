package com.social.apis;

import com.social.dto.request.CommentRequest;
import com.social.dto.request.ReplyRequest;
import com.social.pojo.Comment;
import com.social.pojo.SubComment;
import com.social.services.CommentService;
import com.social.services.UserService;
import java.security.Principal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(path = "/api/comment/")
public class CommentAPI {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * ENDPOINT: [POST][/api/comment/]
     *
     * @commentRequest commentRequest: { content, postId}
     * @param principal
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CommentRequest commentRequest, Principal principal) {
        Comment comment = mapper.map(commentRequest, Comment.class);
        comment.setCountAction(0);
        comment.setUser(this.userService.getUserByAlumniId(principal.getName()));
        this.commentService.save(comment);
    }

    /**
     * ENDPOINT: [POST][/api/comment/addReply]
     *
     * @comment comment: { content, comment (commentId á)}
     * @param principal
     */
    @PostMapping(path = "/addReply")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSubComment(@RequestBody ReplyRequest reply, Principal principal) {
        SubComment subComment = mapper.map(reply, SubComment.class);
        subComment.setUser(this.userService.getUserByAlumniId(principal.getName()));
        this.commentService.save(subComment);
    }

    @PostMapping(path = "/actionOnComment")
    @ResponseStatus(HttpStatus.CREATED)
    public void actionOnComment(Principal principal) {

    }
}
