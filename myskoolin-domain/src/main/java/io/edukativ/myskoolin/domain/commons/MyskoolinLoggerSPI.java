package io.edukativ.myskoolin.domain.commons;

public interface MyskoolinLoggerSPI {

    void info(Class<?> clazz, String message);

    void info(Class<?> clazz, String message, Throwable throwable);

    void warn(Class<?> clazz, String message);

    void warn(Class<?> clazz, String message, Throwable throwable);

    void error(Class<?> clazz, String message);

    void error(Class<?> clazz, String message, Throwable throwable);

    void debug(Class<?> clazz, String message);

    void debug(Class<?> clazz, String message, Throwable throwable);
}
