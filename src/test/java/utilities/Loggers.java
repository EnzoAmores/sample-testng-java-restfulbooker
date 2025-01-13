package utilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/* Loggers
 * No need to modify this class unless there is a need. Avoid in adding non-logs methods/snippets of code. */
public class Loggers {
    private static final Logger logger = LogManager.getLogger(Loggers.class);

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }
}