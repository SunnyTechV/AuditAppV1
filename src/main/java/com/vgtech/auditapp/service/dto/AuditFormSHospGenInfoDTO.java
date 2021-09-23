package com.vgtech.auditapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.vgtech.auditapp.domain.AuditFormSHospGenInfo} entity.
 */
public class AuditFormSHospGenInfo implements Serializable {

  private Long id;

  private String hospName;

  private String hospType;

  private String type;

  private String subType;

  private String formName;

  private String inchargeName;

  private String hospAddress;

  private String hospPhoneNo;

  private Integer normalBeds;

  private Integer oxygenBeds;

  private Integer ventilatorBeds;

  private Integer icuBeds;

  private Integer onCylinderPatient;

  private Integer onPipedBedsPatient;

  private Integer onNIV;

  private Integer onIntubated;

  private String jumboSystemInstalledType;

  private Integer availableCylinderTypeD7;

  private Integer availableCylinderTypeB;

  private String cylinderAgencyName;

  private String cylinderAgencyAddress;

  private Double availableLMOCapacityKL;

  private String lmoSupplierName;

  private Integer lmoSupplierFrequency;

  private Double lastLmoSuppliedQuantity;

  private String remark;

  private LocalDate createdDate;

  private String createdBy;

  private LocalDate lastModified;

  private String lastModifiedBy;

  private String freeField1;

  private String freeField2;

  private String freeField3;

  private String freeField4;

  private AuditDTO audit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getHospName() {
    return hospName;
  }

  public void setHospName(String hospName) {
    this.hospName = hospName;
  }

  public String getHospType() {
    return hospType;
  }

  public void setHospType(String hospType) {
    this.hospType = hospType;
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

  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getInchargeName() {
    return inchargeName;
  }

  public void setInchargeName(String inchargeName) {
    this.inchargeName = inchargeName;
  }

  public String getHospAddress() {
    return hospAddress;
  }

  public void setHospAddress(String hospAddress) {
    this.hospAddress = hospAddress;
  }

  public String getHospPhoneNo() {
    return hospPhoneNo;
  }

  public void setHospPhoneNo(String hospPhoneNo) {
    this.hospPhoneNo = hospPhoneNo;
  }

  public Integer getNormalBeds() {
    return normalBeds;
  }

  public void setNormalBeds(Integer normalBeds) {
    this.normalBeds = normalBeds;
  }

  public Integer getOxygenBeds() {
    return oxygenBeds;
  }

  public void setOxygenBeds(Integer oxygenBeds) {
    this.oxygenBeds = oxygenBeds;
  }

  public Integer getVentilatorBeds() {
    return ventilatorBeds;
  }

  public void setVentilatorBeds(Integer ventilatorBeds) {
    this.ventilatorBeds = ventilatorBeds;
  }

  public Integer getIcuBeds() {
    return icuBeds;
  }

  public void setIcuBeds(Integer icuBeds) {
    this.icuBeds = icuBeds;
  }

  public Integer getOnCylinderPatient() {
    return onCylinderPatient;
  }

  public void setOnCylinderPatient(Integer onCylinderPatient) {
    this.onCylinderPatient = onCylinderPatient;
  }

  public Integer getOnPipedBedsPatient() {
    return onPipedBedsPatient;
  }

  public void setOnPipedBedsPatient(Integer onPipedBedsPatient) {
    this.onPipedBedsPatient = onPipedBedsPatient;
  }

  public Integer getOnNIV() {
    return onNIV;
  }

  public void setOnNIV(Integer onNIV) {
    this.onNIV = onNIV;
  }

  public Integer getOnIntubated() {
    return onIntubated;
  }

  public void setOnIntubated(Integer onIntubated) {
    this.onIntubated = onIntubated;
  }

  public String getJumboSystemInstalledType() {
    return jumboSystemInstalledType;
  }

  public void setJumboSystemInstalledType(String jumboSystemInstalledType) {
    this.jumboSystemInstalledType = jumboSystemInstalledType;
  }

  public Integer getAvailableCylinderTypeD7() {
    return availableCylinderTypeD7;
  }

  public void setAvailableCylinderTypeD7(Integer availableCylinderTypeD7) {
    this.availableCylinderTypeD7 = availableCylinderTypeD7;
  }

  public Integer getAvailableCylinderTypeB() {
    return availableCylinderTypeB;
  }

  public void setAvailableCylinderTypeB(Integer availableCylinderTypeB) {
    this.availableCylinderTypeB = availableCylinderTypeB;
  }

  public String getCylinderAgencyName() {
    return cylinderAgencyName;
  }

  public void setCylinderAgencyName(String cylinderAgencyName) {
    this.cylinderAgencyName = cylinderAgencyName;
  }

  public String getCylinderAgencyAddress() {
    return cylinderAgencyAddress;
  }

  public void setCylinderAgencyAddress(String cylinderAgencyAddress) {
    this.cylinderAgencyAddress = cylinderAgencyAddress;
  }

  public Double getAvailableLMOCapacityKL() {
    return availableLMOCapacityKL;
  }

  public void setAvailableLMOCapacityKL(Double availableLMOCapacityKL) {
    this.availableLMOCapacityKL = availableLMOCapacityKL;
  }

  public String getLmoSupplierName() {
    return lmoSupplierName;
  }

  public void setLmoSupplierName(String lmoSupplierName) {
    this.lmoSupplierName = lmoSupplierName;
  }

  public Integer getLmoSupplierFrequency() {
    return lmoSupplierFrequency;
  }

  public void setLmoSupplierFrequency(Integer lmoSupplierFrequency) {
    this.lmoSupplierFrequency = lmoSupplierFrequency;
  }

  public Double getLastLmoSuppliedQuantity() {
    return lastLmoSuppliedQuantity;
  }

  public void setLastLmoSuppliedQuantity(Double lastLmoSuppliedQuantity) {
    this.lastLmoSuppliedQuantity = lastLmoSuppliedQuantity;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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
    if (!(o instanceof AuditFormSHospGenInfo)) {
      return false;
    }

    AuditFormSHospGenInfo auditFormSHospGenInfo = (AuditFormSHospGenInfo) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, auditFormSHospGenInfo.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AuditFormSHospGenInfo{" +
            "id=" + getId() +
            ", hospName='" + getHospName() + "'" +
            ", hospType='" + getHospType() + "'" +
            ", type='" + getType() + "'" +
            ", subType='" + getSubType() + "'" +
            ", formName='" + getFormName() + "'" +
            ", inchargeName='" + getInchargeName() + "'" +
            ", hospAddress='" + getHospAddress() + "'" +
            ", hospPhoneNo='" + getHospPhoneNo() + "'" +
            ", normalBeds=" + getNormalBeds() +
            ", oxygenBeds=" + getOxygenBeds() +
            ", ventilatorBeds=" + getVentilatorBeds() +
            ", icuBeds=" + getIcuBeds() +
            ", onCylinderPatient=" + getOnCylinderPatient() +
            ", onPipedBedsPatient=" + getOnPipedBedsPatient() +
            ", onNIV=" + getOnNIV() +
            ", onIntubated=" + getOnIntubated() +
            ", jumboSystemInstalledType='" + getJumboSystemInstalledType() + "'" +
            ", availableCylinderTypeD7=" + getAvailableCylinderTypeD7() +
            ", availableCylinderTypeB=" + getAvailableCylinderTypeB() +
            ", cylinderAgencyName='" + getCylinderAgencyName() + "'" +
            ", cylinderAgencyAddress='" + getCylinderAgencyAddress() + "'" +
            ", availableLMOCapacityKL=" + getAvailableLMOCapacityKL() +
            ", lmoSupplierName='" + getLmoSupplierName() + "'" +
            ", lmoSupplierFrequency=" + getLmoSupplierFrequency() +
            ", lastLmoSuppliedQuantity=" + getLastLmoSuppliedQuantity() +
            ", remark='" + getRemark() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", audit=" + getAudit() +
            "}";
    }
}
