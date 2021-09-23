package com.vgtech.auditapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TableDetails.
 */
@Entity
@Table(name = "table_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TableDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "form_name")
  private String formName;

  @Column(name = "type")
  private String type;

  @Column(name = "sub_type")
  private String subType;

  @Column(name = "table_name")
  private String tableName;

  @Column(name = "description")
  private String description;

  @Column(name = "description_parameter")
  private String descriptionParameter;

  @Column(name = "free_field_1")
  private String freeField1;

  @Column(name = "free_field_2")
  private String freeField2;

  @Column(name = "free_field_3")
  private String freeField3;

  @Column(name = "created_date")
  private LocalDate createdDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "last_modified")
  private LocalDate lastModified;

  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @OneToMany(mappedBy = "tableDetails")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "audit", "tableDetails" },
    allowSetters = true
  )
  private Set<OxygenConsumptionData> oxygenConsumptionData = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public TableDetails id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFormName() {
    return this.formName;
  }

  public TableDetails formName(String formName) {
    this.setFormName(formName);
    return this;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getType() {
    return this.type;
  }

  public TableDetails type(String type) {
    this.setType(type);
    return this;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return this.subType;
  }

  public TableDetails subType(String subType) {
    this.setSubType(subType);
    return this;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getTableName() {
    return this.tableName;
  }

  public TableDetails tableName(String tableName) {
    this.setTableName(tableName);
    return this;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getDescription() {
    return this.description;
  }

  public TableDetails description(String description) {
    this.setDescription(description);
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescriptionParameter() {
    return this.descriptionParameter;
  }

  public TableDetails descriptionParameter(String descriptionParameter) {
    this.setDescriptionParameter(descriptionParameter);
    return this;
  }

  public void setDescriptionParameter(String descriptionParameter) {
    this.descriptionParameter = descriptionParameter;
  }

  public String getFreeField1() {
    return this.freeField1;
  }

  public TableDetails freeField1(String freeField1) {
    this.setFreeField1(freeField1);
    return this;
  }

  public void setFreeField1(String freeField1) {
    this.freeField1 = freeField1;
  }

  public String getFreeField2() {
    return this.freeField2;
  }

  public TableDetails freeField2(String freeField2) {
    this.setFreeField2(freeField2);
    return this;
  }

  public void setFreeField2(String freeField2) {
    this.freeField2 = freeField2;
  }

  public String getFreeField3() {
    return this.freeField3;
  }

  public TableDetails freeField3(String freeField3) {
    this.setFreeField3(freeField3);
    return this;
  }

  public void setFreeField3(String freeField3) {
    this.freeField3 = freeField3;
  }

  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

  public TableDetails createdDate(LocalDate createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public TableDetails createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getLastModified() {
    return this.lastModified;
  }

  public TableDetails lastModified(LocalDate lastModified) {
    this.setLastModified(lastModified);
    return this;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public TableDetails lastModifiedBy(String lastModifiedBy) {
    this.setLastModifiedBy(lastModifiedBy);
    return this;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Set<OxygenConsumptionData> getOxygenConsumptionData() {
    return this.oxygenConsumptionData;
  }

  public void setOxygenConsumptionData(
    Set<OxygenConsumptionData> oxygenConsumptionData
  ) {
    if (this.oxygenConsumptionData != null) {
      this.oxygenConsumptionData.forEach(i -> i.setTableDetails(null));
    }
    if (oxygenConsumptionData != null) {
      oxygenConsumptionData.forEach(i -> i.setTableDetails(this));
    }
    this.oxygenConsumptionData = oxygenConsumptionData;
  }

  public TableDetails oxygenConsumptionData(
    Set<OxygenConsumptionData> oxygenConsumptionData
  ) {
    this.setOxygenConsumptionData(oxygenConsumptionData);
    return this;
  }

  public TableDetails addOxygenConsumptionData(
    OxygenConsumptionData oxygenConsumptionData
  ) {
    this.oxygenConsumptionData.add(oxygenConsumptionData);
    oxygenConsumptionData.setTableDetails(this);
    return this;
  }

  public TableDetails removeOxygenConsumptionData(
    OxygenConsumptionData oxygenConsumptionData
  ) {
    this.oxygenConsumptionData.remove(oxygenConsumptionData);
    oxygenConsumptionData.setTableDetails(null);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TableDetails)) {
      return false;
    }
    return id != null && id.equals(((TableDetails) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TableDetails{" +
            "id=" + getId() +
            ", formName='" + getFormName() + "'" +
            ", type='" + getType() + "'" +
            ", subType='" + getSubType() + "'" +
            ", tableName='" + getTableName() + "'" +
            ", description='" + getDescription() + "'" +
            ", descriptionParameter='" + getDescriptionParameter() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
