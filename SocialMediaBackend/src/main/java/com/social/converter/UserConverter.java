package com.social.converter;

import com.social.dto.UserDTO;
import com.social.pojo.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author LENOVO
 */
@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO PojoToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        return dto;
    }

    public User DTOToPojo(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
