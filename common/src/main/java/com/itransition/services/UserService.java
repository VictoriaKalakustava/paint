package com.itransition.services;

import com.itransition.entity.User;
import com.itransition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public User save(User user){
        return userRepository.saveAndFlush(user);
    }

    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
    }

    public Optional<User> findUserByKey(String key) {
        return Optional.ofNullable(userRepository.getUserByEnableKey(key));
    }

    public String findBySocialId(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.getBySocialId(id));
        if(user.isPresent()) {
            return user.get().getLogin();
        } else {
            return null;
        }
    }
}
