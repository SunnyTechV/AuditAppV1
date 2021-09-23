package com.vgtech.auditapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.vgtech.auditapp.domain.AuditPatientMonitoringForm} entity.
 */
public class AuditPatientMonitoringForm implements Serializable {

  private Long id;

  private Integer wardNo;

  private String patientName;

  private Integer bedNo;

  private LocalDate dateOfAdmission;

  private String oxygenType;

  private Double sixToEightAM;

  private Double oxySixToEightAM;

  private Double eightToTenAM;

  private Double oxyEightToTenAM;

  private Double tenToTwelveAM;

  private Double oxyTenToTwelveAM;

  private Double twelveToTowPM;

  private Double oxyTwelveToTowPM;

  private Double twoToFourPM;

  private Double oxyTwoToFourPM;

  private Double fourToSixPM;

  private Double oxyFourToSixPM;

  private Double sixToEightPM;

  private Double oxySixToEightPM;

  private Double eightToTenPM;

  private Double oxyEightToTenPM;

  private Double tenToTwelvePM;

  private Double oxyTenToTwelvePM;

  private Double twelveToTwoAM;

  private Double oxyTwelveToTwoAM;

  private Double twoToFourAM;

  private Double oxyTwoToFourAM;

  private Double fourToSixAM;

  private Double oxyFourToSixAM;

  private String drName;

  private String nurseName;

  private String createdBy;

  private LocalDate createdDate;

  private String lastModifiedBy;

  private LocalDate lastModified;

  private String freeField1;

  private String freeField2;

  private String freeField3;

  private String freeField4;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getWardNo() {
    return wardNo;
  }

  public void setWardNo(Integer wardNo) {
    this.wardNo = wardNo;
  }

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public Integer getBedNo() {
    return bedNo;
  }

  public void setBedNo(Integer bedNo) {
    this.bedNo = bedNo;
  }

  public LocalDate getDateOfAdmission() {
    return dateOfAdmission;
  }

  public void setDateOfAdmission(LocalDate dateOfAdmission) {
    this.dateOfAdmission = dateOfAdmission;
  }

  public String getOxygenType() {
    return oxygenType;
  }

  public void setOxygenType(String oxygenType) {
    this.oxygenType = oxygenType;
  }

  public Double getSixToEightAM() {
    return sixToEightAM;
  }

  public void setSixToEightAM(Double sixToEightAM) {
    this.sixToEightAM = sixToEightAM;
  }

  public Double getOxySixToEightAM() {
    return oxySixToEightAM;
  }

  public void setOxySixToEightAM(Double oxySixToEightAM) {
    this.oxySixToEightAM = oxySixToEightAM;
  }

  public Double getEightToTenAM() {
    return eightToTenAM;
  }

  public void setEightToTenAM(Double eightToTenAM) {
    this.eightToTenAM = eightToTenAM;
  }

  public Double getOxyEightToTenAM() {
    return oxyEightToTenAM;
  }

  public void setOxyEightToTenAM(Double oxyEightToTenAM) {
    this.oxyEightToTenAM = oxyEightToTenAM;
  }

  public Double getTenToTwelveAM() {
    return tenToTwelveAM;
  }

  public void setTenToTwelveAM(Double tenToTwelveAM) {
    this.tenToTwelveAM = tenToTwelveAM;
  }

  public Double getOxyTenToTwelveAM() {
    return oxyTenToTwelveAM;
  }

  public void setOxyTenToTwelveAM(Double oxyTenToTwelveAM) {
    this.oxyTenToTwelveAM = oxyTenToTwelveAM;
  }

  public Double getTwelveToTowPM() {
    return twelveToTowPM;
  }

  public void setTwelveToTowPM(Double twelveToTowPM) {
    this.twelveToTowPM = twelveToTowPM;
  }

  public Double getOxyTwelveToTowPM() {
    return oxyTwelveToTowPM;
  }

  public void setOxyTwelveToTowPM(Double oxyTwelveToTowPM) {
    this.oxyTwelveToTowPM = oxyTwelveToTowPM;
  }

  public Double getTwoToFourPM() {
    return twoToFourPM;
  }

  public void setTwoToFourPM(Double twoToFourPM) {
    this.twoToFourPM = twoToFourPM;
  }

  public Double getOxyTwoToFourPM() {
    return oxyTwoToFourPM;
  }

  public void setOxyTwoToFourPM(Double oxyTwoToFourPM) {
    this.oxyTwoToFourPM = oxyTwoToFourPM;
  }

  public Double getFourToSixPM() {
    return fourToSixPM;
  }

  public void setFourToSixPM(Double fourToSixPM) {
    this.fourToSixPM = fourToSixPM;
  }

  public Double getOxyFourToSixPM() {
    return oxyFourToSixPM;
  }

  public void setOxyFourToSixPM(Double oxyFourToSixPM) {
    this.oxyFourToSixPM = oxyFourToSixPM;
  }

  public Double getSixToEightPM() {
    return sixToEightPM;
  }

  public void setSixToEightPM(Double sixToEightPM) {
    this.sixToEightPM = sixToEightPM;
  }

  public Double getOxySixToEightPM() {
    return oxySixToEightPM;
  }

  public void setOxySixToEightPM(Double oxySixToEightPM) {
    this.oxySixToEightPM = oxySixToEightPM;
  }

  public Double getEightToTenPM() {
    return eightToTenPM;
  }

  public void setEightToTenPM(Double eightToTenPM) {
    this.eightToTenPM = eightToTenPM;
  }

  public Double getOxyEightToTenPM() {
    return oxyEightToTenPM;
  }

  public void setOxyEightToTenPM(Double oxyEightToTenPM) {
    this.oxyEightToTenPM = oxyEightToTenPM;
  }

  public Double getTenToTwelvePM() {
    return tenToTwelvePM;
  }

  public void setTenToTwelvePM(Double tenToTwelvePM) {
    this.tenToTwelvePM = tenToTwelvePM;
  }

  public Double getOxyTenToTwelvePM() {
    return oxyTenToTwelvePM;
  }

  public void setOxyTenToTwelvePM(Double oxyTenToTwelvePM) {
    this.oxyTenToTwelvePM = oxyTenToTwelvePM;
  }

  public Double getTwelveToTwoAM() {
    return twelveToTwoAM;
  }

  public void setTwelveToTwoAM(Double twelveToTwoAM) {
    this.twelveToTwoAM = twelveToTwoAM;
  }

  public Double getOxyTwelveToTwoAM() {
    return oxyTwelveToTwoAM;
  }

  public void setOxyTwelveToTwoAM(Double oxyTwelveToTwoAM) {
    this.oxyTwelveToTwoAM = oxyTwelveToTwoAM;
  }

  public Double getTwoToFourAM() {
    return twoToFourAM;
  }

  public void setTwoToFourAM(Double twoToFourAM) {
    this.twoToFourAM = twoToFourAM;
  }

  public Double getOxyTwoToFourAM() {
    return oxyTwoToFourAM;
  }

  public void setOxyTwoToFourAM(Double oxyTwoToFourAM) {
    this.oxyTwoToFourAM = oxyTwoToFourAM;
  }

  public Double getFourToSixAM() {
    return fourToSixAM;
  }

  public void setFourToSixAM(Double fourToSixAM) {
    this.fourToSixAM = fourToSixAM;
  }

  public Double getOxyFourToSixAM() {
    return oxyFourToSixAM;
  }

  public void setOxyFourToSixAM(Double oxyFourToSixAM) {
    this.oxyFourToSixAM = oxyFourToSixAM;
  }

  public String getDrName() {
    return drName;
  }

  public void setDrName(String drName) {
    this.drName = drName;
  }

  public String getNurseName() {
    return nurseName;
  }

  public void setNurseName(String nurseName) {
    this.nurseName = nurseName;
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

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public LocalDate getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AuditPatientMonitoringForm)) {
      return false;
    }

    AuditPatientMonitoringForm auditPatientMonitoringForm = (AuditPatientMonitoringForm) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, auditPatientMonitoringForm.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AuditPatientMonitoringForm{" +
            "id=" + getId() +
            ", wardNo=" + getWardNo() +
            ", patientName='" + getPatientName() + "'" +
            ", bedNo=" + getBedNo() +
            ", dateOfAdmission='" + getDateOfAdmission() + "'" +
            ", oxygenType='" + getOxygenType() + "'" +
            ", sixToEightAM=" + getSixToEightAM() +
            ", oxySixToEightAM=" + getOxySixToEightAM() +
            ", eightToTenAM=" + getEightToTenAM() +
            ", oxyEightToTenAM=" + getOxyEightToTenAM() +
            ", tenToTwelveAM=" + getTenToTwelveAM() +
            ", oxyTenToTwelveAM=" + getOxyTenToTwelveAM() +
            ", twelveToTowPM=" + getTwelveToTowPM() +
            ", oxyTwelveToTowPM=" + getOxyTwelveToTowPM() +
            ", twoToFourPM=" + getTwoToFourPM() +
            ", oxyTwoToFourPM=" + getOxyTwoToFourPM() +
            ", fourToSixPM=" + getFourToSixPM() +
            ", oxyFourToSixPM=" + getOxyFourToSixPM() +
            ", sixToEightPM=" + getSixToEightPM() +
            ", oxySixToEightPM=" + getOxySixToEightPM() +
            ", eightToTenPM=" + getEightToTenPM() +
            ", oxyEightToTenPM=" + getOxyEightToTenPM() +
            ", tenToTwelvePM=" + getTenToTwelvePM() +
            ", oxyTenToTwelvePM=" + getOxyTenToTwelvePM() +
            ", twelveToTwoAM=" + getTwelveToTwoAM() +
            ", oxyTwelveToTwoAM=" + getOxyTwelveToTwoAM() +
            ", twoToFourAM=" + getTwoToFourAM() +
            ", oxyTwoToFourAM=" + getOxyTwoToFourAM() +
            ", fourToSixAM=" + getFourToSixAM() +
            ", oxyFourToSixAM=" + getOxyFourToSixAM() +
            ", drName='" + getDrName() + "'" +
            ", nurseName='" + getNurseName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            "}";
    }
}
