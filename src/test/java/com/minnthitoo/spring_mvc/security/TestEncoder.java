package com.minnthitoo.spring_mvc.security;

import com.minnthitoo.spring_mvc.model.Role;
import com.minnthitoo.spring_mvc.model.User;
import com.minnthitoo.spring_mvc.repository.UserDao;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(value = false)
public class TestEncoder {

    @Autowired
    SecurityUtil securityUtil;

    @Autowired
    UserDao userDao;

    @Transactional
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("user");
        user.setPassword(this.securityUtil.getHash("password"));

        Role role1 = new Role();
        role1.setRole("USER");
        role1.setUser(user);

        user.getRoles().add(role1);

        this.userDao.save(user);
    }

    @Transactional
    @Test
    public void testAddAdmin(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword(this.securityUtil.getHash("admin"));

        Role role1 = new Role();
        role1.setRole("ADMIN");
        role1.setUser(user);

        user.getRoles().add(role1);

        this.userDao.save(user);
    }


}
