package org;

import java.util.logging.Logger;

class Printer {
    private static final Logger logger =
            Logger.getLogger(Printer.class.getName());


    void print(String message) {
        logger.info(message);
    }
}

class Report {
    private final Printer printer; // tightly coupled

    public Report(Printer printer) {
        this.printer = printer;
    }

    void generate() {
        printer.print("Generating report...");
    }
}

