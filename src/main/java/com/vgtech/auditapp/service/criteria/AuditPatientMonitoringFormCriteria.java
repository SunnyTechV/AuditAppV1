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
 * Criteria class for the {@link com.vgtech.auditapp.domain.AuditPatientMonitoringForm} entity. This class is used
 * in {@link com.vgtech.auditapp.web.rest.AuditPatientMonitoringFormResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /audit-patient-monitoring-forms?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AuditPatientMonitoringFormCriteria
  implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private IntegerFilter wardNo;

  private StringFilter patientName;

  private IntegerFilter bedNo;

  private LocalDateFilter dateOfAdmission;

  private StringFilter oxygenType;

  private DoubleFilter sixToEightAM;

  private DoubleFilter oxySixToEightAM;

  private DoubleFilter eightToTenAM;

  private DoubleFilter oxyEightToTenAM;

  private DoubleFilter tenToTwelveAM;

  private DoubleFilter oxyTenToTwelveAM;

  private DoubleFilter twelveToTowPM;

  private DoubleFilter oxyTwelveToTowPM;

  private DoubleFilter twoToFourPM;

  private DoubleFilter oxyTwoToFourPM;

  private DoubleFilter fourToSixPM;

  private DoubleFilter oxyFourToSixPM;

  private DoubleFilter sixToEightPM;

  private DoubleFilter oxySixToEightPM;

  private DoubleFilter eightToTenPM;

  private DoubleFilter oxyEightToTenPM;

  private DoubleFilter tenToTwelvePM;

  private DoubleFilter oxyTenToTwelvePM;

  private DoubleFilter twelveToTwoAM;

  private DoubleFilter oxyTwelveToTwoAM;

  private DoubleFilter twoToFourAM;

  private DoubleFilter oxyTwoToFourAM;

  private DoubleFilter fourToSixAM;

  private DoubleFilter oxyFourToSixAM;

  private StringFilter drName;

  private StringFilter nurseName;

  private StringFilter createdBy;

  private LocalDateFilter createdDate;

  private StringFilter lastModifiedBy;

  private LocalDateFilter lastModified;

  private StringFilter freeField1;

  private StringFilter freeField2;

  private StringFilter freeField3;

  private StringFilter freeField4;

  private LongFilter auditId;

  private Boolean distinct;

  public AuditPatientMonitoringFormCriteria() {}

  public AuditPatientMonitoringFormCriteria(
    AuditPatientMonitoringFormCriteria other
  ) {
    this.id = other.id == null ? null : other.id.copy();
    this.wardNo = other.wardNo == null ? null : other.wardNo.copy();
    this.patientName =
      other.patientName == null ? null : other.patientName.copy();
    this.bedNo = other.bedNo == null ? null : other.bedNo.copy();
    this.dateOfAdmission =
      other.dateOfAdmission == null ? null : other.dateOfAdmission.copy();
    this.oxygenType = other.oxygenType == null ? null : other.oxygenType.copy();
    this.sixToEightAM =
      other.sixToEightAM == null ? null : other.sixToEightAM.copy();
    this.oxySixToEightAM =
      other.oxySixToEightAM == null ? null : other.oxySixToEightAM.copy();
    this.eightToTenAM =
      other.eightToTenAM == null ? null : other.eightToTenAM.copy();
    this.oxyEightToTenAM =
      other.oxyEightToTenAM == null ? null : other.oxyEightToTenAM.copy();
    this.tenToTwelveAM =
      other.tenToTwelveAM == null ? null : other.tenToTwelveAM.copy();
    this.oxyTenToTwelveAM =
      other.oxyTenToTwelveAM == null ? null : other.oxyTenToTwelveAM.copy();
    this.twelveToTowPM =
      other.twelveToTowPM == null ? null : other.twelveToTowPM.copy();
    this.oxyTwelveToTowPM =
      other.oxyTwelveToTowPM == null ? null : other.oxyTwelveToTowPM.copy();
    this.twoToFourPM =
      other.twoToFourPM == null ? null : other.twoToFourPM.copy();
    this.oxyTwoToFourPM =
      other.oxyTwoToFourPM == null ? null : other.oxyTwoToFourPM.copy();
    this.fourToSixPM =
      other.fourToSixPM == null ? null : other.fourToSixPM.copy();
    this.oxyFourToSixPM =
      other.oxyFourToSixPM == null ? null : other.oxyFourToSixPM.copy();
    this.sixToEightPM =
      other.sixToEightPM == null ? null : other.sixToEightPM.copy();
    this.oxySixToEightPM =
      other.oxySixToEightPM == null ? null : other.oxySixToEightPM.copy();
    this.eightToTenPM =
      other.eightToTenPM == null ? null : other.eightToTenPM.copy();
    this.oxyEightToTenPM =
      other.oxyEightToTenPM == null ? null : other.oxyEightToTenPM.copy();
    this.tenToTwelvePM =
      other.tenToTwelvePM == null ? null : other.tenToTwelvePM.copy();
    this.oxyTenToTwelvePM =
      other.oxyTenToTwelvePM == null ? null : other.oxyTenToTwelvePM.copy();
    this.twelveToTwoAM =
      other.twelveToTwoAM == null ? null : other.twelveToTwoAM.copy();
    this.oxyTwelveToTwoAM =
      other.oxyTwelveToTwoAM == null ? null : other.oxyTwelveToTwoAM.copy();
    this.twoToFourAM =
      other.twoToFourAM == null ? null : other.twoToFourAM.copy();
    this.oxyTwoToFourAM =
      other.oxyTwoToFourAM == null ? null : other.oxyTwoToFourAM.copy();
    this.fourToSixAM =
      other.fourToSixAM == null ? null : other.fourToSixAM.copy();
    this.oxyFourToSixAM =
      other.oxyFourToSixAM == null ? null : other.oxyFourToSixAM.copy();
    this.drName = other.drName == null ? null : other.drName.copy();
    this.nurseName = other.nurseName == null ? null : other.nurseName.copy();
    this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
    this.createdDate =
      other.createdDate == null ? null : other.createdDate.copy();
    this.lastModifiedBy =
      other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
    this.lastModified =
      other.lastModified == null ? null : other.lastModified.copy();
    this.freeField1 = other.freeField1 == null ? null : other.freeField1.copy();
    this.freeField2 = other.freeField2 == null ? null : other.freeField2.copy();
    this.freeField3 = other.freeField3 == null ? null : other.freeField3.copy();
    this.freeField4 = other.freeField4 == null ? null : other.freeField4.copy();
    this.auditId = other.auditId == null ? null : other.auditId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public AuditPatientMonitoringFormCriteria copy() {
    return new AuditPatientMonitoringFormCriteria(this);
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

  public IntegerFilter getWardNo() {
    return wardNo;
  }

  public IntegerFilter wardNo() {
    if (wardNo == null) {
      wardNo = new IntegerFilter();
    }
    return wardNo;
  }

  public void setWardNo(IntegerFilter wardNo) {
    this.wardNo = wardNo;
  }

  public StringFilter getPatientName() {
    return patientName;
  }

  public StringFilter patientName() {
    if (patientName == null) {
      patientName = new StringFilter();
    }
    return patientName;
  }

  public void setPatientName(StringFilter patientName) {
    this.patientName = patientName;
  }

  public IntegerFilter getBedNo() {
    return bedNo;
  }

  public IntegerFilter bedNo() {
    if (bedNo == null) {
      bedNo = new IntegerFilter();
    }
    return bedNo;
  }

  public void setBedNo(IntegerFilter bedNo) {
    this.bedNo = bedNo;
  }

  public LocalDateFilter getDateOfAdmission() {
    return dateOfAdmission;
  }

  public LocalDateFilter dateOfAdmission() {
    if (dateOfAdmission == null) {
      dateOfAdmission = new LocalDateFilter();
    }
    return dateOfAdmission;
  }

  public void setDateOfAdmission(LocalDateFilter dateOfAdmission) {
    this.dateOfAdmission = dateOfAdmission;
  }

  public StringFilter getOxygenType() {
    return oxygenType;
  }

  public StringFilter oxygenType() {
    if (oxygenType == null) {
      oxygenType = new StringFilter();
    }
    return oxygenType;
  }

  public void setOxygenType(StringFilter oxygenType) {
    this.oxygenType = oxygenType;
  }

  public DoubleFilter getSixToEightAM() {
    return sixToEightAM;
  }

  public DoubleFilter sixToEightAM() {
    if (sixToEightAM == null) {
      sixToEightAM = new DoubleFilter();
    }
    return sixToEightAM;
  }

  public void setSixToEightAM(DoubleFilter sixToEightAM) {
    this.sixToEightAM = sixToEightAM;
  }

  public DoubleFilter getOxySixToEightAM() {
    return oxySixToEightAM;
  }

  public DoubleFilter oxySixToEightAM() {
    if (oxySixToEightAM == null) {
      oxySixToEightAM = new DoubleFilter();
    }
    return oxySixToEightAM;
  }

  public void setOxySixToEightAM(DoubleFilter oxySixToEightAM) {
    this.oxySixToEightAM = oxySixToEightAM;
  }

  public DoubleFilter getEightToTenAM() {
    return eightToTenAM;
  }

  public DoubleFilter eightToTenAM() {
    if (eightToTenAM == null) {
      eightToTenAM = new DoubleFilter();
    }
    return eightToTenAM;
  }

  public void setEightToTenAM(DoubleFilter eightToTenAM) {
    this.eightToTenAM = eightToTenAM;
  }

  public DoubleFilter getOxyEightToTenAM() {
    return oxyEightToTenAM;
  }

  public DoubleFilter oxyEightToTenAM() {
    if (oxyEightToTenAM == null) {
      oxyEightToTenAM = new DoubleFilter();
    }
    return oxyEightToTenAM;
  }

  public void setOxyEightToTenAM(DoubleFilter oxyEightToTenAM) {
    this.oxyEightToTenAM = oxyEightToTenAM;
  }

  public DoubleFilter getTenToTwelveAM() {
    return tenToTwelveAM;
  }

  public DoubleFilter tenToTwelveAM() {
    if (tenToTwelveAM == null) {
      tenToTwelveAM = new DoubleFilter();
    }
    return tenToTwelveAM;
  }

  public void setTenToTwelveAM(DoubleFilter tenToTwelveAM) {
    this.tenToTwelveAM = tenToTwelveAM;
  }

  public DoubleFilter getOxyTenToTwelveAM() {
    return oxyTenToTwelveAM;
  }

  public DoubleFilter oxyTenToTwelveAM() {
    if (oxyTenToTwelveAM == null) {
      oxyTenToTwelveAM = new DoubleFilter();
    }
    return oxyTenToTwelveAM;
  }

  public void setOxyTenToTwelveAM(DoubleFilter oxyTenToTwelveAM) {
    this.oxyTenToTwelveAM = oxyTenToTwelveAM;
  }

  public DoubleFilter getTwelveToTowPM() {
    return twelveToTowPM;
  }

  public DoubleFilter twelveToTowPM() {
    if (twelveToTowPM == null) {
      twelveToTowPM = new DoubleFilter();
    }
    return twelveToTowPM;
  }

  public void setTwelveToTowPM(DoubleFilter twelveToTowPM) {
    this.twelveToTowPM = twelveToTowPM;
  }

  public DoubleFilter getOxyTwelveToTowPM() {
    return oxyTwelveToTowPM;
  }

  public DoubleFilter oxyTwelveToTowPM() {
    if (oxyTwelveToTowPM == null) {
      oxyTwelveToTowPM = new DoubleFilter();
    }
    return oxyTwelveToTowPM;
  }

  public void setOxyTwelveToTowPM(DoubleFilter oxyTwelveToTowPM) {
    this.oxyTwelveToTowPM = oxyTwelveToTowPM;
  }

  public DoubleFilter getTwoToFourPM() {
    return twoToFourPM;
  }

  public DoubleFilter twoToFourPM() {
    if (twoToFourPM == null) {
      twoToFourPM = new DoubleFilter();
    }
    return twoToFourPM;
  }

  public void setTwoToFourPM(DoubleFilter twoToFourPM) {
    this.twoToFourPM = twoToFourPM;
  }

  public DoubleFilter getOxyTwoToFourPM() {
    return oxyTwoToFourPM;
  }

  public DoubleFilter oxyTwoToFourPM() {
    if (oxyTwoToFourPM == null) {
      oxyTwoToFourPM = new DoubleFilter();
    }
    return oxyTwoToFourPM;
  }

  public void setOxyTwoToFourPM(DoubleFilter oxyTwoToFourPM) {
    this.oxyTwoToFourPM = oxyTwoToFourPM;
  }

  public DoubleFilter getFourToSixPM() {
    return fourToSixPM;
  }

  public DoubleFilter fourToSixPM() {
    if (fourToSixPM == null) {
      fourToSixPM = new DoubleFilter();
    }
    return fourToSixPM;
  }

  public void setFourToSixPM(DoubleFilter fourToSixPM) {
    this.fourToSixPM = fourToSixPM;
  }

  public DoubleFilter getOxyFourToSixPM() {
    return oxyFourToSixPM;
  }

  public DoubleFilter oxyFourToSixPM() {
    if (oxyFourToSixPM == null) {
      oxyFourToSixPM = new DoubleFilter();
    }
    return oxyFourToSixPM;
  }

  public void setOxyFourToSixPM(DoubleFilter oxyFourToSixPM) {
    this.oxyFourToSixPM = oxyFourToSixPM;
  }

  public DoubleFilter getSixToEightPM() {
    return sixToEightPM;
  }

  public DoubleFilter sixToEightPM() {
    if (sixToEightPM == null) {
      sixToEightPM = new DoubleFilter();
    }
    return sixToEightPM;
  }

  public void setSixToEightPM(DoubleFilter sixToEightPM) {
    this.sixToEightPM = sixToEightPM;
  }

  public DoubleFilter getOxySixToEightPM() {
    return oxySixToEightPM;
  }

  public DoubleFilter oxySixToEightPM() {
    if (oxySixToEightPM == null) {
      oxySixToEightPM = new DoubleFilter();
    }
    return oxySixToEightPM;
  }

  public void setOxySixToEightPM(DoubleFilter oxySixToEightPM) {
    this.oxySixToEightPM = oxySixToEightPM;
  }

  public DoubleFilter getEightToTenPM() {
    return eightToTenPM;
  }

  public DoubleFilter eightToTenPM() {
    if (eightToTenPM == null) {
      eightToTenPM = new DoubleFilter();
    }
    return eightToTenPM;
  }

  public void setEightToTenPM(DoubleFilter eightToTenPM) {
    this.eightToTenPM = eightToTenPM;
  }

  public DoubleFilter getOxyEightToTenPM() {
    return oxyEightToTenPM;
  }

  public DoubleFilter oxyEightToTenPM() {
    if (oxyEightToTenPM == null) {
      oxyEightToTenPM = new DoubleFilter();
    }
    return oxyEightToTenPM;
  }

  public void setOxyEightToTenPM(DoubleFilter oxyEightToTenPM) {
    this.oxyEightToTenPM = oxyEightToTenPM;
  }

  public DoubleFilter getTenToTwelvePM() {
    return tenToTwelvePM;
  }

  public DoubleFilter tenToTwelvePM() {
    if (tenToTwelvePM == null) {
      tenToTwelvePM = new DoubleFilter();
    }
    return tenToTwelvePM;
  }

  public void setTenToTwelvePM(DoubleFilter tenToTwelvePM) {
    this.tenToTwelvePM = tenToTwelvePM;
  }

  public DoubleFilter getOxyTenToTwelvePM() {
    return oxyTenToTwelvePM;
  }

  public DoubleFilter oxyTenToTwelvePM() {
    if (oxyTenToTwelvePM == null) {
      oxyTenToTwelvePM = new DoubleFilter();
    }
    return oxyTenToTwelvePM;
  }

  public void setOxyTenToTwelvePM(DoubleFilter oxyTenToTwelvePM) {
    this.oxyTenToTwelvePM = oxyTenToTwelvePM;
  }

  public DoubleFilter getTwelveToTwoAM() {
    return twelveToTwoAM;
  }

  public DoubleFilter twelveToTwoAM() {
    if (twelveToTwoAM == null) {
      twelveToTwoAM = new DoubleFilter();
    }
    return twelveToTwoAM;
  }

  public void setTwelveToTwoAM(DoubleFilter twelveToTwoAM) {
    this.twelveToTwoAM = twelveToTwoAM;
  }

  public DoubleFilter getOxyTwelveToTwoAM() {
    return oxyTwelveToTwoAM;
  }

  public DoubleFilter oxyTwelveToTwoAM() {
    if (oxyTwelveToTwoAM == null) {
      oxyTwelveToTwoAM = new DoubleFilter();
    }
    return oxyTwelveToTwoAM;
  }

  public void setOxyTwelveToTwoAM(DoubleFilter oxyTwelveToTwoAM) {
    this.oxyTwelveToTwoAM = oxyTwelveToTwoAM;
  }

  public DoubleFilter getTwoToFourAM() {
    return twoToFourAM;
  }

  public DoubleFilter twoToFourAM() {
    if (twoToFourAM == null) {
      twoToFourAM = new DoubleFilter();
    }
    return twoToFourAM;
  }

  public void setTwoToFourAM(DoubleFilter twoToFourAM) {
    this.twoToFourAM = twoToFourAM;
  }

  public DoubleFilter getOxyTwoToFourAM() {
    return oxyTwoToFourAM;
  }

  public DoubleFilter oxyTwoToFourAM() {
    if (oxyTwoToFourAM == null) {
      oxyTwoToFourAM = new DoubleFilter();
    }
    return oxyTwoToFourAM;
  }

  public void setOxyTwoToFourAM(DoubleFilter oxyTwoToFourAM) {
    this.oxyTwoToFourAM = oxyTwoToFourAM;
  }

  public DoubleFilter getFourToSixAM() {
    return fourToSixAM;
  }

  public DoubleFilter fourToSixAM() {
    if (fourToSixAM == null) {
      fourToSixAM = new DoubleFilter();
    }
    return fourToSixAM;
  }

  public void setFourToSixAM(DoubleFilter fourToSixAM) {
    this.fourToSixAM = fourToSixAM;
  }

  public DoubleFilter getOxyFourToSixAM() {
    return oxyFourToSixAM;
  }

  public DoubleFilter oxyFourToSixAM() {
    if (oxyFourToSixAM == null) {
      oxyFourToSixAM = new DoubleFilter();
    }
    return oxyFourToSixAM;
  }

  public void setOxyFourToSixAM(DoubleFilter oxyFourToSixAM) {
    this.oxyFourToSixAM = oxyFourToSixAM;
  }

  public StringFilter getDrName() {
    return drName;
  }

  public StringFilter drName() {
    if (drName == null) {
      drName = new StringFilter();
    }
    return drName;
  }

  public void setDrName(StringFilter drName) {
    this.drName = drName;
  }

  public StringFilter getNurseName() {
    return nurseName;
  }

  public StringFilter nurseName() {
    if (nurseName == null) {
      nurseName = new StringFilter();
    }
    return nurseName;
  }

  public void setNurseName(StringFilter nurseName) {
    this.nurseName = nurseName;
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
    final AuditPatientMonitoringFormCriteria that = (AuditPatientMonitoringFormCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(wardNo, that.wardNo) &&
      Objects.equals(patientName, that.patientName) &&
      Objects.equals(bedNo, that.bedNo) &&
      Objects.equals(dateOfAdmission, that.dateOfAdmission) &&
      Objects.equals(oxygenType, that.oxygenType) &&
      Objects.equals(sixToEightAM, that.sixToEightAM) &&
      Objects.equals(oxySixToEightAM, that.oxySixToEightAM) &&
      Objects.equals(eightToTenAM, that.eightToTenAM) &&
      Objects.equals(oxyEightToTenAM, that.oxyEightToTenAM) &&
      Objects.equals(tenToTwelveAM, that.tenToTwelveAM) &&
      Objects.equals(oxyTenToTwelveAM, that.oxyTenToTwelveAM) &&
      Objects.equals(twelveToTowPM, that.twelveToTowPM) &&
      Objects.equals(oxyTwelveToTowPM, that.oxyTwelveToTowPM) &&
      Objects.equals(twoToFourPM, that.twoToFourPM) &&
      Objects.equals(oxyTwoToFourPM, that.oxyTwoToFourPM) &&
      Objects.equals(fourToSixPM, that.fourToSixPM) &&
      Objects.equals(oxyFourToSixPM, that.oxyFourToSixPM) &&
      Objects.equals(sixToEightPM, that.sixToEightPM) &&
      Objects.equals(oxySixToEightPM, that.oxySixToEightPM) &&
      Objects.equals(eightToTenPM, that.eightToTenPM) &&
      Objects.equals(oxyEightToTenPM, that.oxyEightToTenPM) &&
      Objects.equals(tenToTwelvePM, that.tenToTwelvePM) &&
      Objects.equals(oxyTenToTwelvePM, that.oxyTenToTwelvePM) &&
      Objects.equals(twelveToTwoAM, that.twelveToTwoAM) &&
      Objects.equals(oxyTwelveToTwoAM, that.oxyTwelveToTwoAM) &&
      Objects.equals(twoToFourAM, that.twoToFourAM) &&
      Objects.equals(oxyTwoToFourAM, that.oxyTwoToFourAM) &&
      Objects.equals(fourToSixAM, that.fourToSixAM) &&
      Objects.equals(oxyFourToSixAM, that.oxyFourToSixAM) &&
      Objects.equals(drName, that.drName) &&
      Objects.equals(nurseName, that.nurseName) &&
      Objects.equals(createdBy, that.createdBy) &&
      Objects.equals(createdDate, that.createdDate) &&
      Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
      Objects.equals(lastModified, that.lastModified) &&
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
      wardNo,
      patientName,
      bedNo,
      dateOfAdmission,
      oxygenType,
      sixToEightAM,
      oxySixToEightAM,
      eightToTenAM,
      oxyEightToTenAM,
      tenToTwelveAM,
      oxyTenToTwelveAM,
      twelveToTowPM,
      oxyTwelveToTowPM,
      twoToFourPM,
      oxyTwoToFourPM,
      fourToSixPM,
      oxyFourToSixPM,
      sixToEightPM,
      oxySixToEightPM,
      eightToTenPM,
      oxyEightToTenPM,
      tenToTwelvePM,
      oxyTenToTwelvePM,
      twelveToTwoAM,
      oxyTwelveToTwoAM,
      twoToFourAM,
      oxyTwoToFourAM,
      fourToSixAM,
      oxyFourToSixAM,
      drName,
      nurseName,
      createdBy,
      createdDate,
      lastModifiedBy,
      lastModified,
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
        return "AuditPatientMonitoringFormCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (wardNo != null ? "wardNo=" + wardNo + ", " : "") +
            (patientName != null ? "patientName=" + patientName + ", " : "") +
            (bedNo != null ? "bedNo=" + bedNo + ", " : "") +
            (dateOfAdmission != null ? "dateOfAdmission=" + dateOfAdmission + ", " : "") +
            (oxygenType != null ? "oxygenType=" + oxygenType + ", " : "") +
            (sixToEightAM != null ? "sixToEightAM=" + sixToEightAM + ", " : "") +
            (oxySixToEightAM != null ? "oxySixToEightAM=" + oxySixToEightAM + ", " : "") +
            (eightToTenAM != null ? "eightToTenAM=" + eightToTenAM + ", " : "") +
            (oxyEightToTenAM != null ? "oxyEightToTenAM=" + oxyEightToTenAM + ", " : "") +
            (tenToTwelveAM != null ? "tenToTwelveAM=" + tenToTwelveAM + ", " : "") +
            (oxyTenToTwelveAM != null ? "oxyTenToTwelveAM=" + oxyTenToTwelveAM + ", " : "") +
            (twelveToTowPM != null ? "twelveToTowPM=" + twelveToTowPM + ", " : "") +
            (oxyTwelveToTowPM != null ? "oxyTwelveToTowPM=" + oxyTwelveToTowPM + ", " : "") +
            (twoToFourPM != null ? "twoToFourPM=" + twoToFourPM + ", " : "") +
            (oxyTwoToFourPM != null ? "oxyTwoToFourPM=" + oxyTwoToFourPM + ", " : "") +
            (fourToSixPM != null ? "fourToSixPM=" + fourToSixPM + ", " : "") +
            (oxyFourToSixPM != null ? "oxyFourToSixPM=" + oxyFourToSixPM + ", " : "") +
            (sixToEightPM != null ? "sixToEightPM=" + sixToEightPM + ", " : "") +
            (oxySixToEightPM != null ? "oxySixToEightPM=" + oxySixToEightPM + ", " : "") +
            (eightToTenPM != null ? "eightToTenPM=" + eightToTenPM + ", " : "") +
            (oxyEightToTenPM != null ? "oxyEightToTenPM=" + oxyEightToTenPM + ", " : "") +
            (tenToTwelvePM != null ? "tenToTwelvePM=" + tenToTwelvePM + ", " : "") +
            (oxyTenToTwelvePM != null ? "oxyTenToTwelvePM=" + oxyTenToTwelvePM + ", " : "") +
            (twelveToTwoAM != null ? "twelveToTwoAM=" + twelveToTwoAM + ", " : "") +
            (oxyTwelveToTwoAM != null ? "oxyTwelveToTwoAM=" + oxyTwelveToTwoAM + ", " : "") +
            (twoToFourAM != null ? "twoToFourAM=" + twoToFourAM + ", " : "") +
            (oxyTwoToFourAM != null ? "oxyTwoToFourAM=" + oxyTwoToFourAM + ", " : "") +
            (fourToSixAM != null ? "fourToSixAM=" + fourToSixAM + ", " : "") +
            (oxyFourToSixAM != null ? "oxyFourToSixAM=" + oxyFourToSixAM + ", " : "") +
            (drName != null ? "drName=" + drName + ", " : "") +
            (nurseName != null ? "nurseName=" + nurseName + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModified != null ? "lastModified=" + lastModified + ", " : "") +
            (freeField1 != null ? "freeField1=" + freeField1 + ", " : "") +
            (freeField2 != null ? "freeField2=" + freeField2 + ", " : "") +
            (freeField3 != null ? "freeField3=" + freeField3 + ", " : "") +
            (freeField4 != null ? "freeField4=" + freeField4 + ", " : "") +
            (auditId != null ? "auditId=" + auditId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
