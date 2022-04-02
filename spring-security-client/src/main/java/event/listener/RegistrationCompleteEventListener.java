package event.listener;

import entity.User;
import event.RegistrationCompleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import service.UserService;

import java.util.UUID;


@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
    //create token verification for the user with link

        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);
     //Send email to the user
        String url = event.getApplicationUrl() + "verifyRegistration?token="
                + token;
        log.info("Click the link to activate your account: {}", url);
    }
}
