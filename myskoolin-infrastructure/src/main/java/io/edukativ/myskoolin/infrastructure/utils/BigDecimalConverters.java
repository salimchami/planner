package io.edukativ.myskoolin.infrastructure.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class BigDecimalConverters {

    private BigDecimalConverters(){}

    public static class BigDecimalToDbObjectConverter implements Converter<BigDecimal, DBObject> {

        public static final BigDecimalToDbObjectConverter INSTANCE = new BigDecimalToDbObjectConverter();

        private BigDecimalToDbObjectConverter() {}

        @Override
        public DBObject convert(BigDecimal source) {
            if (source == null) {
                return null;
            }
            BigDecimal bigDecimalSource;
            if (source.scale() > 18) {
                bigDecimalSource = source.setScale(18, BigDecimal.ROUND_DOWN);
            } else {
                bigDecimalSource = source;
            }

            DBObject dbo = new BasicDBObject();

            dbo.put("unscaled", bigDecimalSource.unscaledValue().longValue());
            dbo.put("scale", bigDecimalSource.scale());
            return dbo;
        }
    }

    public static class DbObjectToBigDecimalConverter implements Converter<DBObject, BigDecimal> {

        public static final DbObjectToBigDecimalConverter INSTANCE = new DbObjectToBigDecimalConverter();

        private DbObjectToBigDecimalConverter() {}

        @Override
        public BigDecimal convert(DBObject source) {
            if (source == null) {
                return null;
            }

            BigDecimal bigDecimal = null;

            Long unscaled = (Long) source.get("unscaled");
            Integer scale = (Integer) source.get("scale");

            if (unscaled != null && scale != null) {
                bigDecimal = new BigDecimal(new BigInteger(unscaled.toString()), scale);
            }

            return bigDecimal;
        }

    }
}
