package com.eshipper.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A WoWorkOrder.
 */
@Entity
@Table(name = "wo_work_order")
public class WoWorkOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "customs_broker_name", length = 255)
    private String customsBrokerName;

    @Size(max = 255)
    @Column(name = "customs_contact_name", length = 255)
    private String customsContactName;

    @Size(max = 255)
    @Column(name = "customs_currency", length = 255)
    private String customsCurrency;

    @Size(max = 255)
    @Column(name = "customs_phone_number", length = 255)
    private String customsPhoneNumber;

    @Column(name = "customs_value")
    private Double customsValue;

    @Size(max = 255)
    @Column(name = "from_airport_code", length = 255)
    private String fromAirportCode;

    @Size(max = 255)
    @Column(name = "job_content_descr", length = 255)
    private String jobContentDescr;

    @Max(value = 11)
    @Column(name = "ship_quantity")
    private Integer shipQuantity;

    @Max(value = 11)
    @Column(name = "job_original_cost")
    private Integer jobOriginalCost;

    @Size(max = 255)
    @Column(name = "quoted_amount", length = 255)
    private String quotedAmount;

    @Size(max = 255)
    @Column(name = "assign_to", length = 255)
    private String assignTo;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @Size(max = 255)
    @Column(name = "job_number", length = 255)
    private String jobNumber;

    @Size(max = 255)
    @Column(name = "job_customer", length = 255)
    private String jobCustomer;

    @Column(name = "job_deadline_time")
    private Long jobDeadlineTime;

    @Column(name = "job_time_zone")
    private Long jobTimeZone;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @Column(name = "job_deadline_date")
    private LocalDate jobDeadlineDate;

    @Column(name = "shipping_time")
    private Long shippingTime;

    @Column(name = "is_storage")
    private Boolean isStorage;

    @Column(name = "is_pick_pack")
    private Boolean isPickPack;

    @Size(max = 255)
    @Column(name = "ship_total_weight", length = 255)
    private String shipTotalWeight;

    @Max(value = 11)
    @Column(name = "wo_request_type")
    private Integer woRequestType;

    @ManyToOne
    @JsonIgnoreProperties("woWorkOrders")
    private LocationType fromLocation;

    @ManyToOne
    @JsonIgnoreProperties("woWorkOrders")
    private LocationType toLocation;

    @ManyToOne
    @JsonIgnoreProperties("woWorkOrders")
    private WoSalesAgent woSalesAgent;

    @ManyToOne
    @JsonIgnoreProperties("woWorkOrders")
    private InsuranceType insuranceType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomsBrokerName() {
        return customsBrokerName;
    }

    public WoWorkOrder customsBrokerName(String customsBrokerName) {
        this.customsBrokerName = customsBrokerName;
        return this;
    }

    public void setCustomsBrokerName(String customsBrokerName) {
        this.customsBrokerName = customsBrokerName;
    }

    public String getCustomsContactName() {
        return customsContactName;
    }

    public WoWorkOrder customsContactName(String customsContactName) {
        this.customsContactName = customsContactName;
        return this;
    }

    public void setCustomsContactName(String customsContactName) {
        this.customsContactName = customsContactName;
    }

    public String getCustomsCurrency() {
        return customsCurrency;
    }

    public WoWorkOrder customsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
        return this;
    }

    public void setCustomsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
    }

    public String getCustomsPhoneNumber() {
        return customsPhoneNumber;
    }

    public WoWorkOrder customsPhoneNumber(String customsPhoneNumber) {
        this.customsPhoneNumber = customsPhoneNumber;
        return this;
    }

    public void setCustomsPhoneNumber(String customsPhoneNumber) {
        this.customsPhoneNumber = customsPhoneNumber;
    }

    public Double getCustomsValue() {
        return customsValue;
    }

    public WoWorkOrder customsValue(Double customsValue) {
        this.customsValue = customsValue;
        return this;
    }

    public void setCustomsValue(Double customsValue) {
        this.customsValue = customsValue;
    }

    public String getFromAirportCode() {
        return fromAirportCode;
    }

    public WoWorkOrder fromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
        return this;
    }

    public void setFromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
    }

    public String getJobContentDescr() {
        return jobContentDescr;
    }

    public WoWorkOrder jobContentDescr(String jobContentDescr) {
        this.jobContentDescr = jobContentDescr;
        return this;
    }

    public void setJobContentDescr(String jobContentDescr) {
        this.jobContentDescr = jobContentDescr;
    }

    public Integer getShipQuantity() {
        return shipQuantity;
    }

    public WoWorkOrder shipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
        return this;
    }

    public void setShipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    public Integer getJobOriginalCost() {
        return jobOriginalCost;
    }

    public WoWorkOrder jobOriginalCost(Integer jobOriginalCost) {
        this.jobOriginalCost = jobOriginalCost;
        return this;
    }

    public void setJobOriginalCost(Integer jobOriginalCost) {
        this.jobOriginalCost = jobOriginalCost;
    }

    public String getQuotedAmount() {
        return quotedAmount;
    }

    public WoWorkOrder quotedAmount(String quotedAmount) {
        this.quotedAmount = quotedAmount;
        return this;
    }

    public void setQuotedAmount(String quotedAmount) {
        this.quotedAmount = quotedAmount;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public WoWorkOrder assignTo(String assignTo) {
        this.assignTo = assignTo;
        return this;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public WoWorkOrder dateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public WoWorkOrder jobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
        return this;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobCustomer() {
        return jobCustomer;
    }

    public WoWorkOrder jobCustomer(String jobCustomer) {
        this.jobCustomer = jobCustomer;
        return this;
    }

    public void setJobCustomer(String jobCustomer) {
        this.jobCustomer = jobCustomer;
    }

    public Long getJobDeadlineTime() {
        return jobDeadlineTime;
    }

    public WoWorkOrder jobDeadlineTime(Long jobDeadlineTime) {
        this.jobDeadlineTime = jobDeadlineTime;
        return this;
    }

    public void setJobDeadlineTime(Long jobDeadlineTime) {
        this.jobDeadlineTime = jobDeadlineTime;
    }

    public Long getJobTimeZone() {
        return jobTimeZone;
    }

    public WoWorkOrder jobTimeZone(Long jobTimeZone) {
        this.jobTimeZone = jobTimeZone;
        return this;
    }

    public void setJobTimeZone(Long jobTimeZone) {
        this.jobTimeZone = jobTimeZone;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public WoWorkOrder shippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public LocalDate getJobDeadlineDate() {
        return jobDeadlineDate;
    }

    public WoWorkOrder jobDeadlineDate(LocalDate jobDeadlineDate) {
        this.jobDeadlineDate = jobDeadlineDate;
        return this;
    }

    public void setJobDeadlineDate(LocalDate jobDeadlineDate) {
        this.jobDeadlineDate = jobDeadlineDate;
    }

    public Long getShippingTime() {
        return shippingTime;
    }

    public WoWorkOrder shippingTime(Long shippingTime) {
        this.shippingTime = shippingTime;
        return this;
    }

    public void setShippingTime(Long shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Boolean isIsStorage() {
        return isStorage;
    }

    public WoWorkOrder isStorage(Boolean isStorage) {
        this.isStorage = isStorage;
        return this;
    }

    public void setIsStorage(Boolean isStorage) {
        this.isStorage = isStorage;
    }

    public Boolean isIsPickPack() {
        return isPickPack;
    }

    public WoWorkOrder isPickPack(Boolean isPickPack) {
        this.isPickPack = isPickPack;
        return this;
    }

    public void setIsPickPack(Boolean isPickPack) {
        this.isPickPack = isPickPack;
    }

    public String getShipTotalWeight() {
        return shipTotalWeight;
    }

    public WoWorkOrder shipTotalWeight(String shipTotalWeight) {
        this.shipTotalWeight = shipTotalWeight;
        return this;
    }

    public void setShipTotalWeight(String shipTotalWeight) {
        this.shipTotalWeight = shipTotalWeight;
    }

    public Integer getWoRequestType() {
        return woRequestType;
    }

    public WoWorkOrder woRequestType(Integer woRequestType) {
        this.woRequestType = woRequestType;
        return this;
    }

    public void setWoRequestType(Integer woRequestType) {
        this.woRequestType = woRequestType;
    }

    public LocationType getFromLocation() {
        return fromLocation;
    }

    public WoWorkOrder fromLocation(LocationType locationType) {
        this.fromLocation = locationType;
        return this;
    }

    public void setFromLocation(LocationType locationType) {
        this.fromLocation = locationType;
    }

    public LocationType getToLocation() {
        return toLocation;
    }

    public WoWorkOrder toLocation(LocationType locationType) {
        this.toLocation = locationType;
        return this;
    }

    public void setToLocation(LocationType locationType) {
        this.toLocation = locationType;
    }

    public WoSalesAgent getWoSalesAgent() {
        return woSalesAgent;
    }

    public WoWorkOrder woSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
        return this;
    }

    public void setWoSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public WoWorkOrder insuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
        return this;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoWorkOrder)) {
            return false;
        }
        return id != null && id.equals(((WoWorkOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoWorkOrder{" +
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
            "}";
    }
}
