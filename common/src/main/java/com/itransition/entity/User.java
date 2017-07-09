package com.itransition.entity;

import com.itransition.entity.enums.SocialType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String email;

    private String login;
    private String password;

    private Boolean isActivated;
    private String enableKey;

    private SocialType socialType;
    private Long socialId;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false, insertable = true, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = true, insertable = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname", nullable = false, insertable = true, updatable = true)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "email", nullable = true, insertable = true, updatable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "login", nullable = false, insertable = true, updatable = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password", nullable = false, insertable = true, updatable = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "is_activated", nullable = false, insertable = true, updatable = true)
    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    @Column(name = "enable_key", nullable = true, insertable = true, updatable = true)
    public String getEnableKey() {
        return enableKey;
    }

    public void setEnableKey(String enableKey) {
        this.enableKey = enableKey;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = true, insertable = true, updatable = true)
    public SocialType getSocialType() {
        return socialType;
    }

    public void setSocialType(SocialType socialType) {
        this.socialType = socialType;
    }

    @Column(name = "social_id", nullable = true, insertable = true, updatable = true)
    public Long getSocialId() {
        return socialId;
    }

    public void setSocialId(Long id) {
        this.socialId = id;
    }


}
