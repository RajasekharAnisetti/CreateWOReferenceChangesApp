package com.eshipper.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.WoSalesAgent} entity.
 */
public class WoSalesAgentDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String agentName;

    @Size(max = 255)
    private String promoCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoSalesAgentDTO woSalesAgentDTO = (WoSalesAgentDTO) o;
        if (woSalesAgentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woSalesAgentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoSalesAgentDTO{" +
            "id=" + getId() +
            ", agentName='" + getAgentName() + "'" +
            ", promoCode='" + getPromoCode() + "'" +
            "}";
    }
}
