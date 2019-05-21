package com.eshipper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A InsuranceType.
 */
@Entity
@Table(name = "insurance_type")
public class InsuranceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy = "insuranceType")
    private Set<WoWorkOrder> woWorkOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public InsuranceType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WoWorkOrder> getWoWorkOrders() {
        return woWorkOrders;
    }

    public InsuranceType woWorkOrders(Set<WoWorkOrder> woWorkOrders) {
        this.woWorkOrders = woWorkOrders;
        return this;
    }

    public InsuranceType addWoWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrders.add(woWorkOrder);
        woWorkOrder.setInsuranceType(this);
        return this;
    }

    public InsuranceType removeWoWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrders.remove(woWorkOrder);
        woWorkOrder.setInsuranceType(null);
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
        if (!(o instanceof InsuranceType)) {
            return false;
        }
        return id != null && id.equals(((InsuranceType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InsuranceType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
