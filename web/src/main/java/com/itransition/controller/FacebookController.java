package com.itransition.controller;

import com.itransition.converter.UserConverter;
import com.itransition.dto.FacebookUserDTO;
import com.itransition.entity.User;
import com.itransition.entity.enums.SocialType;
import com.itransition.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/fb-controller")
public class FacebookController {

    private Facebook facebook;
    private ConnectionRepository connectionRepository;
    @Autowired
    private UserService userService;

    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    @RequestMapping(method= RequestMethod.GET)
    public String isSignUp(Model model) {
        if (!facebook.isAuthorized()) {
            return "redirect:/connect/facebook";
        }
        model.addAttribute("user", facebook.fetchObject("me", FacebookUserDTO.class,
                new String[]{"id", "first_name", "last_name", "email"}));

        return "hello";
    }

    @RequestMapping(value = "/is_exist")
    public ResponseEntity<FacebookUserDTO> isExist() {
        FacebookUserDTO user = facebook.fetchObject("me", FacebookUserDTO.class,
                new String[]{"id", "first_name", "last_name"});
        user.setLogin(userService.findBySocialId(user.getId()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/sign-up")
    public ResponseEntity<FacebookUserDTO> sigUp(@RequestBody FacebookUserDTO userDTO) {
        User user = UserConverter.convert(userDTO);
        if(userService.findByLogin(userDTO.getLogin()).isPresent()) {
            return new ResponseEntity<>(userDTO, HttpStatus.CONFLICT);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getId().toString()));
        user.setSocialType(SocialType.FACEBOOK);
        userService.save(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
