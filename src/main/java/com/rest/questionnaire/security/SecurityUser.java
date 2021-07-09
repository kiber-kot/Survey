package com.rest.questionnaire.security;

import com.rest.questionnaire.entity.UserEntity;
import com.rest.questionnaire.utils.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {

    private final String userName;
    private final String password;

    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(String userName, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(UserEntity userEntity) {
        return new User(
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.getStatus().equals(Status.ACTIVE),
                userEntity.getStatus().equals(Status.ACTIVE),
                userEntity.getStatus().equals(Status.ACTIVE),
                userEntity.getStatus().equals(Status.ACTIVE),
                userEntity.getRole().getAuthority()
        );
    }
}
