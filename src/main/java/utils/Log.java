package utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log {

    private Log() {
        // Empty constructor
    }

    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void info(String strMessage) {
        logger.info(strMessage);
    }

    public static void warn(String strMessage) {
        logger.warn(strMessage);
    }

    public static void error(String strMessage) {
        logger.error(strMessage);
    }

    public static void  fatal(String strMessage) {
        logger.fatal(strMessage);
    }

    public static void debug(String strMessage) {
        logger.debug(strMessage);
    }
}
