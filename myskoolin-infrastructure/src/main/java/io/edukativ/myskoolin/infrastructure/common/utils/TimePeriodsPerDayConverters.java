package io.edukativ.myskoolin.infrastructure.common.utils;

import io.edukativ.myskoolin.infrastructure.timetabling.TimePeriodDbVO;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slim on 22/04/17.
 */
public final class TimePeriodsPerDayConverters {

    private TimePeriodsPerDayConverters() {
    }

    public static class TimePeriodsByDayCodeToDayNumberConverter implements Converter<Map<String, List<TimePeriodDbVO>>, Map<String, List<TimePeriodDbVO>>> {

        public static final TimePeriodsByDayCodeToDayNumberConverter INSTANCE = new TimePeriodsByDayCodeToDayNumberConverter();

        private TimePeriodsByDayCodeToDayNumberConverter() {
        }

        @Override
        public Map<String, List<TimePeriodDbVO>> convert(Map<String, List<TimePeriodDbVO>> source) {
            Map<String, List<TimePeriodDbVO>> sourceMap = new HashMap<>();
            source.forEach((dayCode, timePeriods) -> sourceMap.put(dayCode.replaceAll("\\.", "_"), timePeriods));
            return sourceMap;
        }
    }

    public static class TimePeriodsByDayNumberToDayCodeConverter implements Converter<Map<String, List<TimePeriodDbVO>>, Map<String, List<TimePeriodDbVO>>> {

        public static final TimePeriodsByDayNumberToDayCodeConverter INSTANCE = new TimePeriodsByDayNumberToDayCodeConverter();

        private TimePeriodsByDayNumberToDayCodeConverter() {
        }

        @Override
        public Map<String, List<TimePeriodDbVO>> convert(Map<String, List<TimePeriodDbVO>> source) {
            Map<String, List<TimePeriodDbVO>> sourceMap = new HashMap<>();
            source.forEach((dayCode, timePeriods) -> sourceMap.put(dayCode.replaceAll("_", "\\."), timePeriods));
            return sourceMap;
        }
    }
}
