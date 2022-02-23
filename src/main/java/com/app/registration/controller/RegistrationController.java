package com.app.registration.controller;

import com.app.registration.model.User;
import com.app.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public User register(@RequestBody User user) throws Exception {
        String tempEmailId = user.getEmailId();
        if (tempEmailId != null && !"".equals(tempEmailId)) {
            User userObj = service.fetchUserByEmailId(tempEmailId);
            if (userObj != null) {
                throw new Exception("user with " + tempEmailId + " is already exist.");
            }
        }
        User userObj = null;
        userObj = service.saveUser(user);
        return userObj;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public User login(@RequestBody User user) throws Exception {
        String tempEmailId = user.getEmailId();
        String tempPass = user.getPassword();
        User userObj;
        if (tempEmailId != null && tempPass != null) {
            userObj = service.fetchUserByEmailAndPassword(tempEmailId, tempPass);
            return userObj;
        }
        return null;
    }
}
