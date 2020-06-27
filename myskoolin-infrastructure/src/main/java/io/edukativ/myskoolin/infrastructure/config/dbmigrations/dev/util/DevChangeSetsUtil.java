package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.util;

import io.edukativ.myskoolin.infrastructure.commercial.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ContractDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.InterlocutorDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.PricingDbDTO;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumContactsBy;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumGender;
import io.edukativ.myskoolin.infrastructure.common.enums.EnumSex;
import io.edukativ.myskoolin.infrastructure.common.vo.AddressDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.EmailDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.PhoneDbVO;
import io.edukativ.myskoolin.infrastructure.common.vo.WebsiteDbVO;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsConstants;
import io.edukativ.myskoolin.infrastructure.config.dbmigrations.DbMigrationsFindUtils;
import io.edukativ.myskoolin.infrastructure.common.vo.AcademicYearDbVO;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class DevChangeSetsUtil {

    private DevChangeSetsUtil() {
    }

    public static ClientDbDTO defaultClient(MongoTemplate mongoTemplate) {
        String clientAddress = "9, rue du capitaine ferber";
        AddressDbVO address = new AddressDbVO(clientAddress, "75020", "Paris", "France");
        List<EmailDbVO> emails = Collections.singletonList(new EmailDbVO("Contact", "contact@fqsdgfqsfg.com"));
        PricingDbDTO pricing = DbMigrationsFindUtils.findPricingByTitleCode(mongoTemplate, DbMigrationsConstants.PRICING_TITLE_CODES_BASIC);
        ContractDbDTO contract = new ContractDbDTO(
            ZonedDateTime.of(2016, 8, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2017, 7, 31, 0, 0, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2016, 8, 1, 0, 0, 0, 0, ZoneId.systemDefault()), null,
            BigDecimal.valueOf(1200), BigDecimal.valueOf(1200), BigDecimal.valueOf(6000), BigDecimal.valueOf(6000), "VAT12134657",
            pricing,
            new AcademicYearDbVO(2016, 2017,
                ZonedDateTime.of(2016, 8, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
                ZonedDateTime.of(2017, 8, 31, 0, 0, 0, 0, ZoneId.systemDefault()),
                ZonedDateTime.of(2017, 7, 31, 0, 0, 0, 0, ZoneId.systemDefault())), true);
        return new ClientDbDTO(DevDbMigrationsConstants.CLIENT_01_ID, address, "dfghdfghdfgh", Arrays.asList(EnumContactsBy.values()),
            ClientDbDTO.defaultTimeTableOptions(), emails, 18, "Lycée Victor Hugo", 30, Collections.singletonList(new PhoneDbVO("0000000000000", "Accueil")),
            ZonedDateTime.of(2016, 9, 9, 0, 0, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2017, 7, 7, 0, 0, 0, 0, ZoneId.systemDefault()),
            contract, "xcxcvbxcxcn", "fdhdfghfjfhjfghjfghjfhj",
            Collections.singletonList(new WebsiteDbVO("http://sdfgdsfgsdfg.com", "Site web principal")),
            "www.schoolme.net", false, "global.enums.surfaces.squareMeter",
            "global.enums.distances.meter",
            new InterlocutorDbDTO("Linterlocuteur", "Jack", EnumGender.MR.getCode(), EnumSex.MALE.getCode(),"Proviseur adjoint", "0000000000", "0000000000", "linterlocuteur@victorhugolelycee.org"));
    }
}
