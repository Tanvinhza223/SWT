package org;

import java.util.logging.Logger;

class InterfaceFieldModificationExample {
    private static final Logger logger = Logger.getLogger(InterfaceFieldModificationExample.class.getName());
    public static void main(String[] args) {
        logger.info("Max users allowed: " + ConstantsValues.MAX_USERS);
    }
}

class ConstantsValues {
    public static final int MAX_USERS = 100;
}
