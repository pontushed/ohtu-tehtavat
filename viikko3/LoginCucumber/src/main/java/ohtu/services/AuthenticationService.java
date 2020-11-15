package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        boolean usernameInvalid = username.length() < 3;
        for (int i = 0; i < username.length(); i++) {
            if (!Character.isLetter(username.charAt(i))) {
                return true;
            }

        }
        boolean passwordInvalid = password.length() < 8;
        boolean passwordDoesNotContainDigits = true;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                passwordDoesNotContainDigits = false;
            }
        }
        return (usernameInvalid || passwordInvalid || passwordDoesNotContainDigits);
    }
}
