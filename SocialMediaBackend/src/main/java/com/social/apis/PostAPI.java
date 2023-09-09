package com.social.apis;

import com.social.dto.request.PostRequest;
import com.social.dto.request.ResultSurveyRequest;
import com.social.dto.response.ModelResponse;
import com.social.pojo.Post;
import com.social.services.PostService;
import com.social.services.SurveyService;
import com.social.validator.PostRequestValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/posts/")
public class PostAPI {

    @Autowired
    private PostRequestValidator postRequestValidator;

    @Autowired
    private PostService postService;

    @Autowired
    private SurveyService surveyService;

    @InitBinder("post")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(postRequestValidator);
    }

    @GetMapping
    public ResponseEntity getPosts(@RequestParam Map<String, String> params) {
        List<Post> posts = postService.getPosts(params);
        Map<String, Object> res = new HashMap<>();
        res.put("posts", posts);
        return ResponseEntity.ok(res);

    }

    @GetMapping("/slug/{slug}/")
    public ResponseEntity getPostsWithSlug(@PathVariable(name = "slug", required = true) String slug,
            @RequestParam Map<String, String> params) {
        params.put("slug", slug);
        List<Post> posts = postService.getPosts(params);
        Map<String, Object> res = new HashMap<>();
        res.put("posts", posts);
        return ResponseEntity.ok(res);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPost(@Valid @ModelAttribute("post") PostRequest post, BindingResult rs) {
        if (rs.hasErrors()) {
            return new ResponseEntity(rs.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Post newPost = postService.save(post);
        return ResponseEntity.ok(newPost);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/answer/")
    public ResponseEntity createAnswer(@RequestBody ResultSurveyRequest result) {
        surveyService.save(result);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(value = "/{id}/toggle-lock/")
    public ResponseEntity lockCommentOnPost(@PathVariable(name = "id") int id) {
        if (this.postService.toggleLockCOmment(id) != null) {
            return new ResponseEntity(null, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    @GetMapping("/block-comment/{postId}")
    public ResponseEntity<ModelResponse> blockComment(@PathVariable int postId) {
        ModelResponse res = new ModelResponse();
        Post post = postService.toggleBlockComment(postId);
        res.setData(post);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}/delete/")
    public ResponseEntity deleteAPost(@PathVariable(name = "id") int id) {
        postService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
