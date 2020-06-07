package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.common.enums.EnumCurrency;

import java.math.BigDecimal;
import java.util.List;

public class PricingDTO {

    private String id;
    private String titleCode;
    private BigDecimal amount;
    private String intAmount;
    private String decAmount;
    private EnumCurrency currency;
    private String period;
    private List<FeatureDTO> features;
    private Integer schoolOffice;
    private Integer teachers;
    private Integer administration;
    private Integer infirmary;
    private Integer timeTables;
    private String color;
    private String icon;
    private Integer position;

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

    public List<FeatureDTO> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDTO> features) {
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
