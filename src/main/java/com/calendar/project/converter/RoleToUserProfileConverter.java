package com.calendar.project.converter;

/**
 * Created by icebotari on 10/19/2017.
 */

import com.calendar.project.model.Role;
import com.calendar.project.service.RoleService;
import com.calendar.project.model.Role;
import com.calendar.project.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, Role> {

    static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

    @Autowired
    RoleService roleService;

    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(Object)
     */
    public Role convert(Object element) {
        Long id = Long.parseLong((String)element);
        Role profile= roleService.findById(id);
        logger.info("Profile : {}",profile);
        return profile;
    }

}