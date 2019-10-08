/* 
 * The MIT License
 *
 * Copyright 2019 Florenz A. P. Hollebrandse.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class CalendarValidator {

    @Parameter(description = "iCalendar file to validate")
    String filepath;

    @Parameter(names = {"--verbose", "-v"}, description = "Verbose, debug mode")
    private boolean verbose = false;

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
        if (verbose) {
            Logger.getRootLogger().setLevel(Level.DEBUG);
        } else {
            Logger.getRootLogger().setLevel(Level.INFO);
        }
        FileInputStream inputStream = new FileInputStream(filepath);
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(inputStream);
        calendar.validate(true);
    }
}
