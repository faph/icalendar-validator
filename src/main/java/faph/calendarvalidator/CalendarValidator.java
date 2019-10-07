/*
 * Copyright 2018-2019 Florenz A. P. Hollebrandse. All rights reserved. 
 */
package faph.calendarvalidator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class CalendarValidator {

    @Parameter()
    String filepath;

    static Logger logger = Logger.getLogger(CalendarValidator.class);

    public static void main(String... argv) {
        BasicConfigurator.configure();

        CalendarValidator calendarValidator = new CalendarValidator();
        JCommander.newBuilder()
                .addObject(calendarValidator)
                .build()
                .parse(argv);
        try {
            calendarValidator.run();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        } catch (ParserException ex) {
            System.out.printf(ex.getMessage());
        }
    }

    public void run() throws FileNotFoundException, IOException, ParserException {
        System.out.printf("Running validation on file %s", filepath);
        FileInputStream inputStream = new FileInputStream(filepath);
        CalendarBuilder builder = new CalendarBuilder();
        TimeZoneRegistry registry = builder.getRegistry();
        Calendar calendar = builder.build(inputStream);
        calendar.validate(true);
    }
}
