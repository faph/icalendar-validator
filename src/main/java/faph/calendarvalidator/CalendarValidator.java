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
            logger.fatal(ex.getMessage());
            System.exit(2);
        } catch (ParserException ex) {
            logger.error(ex.getMessage());
            System.exit(1);
        }
    }

    public void run() throws FileNotFoundException, IOException, ParserException {
        FileInputStream inputStream = new FileInputStream(filepath);
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(inputStream);
        calendar.validate(true);
    }
}
