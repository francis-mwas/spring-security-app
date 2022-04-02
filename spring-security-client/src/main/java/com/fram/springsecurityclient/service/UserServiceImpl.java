package com.fram.springsecurityclient.service;

import com.fram.springsecurityclient.entity.AppUser;
import com.fram.springsecurityclient.entity.VerificationToken;
import com.fram.springsecurityclient.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fram.springsecurityclient.repository.UserRepository;
import com.fram.springsecurityclient.repository.VerificationTokenRepository;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;


    @Override
    public AppUser registerUser(UserModel userModel) {
        AppUser user = new AppUser();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, AppUser user) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken
                = verificationTokenRepository.findByToken(token);
        if(verificationToken == null){
            return "Invalid Token";
        }
        AppUser user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if((verificationToken.getExprirationTime().getTime() - calendar.getTime().getTime())
        <=0){
            verificationTokenRepository.delete(verificationToken);
            return "Token has expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "User email validated successfully!";
    }
}
