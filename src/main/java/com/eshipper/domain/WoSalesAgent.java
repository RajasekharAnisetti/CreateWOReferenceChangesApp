package com.eshipper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WoSalesAgent.
 */
@Entity
@Table(name = "wo_sales_agent")
public class WoSalesAgent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "agent_name", length = 255)
    private String agentName;

    @Size(max = 255)
    @Column(name = "promo_code", length = 255)
    private String promoCode;

    @OneToMany(mappedBy = "woSalesAgent")
    private Set<WoWorkOrder> woWorkOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public WoSalesAgent agentName(String agentName) {
        this.agentName = agentName;
        return this;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public WoSalesAgent promoCode(String promoCode) {
        this.promoCode = promoCode;
        return this;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Set<WoWorkOrder> getWoWorkOrders() {
        return woWorkOrders;
    }

    public WoSalesAgent woWorkOrders(Set<WoWorkOrder> woWorkOrders) {
        this.woWorkOrders = woWorkOrders;
        return this;
    }

    public WoSalesAgent addWoWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrders.add(woWorkOrder);
        woWorkOrder.setWoSalesAgent(this);
        return this;
    }

    public WoSalesAgent removeWoWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrders.remove(woWorkOrder);
        woWorkOrder.setWoSalesAgent(null);
        return this;
    }

    public void setWoWorkOrders(Set<WoWorkOrder> woWorkOrders) {
        this.woWorkOrders = woWorkOrders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoSalesAgent)) {
            return false;
        }
        return id != null && id.equals(((WoSalesAgent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoSalesAgent{" +
            "id=" + getId() +
            ", agentName='" + getAgentName() + "'" +
            ", promoCode='" + getPromoCode() + "'" +
            "}";
    }
}
