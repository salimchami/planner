package io.edukativ.myskoolin.infrastructure.app.providers;

import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyskoolinLogger implements MyskoolinLoggerSPI {

    @Override
    public void info(Class<?> clazz, String message) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(message);
    }

    @Override
    public void warn(Class<?> clazz, String message) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        logger.warn(message);
    }

    @Override
    public void error(Class<?> clazz, String message) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(message);
    }

    @Override
    public void debug(Class<?> clazz, String message) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        logger.debug(message);
    }

    @Override
    public void info(Class<?> clazz, String message, Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(message, throwable);
    }

    @Override
    public void warn(Class<?> clazz, String message, Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        logger.warn(message, throwable);
    }

    @Override
    public void error(Class<?> clazz, String message, Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(message, throwable);
    }

    @Override
    public void debug(Class<?> clazz, String message, Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        logger.debug(message, throwable);
    }
}
