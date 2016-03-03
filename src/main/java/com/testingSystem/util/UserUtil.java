package com.testingSystem.util;

import com.testingSystem.entity.User;
import com.testingSystem.exception.AuthException;
import com.testingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserUtil {

    private final static Pattern EMAIL_PATTERN = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
    private final static Pattern NAME_PATTERN = Pattern.compile("^[A-Z][a-z]{1,20}$");

    @Autowired private UserService userService;

    public boolean validateUserInfo(String firstName, String lastName, String email) throws AuthException {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new AuthException("Wrong email");
        }
        if (!NAME_PATTERN.matcher(firstName).matches() || !NAME_PATTERN.matcher(lastName).matches()) {
            throw new AuthException("Wrong name");
        }
        return true;
    }
}
