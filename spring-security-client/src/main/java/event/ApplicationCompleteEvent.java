package event;

import entity.User;
import org.springframework.context.ApplicationEvent;

public class ApplicationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;
    public ApplicationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
