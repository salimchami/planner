package io.edukativ.myskoolin.infrastructure.app.providers;

import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyskoolinLogger implements MyskoolinLoggerSPI {

    @Override
    public void info(String message) {
        final Logger logger = LoggerFactory.getLogger(callerClass());
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        final Logger logger = LoggerFactory.getLogger(callerClass());
        logger.warn(message);
    }

    @Override
    public void error(String message) {
        final Logger logger = LoggerFactory.getLogger(callerClass());
        logger.error(message);
    }

    @Override
    public void debug(String message) {
        final Logger logger = LoggerFactory.getLogger(callerClass());
        logger.debug(message);
    }

    @Override
    public void info(String message, Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(callerClass());
        logger.info(message, throwable);
    }

    @Override
    public void warn(String message, Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(callerClass());
        logger.warn(message, throwable);
    }

    @Override
    public void error(String message, Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(callerClass());
        logger.error(message, throwable);
    }

    @Override
    public void debug(String message, Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(callerClass());
        logger.debug(message, throwable);
    }

    private Class<?> callerClass() {
        return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass();
    }
}
