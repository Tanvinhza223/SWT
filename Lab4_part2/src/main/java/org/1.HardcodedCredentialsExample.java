package org;

import java.util.logging.Logger;

class HardcodedCredentialsExample {
    private static final Logger LOGGER =
            Logger.getLogger(HardcodedCredentialsExample.class.getName());
    public static void main(String[] args) {
        String username = "admin";
        String password = "123456"; // hardcoded password
        if(authenticate(username, password)) {
            LOGGER.info("Access granted");
        } else {
            LOGGER.info("Access denied");
        }
    }

    private static boolean authenticate(String user, String pass) {
        // Dummy authentication logic
        return user.equals("admin") && pass.equals("123456");
    }
}
