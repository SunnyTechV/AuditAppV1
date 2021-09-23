package com.vgtech.auditapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InventorySupplierDetails.
 */
@Entity
@Table(name = "inventory_supplier_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InventorySupplierDetails implements Serializable {

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

  @Column(name = "supplier_name")
  private String supplierName;

  @Column(name = "supplier_address")
  private String supplierAddress;

  @Column(name = "supplier_contact_name")
  private String supplierContactName;

  @Column(name = "supplier_contact_name_number")
  private String supplierContactNameNumber;

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

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public InventorySupplierDetails id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFormName() {
    return this.formName;
  }

  public InventorySupplierDetails formName(String formName) {
    this.setFormName(formName);
    return this;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getType() {
    return this.type;
  }

  public InventorySupplierDetails type(String type) {
    this.setType(type);
    return this;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return this.subType;
  }

  public InventorySupplierDetails subType(String subType) {
    this.setSubType(subType);
    return this;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getTableName() {
    return this.tableName;
  }

  public InventorySupplierDetails tableName(String tableName) {
    this.setTableName(tableName);
    return this;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getSupplierName() {
    return this.supplierName;
  }

  public InventorySupplierDetails supplierName(String supplierName) {
    this.setSupplierName(supplierName);
    return this;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }

  public String getSupplierAddress() {
    return this.supplierAddress;
  }

  public InventorySupplierDetails supplierAddress(String supplierAddress) {
    this.setSupplierAddress(supplierAddress);
    return this;
  }

  public void setSupplierAddress(String supplierAddress) {
    this.supplierAddress = supplierAddress;
  }

  public String getSupplierContactName() {
    return this.supplierContactName;
  }

  public InventorySupplierDetails supplierContactName(
    String supplierContactName
  ) {
    this.setSupplierContactName(supplierContactName);
    return this;
  }

  public void setSupplierContactName(String supplierContactName) {
    this.supplierContactName = supplierContactName;
  }

  public String getSupplierContactNameNumber() {
    return this.supplierContactNameNumber;
  }

  public InventorySupplierDetails supplierContactNameNumber(
    String supplierContactNameNumber
  ) {
    this.setSupplierContactNameNumber(supplierContactNameNumber);
    return this;
  }

  public void setSupplierContactNameNumber(String supplierContactNameNumber) {
    this.supplierContactNameNumber = supplierContactNameNumber;
  }

  public String getFreeField1() {
    return this.freeField1;
  }

  public InventorySupplierDetails freeField1(String freeField1) {
    this.setFreeField1(freeField1);
    return this;
  }

  public void setFreeField1(String freeField1) {
    this.freeField1 = freeField1;
  }

  public String getFreeField2() {
    return this.freeField2;
  }

  public InventorySupplierDetails freeField2(String freeField2) {
    this.setFreeField2(freeField2);
    return this;
  }

  public void setFreeField2(String freeField2) {
    this.freeField2 = freeField2;
  }

  public String getFreeField3() {
    return this.freeField3;
  }

  public InventorySupplierDetails freeField3(String freeField3) {
    this.setFreeField3(freeField3);
    return this;
  }

  public void setFreeField3(String freeField3) {
    this.freeField3 = freeField3;
  }

  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

  public InventorySupplierDetails createdDate(LocalDate createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public InventorySupplierDetails createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getLastModified() {
    return this.lastModified;
  }

  public InventorySupplierDetails lastModified(LocalDate lastModified) {
    this.setLastModified(lastModified);
    return this;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public InventorySupplierDetails lastModifiedBy(String lastModifiedBy) {
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

  public InventorySupplierDetails audit(Audit audit) {
    this.setAudit(audit);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InventorySupplierDetails)) {
      return false;
    }
    return id != null && id.equals(((InventorySupplierDetails) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
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
            "}";
    }
}
