package app.utils;

import app.entities.userRoles.User;
import app.exceptions.DatabaseException;
import app.persistence.UserMapper;

public class EmailUtils {
    public static boolean checkDuplicateEmail(String email) throws DatabaseException {
        boolean emailDuplicate = false;
        for (User user : UserMapper.getAllUsers()) {
            if (user.getEmail().equals(email)) {
                emailDuplicate = true;
                return emailDuplicate;
            }
        }
        return emailDuplicate;
    }
}
