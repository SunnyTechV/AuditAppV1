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
 * Criteria class for the {@link com.vgtech.auditapp.domain.InventoryReport} entity. This class is used
 * in {@link com.vgtech.auditapp.web.rest.InventoryReportResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /inventory-reports?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class InventoryReportCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private StringFilter formName;

  private StringFilter type;

  private StringFilter subType;

  private StringFilter tableName;

  private StringFilter description;

  private StringFilter descriptionParameter;

  private StringFilter actualAvailable;

  private StringFilter remark;

  private StringFilter freeField1;

  private StringFilter freeField2;

  private StringFilter freeField3;

  private LocalDateFilter createdDate;

  private StringFilter createdBy;

  private LocalDateFilter lastModified;

  private StringFilter lastModifiedBy;

  private LongFilter auditId;

  private Boolean distinct;

  public InventoryReportCriteria() {}

  public InventoryReportCriteria(InventoryReportCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.formName = other.formName == null ? null : other.formName.copy();
    this.type = other.type == null ? null : other.type.copy();
    this.subType = other.subType == null ? null : other.subType.copy();
    this.tableName = other.tableName == null ? null : other.tableName.copy();
    this.description =
      other.description == null ? null : other.description.copy();
    this.descriptionParameter =
      other.descriptionParameter == null
        ? null
        : other.descriptionParameter.copy();
    this.actualAvailable =
      other.actualAvailable == null ? null : other.actualAvailable.copy();
    this.remark = other.remark == null ? null : other.remark.copy();
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
    this.distinct = other.distinct;
  }

  @Override
  public InventoryReportCriteria copy() {
    return new InventoryReportCriteria(this);
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

  public StringFilter getDescription() {
    return description;
  }

  public StringFilter description() {
    if (description == null) {
      description = new StringFilter();
    }
    return description;
  }

  public void setDescription(StringFilter description) {
    this.description = description;
  }

  public StringFilter getDescriptionParameter() {
    return descriptionParameter;
  }

  public StringFilter descriptionParameter() {
    if (descriptionParameter == null) {
      descriptionParameter = new StringFilter();
    }
    return descriptionParameter;
  }

  public void setDescriptionParameter(StringFilter descriptionParameter) {
    this.descriptionParameter = descriptionParameter;
  }

  public StringFilter getActualAvailable() {
    return actualAvailable;
  }

  public StringFilter actualAvailable() {
    if (actualAvailable == null) {
      actualAvailable = new StringFilter();
    }
    return actualAvailable;
  }

  public void setActualAvailable(StringFilter actualAvailable) {
    this.actualAvailable = actualAvailable;
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
    final InventoryReportCriteria that = (InventoryReportCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(formName, that.formName) &&
      Objects.equals(type, that.type) &&
      Objects.equals(subType, that.subType) &&
      Objects.equals(tableName, that.tableName) &&
      Objects.equals(description, that.description) &&
      Objects.equals(descriptionParameter, that.descriptionParameter) &&
      Objects.equals(actualAvailable, that.actualAvailable) &&
      Objects.equals(remark, that.remark) &&
      Objects.equals(freeField1, that.freeField1) &&
      Objects.equals(freeField2, that.freeField2) &&
      Objects.equals(freeField3, that.freeField3) &&
      Objects.equals(createdDate, that.createdDate) &&
      Objects.equals(createdBy, that.createdBy) &&
      Objects.equals(lastModified, that.lastModified) &&
      Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
      Objects.equals(auditId, that.auditId) &&
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
      description,
      descriptionParameter,
      actualAvailable,
      remark,
      freeField1,
      freeField2,
      freeField3,
      createdDate,
      createdBy,
      lastModified,
      lastModifiedBy,
      auditId,
      distinct
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "InventoryReportCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (formName != null ? "formName=" + formName + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (subType != null ? "subType=" + subType + ", " : "") +
            (tableName != null ? "tableName=" + tableName + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (descriptionParameter != null ? "descriptionParameter=" + descriptionParameter + ", " : "") +
            (actualAvailable != null ? "actualAvailable=" + actualAvailable + ", " : "") +
            (remark != null ? "remark=" + remark + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField3 != null ? "freeField3=" + freeField3 + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (auditId != null ? "auditId=" + auditId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
