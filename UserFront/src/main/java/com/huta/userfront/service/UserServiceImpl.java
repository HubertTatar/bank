package com.huta.userfront.service;

import com.huta.userfront.dao.RoleDao;
import com.huta.userfront.dao.UserDao;
import com.huta.userfront.domain.User;
import com.huta.userfront.domain.security.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private AccountService accountService;

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public User create(User user, Set<UserRole> userRoles) {
        User localUser = userDao.findByUsername(user.getUsername());

        if(localUser == null) {
            String encrptedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encrptedPass);

            for (UserRole ur : userRoles) {
                roleDao.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);

            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userDao.save(user);
        } else {
            LOG.info("User with username {} already exists", user.getUsername());
        }
        return localUser;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }

}
