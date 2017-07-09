package com.itransition.security.details;

import com.itransition.converter.UserConverter;
import com.itransition.entity.User;
import com.itransition.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User " + login + " not found"));
        return new CustomUserDetails(UserConverter.convert(user));
    }
}
