package com.calendar.project.service;

import com.calendar.project.config.BASE64DecodedMultipartFile;
import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.Tag;
import com.calendar.project.model.User;
import com.calendar.project.model.dto.EventResource;
import com.calendar.project.model.dto.UserDTO;
import com.calendar.project.model.dto.UserResource;
import org.apache.commons.codec.binary.Base64;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Converter {

    public static Event convert(EventResource eventResource){
        Event ev = new Event();
        ev.setId(eventResource.getId());
        ev.setTitle(eventResource.getTitle());
        ev.setStart(eventResource.getStart());
        ev.setEnd(eventResource.getEnd());
        ev.setEventType(eventResource.getEventType());
        ev.setLocation(eventResource.getLocation());
        ev.setDescription(eventResource.getDescription());
        ev.setParticipants(eventResource.getParticipants());
        ev.setTags(eventResource.getTags());
        ev.setAllDay(eventResource.isAllDay());
        return ev;
    }

    public static User convert(UserResource userResource){
        User user = new User();
        user.setUsername(userResource.getUsername());
        user.setFirstname(userResource.getFirstname());
        user.setLastname(userResource.getLastname());
        user.setPassword(userResource.getPassword());
        user.setPosition(userResource.getPosition());
        try{
        user.setImage(userResource.getMultipartFile().getBytes());
        }catch(IOException e){e.printStackTrace();}
        user.setEmail(userResource.getEmail());
        user.setConfirmPassword(userResource.getConfirmPassword());
        return user;
    }

    public static UserResource convert(User user){
        UserResource userResource = new UserResource();
        userResource.setId(user.getId());
        userResource.setUsername(user.getUsername());
        userResource.setFirstname(user.getFirstname());
        userResource.setLastname(user.getLastname());
        userResource.setPassword(user.getPassword());
        userResource.setPosition(user.getPosition());
        BASE64DecodedMultipartFile base64DecodedMultipartFile = new BASE64DecodedMultipartFile(user.getImage());
        userResource.setMultipartFile(base64DecodedMultipartFile);
        userResource.setEmail(user.getEmail());
        userResource.setConfirmPassword(user.getConfirmPassword());
        return userResource;
    }


    public static User convert(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setPassword(userDTO.getPassword());
        user.setPosition(userDTO.getPosition());
        user.setImage(Base64.decodeBase64(userDTO.getImage()));
        user.setEmail(userDTO.getEmail());
        user.setConfirmPassword(userDTO.getConfirmPassword());
        return user;
    }

    public static UserDTO convertToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setPassword(user.getPassword());
        userDTO.setPosition(user.getPosition());
        userDTO.setImage(Base64.encodeBase64String(user.getImage()));
        userDTO.setEmail(user.getEmail());
        userDTO.setConfirmPassword(user.getConfirmPassword());
        return userDTO;
    }
}
