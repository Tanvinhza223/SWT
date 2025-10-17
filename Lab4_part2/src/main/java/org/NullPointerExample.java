package org;

import java.util.logging.Logger;

public class NullPointerExample {
    private static final Logger logger =
            Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        String text = null; // giả lập dữ liệu đầu vào

        if (text == null) {
            logger.warning("Text is null");
        } else if (text.trim().isEmpty()) {
            logger.warning("Text is empty");
        } else {
            logger.info("Text is not empty");
        }
    }
}
