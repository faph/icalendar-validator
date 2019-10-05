/*
 * Copyright 2018-2019 Florenz A. P. Hollebrandse. All rights reserved. 
 */
package faph.calendarvalidator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class CalendarValidator {

    @Parameter()
    String filepath;

    public static void main(String... argv) {
        CalendarValidator calendarValidator = new CalendarValidator();
        JCommander.newBuilder()
                .addObject(calendarValidator)
                .build()
                .parse(argv);
        calendarValidator.run();
    }

    public void run() {
        System.out.printf("Running validation on file %s", filepath);
    }
}
