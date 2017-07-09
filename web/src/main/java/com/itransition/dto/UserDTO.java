package com.itransition.dto;

import com.itransition.entity.enums.SocialType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;

    private String login;
    private String password;

    private SocialType socialType;
    private Long socialId;

    private Boolean isActivated;
}