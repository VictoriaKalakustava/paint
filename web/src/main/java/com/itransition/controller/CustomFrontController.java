package com.itransition.controller;

import com.itransition.security.details.CustomUserDetails;
import com.itransition.security.details.CustomUserDetailsProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class CustomFrontController {

    @RequestMapping(value = "/login")
    public ModelAndView onLoginAttempt() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/paint")
    public ModelAndView onPaintAttempt() {
        ModelAndView model = new ModelAndView();
        CustomUserDetails details = CustomUserDetailsProvider.getUserDetails();
        if(details.isActivated()) {
            model.setViewName("paint");
        } else {
            model.setViewName("not-activated");
        }

        return model;
    }

    @RequestMapping(value = "/used-link")
    public ModelAndView onLinkUsing() {
        ModelAndView model = new ModelAndView();
        model.setViewName("used-link");
        return model;
    }
}
