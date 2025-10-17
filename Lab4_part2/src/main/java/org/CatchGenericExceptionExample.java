package org;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {
    private static final Logger logger =
            Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        String s = null; // ví dụ dữ liệu từ bên ngoài

        // Tránh ném NPE bằng cách kiểm tra null
        if (s == null) {
            logger.warning("Input string 's' is null");
            return;
        }

        // Không cần bắt Exception tổng quát, chỉ bắt khi thực sự cần
        try {
            logger.info("Length = " + s.length());
        } catch (RuntimeException ex) {
            // Trường hợp phát sinh lỗi khác ngoài dự kiến
            logger.log(Level.SEVERE, "Unexpected error while computing length", ex);
        }
    }
}

