package com.testingSystem.util;

import com.testingSystem.entity.Test;
import com.testingSystem.entity.User;
import com.testingSystem.exception.AuthException;
import com.testingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.regex.Pattern;

@Service
public class UserUtil {

    private final static Pattern EMAIL_PATTERN = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
    private final static Pattern NAME_PATTERN = Pattern.compile("^[A-Z][a-z]{1,20}$");

    @Autowired private UserService userService;

    private boolean validateUserInfo(String firstName, String lastName, String email) throws AuthException {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new AuthException("Wrong email");
        }
        if (!NAME_PATTERN.matcher(firstName).matches() || !NAME_PATTERN.matcher(lastName).matches()) {
            throw new AuthException("Wrong name");
        }
        User user = userService.getByEmail(email);
        if (user != null && !user.getFirstName().equals(firstName) && !user.getLastName().equals(lastName)) {
            throw new AuthException("User with such email already exist");
        }
        return true;
    }

    public Long createUser(String firstName, String lastName, String email) throws AuthException {
        validateUserInfo(firstName, lastName, email);
        User user = new User(firstName, lastName, email);
        user.getTests().add(new Test("TEST", Calendar.getInstance().getTime()));
        Long userId = userService.save(user);
        return userId;
    }
}
