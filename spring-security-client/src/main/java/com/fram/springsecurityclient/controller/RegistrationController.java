package com.fram.springsecurityclient.controller;

import com.fram.springsecurityclient.entity.AppUser;
import com.fram.springsecurityclient.event.RegistrationCompleteEvent;
import lombok.extern.slf4j.Slf4j;
import com.fram.springsecurityclient.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import com.fram.springsecurityclient.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping(path = "/api/v1/users")
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        AppUser user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user,
                applicationUrl(request)));

        return "Success";
    }
    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User veried successfully";
        }
        return "Unable to verify user";
    }
    @GetMapping("/hello")
    public String hello(){
        return  "Hello good coders!!";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
