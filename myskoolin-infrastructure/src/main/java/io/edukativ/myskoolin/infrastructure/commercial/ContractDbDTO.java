package io.edukativ.myskoolin.infrastructure.commercial;

import io.edukativ.myskoolin.infrastructure.common.vo.AcademicYearDbVO;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * A Contract.
 */
public class ContractDbDTO implements Serializable {

    public static final String MONGO_FIELD_CURRENT_PERIOD_START = "current_period_start";
    public static final String MONGO_FIELD_CURRENT_PERIOD_END = "current_period_end";
    public static final String MONGO_FIELD_GLOBAL_PERIOD_START = "global_period_start";
    public static final String MONGO_FIELD_GLOBAL_PERIOD_END = "global_period_end";
    public static final String MONGO_FIELD_CURRENT_PERIOD_OVERDUE = "current_period_overdue";
    public static final String MONGO_FIELD_GLOBAL_OVERDUE = "global_overdue";
    public static final String MONGO_FIELD_GLOBAL_PAID = "global_paid";
    public static final String MONGO_FIELD_PERIOD_PAID = "period_paid";
    public static final String MONGO_FIELD_PRICING = "pricing";
    public static final String MONGO_FIELD_ACADEMIC_YEAR = "academic_year";
    public static final String MONGO_FIELD_SIGNED = "signed";
    public static final String MONGO_FIELD_VAT_NUMBER = "vat_number";
    public static final String MONGO_FIELD_CONTRACT_REQUEST = "contract_request";

    @Field(MONGO_FIELD_CURRENT_PERIOD_START)
    private ZonedDateTime currentPeriodStart;

    @Field(MONGO_FIELD_CURRENT_PERIOD_END)
    private ZonedDateTime currentPeriodEnd;

    @Field(MONGO_FIELD_GLOBAL_PERIOD_START)
    private ZonedDateTime globalPeriodStart;

    @Field(MONGO_FIELD_GLOBAL_PERIOD_END)
    private ZonedDateTime globalPeriodEnd;

    @Field(MONGO_FIELD_CURRENT_PERIOD_OVERDUE)
    private BigDecimal currentPeriodOverdue;

    @Field(MONGO_FIELD_GLOBAL_OVERDUE)
    private BigDecimal globalOverdue;

    @Field(MONGO_FIELD_GLOBAL_PAID)
    private BigDecimal globalPaid;

    @Field(MONGO_FIELD_PERIOD_PAID)
    private BigDecimal periodPaid;

    @Field(MONGO_FIELD_VAT_NUMBER)
    private String vatNumber;//OK

    @DBRef
    @Field(MONGO_FIELD_PRICING)
    private PricingDbDTO pricing;

    @Field(MONGO_FIELD_ACADEMIC_YEAR)
    private AcademicYearDbVO academicYear;

    @Field(MONGO_FIELD_SIGNED)
    private boolean signed = false;

    @Field(MONGO_FIELD_CONTRACT_REQUEST)
    private ClientContractRequestDbDTO contractRequest;

    public ContractDbDTO() {
    }

    public ContractDbDTO(ZonedDateTime currentPeriodStart, ZonedDateTime currentPeriodEnd,
                         ZonedDateTime globalPeriodStart, ZonedDateTime globalPeriodEnd,
                         BigDecimal currentPeriodOverdue, BigDecimal globalOverdue, BigDecimal globalPaid,
                         BigDecimal periodPaid, String vatNumber, PricingDbDTO pricing,
                         AcademicYearDbVO academicYear, boolean signed) {
        this.currentPeriodStart = currentPeriodStart;
        this.currentPeriodEnd = currentPeriodEnd;
        this.globalPeriodStart = globalPeriodStart;
        this.globalPeriodEnd = globalPeriodEnd;
        this.currentPeriodOverdue = currentPeriodOverdue;
        this.globalOverdue = globalOverdue;
        this.globalPaid = globalPaid;
        this.periodPaid = periodPaid;
        this.vatNumber = vatNumber;
        this.pricing = pricing;
        this.academicYear = academicYear;
        this.signed = signed;
    }

    public ZonedDateTime getCurrentPeriodStart() {
        return currentPeriodStart;
    }

    public void setCurrentPeriodStart(ZonedDateTime currentPeriodStart) {
        this.currentPeriodStart = currentPeriodStart;
    }

    public ZonedDateTime getCurrentPeriodEnd() {
        return currentPeriodEnd;
    }

    public void setCurrentPeriodEnd(ZonedDateTime currentPeriodEnd) {
        this.currentPeriodEnd = currentPeriodEnd;
    }

    public ZonedDateTime getGlobalPeriodStart() {
        return globalPeriodStart;
    }

    public void setGlobalPeriodStart(ZonedDateTime globalPeriodStart) {
        this.globalPeriodStart = globalPeriodStart;
    }

    public ZonedDateTime getGlobalPeriodEnd() {
        return globalPeriodEnd;
    }

    public void setGlobalPeriodEnd(ZonedDateTime globalPeriodEnd) {
        this.globalPeriodEnd = globalPeriodEnd;
    }

    public BigDecimal getCurrentPeriodOverdue() {
        return currentPeriodOverdue;
    }

    public void setCurrentPeriodOverdue(BigDecimal currentPeriodOverdue) {
        this.currentPeriodOverdue = currentPeriodOverdue;
    }

    public BigDecimal getGlobalOverdue() {
        return globalOverdue;
    }

    public void setGlobalOverdue(BigDecimal globalOverdue) {
        this.globalOverdue = globalOverdue;
    }

    public BigDecimal getGlobalPaid() {
        return globalPaid;
    }

    public void setGlobalPaid(BigDecimal globalPaid) {
        this.globalPaid = globalPaid;
    }

    public BigDecimal getPeriodPaid() {
        return periodPaid;
    }

    public void setPeriodPaid(BigDecimal periodPaid) {
        this.periodPaid = periodPaid;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public PricingDbDTO getPricing() {
        return pricing;
    }

    public void setPricing(PricingDbDTO pricing) {
        this.pricing = pricing;
    }

    public AcademicYearDbVO getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYearDbVO academicYear) {
        this.academicYear = academicYear;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public ClientContractRequestDbDTO getContractRequest() {
        return contractRequest;
    }

    public void setContractRequest(ClientContractRequestDbDTO contractRequest) {
        this.contractRequest = contractRequest;
    }
}
