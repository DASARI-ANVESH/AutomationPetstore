package com.petstore.automation.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LoggerUtil {
private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
public static void info(String message) {
logger.info(message);
// Also print to console for Cucumber reports
System.out.println("[INFO] " + message);
}
public static void error(String message) {
logger.error(message);
System.err.println("[ERROR] " + message);
}
public static void debug(String message) {
logger.debug(message);
System.out.println("[DEBUG] " + message);
}
public static void warn(String message) {
logger.warn(message);
System.out.println("[WARN] " + message);
}
}
