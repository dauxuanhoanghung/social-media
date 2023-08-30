package com.social.apis;

import com.social.dto.request.CommentActionRequest;
import com.social.dto.request.PostActionRequest;
import com.social.dto.request.ReplyActionRequest;
import com.social.validator.ActionValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Lazy
@RestController
@RequestMapping("/api/actions/")
public class ActionAPI {

    @Autowired
    private ActionValidator actionValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(actionValidator);
    }

    /**
     * post, action
     *
     * @return
     */
    @PostMapping("/post/")
    public ResponseEntity actionOnPost(@Valid @RequestBody PostActionRequest postActionRequest,
            BindingResult rs) {
        if (rs.hasErrors()) {
            
        }
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    /**
     * comment, action
     *
     * @return
     */
    @PostMapping("/comment/")
    public ResponseEntity actionOnComment(@Valid @RequestBody CommentActionRequest commentActionRequest,
            BindingResult rs) {
        if (rs.hasErrors()) {
            
        }
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    /**
     * subcomment, action
     *
     * @return
     */
    @PostMapping("/reply/")
    public ResponseEntity actionOnReply(@Valid @RequestBody ReplyActionRequest replyActionRequest,
            BindingResult rs) {
        if (rs.hasErrors()) {
            
        }
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}
