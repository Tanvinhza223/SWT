package org;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
class PathTraversalExample {
    private static final Logger logger =
            Logger.getLogger(PathTraversalExample.class.getName());

    public static void main(String[] args) {
        String userInput = "../secret.txt";
        Path baseDir = Paths.get(System.getenv().getOrDefault("SAFE_BASE_DIR", "data"));

        try {
            Path target = baseDir.resolve(userInput).normalize();
            if (!target.startsWith(baseDir)) {
                logger.log(Level.WARNING, "Rejected path traversal attempt: {0}", userInput);
                return;
            }

            if (Files.exists(target) && Files.isRegularFile(target)) {
                try (BufferedReader br = Files.newBufferedReader(target)) {
                    logger.log(Level.INFO, "Reading file: {0}", target.toAbsolutePath());
                    // ví dụ: đọc nội dung file
                    String line = br.readLine();
                    if (line != null) {
                        logger.log(Level.FINE, "First line: {0}", line);
                    }
                }
            } else {
                logger.log(Level.WARNING, "File not found: {0}", target.toAbsolutePath());
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "I/O error while accessing file", ex);
        }
    }
}
