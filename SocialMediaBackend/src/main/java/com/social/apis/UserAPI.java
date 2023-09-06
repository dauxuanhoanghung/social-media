package com.social.apis;

import com.social.dto.UserDTO;
import com.social.dto.request.FileUploadRequest;
import com.social.dto.request.UserRegisterDTO;
import com.social.dto.response.ModelResponse;
import com.social.dto.response.UserResponse;
import com.social.pojo.Post;
import com.social.pojo.User;
import com.social.services.PostService;
import com.social.services.UserService;
import com.social.validator.FileValidator;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@Lazy
@RestController
@RequestMapping(path = "/api/users/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private FileValidator fileValidator;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(
                modelMapper.map(this.userService.getUserByAlumniId(id), UserDTO.class),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/current-user/")
    public ResponseEntity<UserResponse> getCurrentUser(Principal principal) {
        return new ResponseEntity<>(
                modelMapper.map(this.userService.getUserByAlumniId(principal.getName()),
                        UserResponse.class),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<UserResponse> getProfileUser(@RequestParam Map<String, String> params) {
        String slug = params.getOrDefault("slug", null);
        String id = params.getOrDefault("id", null);
        if (slug != null) {
            return new ResponseEntity<>(
                    modelMapper.map(this.userService.getUserBySlug(slug),
                            UserResponse.class),
                    HttpStatus.OK
            );
        } else if (slug == null && id != null) {
            return new ResponseEntity<>(
                    modelMapper.map(this.userService.getUserById(Integer.parseInt(id)),
                            UserResponse.class),
                    HttpStatus.OK
            );
        }
        return null;
    }

    @PostMapping(value = "/register/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ModelResponse> create(@ModelAttribute @Valid UserRegisterDTO user, BindingResult rs) {
        if (rs.hasErrors()) {
            return new ResponseEntity<>(new ModelResponse("400", "Bad request data user", rs.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.save(user);
        ModelResponse res = new ModelResponse();
        if (newUser != null) {
            res.setData(newUser);
            res.setMessage("Request Success");
        } else {
            res.setData("Username exists");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // slug and 
    @PostMapping
    public ResponseEntity<ModelResponse> updateUser(@RequestBody Map<String, String> updateUser, Principal principal) {
        User currentUser = this.userService.getUserByAlumniId(principal.getName());
        String slug = updateUser.getOrDefault("slug", null);
        String displayName = updateUser.getOrDefault("displayName", null);

        if (slug != null && !slug.equals(currentUser.getSlug())) {
            currentUser.setSlug(slug);
        }

        if (displayName != null && !displayName.equals(currentUser.getDisplayName())) {
            currentUser.setDisplayName(displayName);
        }

        if (slug != null || displayName != null) {
            User newUser = userService.updateInfo(currentUser);
            ModelResponse res = new ModelResponse();
            res.setData(newUser);
            res.setMessage("Request Success");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        // No changes were made, return an appropriate response
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/upload-avatar/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadAvatar(@ModelAttribute @Valid FileUploadRequest file, BindingResult rs) {
        fileValidator.validate(file, rs);
        if (rs.hasErrors()) {
            return ResponseEntity.badRequest().body(rs.getAllErrors());
        }
        userService.updateAvatar(file.getFiles().get(0), false);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PostMapping(value = "/upload-bg/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadBg(@ModelAttribute @Valid FileUploadRequest file, BindingResult rs) {
        fileValidator.validate(file, rs);
        if (rs.hasErrors()) {
            return new ResponseEntity(rs.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        User user = userService.updateAvatar(file.getFiles().get(0), true);
        Map res = new HashMap();
        res.put("data", user);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/lecture-active/")
    public ResponseEntity<UserResponse> activeLecturePassword(@RequestBody Map<String, String> request) {
        String oldPassword = request.getOrDefault("oldPassword", null);
        if (oldPassword != null) {
            if (request.containsKey("password")) {
                User user = userService.activeLecture(request.get("password"), oldPassword);
                if (user != null)
                    return new ResponseEntity<>(modelMapper.map(user, UserResponse.class),
                            HttpStatus.OK
                    );
            }
        }

        return null;
    }
}
