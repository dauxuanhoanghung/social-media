package com.social.apis;

import com.social.dto.UserDTO;
import com.social.pojo.User;
import com.social.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
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
    
    
}
