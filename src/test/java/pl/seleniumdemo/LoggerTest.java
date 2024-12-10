package pl.seleniumdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerTest {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();
        logger.fatal("fatal"); //1
        logger.error("error"); //2
        logger.warn("warn"); //3
        logger.info("info"); //4
        logger.debug("debug"); //5
        logger.trace("trace"); //6
    }
}
