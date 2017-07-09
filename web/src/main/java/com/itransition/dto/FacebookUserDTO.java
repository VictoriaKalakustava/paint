package com.itransition.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FacebookUserDTO {
    private Long id;
    private String login;
    private String first_name;
    private String last_name;
}
