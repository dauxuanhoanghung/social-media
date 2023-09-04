package com.social.apis;

import com.social.dto.request.PostRequest;
import com.social.pojo.Post;
import com.social.services.CommentService;
import com.social.services.PostService;
import com.social.validator.PostRequestValidator;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api/posts/")
public class PostAPI {

    @Autowired
    private PostRequestValidator postRequestValidator;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper mapper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(postRequestValidator);
    }

    @GetMapping
    public ResponseEntity getPosts(Map<String, String> params) {
        List<Post> posts = postService.getPosts(params);
        Map<String, Object> res = new HashMap<>();
        res.put("posts", posts);
//        res.put("action", );
        return ResponseEntity.ok(res);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPost(@Valid @ModelAttribute PostRequest post, BindingResult rs) {
        if (rs.hasErrors()) {
            return new ResponseEntity(rs.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Post newPost = postService.save(post);
        return ResponseEntity.ok(newPost);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(name = "id") int id) {
        Post post = this.postService.getPostById(id);
//        List<Comment> comments = this.commentService
        return null;
    }

    @PatchMapping(value = "/{id}/toggle-lock/")
    public ResponseEntity lockCommentOnPost(@PathVariable(name = "id") int id) {

        if (this.postService.toggleLockCOmment(id) != null) {
            return new ResponseEntity(null, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }
}
