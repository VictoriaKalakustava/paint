package com.itransition.controller;

import com.itransition.converter.UserConverter;
import com.itransition.dto.UserDTO;
import com.itransition.entity.User;
import com.itransition.security.details.CustomUserDetailsProvider;
import com.itransition.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/info")
public class RestFullDataController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    public ResponseEntity<UserDTO> index() {
        Long id = CustomUserDetailsProvider.getUserDetails().getId();
        User user = userService.findOne(id);
        return new ResponseEntity<>(UserConverter.convert(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloGET() {
        String name = CustomUserDetailsProvider.getUserDetails().getUsername();
        return "Hello, " + name + "!";
    }
}
