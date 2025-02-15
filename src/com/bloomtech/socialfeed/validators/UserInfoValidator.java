package com.bloomtech.socialfeed.validators;

import com.bloomtech.socialfeed.exceptions.UserValidationException;
import com.bloomtech.socialfeed.models.Role;
import com.bloomtech.socialfeed.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfoValidator implements Validator {

    private boolean isValidUsername(String username) {
        //TODO: validate username begins with an uppercase letter, is at least 4 characters long, and only contains
        //letters, numbers, and underscores
        String userNameRegex = "^[A-Z][A-Za-z0-9_]{3}[A-Za-z0-9_]*$";
        Pattern p = Pattern.compile(userNameRegex);

        Matcher m = p.matcher(username);
        return m.matches();

    }

    private boolean isValidPassword(String password) {
        //TODO: validate password contains at least 8 characters, an uppercase, and a lowercase letter.
        //valid symbols include: !@#$%^&*
        String passwordRegex = "(.{8}+)";
        String upperRegex = "([A-Z]+)";
        String lowerRegex = "([a-z]+)";
        String digitRegex = "(\\d+)";
        String symbolRegex = "([!@#$%^&*a-zA-Z\\d]+)";
        Pattern pass = Pattern.compile(passwordRegex);
        Pattern upper = Pattern.compile(upperRegex);
        Pattern lower = Pattern.compile(lowerRegex);
        Pattern digit = Pattern.compile(digitRegex);
        Pattern symbol = Pattern.compile(symbolRegex);

        Matcher mPass = pass.matcher(password);
        Matcher mUpper = upper.matcher(password);
        Matcher mLower = lower.matcher(password);
        Matcher mDigit = digit.matcher(password);
        Matcher mSymbol = symbol.matcher(password);

        if (mPass.find() && mUpper.find() && mLower.find() && mDigit.find()) {
            return mSymbol.find();
        }
        return false;
    }

    @Override
    public void validate(Object userData) {

        User user = (User) userData;

        if (!isValidUsername(user.getUsername())) {
            throw new UserValidationException("Invalid Username: Username must be at least 4 characters long, " +
                    "must begin with an uppercase letter, and may not contain special characters or spaces!");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new UserValidationException("Invalid Password: Password must be at least 8 characters long, " +
                    "contain at least one uppercase letter, one lowercase letter, and one special character!");
        }
        if (user.getRole() == null) { user.setRole(Role.USER); }
    }
}
