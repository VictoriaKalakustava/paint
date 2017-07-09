package com.itransition.repository;

import com.itransition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User getUserByEnableKey(String key);
    User getBySocialId(Long id);
}
