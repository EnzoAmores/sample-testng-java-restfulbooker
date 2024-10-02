package utilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/* Loggers
 * No need to modify this class unless there is a need. Avoid in adding non-logs methods/snippets of code. */
public class Log {
    private static final Logger log = LogManager.getLogger(Log.class);

    public static void debug(String message) {
        log.debug(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void fatal(String message) {
        log.fatal(message);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void warn(String message) {
        log.warn(message);
    }
}