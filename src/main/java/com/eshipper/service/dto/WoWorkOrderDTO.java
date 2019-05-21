package com.eshipper.service.dto;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.WoWorkOrder} entity.
 */
public class WoWorkOrderDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String customsBrokerName;

    @Size(max = 255)
    private String customsContactName;

    @Size(max = 255)
    private String customsCurrency;

    @Size(max = 255)
    private String customsPhoneNumber;

    private Double customsValue;

    @Size(max = 255)
    private String fromAirportCode;

    @Size(max = 255)
    private String jobContentDescr;

    @Max(value = 11)
    private Integer shipQuantity;

    @Max(value = 11)
    private Integer jobOriginalCost;

    @Size(max = 255)
    private String quotedAmount;

    @Size(max = 255)
    private String assignTo;

    private ZonedDateTime dateCreated;

    @Size(max = 255)
    private String jobNumber;

    @Size(max = 255)
    private String jobCustomer;

    private Long jobDeadlineTime;

    private Long jobTimeZone;

    private LocalDate shippingDate;

    private LocalDate jobDeadlineDate;

    private Long shippingTime;

    private Boolean isStorage;

    private Boolean isPickPack;

    @Size(max = 255)
    private String shipTotalWeight;

    @Max(value = 11)
    private Integer woRequestType;


    private Long fromLocationId;

    private Long toLocationId;

    private Long woSalesAgentId;

    private Long insuranceTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomsBrokerName() {
        return customsBrokerName;
    }

    public void setCustomsBrokerName(String customsBrokerName) {
        this.customsBrokerName = customsBrokerName;
    }

    public String getCustomsContactName() {
        return customsContactName;
    }

    public void setCustomsContactName(String customsContactName) {
        this.customsContactName = customsContactName;
    }

    public String getCustomsCurrency() {
        return customsCurrency;
    }

    public void setCustomsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
    }

    public String getCustomsPhoneNumber() {
        return customsPhoneNumber;
    }

    public void setCustomsPhoneNumber(String customsPhoneNumber) {
        this.customsPhoneNumber = customsPhoneNumber;
    }

    public Double getCustomsValue() {
        return customsValue;
    }

    public void setCustomsValue(Double customsValue) {
        this.customsValue = customsValue;
    }

    public String getFromAirportCode() {
        return fromAirportCode;
    }

    public void setFromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
    }

    public String getJobContentDescr() {
        return jobContentDescr;
    }

    public void setJobContentDescr(String jobContentDescr) {
        this.jobContentDescr = jobContentDescr;
    }

    public Integer getShipQuantity() {
        return shipQuantity;
    }

    public void setShipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    public Integer getJobOriginalCost() {
        return jobOriginalCost;
    }

    public void setJobOriginalCost(Integer jobOriginalCost) {
        this.jobOriginalCost = jobOriginalCost;
    }

    public String getQuotedAmount() {
        return quotedAmount;
    }

    public void setQuotedAmount(String quotedAmount) {
        this.quotedAmount = quotedAmount;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobCustomer() {
        return jobCustomer;
    }

    public void setJobCustomer(String jobCustomer) {
        this.jobCustomer = jobCustomer;
    }

    public Long getJobDeadlineTime() {
        return jobDeadlineTime;
    }

    public void setJobDeadlineTime(Long jobDeadlineTime) {
        this.jobDeadlineTime = jobDeadlineTime;
    }

    public Long getJobTimeZone() {
        return jobTimeZone;
    }

    public void setJobTimeZone(Long jobTimeZone) {
        this.jobTimeZone = jobTimeZone;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public LocalDate getJobDeadlineDate() {
        return jobDeadlineDate;
    }

    public void setJobDeadlineDate(LocalDate jobDeadlineDate) {
        this.jobDeadlineDate = jobDeadlineDate;
    }

    public Long getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Long shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Boolean isIsStorage() {
        return isStorage;
    }

    public void setIsStorage(Boolean isStorage) {
        this.isStorage = isStorage;
    }

    public Boolean isIsPickPack() {
        return isPickPack;
    }

    public void setIsPickPack(Boolean isPickPack) {
        this.isPickPack = isPickPack;
    }

    public String getShipTotalWeight() {
        return shipTotalWeight;
    }

    public void setShipTotalWeight(String shipTotalWeight) {
        this.shipTotalWeight = shipTotalWeight;
    }

    public Integer getWoRequestType() {
        return woRequestType;
    }

    public void setWoRequestType(Integer woRequestType) {
        this.woRequestType = woRequestType;
    }

    public Long getFromLocationId() {
        return fromLocationId;
    }

    public void setFromLocationId(Long locationTypeId) {
        this.fromLocationId = locationTypeId;
    }

    public Long getToLocationId() {
        return toLocationId;
    }

    public void setToLocationId(Long locationTypeId) {
        this.toLocationId = locationTypeId;
    }

    public Long getWoSalesAgentId() {
        return woSalesAgentId;
    }

    public void setWoSalesAgentId(Long woSalesAgentId) {
        this.woSalesAgentId = woSalesAgentId;
    }

    public Long getInsuranceTypeId() {
        return insuranceTypeId;
    }

    public void setInsuranceTypeId(Long insuranceTypeId) {
        this.insuranceTypeId = insuranceTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoWorkOrderDTO woWorkOrderDTO = (WoWorkOrderDTO) o;
        if (woWorkOrderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woWorkOrderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoWorkOrderDTO{" +
            "id=" + getId() +
            ", customsBrokerName='" + getCustomsBrokerName() + "'" +
            ", customsContactName='" + getCustomsContactName() + "'" +
            ", customsCurrency='" + getCustomsCurrency() + "'" +
            ", customsPhoneNumber='" + getCustomsPhoneNumber() + "'" +
            ", customsValue=" + getCustomsValue() +
            ", fromAirportCode='" + getFromAirportCode() + "'" +
            ", jobContentDescr='" + getJobContentDescr() + "'" +
            ", shipQuantity=" + getShipQuantity() +
            ", jobOriginalCost=" + getJobOriginalCost() +
            ", quotedAmount='" + getQuotedAmount() + "'" +
            ", assignTo='" + getAssignTo() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", jobNumber='" + getJobNumber() + "'" +
            ", jobCustomer='" + getJobCustomer() + "'" +
            ", jobDeadlineTime=" + getJobDeadlineTime() +
            ", jobTimeZone=" + getJobTimeZone() +
            ", shippingDate='" + getShippingDate() + "'" +
            ", jobDeadlineDate='" + getJobDeadlineDate() + "'" +
            ", shippingTime=" + getShippingTime() +
            ", isStorage='" + isIsStorage() + "'" +
            ", isPickPack='" + isIsPickPack() + "'" +
            ", shipTotalWeight='" + getShipTotalWeight() + "'" +
            ", woRequestType=" + getWoRequestType() +
            ", fromLocation=" + getFromLocationId() +
            ", toLocation=" + getToLocationId() +
            ", woSalesAgent=" + getWoSalesAgentId() +
            ", insuranceType=" + getInsuranceTypeId() +
            "}";
    }
}
