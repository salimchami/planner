package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumCurrency;
import io.edukativ.myskoolin.infrastructure.app.dto.FeatureDbDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A Pricing.
 */

@Document(collection = PricingDbDTO.MONGO_COLLECTION_NAME)
public class PricingDbDTO implements Serializable {

    public static final String MONGO_COLLECTION_NAME = "pricing";
    public static final String MONGO_FIELD_TITLE_CODE = "title_code";
    private static final String MONGO_FIELD_INT_AMOUNT = "int_amount";
    private static final String MONGO_FIELD_AMOUNT = "amount";
    private static final String MONGO_FIELD_DEC_AMOUNT = "dec_amount";
    private static final String MONGO_FIELD_CURRENCY = "currency";
    private static final String MONGO_FIELD_PERIOD = "period";
    private static final String MONGO_FIELD_FEATURES = "features";
    private static final String MONGO_FIELD_SCHOOL_OFFICE = "school_office";
    private static final String MONGO_FIELD_TEACHERS = "teachers";
    private static final String MONGO_FIELD_ADMINISTRATION = "administration";
    private static final String MONGO_FIELD_INFIRMARY = "infirmary";
    private static final String MONGO_FIELD_TIME_TABLES = "time_tables";
    private static final String MONGO_FIELD_COLOR = "color";
    private static final String MONGO_FIELD_ICON = "icon";
    private static final String MONGO_FIELD_POSITION = "position";

    @Id
    private String id;

    @Field(MONGO_FIELD_TITLE_CODE)
    private String titleCode;

    @Field(MONGO_FIELD_AMOUNT)
    private BigDecimal amount;

    @Field(MONGO_FIELD_INT_AMOUNT)
    private String intAmount;

    @Field(MONGO_FIELD_DEC_AMOUNT)
    private String decAmount;

    @Field(MONGO_FIELD_CURRENCY)
    private EnumCurrency currency;

    @Field(MONGO_FIELD_PERIOD)
    private String period;

    @DBRef(db = "feature", lazy = true)
    @Field(MONGO_FIELD_FEATURES)
    private List<FeatureDbDTO> features;

    @Field(MONGO_FIELD_SCHOOL_OFFICE)
    private Integer schoolOffice;

    @Field(MONGO_FIELD_TEACHERS)
    private Integer teachers;

    @Field(MONGO_FIELD_ADMINISTRATION)
    private Integer administration;

    @Field(MONGO_FIELD_INFIRMARY)
    private Integer infirmary;

    @Field(MONGO_FIELD_TIME_TABLES)
    private Integer timeTables;

    @Field(MONGO_FIELD_COLOR)
    private String color;

    @Field(MONGO_FIELD_ICON)
    private String icon;

    @Field(MONGO_FIELD_POSITION)
    private Integer position;

    public PricingDbDTO() {
    }

    public PricingDbDTO(String id, String titleCode, BigDecimal amount, String intAmount, String decAmount,
                        EnumCurrency currency, String period, List<FeatureDbDTO> features, Integer schoolOffice,
                        Integer teachers, Integer administration, Integer infirmary, Integer timeTables, String color,
                        String icon, Integer position) {
        this.id = id;
        this.titleCode = titleCode;
        this.amount = amount;
        this.intAmount = intAmount;
        this.decAmount = decAmount;
        this.currency = currency;
        this.period = period;
        this.features = features;
        this.schoolOffice = schoolOffice;
        this.teachers = teachers;
        this.administration = administration;
        this.infirmary = infirmary;
        this.timeTables = timeTables;
        this.color = color;
        this.icon = icon;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getIntAmount() {
        return intAmount;
    }

    public void setIntAmount(String intAmount) {
        this.intAmount = intAmount;
    }

    public String getDecAmount() {
        return decAmount;
    }

    public void setDecAmount(String decAmount) {
        this.decAmount = decAmount;
    }

    public EnumCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(EnumCurrency currency) {
        this.currency = currency;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<FeatureDbDTO> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDbDTO> features) {
        this.features = features;
    }

    public Integer getSchoolOffice() {
        return schoolOffice;
    }

    public void setSchoolOffice(Integer schoolOffice) {
        this.schoolOffice = schoolOffice;
    }

    public Integer getTeachers() {
        return teachers;
    }

    public void setTeachers(Integer teachers) {
        this.teachers = teachers;
    }

    public Integer getAdministration() {
        return administration;
    }

    public void setAdministration(Integer administration) {
        this.administration = administration;
    }

    public Integer getInfirmary() {
        return infirmary;
    }

    public void setInfirmary(Integer infirmary) {
        this.infirmary = infirmary;
    }

    public Integer getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(Integer timeTables) {
        this.timeTables = timeTables;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}
