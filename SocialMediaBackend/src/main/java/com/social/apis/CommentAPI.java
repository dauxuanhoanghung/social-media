package com.social.apis;

import com.social.dto.CommentDTO;
import com.social.dto.ReplyDTO;
import com.social.dto.request.CommentRequest;
import com.social.dto.request.ReplyRequest;
import com.social.dto.response.ErrorResponse;
import com.social.dto.response.ModelResponse;
import com.social.dto.response.UserResponse;
import com.social.pojo.Comment;
import com.social.pojo.SubComment;
import com.social.services.CommentService;
import com.social.services.SubCommentService;
import com.social.validator.CommentValidator;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@CrossOrigin
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
    private CommentValidator commentValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(commentValidator);
    }

    @GetMapping
    public ResponseEntity<ModelResponse> getComments(@RequestParam Map<String, String> params) {
        List<Comment> comments = this.commentService.getComments(params);
        List<CommentDTO> dtos = comments.stream().map(comment -> {
            CommentDTO dto = mapper.map(comment, CommentDTO.class);
            dto.setUser(mapper.map(comment.getUser(), UserResponse.class));
            return dto;
        }).collect(Collectors.toList());
        ModelResponse res = new ModelResponse();
        res.setCode(200 + "");
        res.setData(dtos);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getReplies/")
    public ResponseEntity<ModelResponse> getReplies(@RequestParam Map<String, String> params) {
        List<SubComment> replies = this.subCommentService.getReplies(params);
        List<ReplyDTO> dtos = replies.stream().map(reply -> {
            ReplyDTO dto = mapper.map(reply, ReplyDTO.class);
            dto.setUser(mapper.map(reply.getUser(), UserResponse.class));
            return dto;
        }).collect(Collectors.toList());
        ModelResponse res = new ModelResponse();
        res.setCode(200 + "");
        res.setData(dtos);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * ENDPOINT: [POST][/api/comments/]
     *
     * @commentRequest commentRequest: { content, postId}
     */
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CommentRequest commentRequest, BindingResult rs) {
        if (rs.hasErrors()) {
            return new ResponseEntity(rs.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(this.commentService.save(commentRequest), HttpStatus.CREATED);
    }

    /**
     * ENDPOINT: [POST][/api/comments/addReply]
     *
     * @comment comment: { content, comment (commentId á)}
     */
    @PostMapping(path = "/addReply/")
    public ResponseEntity addSubComment(@Valid @RequestBody ReplyRequest reply, BindingResult rs) {
        if (rs.hasErrors()) {
            return new ResponseEntity(rs.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(this.subCommentService.save(reply), HttpStatus.CREATED);
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
