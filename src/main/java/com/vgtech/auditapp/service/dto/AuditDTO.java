package com.vgtech.auditapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.vgtech.auditapp.domain.Audit} entity.
 */
public class Audit implements Serializable {

  private Long id;

  private LocalDate auditDate;

  private String hospName;

  private Boolean isAuditComplete;

  private String freeField1;

  private String freeField2;

  private String freeField3;

  private String freeField4;

  private String remark;

  private String createdBy;

  private LocalDate createdDate;

  private LocalDate lastModified;

  private String lastModifiedBy;

  private AuditPatientMonitoringFormDTO auditPatientMonitoringForm;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getAuditDate() {
    return auditDate;
  }

  public void setAuditDate(LocalDate auditDate) {
    this.auditDate = auditDate;
  }

  public String getHospName() {
    return hospName;
  }

  public void setHospName(String hospName) {
    this.hospName = hospName;
  }

  public Boolean getIsAuditComplete() {
    return isAuditComplete;
  }

  public void setIsAuditComplete(Boolean isAuditComplete) {
    this.isAuditComplete = isAuditComplete;
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

  public String getFreeField4() {
    return freeField4;
  }

  public void setFreeField4(String freeField4) {
    this.freeField4 = freeField4;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
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

  public AuditPatientMonitoringFormDTO getAuditPatientMonitoringForm() {
    return auditPatientMonitoringForm;
  }

  public void setAuditPatientMonitoringForm(
    AuditPatientMonitoringFormDTO auditPatientMonitoringForm
  ) {
    this.auditPatientMonitoringForm = auditPatientMonitoringForm;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Audit)) {
      return false;
    }

    Audit audit = (Audit) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, audit.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
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
            ", auditPatientMonitoringForm=" + getAuditPatientMonitoringForm() +
            "}";
    }
}
