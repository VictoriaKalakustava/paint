package com.itransition.converter;

import com.itransition.dto.FacebookUserDTO;
import com.itransition.dto.UserDTO;
import com.itransition.entity.User;
import com.itransition.entity.enums.SocialType;

public class UserConverter {
    public static User convert(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());

        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());

        user.setSocialId(dto.getSocialId());
        user.setSocialType(dto.getSocialType());

        user.setIsActivated(dto.getIsActivated());
        return user;
    }

    public static UserDTO convert(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());

        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());

        dto.setSocialId(user.getSocialId());
        dto.setSocialType(user.getSocialType());

        dto.setIsActivated(user.getIsActivated());
        return dto;
    }

    public static User convert(FacebookUserDTO dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setSocialId(dto.getId());
        user.setName(dto.getFirst_name());
        user.setSurname(dto.getLast_name());
        user.setSocialType(SocialType.FACEBOOK);
        user.setIsActivated(true);
        return user;
    }
}
