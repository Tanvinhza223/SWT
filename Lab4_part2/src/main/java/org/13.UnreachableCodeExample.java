package org;

import java.util.logging.Level;
import java.util.logging.Logger;

class UnreachableCodeExample {
    private static final Logger LOG = Logger.getLogger(UnreachableCodeExample.class.getName());

    public static int getNumber() {
        LOG.info("This will now execute");
        return 42;
    }

    public static void main(String[] args) {
        // Kiểm tra mức log trước khi gọi phương thức
        if (LOG.isLoggable(Level.INFO)) {
            LOG.info(String.valueOf(getNumber()));
        }
    }
}

