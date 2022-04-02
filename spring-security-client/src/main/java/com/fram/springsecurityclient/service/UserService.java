package com.fram.springsecurityclient.service;


import com.fram.springsecurityclient.entity.AppUser;
import com.fram.springsecurityclient.model.UserModel;

public interface UserService {
    AppUser registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, AppUser user);

    String validateVerificationToken(String token);
}
