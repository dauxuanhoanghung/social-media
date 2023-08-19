package com.social.apis;

import com.social.dto.PostDTO;
import com.social.dto.request.PostRequest;
import com.social.pojo.Comment;
import com.social.pojo.Post;
import com.social.services.CommentService;
import com.social.services.PostService;
import com.social.services.UserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api/post/")
public class PostAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper mapper;

//    @GetMapping
//    public String getAllPost() {
//        return "authen success";
//    }
    @GetMapping
    public ResponseEntity<List<Post>> getPosts(Integer page) {
        List<Post> posts = postService.getPosts(Collections.singletonMap("page", page.toString()));
        return ResponseEntity.ok(posts);
    }

    @PostMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@ModelAttribute PostRequest post) {
        postService.save(post);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(name = "id") int id) {
        Post post = this.postService.getPostById(String.valueOf(id));
//        List<Comment> comments = this.commentService
        return null;
    }

}
