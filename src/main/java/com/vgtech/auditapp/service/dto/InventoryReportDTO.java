package com.vgtech.auditapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.vgtech.auditapp.domain.InventoryReport} entity.
 */
public class InventoryReport implements Serializable {

  private Long id;

  private String formName;

  private String type;

  private String subType;

  private String tableName;

  private String description;

  private String descriptionParameter;

  private String actualAvailable;

  private String remark;

  private String freeField1;

  private String freeField2;

  private String freeField3;

  private LocalDate createdDate;

  private String createdBy;

  private LocalDate lastModified;

  private String lastModifiedBy;

  private AuditDTO audit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescriptionParameter() {
    return descriptionParameter;
  }

  public void setDescriptionParameter(String descriptionParameter) {
    this.descriptionParameter = descriptionParameter;
  }

  public String getActualAvailable() {
    return actualAvailable;
  }

  public void setActualAvailable(String actualAvailable) {
    this.actualAvailable = actualAvailable;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getFreeField1() {
    return freeField1;
  }

  public void setFreeField1(String freeField1) {
    this.freeField1 = freeField1;
  }

  public String getFreeField2() {
    return freeField2;
  }

  public void setFreeField2(String freeField2) {
    this.freeField2 = freeField2;
  }

  public String getFreeField3() {
    return freeField3;
  }

  public void setFreeField3(String freeField3) {
    this.freeField3 = freeField3;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public AuditDTO getAudit() {
    return audit;
  }

  public void setAudit(AuditDTO audit) {
    this.audit = audit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InventoryReport)) {
      return false;
    }

    InventoryReport inventoryReport = (InventoryReport) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, inventoryReport.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "InventoryReport{" +
            "id=" + getId() +
            ", formName='" + getFormName() + "'" +
            ", type='" + getType() + "'" +
            ", subType='" + getSubType() + "'" +
            ", tableName='" + getTableName() + "'" +
            ", description='" + getDescription() + "'" +
            ", descriptionParameter='" + getDescriptionParameter() + "'" +
            ", actualAvailable='" + getActualAvailable() + "'" +
            ", remark='" + getRemark() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", audit=" + getAudit() +
            "}";
    }
}
