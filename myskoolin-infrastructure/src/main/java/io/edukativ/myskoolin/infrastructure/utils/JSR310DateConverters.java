package io.edukativ.myskoolin.infrastructure.utils;

import org.springframework.core.convert.converter.Converter;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class JSR310DateConverters {

    private JSR310DateConverters() {
    }

    public static class LocalDateToDateConverter implements Converter<LocalDate, Date> {

        public static final LocalDateToDateConverter INSTANCE = new LocalDateToDateConverter();

        private LocalDateToDateConverter() {
        }

        @Override
        public Date convert(LocalDate source) {
            return source == null ? null : Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    public static class DateToLocalDateConverter implements Converter<Date, LocalDate> {
        public static final DateToLocalDateConverter INSTANCE = new DateToLocalDateConverter();

        private DateToLocalDateConverter() {
        }

        @Override
        public LocalDate convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault()).toLocalDate();
        }
    }

    public static class InstantToDateConverter implements Converter<Instant, Date> {
        public static final InstantToDateConverter INSTANCE = new InstantToDateConverter();

        private InstantToDateConverter() {
        }

        @Override
        public Date convert(Instant source) {
            return source == null ? null : Date.from(source);
        }
    }

    public static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
        public static final ZonedDateTimeToDateConverter INSTANCE = new ZonedDateTimeToDateConverter();

        private ZonedDateTimeToDateConverter() {
        }

        @Override
        public Date convert(ZonedDateTime source) {
            return source == null ? null : Date.from(source.toInstant());
        }
    }

    public static class ZonedDatesTimeToDatesConverter implements Converter<List<ZonedDateTime>, List<Date>> {
        public static final ZonedDatesTimeToDatesConverter INSTANCE = new ZonedDatesTimeToDatesConverter();

        private ZonedDatesTimeToDatesConverter() {
        }

        @Override
        public List<Date> convert(List<ZonedDateTime> source) {

            return source == null ? null : source.stream().map(s -> Date.from(s.toInstant())).collect(Collectors.toList());
        }
    }

    public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
        public static final DateToZonedDateTimeConverter INSTANCE = new DateToZonedDateTimeConverter();

        private DateToZonedDateTimeConverter() {
        }

        @Override
        public ZonedDateTime convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }

    public static class DatesToZonedDatesTimeConverter implements Converter<List<Date>, List<ZonedDateTime>> {
        public static final DatesToZonedDatesTimeConverter INSTANCE = new DatesToZonedDatesTimeConverter();

        private DatesToZonedDatesTimeConverter() {
        }

        @Override
        public List<ZonedDateTime> convert(List<Date> source) {
            return source == null ? null : source.stream().map(s -> ZonedDateTime.ofInstant(s.toInstant(), ZoneId.systemDefault())).collect(Collectors.toList());
        }
    }

    public static class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {
        public static final LocalDateTimeToDateConverter INSTANCE = new LocalDateTimeToDateConverter();

        private LocalDateTimeToDateConverter() {
        }

        @Override
        public Date convert(LocalDateTime source) {
            return source == null ? null : Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    public static class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {
        public static final DateToLocalDateTimeConverter INSTANCE = new DateToLocalDateTimeConverter();

        private DateToLocalDateTimeConverter() {
        }

        @Override
        public LocalDateTime convert(Date source) {
            return source == null ? null : LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }
}
