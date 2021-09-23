package com.vgtech.auditapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.vgtech.auditapp.domain.AuditFormSHospGenInfo} entity. This class is used
 * in {@link com.vgtech.auditapp.web.rest.AuditFormSHospGenInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /audit-form-s-hosp-gen-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AuditFormSHospGenInfoCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private StringFilter hospName;

  private StringFilter hospType;

  private StringFilter type;

  private StringFilter subType;

  private StringFilter formName;

  private StringFilter inchargeName;

  private StringFilter hospAddress;

  private StringFilter hospPhoneNo;

  private IntegerFilter normalBeds;

  private IntegerFilter oxygenBeds;

  private IntegerFilter ventilatorBeds;

  private IntegerFilter icuBeds;

  private IntegerFilter onCylinderPatient;

  private IntegerFilter onPipedBedsPatient;

  private IntegerFilter onNIV;

  private IntegerFilter onIntubated;

  private StringFilter jumboSystemInstalledType;

  private IntegerFilter availableCylinderTypeD7;

  private IntegerFilter availableCylinderTypeB;

  private StringFilter cylinderAgencyName;

  private StringFilter cylinderAgencyAddress;

  private DoubleFilter availableLMOCapacityKL;

  private StringFilter lmoSupplierName;

  private IntegerFilter lmoSupplierFrequency;

  private DoubleFilter lastLmoSuppliedQuantity;

  private StringFilter remark;

  private LocalDateFilter createdDate;

  private StringFilter createdBy;

  private LocalDateFilter lastModified;

  private StringFilter lastModifiedBy;

  private StringFilter freeField1;

  private StringFilter freeField2;

  private StringFilter freeField3;

  private StringFilter freeField4;

  private LongFilter auditId;

  private Boolean distinct;

  public AuditFormSHospGenInfoCriteria() {}

  public AuditFormSHospGenInfoCriteria(AuditFormSHospGenInfoCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.hospName = other.hospName == null ? null : other.hospName.copy();
    this.hospType = other.hospType == null ? null : other.hospType.copy();
    this.type = other.type == null ? null : other.type.copy();
    this.subType = other.subType == null ? null : other.subType.copy();
    this.formName = other.formName == null ? null : other.formName.copy();
    this.inchargeName =
      other.inchargeName == null ? null : other.inchargeName.copy();
    this.hospAddress =
      other.hospAddress == null ? null : other.hospAddress.copy();
    this.hospPhoneNo =
      other.hospPhoneNo == null ? null : other.hospPhoneNo.copy();
    this.normalBeds = other.normalBeds == null ? null : other.normalBeds.copy();
    this.oxygenBeds = other.oxygenBeds == null ? null : other.oxygenBeds.copy();
    this.ventilatorBeds =
      other.ventilatorBeds == null ? null : other.ventilatorBeds.copy();
    this.icuBeds = other.icuBeds == null ? null : other.icuBeds.copy();
    this.onCylinderPatient =
      other.onCylinderPatient == null ? null : other.onCylinderPatient.copy();
    this.onPipedBedsPatient =
      other.onPipedBedsPatient == null ? null : other.onPipedBedsPatient.copy();
    this.onNIV = other.onNIV == null ? null : other.onNIV.copy();
    this.onIntubated =
      other.onIntubated == null ? null : other.onIntubated.copy();
    this.jumboSystemInstalledType =
      other.jumboSystemInstalledType == null
        ? null
        : other.jumboSystemInstalledType.copy();
    this.availableCylinderTypeD7 =
      other.availableCylinderTypeD7 == null
        ? null
        : other.availableCylinderTypeD7.copy();
    this.availableCylinderTypeB =
      other.availableCylinderTypeB == null
        ? null
        : other.availableCylinderTypeB.copy();
    this.cylinderAgencyName =
      other.cylinderAgencyName == null ? null : other.cylinderAgencyName.copy();
    this.cylinderAgencyAddress =
      other.cylinderAgencyAddress == null
        ? null
        : other.cylinderAgencyAddress.copy();
    this.availableLMOCapacityKL =
      other.availableLMOCapacityKL == null
        ? null
        : other.availableLMOCapacityKL.copy();
    this.lmoSupplierName =
      other.lmoSupplierName == null ? null : other.lmoSupplierName.copy();
    this.lmoSupplierFrequency =
      other.lmoSupplierFrequency == null
        ? null
        : other.lmoSupplierFrequency.copy();
    this.lastLmoSuppliedQuantity =
      other.lastLmoSuppliedQuantity == null
        ? null
        : other.lastLmoSuppliedQuantity.copy();
    this.remark = other.remark == null ? null : other.remark.copy();
    this.createdDate =
      other.createdDate == null ? null : other.createdDate.copy();
    this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
    this.lastModified =
      other.lastModified == null ? null : other.lastModified.copy();
    this.lastModifiedBy =
      other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
    this.freeField1 = other.freeField1 == null ? null : other.freeField1.copy();
    this.freeField2 = other.freeField2 == null ? null : other.freeField2.copy();
    this.freeField3 = other.freeField3 == null ? null : other.freeField3.copy();
    this.freeField4 = other.freeField4 == null ? null : other.freeField4.copy();
    this.auditId = other.auditId == null ? null : other.auditId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public AuditFormSHospGenInfoCriteria copy() {
    return new AuditFormSHospGenInfoCriteria(this);
  }

  public LongFilter getId() {
    return id;
  }

  public LongFilter id() {
    if (id == null) {
      id = new LongFilter();
    }
    return id;
  }

  public void setId(LongFilter id) {
    this.id = id;
  }

  public StringFilter getHospName() {
    return hospName;
  }

  public StringFilter hospName() {
    if (hospName == null) {
      hospName = new StringFilter();
    }
    return hospName;
  }

  public void setHospName(StringFilter hospName) {
    this.hospName = hospName;
  }

  public StringFilter getHospType() {
    return hospType;
  }

  public StringFilter hospType() {
    if (hospType == null) {
      hospType = new StringFilter();
    }
    return hospType;
  }

  public void setHospType(StringFilter hospType) {
    this.hospType = hospType;
  }

  public StringFilter getType() {
    return type;
  }

  public StringFilter type() {
    if (type == null) {
      type = new StringFilter();
    }
    return type;
  }

  public void setType(StringFilter type) {
    this.type = type;
  }

  public StringFilter getSubType() {
    return subType;
  }

  public StringFilter subType() {
    if (subType == null) {
      subType = new StringFilter();
    }
    return subType;
  }

  public void setSubType(StringFilter subType) {
    this.subType = subType;
  }

  public StringFilter getFormName() {
    return formName;
  }

  public StringFilter formName() {
    if (formName == null) {
      formName = new StringFilter();
    }
    return formName;
  }

  public void setFormName(StringFilter formName) {
    this.formName = formName;
  }

  public StringFilter getInchargeName() {
    return inchargeName;
  }

  public StringFilter inchargeName() {
    if (inchargeName == null) {
      inchargeName = new StringFilter();
    }
    return inchargeName;
  }

  public void setInchargeName(StringFilter inchargeName) {
    this.inchargeName = inchargeName;
  }

  public StringFilter getHospAddress() {
    return hospAddress;
  }

  public StringFilter hospAddress() {
    if (hospAddress == null) {
      hospAddress = new StringFilter();
    }
    return hospAddress;
  }

  public void setHospAddress(StringFilter hospAddress) {
    this.hospAddress = hospAddress;
  }

  public StringFilter getHospPhoneNo() {
    return hospPhoneNo;
  }

  public StringFilter hospPhoneNo() {
    if (hospPhoneNo == null) {
      hospPhoneNo = new StringFilter();
    }
    return hospPhoneNo;
  }

  public void setHospPhoneNo(StringFilter hospPhoneNo) {
    this.hospPhoneNo = hospPhoneNo;
  }

  public IntegerFilter getNormalBeds() {
    return normalBeds;
  }

  public IntegerFilter normalBeds() {
    if (normalBeds == null) {
      normalBeds = new IntegerFilter();
    }
    return normalBeds;
  }

  public void setNormalBeds(IntegerFilter normalBeds) {
    this.normalBeds = normalBeds;
  }

  public IntegerFilter getOxygenBeds() {
    return oxygenBeds;
  }

  public IntegerFilter oxygenBeds() {
    if (oxygenBeds == null) {
      oxygenBeds = new IntegerFilter();
    }
    return oxygenBeds;
  }

  public void setOxygenBeds(IntegerFilter oxygenBeds) {
    this.oxygenBeds = oxygenBeds;
  }

  public IntegerFilter getVentilatorBeds() {
    return ventilatorBeds;
  }

  public IntegerFilter ventilatorBeds() {
    if (ventilatorBeds == null) {
      ventilatorBeds = new IntegerFilter();
    }
    return ventilatorBeds;
  }

  public void setVentilatorBeds(IntegerFilter ventilatorBeds) {
    this.ventilatorBeds = ventilatorBeds;
  }

  public IntegerFilter getIcuBeds() {
    return icuBeds;
  }

  public IntegerFilter icuBeds() {
    if (icuBeds == null) {
      icuBeds = new IntegerFilter();
    }
    return icuBeds;
  }

  public void setIcuBeds(IntegerFilter icuBeds) {
    this.icuBeds = icuBeds;
  }

  public IntegerFilter getOnCylinderPatient() {
    return onCylinderPatient;
  }

  public IntegerFilter onCylinderPatient() {
    if (onCylinderPatient == null) {
      onCylinderPatient = new IntegerFilter();
    }
    return onCylinderPatient;
  }

  public void setOnCylinderPatient(IntegerFilter onCylinderPatient) {
    this.onCylinderPatient = onCylinderPatient;
  }

  public IntegerFilter getOnPipedBedsPatient() {
    return onPipedBedsPatient;
  }

  public IntegerFilter onPipedBedsPatient() {
    if (onPipedBedsPatient == null) {
      onPipedBedsPatient = new IntegerFilter();
    }
    return onPipedBedsPatient;
  }

  public void setOnPipedBedsPatient(IntegerFilter onPipedBedsPatient) {
    this.onPipedBedsPatient = onPipedBedsPatient;
  }

  public IntegerFilter getOnNIV() {
    return onNIV;
  }

  public IntegerFilter onNIV() {
    if (onNIV == null) {
      onNIV = new IntegerFilter();
    }
    return onNIV;
  }

  public void setOnNIV(IntegerFilter onNIV) {
    this.onNIV = onNIV;
  }

  public IntegerFilter getOnIntubated() {
    return onIntubated;
  }

  public IntegerFilter onIntubated() {
    if (onIntubated == null) {
      onIntubated = new IntegerFilter();
    }
    return onIntubated;
  }

  public void setOnIntubated(IntegerFilter onIntubated) {
    this.onIntubated = onIntubated;
  }

  public StringFilter getJumboSystemInstalledType() {
    return jumboSystemInstalledType;
  }

  public StringFilter jumboSystemInstalledType() {
    if (jumboSystemInstalledType == null) {
      jumboSystemInstalledType = new StringFilter();
    }
    return jumboSystemInstalledType;
  }

  public void setJumboSystemInstalledType(
    StringFilter jumboSystemInstalledType
  ) {
    this.jumboSystemInstalledType = jumboSystemInstalledType;
  }

  public IntegerFilter getAvailableCylinderTypeD7() {
    return availableCylinderTypeD7;
  }

  public IntegerFilter availableCylinderTypeD7() {
    if (availableCylinderTypeD7 == null) {
      availableCylinderTypeD7 = new IntegerFilter();
    }
    return availableCylinderTypeD7;
  }

  public void setAvailableCylinderTypeD7(
    IntegerFilter availableCylinderTypeD7
  ) {
    this.availableCylinderTypeD7 = availableCylinderTypeD7;
  }

  public IntegerFilter getAvailableCylinderTypeB() {
    return availableCylinderTypeB;
  }

  public IntegerFilter availableCylinderTypeB() {
    if (availableCylinderTypeB == null) {
      availableCylinderTypeB = new IntegerFilter();
    }
    return availableCylinderTypeB;
  }

  public void setAvailableCylinderTypeB(IntegerFilter availableCylinderTypeB) {
    this.availableCylinderTypeB = availableCylinderTypeB;
  }

  public StringFilter getCylinderAgencyName() {
    return cylinderAgencyName;
  }

  public StringFilter cylinderAgencyName() {
    if (cylinderAgencyName == null) {
      cylinderAgencyName = new StringFilter();
    }
    return cylinderAgencyName;
  }

  public void setCylinderAgencyName(StringFilter cylinderAgencyName) {
    this.cylinderAgencyName = cylinderAgencyName;
  }

  public StringFilter getCylinderAgencyAddress() {
    return cylinderAgencyAddress;
  }

  public StringFilter cylinderAgencyAddress() {
    if (cylinderAgencyAddress == null) {
      cylinderAgencyAddress = new StringFilter();
    }
    return cylinderAgencyAddress;
  }

  public void setCylinderAgencyAddress(StringFilter cylinderAgencyAddress) {
    this.cylinderAgencyAddress = cylinderAgencyAddress;
  }

  public DoubleFilter getAvailableLMOCapacityKL() {
    return availableLMOCapacityKL;
  }

  public DoubleFilter availableLMOCapacityKL() {
    if (availableLMOCapacityKL == null) {
      availableLMOCapacityKL = new DoubleFilter();
    }
    return availableLMOCapacityKL;
  }

  public void setAvailableLMOCapacityKL(DoubleFilter availableLMOCapacityKL) {
    this.availableLMOCapacityKL = availableLMOCapacityKL;
  }

  public StringFilter getLmoSupplierName() {
    return lmoSupplierName;
  }

  public StringFilter lmoSupplierName() {
    if (lmoSupplierName == null) {
      lmoSupplierName = new StringFilter();
    }
    return lmoSupplierName;
  }

  public void setLmoSupplierName(StringFilter lmoSupplierName) {
    this.lmoSupplierName = lmoSupplierName;
  }

  public IntegerFilter getLmoSupplierFrequency() {
    return lmoSupplierFrequency;
  }

  public IntegerFilter lmoSupplierFrequency() {
    if (lmoSupplierFrequency == null) {
      lmoSupplierFrequency = new IntegerFilter();
    }
    return lmoSupplierFrequency;
  }

  public void setLmoSupplierFrequency(IntegerFilter lmoSupplierFrequency) {
    this.lmoSupplierFrequency = lmoSupplierFrequency;
  }

  public DoubleFilter getLastLmoSuppliedQuantity() {
    return lastLmoSuppliedQuantity;
  }

  public DoubleFilter lastLmoSuppliedQuantity() {
    if (lastLmoSuppliedQuantity == null) {
      lastLmoSuppliedQuantity = new DoubleFilter();
    }
    return lastLmoSuppliedQuantity;
  }

  public void setLastLmoSuppliedQuantity(DoubleFilter lastLmoSuppliedQuantity) {
    this.lastLmoSuppliedQuantity = lastLmoSuppliedQuantity;
  }

  public StringFilter getRemark() {
    return remark;
  }

  public StringFilter remark() {
    if (remark == null) {
      remark = new StringFilter();
    }
    return remark;
  }

  public void setRemark(StringFilter remark) {
    this.remark = remark;
  }

  public LocalDateFilter getCreatedDate() {
    return createdDate;
  }

  public LocalDateFilter createdDate() {
    if (createdDate == null) {
      createdDate = new LocalDateFilter();
    }
    return createdDate;
  }

  public void setCreatedDate(LocalDateFilter createdDate) {
    this.createdDate = createdDate;
  }

  public StringFilter getCreatedBy() {
    return createdBy;
  }

  public StringFilter createdBy() {
    if (createdBy == null) {
      createdBy = new StringFilter();
    }
    return createdBy;
  }

  public void setCreatedBy(StringFilter createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateFilter getLastModified() {
    return lastModified;
  }

  public LocalDateFilter lastModified() {
    if (lastModified == null) {
      lastModified = new LocalDateFilter();
    }
    return lastModified;
  }

  public void setLastModified(LocalDateFilter lastModified) {
    this.lastModified = lastModified;
  }

  public StringFilter getLastModifiedBy() {
    return lastModifiedBy;
  }

  public StringFilter lastModifiedBy() {
    if (lastModifiedBy == null) {
      lastModifiedBy = new StringFilter();
    }
    return lastModifiedBy;
  }

  public void setLastModifiedBy(StringFilter lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public StringFilter getFreeField1() {
    return freeField1;
  }

  public StringFilter freeField1() {
    if (freeField1 == null) {
      freeField1 = new StringFilter();
    }
    return freeField1;
  }

  public void setFreeField1(StringFilter freeField1) {
    this.freeField1 = freeField1;
  }

  public StringFilter getFreeField2() {
    return freeField2;
  }

  public StringFilter freeField2() {
    if (freeField2 == null) {
      freeField2 = new StringFilter();
    }
    return freeField2;
  }

  public void setFreeField2(StringFilter freeField2) {
    this.freeField2 = freeField2;
  }

  public StringFilter getFreeField3() {
    return freeField3;
  }

  public StringFilter freeField3() {
    if (freeField3 == null) {
      freeField3 = new StringFilter();
    }
    return freeField3;
  }

  public void setFreeField3(StringFilter freeField3) {
    this.freeField3 = freeField3;
  }

  public StringFilter getFreeField4() {
    return freeField4;
  }

  public StringFilter freeField4() {
    if (freeField4 == null) {
      freeField4 = new StringFilter();
    }
    return freeField4;
  }

  public void setFreeField4(StringFilter freeField4) {
    this.freeField4 = freeField4;
  }

  public LongFilter getAuditId() {
    return auditId;
  }

  public LongFilter auditId() {
    if (auditId == null) {
      auditId = new LongFilter();
    }
    return auditId;
  }

  public void setAuditId(LongFilter auditId) {
    this.auditId = auditId;
  }

  public Boolean getDistinct() {
    return distinct;
  }

  public void setDistinct(Boolean distinct) {
    this.distinct = distinct;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AuditFormSHospGenInfoCriteria that = (AuditFormSHospGenInfoCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(hospName, that.hospName) &&
      Objects.equals(hospType, that.hospType) &&
      Objects.equals(type, that.type) &&
      Objects.equals(subType, that.subType) &&
      Objects.equals(formName, that.formName) &&
      Objects.equals(inchargeName, that.inchargeName) &&
      Objects.equals(hospAddress, that.hospAddress) &&
      Objects.equals(hospPhoneNo, that.hospPhoneNo) &&
      Objects.equals(normalBeds, that.normalBeds) &&
      Objects.equals(oxygenBeds, that.oxygenBeds) &&
      Objects.equals(ventilatorBeds, that.ventilatorBeds) &&
      Objects.equals(icuBeds, that.icuBeds) &&
      Objects.equals(onCylinderPatient, that.onCylinderPatient) &&
      Objects.equals(onPipedBedsPatient, that.onPipedBedsPatient) &&
      Objects.equals(onNIV, that.onNIV) &&
      Objects.equals(onIntubated, that.onIntubated) &&
      Objects.equals(jumboSystemInstalledType, that.jumboSystemInstalledType) &&
      Objects.equals(availableCylinderTypeD7, that.availableCylinderTypeD7) &&
      Objects.equals(availableCylinderTypeB, that.availableCylinderTypeB) &&
      Objects.equals(cylinderAgencyName, that.cylinderAgencyName) &&
      Objects.equals(cylinderAgencyAddress, that.cylinderAgencyAddress) &&
      Objects.equals(availableLMOCapacityKL, that.availableLMOCapacityKL) &&
      Objects.equals(lmoSupplierName, that.lmoSupplierName) &&
      Objects.equals(lmoSupplierFrequency, that.lmoSupplierFrequency) &&
      Objects.equals(lastLmoSuppliedQuantity, that.lastLmoSuppliedQuantity) &&
      Objects.equals(remark, that.remark) &&
      Objects.equals(createdDate, that.createdDate) &&
      Objects.equals(createdBy, that.createdBy) &&
      Objects.equals(lastModified, that.lastModified) &&
      Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
      Objects.equals(freeField1, that.freeField1) &&
      Objects.equals(freeField2, that.freeField2) &&
      Objects.equals(freeField3, that.freeField3) &&
      Objects.equals(freeField4, that.freeField4) &&
      Objects.equals(auditId, that.auditId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      hospName,
      hospType,
      type,
      subType,
      formName,
      inchargeName,
      hospAddress,
      hospPhoneNo,
      normalBeds,
      oxygenBeds,
      ventilatorBeds,
      icuBeds,
      onCylinderPatient,
      onPipedBedsPatient,
      onNIV,
      onIntubated,
      jumboSystemInstalledType,
      availableCylinderTypeD7,
      availableCylinderTypeB,
      cylinderAgencyName,
      cylinderAgencyAddress,
      availableLMOCapacityKL,
      lmoSupplierName,
      lmoSupplierFrequency,
      lastLmoSuppliedQuantity,
      remark,
      createdDate,
      createdBy,
      lastModified,
      lastModifiedBy,
      freeField1,
      freeField2,
      freeField3,
      freeField4,
      auditId,
      distinct
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AuditFormSHospGenInfoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (hospName != null ? "hospName=" + hospName + ", " : "") +
            (hospType != null ? "hospType=" + hospType + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (subType != null ? "subType=" + subType + ", " : "") +
            (formName != null ? "formName=" + formName + ", " : "") +
            (inchargeName != null ? "inchargeName=" + inchargeName + ", " : "") +
            (hospAddress != null ? "hospAddress=" + hospAddress + ", " : "") +
            (hospPhoneNo != null ? "hospPhoneNo=" + hospPhoneNo + ", " : "") +
            (normalBeds != null ? "normalBeds=" + normalBeds + ", " : "") +
            (oxygenBeds != null ? "oxygenBeds=" + oxygenBeds + ", " : "") +
            (ventilatorBeds != null ? "ventilatorBeds=" + ventilatorBeds + ", " : "") +
            (icuBeds != null ? "icuBeds=" + icuBeds + ", " : "") +
            (onCylinderPatient != null ? "onCylinderPatient=" + onCylinderPatient + ", " : "") +
            (onPipedBedsPatient != null ? "onPipedBedsPatient=" + onPipedBedsPatient + ", " : "") +
            (onNIV != null ? "onNIV=" + onNIV + ", " : "") +
            (onIntubated != null ? "onIntubated=" + onIntubated + ", " : "") +
            (jumboSystemInstalledType != null ? "jumboSystemInstalledType=" + jumboSystemInstalledType + ", " : "") +
            (availableCylinderTypeD7 != null ? "availableCylinderTypeD7=" + availableCylinderTypeD7 + ", " : "") +
            (availableCylinderTypeB != null ? "availableCylinderTypeB=" + availableCylinderTypeB + ", " : "") +
            (cylinderAgencyName != null ? "cylinderAgencyName=" + cylinderAgencyName + ", " : "") +
            (cylinderAgencyAddress != null ? "cylinderAgencyAddress=" + cylinderAgencyAddress + ", " : "") +
            (availableLMOCapacityKL != null ? "availableLMOCapacityKL=" + availableLMOCapacityKL + ", " : "") +
            (lmoSupplierName != null ? "lmoSupplierName=" + lmoSupplierName + ", " : "") +
            (lmoSupplierFrequency != null ? "lmoSupplierFrequency=" + lmoSupplierFrequency + ", " : "") +
            (lastLmoSuppliedQuantity != null ? "lastLmoSuppliedQuantity=" + lastLmoSuppliedQuantity + ", " : "") +
            (remark != null ? "remark=" + remark + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField3 != null ? "freeField3=" + freeField3 + ", " : "") +
            (freeField4 != null ? "freeField4=" + freeField4 + ", " : "") +
            (auditId != null ? "auditId=" + auditId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
