package org;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
class ResourceLeakExample {
    private static final Logger logger =
            Logger.getLogger(ResourceLeakExample.class.getName());

    public static void main(String[] args) {
        // try-with-resources đảm bảo tự động đóng reader kể cả khi xảy ra lỗi
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
        } catch (IOException e) {
            // log thay vì in ra console
            logger.log(Level.SEVERE, "Error reading file", e);
        }
    }
}
