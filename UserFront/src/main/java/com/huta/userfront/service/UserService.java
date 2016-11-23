package com.huta.userfront.service;

import com.huta.userfront.domain.User;
import com.huta.userfront.domain.security.UserRole;

import java.util.Set;

public interface UserService {
    boolean checkUserExists(String username, String email);
    boolean checkEmailExists(String email);
    boolean checkUsernameExists(String username);
    User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);

    User create(User user, Set<UserRole> userRoles);
}
