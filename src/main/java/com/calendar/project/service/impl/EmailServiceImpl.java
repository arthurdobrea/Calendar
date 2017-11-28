package com.calendar.project.service.impl;

import com.calendar.project.mail.EmailSender;
import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.service.EmailService;
import com.calendar.project.service.EventService;
import com.calendar.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    public void mailSubscribersNewEvent(Event event) {
        for (User user : userService.getUsersListBySubscriptionByEventType(event.getEventType().toString())){
            StringBuilder mailText = new StringBuilder();
            mailText.append("Hello " + user.getUsername() + "! \n");
            mailText.append("You are subscribed to next types of events:"+user.getSubscriptionByEventType().toLowerCase()+" \n");
            mailText.append(makeBodyMailFromEvent(event));
            EmailSender.sendTo(user.getEmail(),
                    "Subscribe from EventEndava " + user.getSubscriptionByEventType().toLowerCase(),
                    mailText.toString());
        }
    }
    @Override
    public void mailParticipantsNewEvent(Event event) {
        for (User user : event.getParticipants()){
            StringBuilder mailText = new StringBuilder();
            mailText.append("Hello " + user.getUsername() + "! \n");
            mailText.append(event.getAuthor().getUsername() + " invited you to participate in:\n");
            mailText.append(makeBodyMailFromEvent(event));
            EmailSender.sendTo(user.getEmail(),
                    "Notification from EventEndava: participating in Event " + event.getTitle(),
                    mailText.toString());
        }
    }

    @Override
    public void mailSubscribersAllFutureEvents() {
        for (User user : userService.getAllUsers())
            mailToUserFutureEvents(user);
    }

    @Override
    public void mailToUserFutureEvents(User user) {
        StringBuilder mailText = new StringBuilder();
        mailText.append("Hello " + user.getUsername() + "! \n");
        mailText.append("You are subscribed to next types of events:"+user.getSubscriptionByEventType().toLowerCase()+" \n");
        for (EventType eventType : (user.getSubscriptionByEventTypeAsEnums())) {
            for (Event event : eventService.getFutureEventsByType(eventType))
                if (eventType.equals(event.getEventType()))
                    mailText.append(makeBodyMailFromEvent(event));
        }
        EmailSender.sendTo(user.getEmail(), "Subscribe from EventEndava " + user.getSubscriptionByEventType().toLowerCase(), mailText.toString());
    }

    @Override
    public String makeBodyMailFromEvent(Event event) {
        StringBuilder mailText = new StringBuilder();

        mailText.append(" \n");
        mailText.append("Event title : ");
        mailText.append(event.getTitle());
        mailText.append(" \n");

        mailText.append("Event type : ");
        //mailText.append(event.getEventType().view());
        mailText.append(event.getEventType());
        mailText.append(" \n");

        mailText.append("Description: ");
        mailText.append(event.getDescription());
        mailText.append(" \n");

        mailText.append("Location: ");
        mailText.append(event.getLocation());
        mailText.append(" \n");

        mailText.append("Event starts at time: ");
        mailText.append(event.getStart());
        mailText.append(" \n");

        mailText.append("Was created by: ");
        mailText.append(event.getAuthor().getUsername());
        mailText.append(" at : ");
        mailText.append(event.getEventCreated());
        mailText.append(" \n");

        return mailText.toString();
    }


}
