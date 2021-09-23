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
 * Criteria class for the {@link com.vgtech.auditapp.domain.FireElectricalAudit} entity. This class is used
 * in {@link com.vgtech.auditapp.web.rest.FireElectricalAuditResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /fire-electrical-audits?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FireElectricalAuditCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private BooleanFilter fireAuditDoneOrnot;

  private LocalDateFilter fireAuditDate;

  private StringFilter fireFaults;

  private StringFilter fireCorrectiveAction;

  private StringFilter fireAuditPlan;

  private BooleanFilter electricalAuditDone;

  private LocalDateFilter electricalAuditDate;

  private StringFilter electricalFaults;

  private StringFilter electricalCorrectiveAction;

  private StringFilter electricalAuditInspection;

  private BooleanFilter technicalPersonAppoint;

  private StringFilter techPersonname;

  private StringFilter techPersonMobNo;

  private StringFilter technicalEngineerName;

  private StringFilter technicalEngineerAddress;

  private StringFilter technicalEngineerMob;

  private StringFilter technicalEngineerAlternateMob;

  private DoubleFilter o2HospRequirement;

  private DoubleFilter o2HospProjectedRequirement;

  private DoubleFilter saveO2RequirementPossibleMT;

  private BooleanFilter monitoringO2ValvesPort;

  private BooleanFilter portValvesShutDown;

  private BooleanFilter idPatientDrillDone;

  private BooleanFilter staffCheckingLeakage;

  private BooleanFilter patientO2ReqFinalized;

  private StringFilter timeByDoctor;

  private BooleanFilter isLightingInstalled;

  private StringFilter locLightningArrerstor;

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

  public FireElectricalAuditCriteria() {}

  public FireElectricalAuditCriteria(FireElectricalAuditCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.fireAuditDoneOrnot =
      other.fireAuditDoneOrnot == null ? null : other.fireAuditDoneOrnot.copy();
    this.fireAuditDate =
      other.fireAuditDate == null ? null : other.fireAuditDate.copy();
    this.fireFaults = other.fireFaults == null ? null : other.fireFaults.copy();
    this.fireCorrectiveAction =
      other.fireCorrectiveAction == null
        ? null
        : other.fireCorrectiveAction.copy();
    this.fireAuditPlan =
      other.fireAuditPlan == null ? null : other.fireAuditPlan.copy();
    this.electricalAuditDone =
      other.electricalAuditDone == null
        ? null
        : other.electricalAuditDone.copy();
    this.electricalAuditDate =
      other.electricalAuditDate == null
        ? null
        : other.electricalAuditDate.copy();
    this.electricalFaults =
      other.electricalFaults == null ? null : other.electricalFaults.copy();
    this.electricalCorrectiveAction =
      other.electricalCorrectiveAction == null
        ? null
        : other.electricalCorrectiveAction.copy();
    this.electricalAuditInspection =
      other.electricalAuditInspection == null
        ? null
        : other.electricalAuditInspection.copy();
    this.technicalPersonAppoint =
      other.technicalPersonAppoint == null
        ? null
        : other.technicalPersonAppoint.copy();
    this.techPersonname =
      other.techPersonname == null ? null : other.techPersonname.copy();
    this.techPersonMobNo =
      other.techPersonMobNo == null ? null : other.techPersonMobNo.copy();
    this.technicalEngineerName =
      other.technicalEngineerName == null
        ? null
        : other.technicalEngineerName.copy();
    this.technicalEngineerAddress =
      other.technicalEngineerAddress == null
        ? null
        : other.technicalEngineerAddress.copy();
    this.technicalEngineerMob =
      other.technicalEngineerMob == null
        ? null
        : other.technicalEngineerMob.copy();
    this.technicalEngineerAlternateMob =
      other.technicalEngineerAlternateMob == null
        ? null
        : other.technicalEngineerAlternateMob.copy();
    this.o2HospRequirement =
      other.o2HospRequirement == null ? null : other.o2HospRequirement.copy();
    this.o2HospProjectedRequirement =
      other.o2HospProjectedRequirement == null
        ? null
        : other.o2HospProjectedRequirement.copy();
    this.saveO2RequirementPossibleMT =
      other.saveO2RequirementPossibleMT == null
        ? null
        : other.saveO2RequirementPossibleMT.copy();
    this.monitoringO2ValvesPort =
      other.monitoringO2ValvesPort == null
        ? null
        : other.monitoringO2ValvesPort.copy();
    this.portValvesShutDown =
      other.portValvesShutDown == null ? null : other.portValvesShutDown.copy();
    this.idPatientDrillDone =
      other.idPatientDrillDone == null ? null : other.idPatientDrillDone.copy();
    this.staffCheckingLeakage =
      other.staffCheckingLeakage == null
        ? null
        : other.staffCheckingLeakage.copy();
    this.patientO2ReqFinalized =
      other.patientO2ReqFinalized == null
        ? null
        : other.patientO2ReqFinalized.copy();
    this.timeByDoctor =
      other.timeByDoctor == null ? null : other.timeByDoctor.copy();
    this.isLightingInstalled =
      other.isLightingInstalled == null
        ? null
        : other.isLightingInstalled.copy();
    this.locLightningArrerstor =
      other.locLightningArrerstor == null
        ? null
        : other.locLightningArrerstor.copy();
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
  public FireElectricalAuditCriteria copy() {
    return new FireElectricalAuditCriteria(this);
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

  public BooleanFilter getFireAuditDoneOrnot() {
    return fireAuditDoneOrnot;
  }

  public BooleanFilter fireAuditDoneOrnot() {
    if (fireAuditDoneOrnot == null) {
      fireAuditDoneOrnot = new BooleanFilter();
    }
    return fireAuditDoneOrnot;
  }

  public void setFireAuditDoneOrnot(BooleanFilter fireAuditDoneOrnot) {
    this.fireAuditDoneOrnot = fireAuditDoneOrnot;
  }

  public LocalDateFilter getFireAuditDate() {
    return fireAuditDate;
  }

  public LocalDateFilter fireAuditDate() {
    if (fireAuditDate == null) {
      fireAuditDate = new LocalDateFilter();
    }
    return fireAuditDate;
  }

  public void setFireAuditDate(LocalDateFilter fireAuditDate) {
    this.fireAuditDate = fireAuditDate;
  }

  public StringFilter getFireFaults() {
    return fireFaults;
  }

  public StringFilter fireFaults() {
    if (fireFaults == null) {
      fireFaults = new StringFilter();
    }
    return fireFaults;
  }

  public void setFireFaults(StringFilter fireFaults) {
    this.fireFaults = fireFaults;
  }

  public StringFilter getFireCorrectiveAction() {
    return fireCorrectiveAction;
  }

  public StringFilter fireCorrectiveAction() {
    if (fireCorrectiveAction == null) {
      fireCorrectiveAction = new StringFilter();
    }
    return fireCorrectiveAction;
  }

  public void setFireCorrectiveAction(StringFilter fireCorrectiveAction) {
    this.fireCorrectiveAction = fireCorrectiveAction;
  }

  public StringFilter getFireAuditPlan() {
    return fireAuditPlan;
  }

  public StringFilter fireAuditPlan() {
    if (fireAuditPlan == null) {
      fireAuditPlan = new StringFilter();
    }
    return fireAuditPlan;
  }

  public void setFireAuditPlan(StringFilter fireAuditPlan) {
    this.fireAuditPlan = fireAuditPlan;
  }

  public BooleanFilter getElectricalAuditDone() {
    return electricalAuditDone;
  }

  public BooleanFilter electricalAuditDone() {
    if (electricalAuditDone == null) {
      electricalAuditDone = new BooleanFilter();
    }
    return electricalAuditDone;
  }

  public void setElectricalAuditDone(BooleanFilter electricalAuditDone) {
    this.electricalAuditDone = electricalAuditDone;
  }

  public LocalDateFilter getElectricalAuditDate() {
    return electricalAuditDate;
  }

  public LocalDateFilter electricalAuditDate() {
    if (electricalAuditDate == null) {
      electricalAuditDate = new LocalDateFilter();
    }
    return electricalAuditDate;
  }

  public void setElectricalAuditDate(LocalDateFilter electricalAuditDate) {
    this.electricalAuditDate = electricalAuditDate;
  }

  public StringFilter getElectricalFaults() {
    return electricalFaults;
  }

  public StringFilter electricalFaults() {
    if (electricalFaults == null) {
      electricalFaults = new StringFilter();
    }
    return electricalFaults;
  }

  public void setElectricalFaults(StringFilter electricalFaults) {
    this.electricalFaults = electricalFaults;
  }

  public StringFilter getElectricalCorrectiveAction() {
    return electricalCorrectiveAction;
  }

  public StringFilter electricalCorrectiveAction() {
    if (electricalCorrectiveAction == null) {
      electricalCorrectiveAction = new StringFilter();
    }
    return electricalCorrectiveAction;
  }

  public void setElectricalCorrectiveAction(
    StringFilter electricalCorrectiveAction
  ) {
    this.electricalCorrectiveAction = electricalCorrectiveAction;
  }

  public StringFilter getElectricalAuditInspection() {
    return electricalAuditInspection;
  }

  public StringFilter electricalAuditInspection() {
    if (electricalAuditInspection == null) {
      electricalAuditInspection = new StringFilter();
    }
    return electricalAuditInspection;
  }

  public void setElectricalAuditInspection(
    StringFilter electricalAuditInspection
  ) {
    this.electricalAuditInspection = electricalAuditInspection;
  }

  public BooleanFilter getTechnicalPersonAppoint() {
    return technicalPersonAppoint;
  }

  public BooleanFilter technicalPersonAppoint() {
    if (technicalPersonAppoint == null) {
      technicalPersonAppoint = new BooleanFilter();
    }
    return technicalPersonAppoint;
  }

  public void setTechnicalPersonAppoint(BooleanFilter technicalPersonAppoint) {
    this.technicalPersonAppoint = technicalPersonAppoint;
  }

  public StringFilter getTechPersonname() {
    return techPersonname;
  }

  public StringFilter techPersonname() {
    if (techPersonname == null) {
      techPersonname = new StringFilter();
    }
    return techPersonname;
  }

  public void setTechPersonname(StringFilter techPersonname) {
    this.techPersonname = techPersonname;
  }

  public StringFilter getTechPersonMobNo() {
    return techPersonMobNo;
  }

  public StringFilter techPersonMobNo() {
    if (techPersonMobNo == null) {
      techPersonMobNo = new StringFilter();
    }
    return techPersonMobNo;
  }

  public void setTechPersonMobNo(StringFilter techPersonMobNo) {
    this.techPersonMobNo = techPersonMobNo;
  }

  public StringFilter getTechnicalEngineerName() {
    return technicalEngineerName;
  }

  public StringFilter technicalEngineerName() {
    if (technicalEngineerName == null) {
      technicalEngineerName = new StringFilter();
    }
    return technicalEngineerName;
  }

  public void setTechnicalEngineerName(StringFilter technicalEngineerName) {
    this.technicalEngineerName = technicalEngineerName;
  }

  public StringFilter getTechnicalEngineerAddress() {
    return technicalEngineerAddress;
  }

  public StringFilter technicalEngineerAddress() {
    if (technicalEngineerAddress == null) {
      technicalEngineerAddress = new StringFilter();
    }
    return technicalEngineerAddress;
  }

  public void setTechnicalEngineerAddress(
    StringFilter technicalEngineerAddress
  ) {
    this.technicalEngineerAddress = technicalEngineerAddress;
  }

  public StringFilter getTechnicalEngineerMob() {
    return technicalEngineerMob;
  }

  public StringFilter technicalEngineerMob() {
    if (technicalEngineerMob == null) {
      technicalEngineerMob = new StringFilter();
    }
    return technicalEngineerMob;
  }

  public void setTechnicalEngineerMob(StringFilter technicalEngineerMob) {
    this.technicalEngineerMob = technicalEngineerMob;
  }

  public StringFilter getTechnicalEngineerAlternateMob() {
    return technicalEngineerAlternateMob;
  }

  public StringFilter technicalEngineerAlternateMob() {
    if (technicalEngineerAlternateMob == null) {
      technicalEngineerAlternateMob = new StringFilter();
    }
    return technicalEngineerAlternateMob;
  }

  public void setTechnicalEngineerAlternateMob(
    StringFilter technicalEngineerAlternateMob
  ) {
    this.technicalEngineerAlternateMob = technicalEngineerAlternateMob;
  }

  public DoubleFilter geto2HospRequirement() {
    return o2HospRequirement;
  }

  public DoubleFilter o2HospRequirement() {
    if (o2HospRequirement == null) {
      o2HospRequirement = new DoubleFilter();
    }
    return o2HospRequirement;
  }

  public void seto2HospRequirement(DoubleFilter o2HospRequirement) {
    this.o2HospRequirement = o2HospRequirement;
  }

  public DoubleFilter geto2HospProjectedRequirement() {
    return o2HospProjectedRequirement;
  }

  public DoubleFilter o2HospProjectedRequirement() {
    if (o2HospProjectedRequirement == null) {
      o2HospProjectedRequirement = new DoubleFilter();
    }
    return o2HospProjectedRequirement;
  }

  public void seto2HospProjectedRequirement(
    DoubleFilter o2HospProjectedRequirement
  ) {
    this.o2HospProjectedRequirement = o2HospProjectedRequirement;
  }

  public DoubleFilter getSaveO2RequirementPossibleMT() {
    return saveO2RequirementPossibleMT;
  }

  public DoubleFilter saveO2RequirementPossibleMT() {
    if (saveO2RequirementPossibleMT == null) {
      saveO2RequirementPossibleMT = new DoubleFilter();
    }
    return saveO2RequirementPossibleMT;
  }

  public void setSaveO2RequirementPossibleMT(
    DoubleFilter saveO2RequirementPossibleMT
  ) {
    this.saveO2RequirementPossibleMT = saveO2RequirementPossibleMT;
  }

  public BooleanFilter getMonitoringO2ValvesPort() {
    return monitoringO2ValvesPort;
  }

  public BooleanFilter monitoringO2ValvesPort() {
    if (monitoringO2ValvesPort == null) {
      monitoringO2ValvesPort = new BooleanFilter();
    }
    return monitoringO2ValvesPort;
  }

  public void setMonitoringO2ValvesPort(BooleanFilter monitoringO2ValvesPort) {
    this.monitoringO2ValvesPort = monitoringO2ValvesPort;
  }

  public BooleanFilter getPortValvesShutDown() {
    return portValvesShutDown;
  }

  public BooleanFilter portValvesShutDown() {
    if (portValvesShutDown == null) {
      portValvesShutDown = new BooleanFilter();
    }
    return portValvesShutDown;
  }

  public void setPortValvesShutDown(BooleanFilter portValvesShutDown) {
    this.portValvesShutDown = portValvesShutDown;
  }

  public BooleanFilter getIdPatientDrillDone() {
    return idPatientDrillDone;
  }

  public BooleanFilter idPatientDrillDone() {
    if (idPatientDrillDone == null) {
      idPatientDrillDone = new BooleanFilter();
    }
    return idPatientDrillDone;
  }

  public void setIdPatientDrillDone(BooleanFilter idPatientDrillDone) {
    this.idPatientDrillDone = idPatientDrillDone;
  }

  public BooleanFilter getStaffCheckingLeakage() {
    return staffCheckingLeakage;
  }

  public BooleanFilter staffCheckingLeakage() {
    if (staffCheckingLeakage == null) {
      staffCheckingLeakage = new BooleanFilter();
    }
    return staffCheckingLeakage;
  }

  public void setStaffCheckingLeakage(BooleanFilter staffCheckingLeakage) {
    this.staffCheckingLeakage = staffCheckingLeakage;
  }

  public BooleanFilter getPatientO2ReqFinalized() {
    return patientO2ReqFinalized;
  }

  public BooleanFilter patientO2ReqFinalized() {
    if (patientO2ReqFinalized == null) {
      patientO2ReqFinalized = new BooleanFilter();
    }
    return patientO2ReqFinalized;
  }

  public void setPatientO2ReqFinalized(BooleanFilter patientO2ReqFinalized) {
    this.patientO2ReqFinalized = patientO2ReqFinalized;
  }

  public StringFilter getTimeByDoctor() {
    return timeByDoctor;
  }

  public StringFilter timeByDoctor() {
    if (timeByDoctor == null) {
      timeByDoctor = new StringFilter();
    }
    return timeByDoctor;
  }

  public void setTimeByDoctor(StringFilter timeByDoctor) {
    this.timeByDoctor = timeByDoctor;
  }

  public BooleanFilter getIsLightingInstalled() {
    return isLightingInstalled;
  }

  public BooleanFilter isLightingInstalled() {
    if (isLightingInstalled == null) {
      isLightingInstalled = new BooleanFilter();
    }
    return isLightingInstalled;
  }

  public void setIsLightingInstalled(BooleanFilter isLightingInstalled) {
    this.isLightingInstalled = isLightingInstalled;
  }

  public StringFilter getLocLightningArrerstor() {
    return locLightningArrerstor;
  }

  public StringFilter locLightningArrerstor() {
    if (locLightningArrerstor == null) {
      locLightningArrerstor = new StringFilter();
    }
    return locLightningArrerstor;
  }

  public void setLocLightningArrerstor(StringFilter locLightningArrerstor) {
    this.locLightningArrerstor = locLightningArrerstor;
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
    final FireElectricalAuditCriteria that = (FireElectricalAuditCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(fireAuditDoneOrnot, that.fireAuditDoneOrnot) &&
      Objects.equals(fireAuditDate, that.fireAuditDate) &&
      Objects.equals(fireFaults, that.fireFaults) &&
      Objects.equals(fireCorrectiveAction, that.fireCorrectiveAction) &&
      Objects.equals(fireAuditPlan, that.fireAuditPlan) &&
      Objects.equals(electricalAuditDone, that.electricalAuditDone) &&
      Objects.equals(electricalAuditDate, that.electricalAuditDate) &&
      Objects.equals(electricalFaults, that.electricalFaults) &&
      Objects.equals(
        electricalCorrectiveAction,
        that.electricalCorrectiveAction
      ) &&
      Objects.equals(
        electricalAuditInspection,
        that.electricalAuditInspection
      ) &&
      Objects.equals(technicalPersonAppoint, that.technicalPersonAppoint) &&
      Objects.equals(techPersonname, that.techPersonname) &&
      Objects.equals(techPersonMobNo, that.techPersonMobNo) &&
      Objects.equals(technicalEngineerName, that.technicalEngineerName) &&
      Objects.equals(technicalEngineerAddress, that.technicalEngineerAddress) &&
      Objects.equals(technicalEngineerMob, that.technicalEngineerMob) &&
      Objects.equals(
        technicalEngineerAlternateMob,
        that.technicalEngineerAlternateMob
      ) &&
      Objects.equals(o2HospRequirement, that.o2HospRequirement) &&
      Objects.equals(
        o2HospProjectedRequirement,
        that.o2HospProjectedRequirement
      ) &&
      Objects.equals(
        saveO2RequirementPossibleMT,
        that.saveO2RequirementPossibleMT
      ) &&
      Objects.equals(monitoringO2ValvesPort, that.monitoringO2ValvesPort) &&
      Objects.equals(portValvesShutDown, that.portValvesShutDown) &&
      Objects.equals(idPatientDrillDone, that.idPatientDrillDone) &&
      Objects.equals(staffCheckingLeakage, that.staffCheckingLeakage) &&
      Objects.equals(patientO2ReqFinalized, that.patientO2ReqFinalized) &&
      Objects.equals(timeByDoctor, that.timeByDoctor) &&
      Objects.equals(isLightingInstalled, that.isLightingInstalled) &&
      Objects.equals(locLightningArrerstor, that.locLightningArrerstor) &&
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
      fireAuditDoneOrnot,
      fireAuditDate,
      fireFaults,
      fireCorrectiveAction,
      fireAuditPlan,
      electricalAuditDone,
      electricalAuditDate,
      electricalFaults,
      electricalCorrectiveAction,
      electricalAuditInspection,
      technicalPersonAppoint,
      techPersonname,
      techPersonMobNo,
      technicalEngineerName,
      technicalEngineerAddress,
      technicalEngineerMob,
      technicalEngineerAlternateMob,
      o2HospRequirement,
      o2HospProjectedRequirement,
      saveO2RequirementPossibleMT,
      monitoringO2ValvesPort,
      portValvesShutDown,
      idPatientDrillDone,
      staffCheckingLeakage,
      patientO2ReqFinalized,
      timeByDoctor,
      isLightingInstalled,
      locLightningArrerstor,
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
        return "FireElectricalAuditCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (fireAuditDoneOrnot != null ? "fireAuditDoneOrnot=" + fireAuditDoneOrnot + ", " : "") +
            (fireAuditDate != null ? "fireAuditDate=" + fireAuditDate + ", " : "") +
            (fireFaults != null ? "fireFaults=" + fireFaults + ", " : "") +
            (fireCorrectiveAction != null ? "fireCorrectiveAction=" + fireCorrectiveAction + ", " : "") +
            (fireAuditPlan != null ? "fireAuditPlan=" + fireAuditPlan + ", " : "") +
            (electricalAuditDone != null ? "electricalAuditDone=" + electricalAuditDone + ", " : "") +
            (electricalAuditDate != null ? "electricalAuditDate=" + electricalAuditDate + ", " : "") +
            (electricalFaults != null ? "electricalFaults=" + electricalFaults + ", " : "") +
            (electricalCorrectiveAction != null ? "electricalCorrectiveAction=" + electricalCorrectiveAction + ", " : "") +
            (electricalAuditInspection != null ? "electricalAuditInspection=" + electricalAuditInspection + ", " : "") +
            (technicalPersonAppoint != null ? "technicalPersonAppoint=" + technicalPersonAppoint + ", " : "") +
            (techPersonname != null ? "techPersonname=" + techPersonname + ", " : "") +
            (techPersonMobNo != null ? "techPersonMobNo=" + techPersonMobNo + ", " : "") +
            (technicalEngineerName != null ? "technicalEngineerName=" + technicalEngineerName + ", " : "") +
            (technicalEngineerAddress != null ? "technicalEngineerAddress=" + technicalEngineerAddress + ", " : "") +
            (technicalEngineerMob != null ? "technicalEngineerMob=" + technicalEngineerMob + ", " : "") +
            (technicalEngineerAlternateMob != null ? "technicalEngineerAlternateMob=" + technicalEngineerAlternateMob + ", " : "") +
            (o2HospRequirement != null ? "o2HospRequirement=" + o2HospRequirement + ", " : "") +
            (o2HospProjectedRequirement != null ? "o2HospProjectedRequirement=" + o2HospProjectedRequirement + ", " : "") +
            (saveO2RequirementPossibleMT != null ? "saveO2RequirementPossibleMT=" + saveO2RequirementPossibleMT + ", " : "") +
            (monitoringO2ValvesPort != null ? "monitoringO2ValvesPort=" + monitoringO2ValvesPort + ", " : "") +
            (portValvesShutDown != null ? "portValvesShutDown=" + portValvesShutDown + ", " : "") +
            (idPatientDrillDone != null ? "idPatientDrillDone=" + idPatientDrillDone + ", " : "") +
            (staffCheckingLeakage != null ? "staffCheckingLeakage=" + staffCheckingLeakage + ", " : "") +
            (patientO2ReqFinalized != null ? "patientO2ReqFinalized=" + patientO2ReqFinalized + ", " : "") +
            (timeByDoctor != null ? "timeByDoctor=" + timeByDoctor + ", " : "") +
            (isLightingInstalled != null ? "isLightingInstalled=" + isLightingInstalled + ", " : "") +
            (locLightningArrerstor != null ? "locLightningArrerstor=" + locLightningArrerstor + ", " : "") +
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
