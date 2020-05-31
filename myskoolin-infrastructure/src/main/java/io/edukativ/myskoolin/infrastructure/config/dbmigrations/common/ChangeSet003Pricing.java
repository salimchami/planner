package io.edukativ.myskoolin.infrastructure.config.dbmigrations.common;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import io.edukativ.myskoolin.infrastructure.commercial.dto.PricingDbDTO;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumCurrency;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsConstants;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "003")
public class ChangeSet003Pricing {

    @ChangeSet(order = "01", author = "sch", id = "03-pricing")
    public void addPricing(MongoTemplate mongoTemplate) {
        final List<PricingDbDTO> pricings = Arrays.asList(new PricingDbDTO("pricing-0",
                DbMigrationsConstants.PRICING_TITLE_CODES_BASIC,
                BigDecimal.valueOf(0.6),
                "0",
                "60",
                EnumCurrency.EURO,
                DbMigrationsConstants.PRICING_PERIODS_YEAR,
                Collections.emptyList(),
                2,
                10,
                2,
                1,
                10, "#A3BF41", "mdi mdi-arrow-expand-horizontal", 1),
            new PricingDbDTO("pricing-1",
                DbMigrationsConstants.PRICING_TITLE_CODES_STANDARD,
                BigDecimal.valueOf(1),
                "1",
                "0",
                EnumCurrency.EURO,
                DbMigrationsConstants.PRICING_PERIODS_YEAR,
                Collections.emptyList(),
                6,
                40,
                6,
                2,
                40, "#90C9EF", "mdi mdi-adjust", 2),
            new PricingDbDTO("pricing-2",
                DbMigrationsConstants.PRICING_TITLE_CODES_PREMIUM,
                BigDecimal.valueOf(1.5),
                "1",
                "50",
                EnumCurrency.EURO,
                DbMigrationsConstants.PRICING_PERIODS_YEAR,
                Collections.emptyList(),
                15,
                100,
                15,
                2,
                200, "#A8356E", "mdi mdi-arrow-expand-all", 3),
            new PricingDbDTO("pricing-3",
                DbMigrationsConstants.PRICING_TITLE_CODES_ULTIMATE,
                BigDecimal.valueOf(2),
                "2",
                "00",
                EnumCurrency.EURO,
                DbMigrationsConstants.PRICING_PERIODS_YEAR,
                Collections.emptyList(),
                30,
                250,
                30,
                10,
                1500, "#416DB0", "mdi mdi-all-inclusive", 4));
        mongoTemplate.insert(pricings, PricingDbDTO.MONGO_COLLECTION_NAME);

    }
}
