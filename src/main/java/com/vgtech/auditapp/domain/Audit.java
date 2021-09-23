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
 * A Audit.
 */
@Entity
@Table(name = "audit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Audit implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "audit_date")
  private LocalDate auditDate;

  @Column(name = "hosp_name")
  private String hospName;

  @Column(name = "is_audit_complete")
  private Boolean isAuditComplete;

  @Column(name = "free_field_1")
  private String freeField1;

  @Column(name = "free_field_2")
  private String freeField2;

  @Column(name = "free_field_3")
  private String freeField3;

  @Column(name = "free_field_4")
  private String freeField4;

  @Column(name = "remark")
  private String remark;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "created_date")
  private LocalDate createdDate;

  @Column(name = "last_modified")
  private LocalDate lastModified;

  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @JsonIgnoreProperties(value = { "audit" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private AuditPatientMonitoringForm auditPatientMonitoringForm;

  @OneToMany(mappedBy = "audit")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "audit" }, allowSetters = true)
  private Set<AuditFormSHospGenInfo> auditFormSHospGenInfos = new HashSet<>();

  @OneToMany(mappedBy = "audit")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "audit" }, allowSetters = true)
  private Set<FireElectricalAudit> fireElectricalAudits = new HashSet<>();

  @OneToMany(mappedBy = "audit")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "audit", "annexureQuestions" },
    allowSetters = true
  )
  private Set<AnnexureAnswers> annexureAnswers = new HashSet<>();

  @OneToMany(mappedBy = "audit")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "audit" }, allowSetters = true)
  private Set<InventoryReport> invetoryReports = new HashSet<>();

  @OneToMany(mappedBy = "audit")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "audit" }, allowSetters = true)
  private Set<InventorySupplierDetails> inventorySupplierDetails = new HashSet<>();

  @OneToMany(mappedBy = "audit")
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

  public Audit id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getAuditDate() {
    return this.auditDate;
  }

  public Audit auditDate(LocalDate auditDate) {
    this.setAuditDate(auditDate);
    return this;
  }

  public void setAuditDate(LocalDate auditDate) {
    this.auditDate = auditDate;
  }

  public String getHospName() {
    return this.hospName;
  }

  public Audit hospName(String hospName) {
    this.setHospName(hospName);
    return this;
  }

  public void setHospName(String hospName) {
    this.hospName = hospName;
  }

  public Boolean getIsAuditComplete() {
    return this.isAuditComplete;
  }

  public Audit isAuditComplete(Boolean isAuditComplete) {
    this.setIsAuditComplete(isAuditComplete);
    return this;
  }

  public void setIsAuditComplete(Boolean isAuditComplete) {
    this.isAuditComplete = isAuditComplete;
  }

  public String getFreeField1() {
    return this.freeField1;
  }

  public Audit freeField1(String freeField1) {
    this.setFreeField1(freeField1);
    return this;
  }

  public void setFreeField1(String freeField1) {
    this.freeField1 = freeField1;
  }

  public String getFreeField2() {
    return this.freeField2;
  }

  public Audit freeField2(String freeField2) {
    this.setFreeField2(freeField2);
    return this;
  }

  public void setFreeField2(String freeField2) {
    this.freeField2 = freeField2;
  }

  public String getFreeField3() {
    return this.freeField3;
  }

  public Audit freeField3(String freeField3) {
    this.setFreeField3(freeField3);
    return this;
  }

  public void setFreeField3(String freeField3) {
    this.freeField3 = freeField3;
  }

  public String getFreeField4() {
    return this.freeField4;
  }

  public Audit freeField4(String freeField4) {
    this.setFreeField4(freeField4);
    return this;
  }

  public void setFreeField4(String freeField4) {
    this.freeField4 = freeField4;
  }

  public String getRemark() {
    return this.remark;
  }

  public Audit remark(String remark) {
    this.setRemark(remark);
    return this;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public Audit createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

  public Audit createdDate(LocalDate createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDate getLastModified() {
    return this.lastModified;
  }

  public Audit lastModified(LocalDate lastModified) {
    this.setLastModified(lastModified);
    return this;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public Audit lastModifiedBy(String lastModifiedBy) {
    this.setLastModifiedBy(lastModifiedBy);
    return this;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public AuditPatientMonitoringForm getAuditPatientMonitoringForm() {
    return this.auditPatientMonitoringForm;
  }

  public void setAuditPatientMonitoringForm(
    AuditPatientMonitoringForm auditPatientMonitoringForm
  ) {
    this.auditPatientMonitoringForm = auditPatientMonitoringForm;
  }

  public Audit auditPatientMonitoringForm(
    AuditPatientMonitoringForm auditPatientMonitoringForm
  ) {
    this.setAuditPatientMonitoringForm(auditPatientMonitoringForm);
    return this;
  }

  public Set<AuditFormSHospGenInfo> getAuditFormSHospGenInfos() {
    return this.auditFormSHospGenInfos;
  }

  public void setAuditFormSHospGenInfos(
    Set<AuditFormSHospGenInfo> auditFormSHospGenInfos
  ) {
    if (this.auditFormSHospGenInfos != null) {
      this.auditFormSHospGenInfos.forEach(i -> i.setAudit(null));
    }
    if (auditFormSHospGenInfos != null) {
      auditFormSHospGenInfos.forEach(i -> i.setAudit(this));
    }
    this.auditFormSHospGenInfos = auditFormSHospGenInfos;
  }

  public Audit auditFormSHospGenInfos(
    Set<AuditFormSHospGenInfo> auditFormSHospGenInfos
  ) {
    this.setAuditFormSHospGenInfos(auditFormSHospGenInfos);
    return this;
  }

  public Audit addAuditFormSHospGenInfo(
    AuditFormSHospGenInfo auditFormSHospGenInfo
  ) {
    this.auditFormSHospGenInfos.add(auditFormSHospGenInfo);
    auditFormSHospGenInfo.setAudit(this);
    return this;
  }

  public Audit removeAuditFormSHospGenInfo(
    AuditFormSHospGenInfo auditFormSHospGenInfo
  ) {
    this.auditFormSHospGenInfos.remove(auditFormSHospGenInfo);
    auditFormSHospGenInfo.setAudit(null);
    return this;
  }

  public Set<FireElectricalAudit> getFireElectricalAudits() {
    return this.fireElectricalAudits;
  }

  public void setFireElectricalAudits(
    Set<FireElectricalAudit> fireElectricalAudits
  ) {
    if (this.fireElectricalAudits != null) {
      this.fireElectricalAudits.forEach(i -> i.setAudit(null));
    }
    if (fireElectricalAudits != null) {
      fireElectricalAudits.forEach(i -> i.setAudit(this));
    }
    this.fireElectricalAudits = fireElectricalAudits;
  }

  public Audit fireElectricalAudits(
    Set<FireElectricalAudit> fireElectricalAudits
  ) {
    this.setFireElectricalAudits(fireElectricalAudits);
    return this;
  }

  public Audit addFireElectricalAudit(FireElectricalAudit fireElectricalAudit) {
    this.fireElectricalAudits.add(fireElectricalAudit);
    fireElectricalAudit.setAudit(this);
    return this;
  }

  public Audit removeFireElectricalAudit(
    FireElectricalAudit fireElectricalAudit
  ) {
    this.fireElectricalAudits.remove(fireElectricalAudit);
    fireElectricalAudit.setAudit(null);
    return this;
  }

  public Set<AnnexureAnswers> getAnnexureAnswers() {
    return this.annexureAnswers;
  }

  public void setAnnexureAnswers(Set<AnnexureAnswers> annexureAnswers) {
    if (this.annexureAnswers != null) {
      this.annexureAnswers.forEach(i -> i.setAudit(null));
    }
    if (annexureAnswers != null) {
      annexureAnswers.forEach(i -> i.setAudit(this));
    }
    this.annexureAnswers = annexureAnswers;
  }

  public Audit annexureAnswers(Set<AnnexureAnswers> annexureAnswers) {
    this.setAnnexureAnswers(annexureAnswers);
    return this;
  }

  public Audit addAnnexureAnswers(AnnexureAnswers annexureAnswers) {
    this.annexureAnswers.add(annexureAnswers);
    annexureAnswers.setAudit(this);
    return this;
  }

  public Audit removeAnnexureAnswers(AnnexureAnswers annexureAnswers) {
    this.annexureAnswers.remove(annexureAnswers);
    annexureAnswers.setAudit(null);
    return this;
  }

  public Set<InventoryReport> getInvetoryReports() {
    return this.invetoryReports;
  }

  public void setInvetoryReports(Set<InventoryReport> inventoryReports) {
    if (this.invetoryReports != null) {
      this.invetoryReports.forEach(i -> i.setAudit(null));
    }
    if (inventoryReports != null) {
      inventoryReports.forEach(i -> i.setAudit(this));
    }
    this.invetoryReports = inventoryReports;
  }

  public Audit invetoryReports(Set<InventoryReport> inventoryReports) {
    this.setInvetoryReports(inventoryReports);
    return this;
  }

  public Audit addInvetoryReport(InventoryReport inventoryReport) {
    this.invetoryReports.add(inventoryReport);
    inventoryReport.setAudit(this);
    return this;
  }

  public Audit removeInvetoryReport(InventoryReport inventoryReport) {
    this.invetoryReports.remove(inventoryReport);
    inventoryReport.setAudit(null);
    return this;
  }

  public Set<InventorySupplierDetails> getInventorySupplierDetails() {
    return this.inventorySupplierDetails;
  }

  public void setInventorySupplierDetails(
    Set<InventorySupplierDetails> inventorySupplierDetails
  ) {
    if (this.inventorySupplierDetails != null) {
      this.inventorySupplierDetails.forEach(i -> i.setAudit(null));
    }
    if (inventorySupplierDetails != null) {
      inventorySupplierDetails.forEach(i -> i.setAudit(this));
    }
    this.inventorySupplierDetails = inventorySupplierDetails;
  }

  public Audit inventorySupplierDetails(
    Set<InventorySupplierDetails> inventorySupplierDetails
  ) {
    this.setInventorySupplierDetails(inventorySupplierDetails);
    return this;
  }

  public Audit addInventorySupplierDetails(
    InventorySupplierDetails inventorySupplierDetails
  ) {
    this.inventorySupplierDetails.add(inventorySupplierDetails);
    inventorySupplierDetails.setAudit(this);
    return this;
  }

  public Audit removeInventorySupplierDetails(
    InventorySupplierDetails inventorySupplierDetails
  ) {
    this.inventorySupplierDetails.remove(inventorySupplierDetails);
    inventorySupplierDetails.setAudit(null);
    return this;
  }

  public Set<OxygenConsumptionData> getOxygenConsumptionData() {
    return this.oxygenConsumptionData;
  }

  public void setOxygenConsumptionData(
    Set<OxygenConsumptionData> oxygenConsumptionData
  ) {
    if (this.oxygenConsumptionData != null) {
      this.oxygenConsumptionData.forEach(i -> i.setAudit(null));
    }
    if (oxygenConsumptionData != null) {
      oxygenConsumptionData.forEach(i -> i.setAudit(this));
    }
    this.oxygenConsumptionData = oxygenConsumptionData;
  }

  public Audit oxygenConsumptionData(
    Set<OxygenConsumptionData> oxygenConsumptionData
  ) {
    this.setOxygenConsumptionData(oxygenConsumptionData);
    return this;
  }

  public Audit addOxygenConsumptionData(
    OxygenConsumptionData oxygenConsumptionData
  ) {
    this.oxygenConsumptionData.add(oxygenConsumptionData);
    oxygenConsumptionData.setAudit(this);
    return this;
  }

  public Audit removeOxygenConsumptionData(
    OxygenConsumptionData oxygenConsumptionData
  ) {
    this.oxygenConsumptionData.remove(oxygenConsumptionData);
    oxygenConsumptionData.setAudit(null);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Audit)) {
      return false;
    }
    return id != null && id.equals(((Audit) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Audit{" +
            "id=" + getId() +
            ", auditDate='" + getAuditDate() + "'" +
            ", hospName='" + getHospName() + "'" +
            ", isAuditComplete='" + getIsAuditComplete() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
