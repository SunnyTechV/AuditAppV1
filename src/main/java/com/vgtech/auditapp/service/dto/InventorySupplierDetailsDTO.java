package com.vgtech.auditapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.vgtech.auditapp.domain.InventorySupplierDetails} entity.
 */
public class InventorySupplierDetails implements Serializable {

  private Long id;

  private String formName;

  private String type;

  private String subType;

  private String tableName;

  private String supplierName;

  private String supplierAddress;

  private String supplierContactName;

  private String supplierContactNameNumber;

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

  public String getSupplierName() {
    return supplierName;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }

  public String getSupplierAddress() {
    return supplierAddress;
  }

  public void setSupplierAddress(String supplierAddress) {
    this.supplierAddress = supplierAddress;
  }

  public String getSupplierContactName() {
    return supplierContactName;
  }

  public void setSupplierContactName(String supplierContactName) {
    this.supplierContactName = supplierContactName;
  }

  public String getSupplierContactNameNumber() {
    return supplierContactNameNumber;
  }

  public void setSupplierContactNameNumber(String supplierContactNameNumber) {
    this.supplierContactNameNumber = supplierContactNameNumber;
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
    if (!(o instanceof InventorySupplierDetails)) {
      return false;
    }

    InventorySupplierDetails inventorySupplierDetails = (InventorySupplierDetails) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, inventorySupplierDetails.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "InventorySupplierDetails{" +
            "id=" + getId() +
            ", formName='" + getFormName() + "'" +
            ", type='" + getType() + "'" +
            ", subType='" + getSubType() + "'" +
            ", tableName='" + getTableName() + "'" +
            ", supplierName='" + getSupplierName() + "'" +
            ", supplierAddress='" + getSupplierAddress() + "'" +
            ", supplierContactName='" + getSupplierContactName() + "'" +
            ", supplierContactNameNumber='" + getSupplierContactNameNumber() + "'" +
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
