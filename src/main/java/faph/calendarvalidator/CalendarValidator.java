/*
 * Copyright 2018-2019 Florenz A. P. Hollebrandse. All rights reserved. 
 */
package faph.calendarvalidator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

public class CalendarValidator {

    @Parameter()
    String filepath;

    public static void main(String... argv) {
        CalendarValidator calendarValidator = new CalendarValidator();
        JCommander.newBuilder()
                .addObject(calendarValidator)
                .build()
                .parse(argv);
        try {
            calendarValidator.run();
        } catch (IOException ex) {
            Logger.getLogger(CalendarValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserException ex) {
            Logger.getLogger(CalendarValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() throws FileNotFoundException, IOException, ParserException {
        System.out.printf("Running validation on file %s", filepath);
        FileInputStream inputStream = new FileInputStream(filepath);
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(inputStream);
        calendar.validate(true);
    }
}
