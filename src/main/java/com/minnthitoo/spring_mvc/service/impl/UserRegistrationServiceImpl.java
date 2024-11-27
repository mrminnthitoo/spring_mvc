package com.minnthitoo.spring_mvc.service.impl;

import com.minnthitoo.spring_mvc.model.Role;
import com.minnthitoo.spring_mvc.model.User;
import com.minnthitoo.spring_mvc.model.dto.UserDto;
import com.minnthitoo.spring_mvc.repository.UserDao;
import com.minnthitoo.spring_mvc.security.SecurityUtil;
import com.minnthitoo.spring_mvc.service.UserRegistrationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserDto registerUser(UserDto user) throws Exception {
        User existingUser = this.userDao.findByUsername(user.getUsername());
        if (existingUser != null){
            throw new Exception("User Already Exist");
        }else {
            User userToRegister = new User();
            userToRegister.setUsername(user.getUsername());
            userToRegister.setPassword(this.securityUtil.getHash(user.getPassword()));

            Role role1 = new Role();
            role1.setRole("ROLE_USER");

            role1.setUser(userToRegister);
            userToRegister.getRoles().add(role1);

            this.userDao.save(userToRegister);

            user.setPassword(userToRegister.getPassword());
            return user;

        }
    }
}
