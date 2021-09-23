package com.vgtech.auditapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OxygenConsumptionData.
 */
@Entity
@Table(name = "oxygen_consumption_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OxygenConsumptionData implements Serializable {

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

  @Column(name = "noof_patients")
  private Integer noofPatients;

  @Column(name = "consumption_litper_min")
  private Double consumptionLitperMin;

  @Column(name = "consumption_litper_day")
  private Double consumptionLitperDay;

  @Column(name = "consumption_k_litper_day")
  private Double consumptionKLitperDay;

  @Column(name = "consumption_total")
  private Double consumptionTotal;

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

  @ManyToOne
  @JsonIgnoreProperties(
    value = {
      "auditPatientMonitoringForm",
      "auditFormSHospGenInfos",
      "fireElectricalAudits",
      "annexureAnswers",
      "invetoryReports",
      "inventorySupplierDetails",
      "oxygenConsumptionData",
    },
    allowSetters = true
  )
  private Audit audit;

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "oxygenConsumptionData" },
    allowSetters = true
  )
  private TableDetails tableDetails;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public OxygenConsumptionData id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFormName() {
    return this.formName;
  }

  public OxygenConsumptionData formName(String formName) {
    this.setFormName(formName);
    return this;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getType() {
    return this.type;
  }

  public OxygenConsumptionData type(String type) {
    this.setType(type);
    return this;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return this.subType;
  }

  public OxygenConsumptionData subType(String subType) {
    this.setSubType(subType);
    return this;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getTableName() {
    return this.tableName;
  }

  public OxygenConsumptionData tableName(String tableName) {
    this.setTableName(tableName);
    return this;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public Integer getNoofPatients() {
    return this.noofPatients;
  }

  public OxygenConsumptionData noofPatients(Integer noofPatients) {
    this.setNoofPatients(noofPatients);
    return this;
  }

  public void setNoofPatients(Integer noofPatients) {
    this.noofPatients = noofPatients;
  }

  public Double getConsumptionLitperMin() {
    return this.consumptionLitperMin;
  }

  public OxygenConsumptionData consumptionLitperMin(
    Double consumptionLitperMin
  ) {
    this.setConsumptionLitperMin(consumptionLitperMin);
    return this;
  }

  public void setConsumptionLitperMin(Double consumptionLitperMin) {
    this.consumptionLitperMin = consumptionLitperMin;
  }

  public Double getConsumptionLitperDay() {
    return this.consumptionLitperDay;
  }

  public OxygenConsumptionData consumptionLitperDay(
    Double consumptionLitperDay
  ) {
    this.setConsumptionLitperDay(consumptionLitperDay);
    return this;
  }

  public void setConsumptionLitperDay(Double consumptionLitperDay) {
    this.consumptionLitperDay = consumptionLitperDay;
  }

  public Double getConsumptionKLitperDay() {
    return this.consumptionKLitperDay;
  }

  public OxygenConsumptionData consumptionKLitperDay(
    Double consumptionKLitperDay
  ) {
    this.setConsumptionKLitperDay(consumptionKLitperDay);
    return this;
  }

  public void setConsumptionKLitperDay(Double consumptionKLitperDay) {
    this.consumptionKLitperDay = consumptionKLitperDay;
  }

  public Double getConsumptionTotal() {
    return this.consumptionTotal;
  }

  public OxygenConsumptionData consumptionTotal(Double consumptionTotal) {
    this.setConsumptionTotal(consumptionTotal);
    return this;
  }

  public void setConsumptionTotal(Double consumptionTotal) {
    this.consumptionTotal = consumptionTotal;
  }

  public String getFreeField1() {
    return this.freeField1;
  }

  public OxygenConsumptionData freeField1(String freeField1) {
    this.setFreeField1(freeField1);
    return this;
  }

  public void setFreeField1(String freeField1) {
    this.freeField1 = freeField1;
  }

  public String getFreeField2() {
    return this.freeField2;
  }

  public OxygenConsumptionData freeField2(String freeField2) {
    this.setFreeField2(freeField2);
    return this;
  }

  public void setFreeField2(String freeField2) {
    this.freeField2 = freeField2;
  }

  public String getFreeField3() {
    return this.freeField3;
  }

  public OxygenConsumptionData freeField3(String freeField3) {
    this.setFreeField3(freeField3);
    return this;
  }

  public void setFreeField3(String freeField3) {
    this.freeField3 = freeField3;
  }

  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

  public OxygenConsumptionData createdDate(LocalDate createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public OxygenConsumptionData createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getLastModified() {
    return this.lastModified;
  }

  public OxygenConsumptionData lastModified(LocalDate lastModified) {
    this.setLastModified(lastModified);
    return this;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public OxygenConsumptionData lastModifiedBy(String lastModifiedBy) {
    this.setLastModifiedBy(lastModifiedBy);
    return this;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Audit getAudit() {
    return this.audit;
  }

  public void setAudit(Audit audit) {
    this.audit = audit;
  }

  public OxygenConsumptionData audit(Audit audit) {
    this.setAudit(audit);
    return this;
  }

  public TableDetails getTableDetails() {
    return this.tableDetails;
  }

  public void setTableDetails(TableDetails tableDetails) {
    this.tableDetails = tableDetails;
  }

  public OxygenConsumptionData tableDetails(TableDetails tableDetails) {
    this.setTableDetails(tableDetails);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OxygenConsumptionData)) {
      return false;
    }
    return id != null && id.equals(((OxygenConsumptionData) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "OxygenConsumptionData{" +
            "id=" + getId() +
            ", formName='" + getFormName() + "'" +
            ", type='" + getType() + "'" +
            ", subType='" + getSubType() + "'" +
            ", tableName='" + getTableName() + "'" +
            ", noofPatients=" + getNoofPatients() +
            ", consumptionLitperMin=" + getConsumptionLitperMin() +
            ", consumptionLitperDay=" + getConsumptionLitperDay() +
            ", consumptionKLitperDay=" + getConsumptionKLitperDay() +
            ", consumptionTotal=" + getConsumptionTotal() +
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
