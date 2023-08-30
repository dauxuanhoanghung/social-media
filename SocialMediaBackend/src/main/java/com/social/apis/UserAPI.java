package com.social.apis;

import com.social.dto.UserDTO;
import com.social.dto.request.UserRegisterDTO;
import com.social.dto.response.ModelResponse;
import com.social.pojo.User;
import com.social.services.UserService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(
                modelMapper.map(this.userService.getUserByAlumniId(id), UserDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/register/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ModelResponse> create(@ModelAttribute @Valid UserRegisterDTO user, BindingResult rs) {
        if (rs.hasErrors()) {
            return new ResponseEntity<>(new ModelResponse("400", "Bad request data user", rs.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.save(user);
        ModelResponse res = new ModelResponse();
        res.setData(newUser);
        res.setMessage("Request Success");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
