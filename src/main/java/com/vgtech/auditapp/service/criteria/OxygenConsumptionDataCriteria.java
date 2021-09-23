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
 * Criteria class for the {@link com.vgtech.auditapp.domain.OxygenConsumptionData} entity. This class is used
 * in {@link com.vgtech.auditapp.web.rest.OxygenConsumptionDataResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /oxygen-consumption-data?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OxygenConsumptionDataCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private StringFilter formName;

  private StringFilter type;

  private StringFilter subType;

  private StringFilter tableName;

  private IntegerFilter noofPatients;

  private DoubleFilter consumptionLitperMin;

  private DoubleFilter consumptionLitperDay;

  private DoubleFilter consumptionKLitperDay;

  private DoubleFilter consumptionTotal;

  private StringFilter freeField1;

  private StringFilter freeField2;

  private StringFilter freeField3;

  private LocalDateFilter createdDate;

  private StringFilter createdBy;

  private LocalDateFilter lastModified;

  private StringFilter lastModifiedBy;

  private LongFilter auditId;

  private LongFilter tableDetailsId;

  private Boolean distinct;

  public OxygenConsumptionDataCriteria() {}

  public OxygenConsumptionDataCriteria(OxygenConsumptionDataCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.formName = other.formName == null ? null : other.formName.copy();
    this.type = other.type == null ? null : other.type.copy();
    this.subType = other.subType == null ? null : other.subType.copy();
    this.tableName = other.tableName == null ? null : other.tableName.copy();
    this.noofPatients =
      other.noofPatients == null ? null : other.noofPatients.copy();
    this.consumptionLitperMin =
      other.consumptionLitperMin == null
        ? null
        : other.consumptionLitperMin.copy();
    this.consumptionLitperDay =
      other.consumptionLitperDay == null
        ? null
        : other.consumptionLitperDay.copy();
    this.consumptionKLitperDay =
      other.consumptionKLitperDay == null
        ? null
        : other.consumptionKLitperDay.copy();
    this.consumptionTotal =
      other.consumptionTotal == null ? null : other.consumptionTotal.copy();
    this.freeField1 = other.freeField1 == null ? null : other.freeField1.copy();
    this.freeField2 = other.freeField2 == null ? null : other.freeField2.copy();
    this.freeField3 = other.freeField3 == null ? null : other.freeField3.copy();
    this.createdDate =
      other.createdDate == null ? null : other.createdDate.copy();
    this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
    this.lastModified =
      other.lastModified == null ? null : other.lastModified.copy();
    this.lastModifiedBy =
      other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
    this.auditId = other.auditId == null ? null : other.auditId.copy();
    this.tableDetailsId =
      other.tableDetailsId == null ? null : other.tableDetailsId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public OxygenConsumptionDataCriteria copy() {
    return new OxygenConsumptionDataCriteria(this);
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

  public StringFilter getTableName() {
    return tableName;
  }

  public StringFilter tableName() {
    if (tableName == null) {
      tableName = new StringFilter();
    }
    return tableName;
  }

  public void setTableName(StringFilter tableName) {
    this.tableName = tableName;
  }

  public IntegerFilter getNoofPatients() {
    return noofPatients;
  }

  public IntegerFilter noofPatients() {
    if (noofPatients == null) {
      noofPatients = new IntegerFilter();
    }
    return noofPatients;
  }

  public void setNoofPatients(IntegerFilter noofPatients) {
    this.noofPatients = noofPatients;
  }

  public DoubleFilter getConsumptionLitperMin() {
    return consumptionLitperMin;
  }

  public DoubleFilter consumptionLitperMin() {
    if (consumptionLitperMin == null) {
      consumptionLitperMin = new DoubleFilter();
    }
    return consumptionLitperMin;
  }

  public void setConsumptionLitperMin(DoubleFilter consumptionLitperMin) {
    this.consumptionLitperMin = consumptionLitperMin;
  }

  public DoubleFilter getConsumptionLitperDay() {
    return consumptionLitperDay;
  }

  public DoubleFilter consumptionLitperDay() {
    if (consumptionLitperDay == null) {
      consumptionLitperDay = new DoubleFilter();
    }
    return consumptionLitperDay;
  }

  public void setConsumptionLitperDay(DoubleFilter consumptionLitperDay) {
    this.consumptionLitperDay = consumptionLitperDay;
  }

  public DoubleFilter getConsumptionKLitperDay() {
    return consumptionKLitperDay;
  }

  public DoubleFilter consumptionKLitperDay() {
    if (consumptionKLitperDay == null) {
      consumptionKLitperDay = new DoubleFilter();
    }
    return consumptionKLitperDay;
  }

  public void setConsumptionKLitperDay(DoubleFilter consumptionKLitperDay) {
    this.consumptionKLitperDay = consumptionKLitperDay;
  }

  public DoubleFilter getConsumptionTotal() {
    return consumptionTotal;
  }

  public DoubleFilter consumptionTotal() {
    if (consumptionTotal == null) {
      consumptionTotal = new DoubleFilter();
    }
    return consumptionTotal;
  }

  public void setConsumptionTotal(DoubleFilter consumptionTotal) {
    this.consumptionTotal = consumptionTotal;
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

  public LongFilter getTableDetailsId() {
    return tableDetailsId;
  }

  public LongFilter tableDetailsId() {
    if (tableDetailsId == null) {
      tableDetailsId = new LongFilter();
    }
    return tableDetailsId;
  }

  public void setTableDetailsId(LongFilter tableDetailsId) {
    this.tableDetailsId = tableDetailsId;
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
    final OxygenConsumptionDataCriteria that = (OxygenConsumptionDataCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(formName, that.formName) &&
      Objects.equals(type, that.type) &&
      Objects.equals(subType, that.subType) &&
      Objects.equals(tableName, that.tableName) &&
      Objects.equals(noofPatients, that.noofPatients) &&
      Objects.equals(consumptionLitperMin, that.consumptionLitperMin) &&
      Objects.equals(consumptionLitperDay, that.consumptionLitperDay) &&
      Objects.equals(consumptionKLitperDay, that.consumptionKLitperDay) &&
      Objects.equals(consumptionTotal, that.consumptionTotal) &&
      Objects.equals(freeField1, that.freeField1) &&
      Objects.equals(freeField2, that.freeField2) &&
      Objects.equals(freeField3, that.freeField3) &&
      Objects.equals(createdDate, that.createdDate) &&
      Objects.equals(createdBy, that.createdBy) &&
      Objects.equals(lastModified, that.lastModified) &&
      Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
      Objects.equals(auditId, that.auditId) &&
      Objects.equals(tableDetailsId, that.tableDetailsId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      formName,
      type,
      subType,
      tableName,
      noofPatients,
      consumptionLitperMin,
      consumptionLitperDay,
      consumptionKLitperDay,
      consumptionTotal,
      freeField1,
      freeField2,
      freeField3,
      createdDate,
      createdBy,
      lastModified,
      lastModifiedBy,
      auditId,
      tableDetailsId,
      distinct
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "OxygenConsumptionDataCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (formName != null ? "formName=" + formName + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (subType != null ? "subType=" + subType + ", " : "") +
            (tableName != null ? "tableName=" + tableName + ", " : "") +
            (noofPatients != null ? "noofPatients=" + noofPatients + ", " : "") +
            (consumptionLitperMin != null ? "consumptionLitperMin=" + consumptionLitperMin + ", " : "") +
            (consumptionLitperDay != null ? "consumptionLitperDay=" + consumptionLitperDay + ", " : "") +
            (consumptionKLitperDay != null ? "consumptionKLitperDay=" + consumptionKLitperDay + ", " : "") +
            (consumptionTotal != null ? "consumptionTotal=" + consumptionTotal + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField3 != null ? "freeField3=" + freeField3 + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (auditId != null ? "auditId=" + auditId + ", " : "") +
            (tableDetailsId != null ? "tableDetailsId=" + tableDetailsId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
