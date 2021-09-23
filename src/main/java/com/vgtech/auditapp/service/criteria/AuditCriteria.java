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
 * Criteria class for the {@link com.vgtech.auditapp.domain.Audit} entity. This class is used
 * in {@link com.vgtech.auditapp.web.rest.AuditResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /audits?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AuditCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private LocalDateFilter auditDate;

  private StringFilter hospName;

  private BooleanFilter isAuditComplete;

  private StringFilter freeField1;

  private StringFilter freeField2;

  private StringFilter freeField3;

  private StringFilter freeField4;

  private StringFilter remark;

  private StringFilter createdBy;

  private LocalDateFilter createdDate;

  private LocalDateFilter lastModified;

  private StringFilter lastModifiedBy;

  private LongFilter auditPatientMonitoringFormId;

  private LongFilter auditFormSHospGenInfoId;

  private LongFilter fireElectricalAuditId;

  private LongFilter annexureAnswersId;

  private LongFilter invetoryReportId;

  private LongFilter inventorySupplierDetailsId;

  private LongFilter oxygenConsumptionDataId;

  private Boolean distinct;

  public AuditCriteria() {}

  public AuditCriteria(AuditCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.auditDate = other.auditDate == null ? null : other.auditDate.copy();
    this.hospName = other.hospName == null ? null : other.hospName.copy();
    this.isAuditComplete =
      other.isAuditComplete == null ? null : other.isAuditComplete.copy();
    this.freeField1 = other.freeField1 == null ? null : other.freeField1.copy();
    this.freeField2 = other.freeField2 == null ? null : other.freeField2.copy();
    this.freeField3 = other.freeField3 == null ? null : other.freeField3.copy();
    this.freeField4 = other.freeField4 == null ? null : other.freeField4.copy();
    this.remark = other.remark == null ? null : other.remark.copy();
    this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
    this.createdDate =
      other.createdDate == null ? null : other.createdDate.copy();
    this.lastModified =
      other.lastModified == null ? null : other.lastModified.copy();
    this.lastModifiedBy =
      other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
    this.auditPatientMonitoringFormId =
      other.auditPatientMonitoringFormId == null
        ? null
        : other.auditPatientMonitoringFormId.copy();
    this.auditFormSHospGenInfoId =
      other.auditFormSHospGenInfoId == null
        ? null
        : other.auditFormSHospGenInfoId.copy();
    this.fireElectricalAuditId =
      other.fireElectricalAuditId == null
        ? null
        : other.fireElectricalAuditId.copy();
    this.annexureAnswersId =
      other.annexureAnswersId == null ? null : other.annexureAnswersId.copy();
    this.invetoryReportId =
      other.invetoryReportId == null ? null : other.invetoryReportId.copy();
    this.inventorySupplierDetailsId =
      other.inventorySupplierDetailsId == null
        ? null
        : other.inventorySupplierDetailsId.copy();
    this.oxygenConsumptionDataId =
      other.oxygenConsumptionDataId == null
        ? null
        : other.oxygenConsumptionDataId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public AuditCriteria copy() {
    return new AuditCriteria(this);
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

  public LocalDateFilter getAuditDate() {
    return auditDate;
  }

  public LocalDateFilter auditDate() {
    if (auditDate == null) {
      auditDate = new LocalDateFilter();
    }
    return auditDate;
  }

  public void setAuditDate(LocalDateFilter auditDate) {
    this.auditDate = auditDate;
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

  public BooleanFilter getIsAuditComplete() {
    return isAuditComplete;
  }

  public BooleanFilter isAuditComplete() {
    if (isAuditComplete == null) {
      isAuditComplete = new BooleanFilter();
    }
    return isAuditComplete;
  }

  public void setIsAuditComplete(BooleanFilter isAuditComplete) {
    this.isAuditComplete = isAuditComplete;
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

  public LongFilter getAuditPatientMonitoringFormId() {
    return auditPatientMonitoringFormId;
  }

  public LongFilter auditPatientMonitoringFormId() {
    if (auditPatientMonitoringFormId == null) {
      auditPatientMonitoringFormId = new LongFilter();
    }
    return auditPatientMonitoringFormId;
  }

  public void setAuditPatientMonitoringFormId(
    LongFilter auditPatientMonitoringFormId
  ) {
    this.auditPatientMonitoringFormId = auditPatientMonitoringFormId;
  }

  public LongFilter getAuditFormSHospGenInfoId() {
    return auditFormSHospGenInfoId;
  }

  public LongFilter auditFormSHospGenInfoId() {
    if (auditFormSHospGenInfoId == null) {
      auditFormSHospGenInfoId = new LongFilter();
    }
    return auditFormSHospGenInfoId;
  }

  public void setAuditFormSHospGenInfoId(LongFilter auditFormSHospGenInfoId) {
    this.auditFormSHospGenInfoId = auditFormSHospGenInfoId;
  }

  public LongFilter getFireElectricalAuditId() {
    return fireElectricalAuditId;
  }

  public LongFilter fireElectricalAuditId() {
    if (fireElectricalAuditId == null) {
      fireElectricalAuditId = new LongFilter();
    }
    return fireElectricalAuditId;
  }

  public void setFireElectricalAuditId(LongFilter fireElectricalAuditId) {
    this.fireElectricalAuditId = fireElectricalAuditId;
  }

  public LongFilter getAnnexureAnswersId() {
    return annexureAnswersId;
  }

  public LongFilter annexureAnswersId() {
    if (annexureAnswersId == null) {
      annexureAnswersId = new LongFilter();
    }
    return annexureAnswersId;
  }

  public void setAnnexureAnswersId(LongFilter annexureAnswersId) {
    this.annexureAnswersId = annexureAnswersId;
  }

  public LongFilter getInvetoryReportId() {
    return invetoryReportId;
  }

  public LongFilter invetoryReportId() {
    if (invetoryReportId == null) {
      invetoryReportId = new LongFilter();
    }
    return invetoryReportId;
  }

  public void setInvetoryReportId(LongFilter invetoryReportId) {
    this.invetoryReportId = invetoryReportId;
  }

  public LongFilter getInventorySupplierDetailsId() {
    return inventorySupplierDetailsId;
  }

  public LongFilter inventorySupplierDetailsId() {
    if (inventorySupplierDetailsId == null) {
      inventorySupplierDetailsId = new LongFilter();
    }
    return inventorySupplierDetailsId;
  }

  public void setInventorySupplierDetailsId(
    LongFilter inventorySupplierDetailsId
  ) {
    this.inventorySupplierDetailsId = inventorySupplierDetailsId;
  }

  public LongFilter getOxygenConsumptionDataId() {
    return oxygenConsumptionDataId;
  }

  public LongFilter oxygenConsumptionDataId() {
    if (oxygenConsumptionDataId == null) {
      oxygenConsumptionDataId = new LongFilter();
    }
    return oxygenConsumptionDataId;
  }

  public void setOxygenConsumptionDataId(LongFilter oxygenConsumptionDataId) {
    this.oxygenConsumptionDataId = oxygenConsumptionDataId;
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
    final AuditCriteria that = (AuditCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(auditDate, that.auditDate) &&
      Objects.equals(hospName, that.hospName) &&
      Objects.equals(isAuditComplete, that.isAuditComplete) &&
      Objects.equals(freeField1, that.freeField1) &&
      Objects.equals(freeField2, that.freeField2) &&
      Objects.equals(freeField3, that.freeField3) &&
      Objects.equals(freeField4, that.freeField4) &&
      Objects.equals(remark, that.remark) &&
      Objects.equals(createdBy, that.createdBy) &&
      Objects.equals(createdDate, that.createdDate) &&
      Objects.equals(lastModified, that.lastModified) &&
      Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
      Objects.equals(
        auditPatientMonitoringFormId,
        that.auditPatientMonitoringFormId
      ) &&
      Objects.equals(auditFormSHospGenInfoId, that.auditFormSHospGenInfoId) &&
      Objects.equals(fireElectricalAuditId, that.fireElectricalAuditId) &&
      Objects.equals(annexureAnswersId, that.annexureAnswersId) &&
      Objects.equals(invetoryReportId, that.invetoryReportId) &&
      Objects.equals(
        inventorySupplierDetailsId,
        that.inventorySupplierDetailsId
      ) &&
      Objects.equals(oxygenConsumptionDataId, that.oxygenConsumptionDataId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      auditDate,
      hospName,
      isAuditComplete,
      freeField1,
      freeField2,
      freeField3,
      freeField4,
      remark,
      createdBy,
      createdDate,
      lastModified,
      lastModifiedBy,
      auditPatientMonitoringFormId,
      auditFormSHospGenInfoId,
      fireElectricalAuditId,
      annexureAnswersId,
      invetoryReportId,
      inventorySupplierDetailsId,
      oxygenConsumptionDataId,
      distinct
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AuditCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (auditDate != null ? "auditDate=" + auditDate + ", " : "") +
            (hospName != null ? "hospName=" + hospName + ", " : "") +
            (isAuditComplete != null ? "isAuditComplete=" + isAuditComplete + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField3 != null ? "freeField3=" + freeField3 + ", " : "") +
            (freeField4 != null ? "freeField4=" + freeField4 + ", " : "") +
            (remark != null ? "remark=" + remark + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (auditPatientMonitoringFormId != null ? "auditPatientMonitoringFormId=" + auditPatientMonitoringFormId + ", " : "") +
            (auditFormSHospGenInfoId != null ? "auditFormSHospGenInfoId=" + auditFormSHospGenInfoId + ", " : "") +
            (fireElectricalAuditId != null ? "fireElectricalAuditId=" + fireElectricalAuditId + ", " : "") +
            (annexureAnswersId != null ? "annexureAnswersId=" + annexureAnswersId + ", " : "") +
            (invetoryReportId != null ? "invetoryReportId=" + invetoryReportId + ", " : "") +
            (inventorySupplierDetailsId != null ? "inventorySupplierDetailsId=" + inventorySupplierDetailsId + ", " : "") +
            (oxygenConsumptionDataId != null ? "oxygenConsumptionDataId=" + oxygenConsumptionDataId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
