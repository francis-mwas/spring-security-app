package com.fram.springsecurityclient.event.listener;

import com.fram.springsecurityclient.entity.AppUser;
import com.fram.springsecurityclient.event.RegistrationCompleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.fram.springsecurityclient.service.UserService;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
    //create token verification for the user with link

        AppUser user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);
     //Send email to the user
        String url = event.getApplicationUrl() + "/verifyRegistration?token="
                + token;
        log.info("Click the link to activate your account: {}", url);
    }
}
