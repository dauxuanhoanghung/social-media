package com.social.apis;

import com.social.dto.request.CommentActionRequest;
import com.social.dto.request.PostActionRequest;
import com.social.dto.request.ReplyActionRequest;
import com.social.services.ActionService;
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

    @Autowired
    private ActionService actionService;

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
    public ResponseEntity actionOnPost(@RequestBody @Valid PostActionRequest postActionRequest,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return new ResponseEntity(rs.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(actionService.saveOrUpdateOrDelete(postActionRequest),
                HttpStatus.CREATED);
    }

    /**
     * comment, action
     *
     * @return
     */
    @PostMapping("/comment/")
    public ResponseEntity actionOnComment(@RequestBody @Valid CommentActionRequest commentActionRequest,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return new ResponseEntity(rs.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(actionService.saveOrUpdateOrDelete(commentActionRequest),
                HttpStatus.CREATED);
    }

    /**
     * subcomment, action
     *
     * @return
     */
    @PostMapping("/reply/")
    public ResponseEntity actionOnReply(@RequestBody @Valid ReplyActionRequest replyActionRequest,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return new ResponseEntity(rs.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(actionService.saveOrUpdateOrDelete(replyActionRequest),
                HttpStatus.CREATED);
    }
}
