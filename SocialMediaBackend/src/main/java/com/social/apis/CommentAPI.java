package com.social.apis;

import com.social.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
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
    private CommentService commentService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create() {
        
    }
    
    @PostMapping(path = "/addReply")
    public void addSubComment() {
        
    }
}
