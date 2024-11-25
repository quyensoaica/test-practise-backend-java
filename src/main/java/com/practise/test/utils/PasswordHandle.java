package com.practise.test.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHandle {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        boolean check = BCrypt.checkpw(password, hashedPassword);
        return check;
    }
}
