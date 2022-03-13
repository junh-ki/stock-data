package com.finance.commons.logger;

import com.finance.commons.enums.LoggerEnum;
import org.slf4j.LoggerFactory;

public class Logger {

    private static final org.slf4j.Logger logger;
    private static final String LOGGER_ENUM_PREFIX = "LoggerEnum: ";
    private static final String LOG_INDENTATION = " ---- ";

    static {
        logger = LoggerFactory.getLogger(Logger.class);
    }

    public static void info(LoggerEnum loggerEnum, String message, Object... args) {
        if (loggerEnum != null) {
            message = LOGGER_ENUM_PREFIX + loggerEnum.name() + LOG_INDENTATION + message;
        }
        if (args != null && args.length > 0) {
            logger.info(message, args);
        } else {
            logger.info(message);
        }
    }

    public static void debug(LoggerEnum loggerEnum, String message, Object... args) {
        if (loggerEnum != null) {
            message = LOGGER_ENUM_PREFIX + loggerEnum.name() + LOG_INDENTATION + message;
        }
        if (args != null && args.length > 0) {
            logger.debug(message, args);
        } else {
            logger.debug(message);
        }
    }

    public static void warn(LoggerEnum loggerEnum, String message, Object... args) {
        if (loggerEnum != null) {
            message = LOGGER_ENUM_PREFIX + loggerEnum.name() + LOG_INDENTATION + message;
        }
        if (args != null && args.length > 0) {
            logger.warn(message, args);
        } else {
            logger.warn(message);
        }
    }

    public static void error(LoggerEnum loggerEnum, String message, Exception e, Object... args) {
        if (loggerEnum != null) {
            message = LOGGER_ENUM_PREFIX + loggerEnum.name() + LOG_INDENTATION + message;
        }
        if (args != null && args.length > 0) {
            logger.error(message, e, args);
        } else {
            logger.error(message, e);
        }
    }

}
