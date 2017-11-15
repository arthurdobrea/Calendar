package com.calendar.project.validator;

import com.calendar.project.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.regex.Pattern;

@Component
public class EditFormValidator  implements Validator {

    private static final Logger LOGGER = Logger.getLogger(EditFormValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {

        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        String username = user.getUsername();
        String firstname = user.getFirstname();
        String lastname = user.getLastname();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "Required");
        int firstnameLength = firstname.length();
        if (firstnameLength < 2 || firstnameLength > 32) {
            errors.rejectValue("firstname", "Size.userForm.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "Required");
        int lastnameLength = lastname.length();
        if (lastnameLength < 2 || lastnameLength > 32) {
            errors.rejectValue("lastname", "Size.userForm.lastname");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if(!Pattern.matches("(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", user.getEmail())){
            errors.rejectValue("email", "Mail.userForm.email");
        }
    }
}
