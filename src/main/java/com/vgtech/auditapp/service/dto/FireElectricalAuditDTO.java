package com.vgtech.auditapp.service.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.vgtech.auditapp.domain.FireElectricalAudit} entity.
 */
public class FireElectricalAudit implements Serializable {

  private Long id;

  /**
   * Fire Audit of Hospital has been done?
   */
  @ApiModelProperty(value = "Fire Audit of Hospital has been done?")
  private Boolean fireAuditDoneOrnot;

  /**
   * If yes Pls.mention Date of fire Audit
   */
  @ApiModelProperty(value = "If yes Pls.mention Date of fire Audit")
  private LocalDate fireAuditDate;

  /**
   * Faults found (if any)
   */
  @ApiModelProperty(value = "Faults found (if any)")
  private String fireFaults;

  /**
   * Corrective action taken
   */
  @ApiModelProperty(value = "Corrective action taken")
  private String fireCorrectiveAction;

  /**
   * If No audit done, please mention What is the Plan for Audit?
   */
  @ApiModelProperty(
    value = "If No audit done, please mention What is the Plan for Audit?"
  )
  private String fireAuditPlan;

  /**
   * Electrical Inspection of Hospital has been done?
   */
  @ApiModelProperty(value = "Electrical Inspection of Hospital has been done?")
  private Boolean electricalAuditDone;

  /**
   * If yes Pls.mention Date of fire Audit
   */
  @ApiModelProperty(value = "If yes Pls.mention Date of fire Audit")
  private LocalDate electricalAuditDate;

  /**
   * Faults found (if any)
   */
  @ApiModelProperty(value = "Faults found (if any)")
  private String electricalFaults;

  /**
   * Corrective action taken
   */
  @ApiModelProperty(value = "Corrective action taken")
  private String electricalCorrectiveAction;

  /**
   * If No electrical inspection done, please mention What is the Plan for inspection
   */
  @ApiModelProperty(
    value = "If No electrical inspection done, please mention What is the Plan for inspection"
  )
  private String electricalAuditInspection;

  /**
   * Appointment of dedicated technical person to check / Monitor Oxygen Pipeline, Cylinders & Tank\n(24 X 7)
   */
  @ApiModelProperty(
    value = "Appointment of dedicated technical person to check / Monitor Oxygen Pipeline, Cylinders & Tank\n(24 X 7)"
  )
  private Boolean technicalPersonAppoint;

  private String techPersonname;

  private String techPersonMobNo;

  /**
   * Name of technical Engineer
   */
  @ApiModelProperty(value = "Name of technical Engineer")
  private String technicalEngineerName;

  private String technicalEngineerAddress;

  private String technicalEngineerMob;

  private String technicalEngineerAlternateMob;

  /**
   * Daily Oxygen Requirement by Hospital (In MT) Before Audit
   */
  @ApiModelProperty(
    value = "Daily Oxygen Requirement by Hospital (In MT) Before Audit"
  )
  private Double o2HospRequirement;

  /**
   * Projected requirement of Oxygen by Hospital (In MT) As per Audit
   */
  @ApiModelProperty(
    value = "Projected requirement of Oxygen by Hospital (In MT) As per Audit"
  )
  private Double o2HospProjectedRequirement;

  /**
   * Saving of Oxygen Requirement (In MT) which is possible
   */
  @ApiModelProperty(
    value = "Saving of Oxygen Requirement (In MT) which is possible"
  )
  private Double saveO2RequirementPossibleMT;

  /**
   * Whether ward boy / sister is appointed (24 X 7) for monitoring of Oxygen Valves / Ports and patient wise supply?
   */
  @ApiModelProperty(
    value = "Whether ward boy / sister is appointed (24 X 7) for monitoring of Oxygen Valves / Ports and patient wise supply?"
  )
  private Boolean monitoringO2ValvesPort;

  /**
   * Whether Port / Valves is shut down, when patient goes to washroom / Eating
   */
  @ApiModelProperty(
    value = "Whether Port / Valves is shut down, when patient goes to washroom / Eating"
  )
  private Boolean portValvesShutDown;

  /**
   * Is there patient drill taken for Oxygen Usage?
   */
  @ApiModelProperty(value = "Is there patient drill taken for Oxygen Usage?")
  private Boolean idPatientDrillDone;

  /**
   * Are staff Checking Carefully leakages of Oxygen Pipeline Cylinder & Cryogenic tank daily?
   */
  @ApiModelProperty(
    value = "Are staff Checking Carefully leakages of Oxygen Pipeline Cylinder & Cryogenic tank daily?"
  )
  private Boolean staffCheckingLeakage;

  /**
   * Is patient Oxygen requirement finalized carefully by using prone position after giving sufficient
   */
  @ApiModelProperty(
    value = "Is patient Oxygen requirement finalized carefully by using prone position after giving sufficient"
  )
  private Boolean patientO2ReqFinalized;

  private String timeByDoctor;

  /**
   * Are Lightening Arresters available and installed on building?
   */
  @ApiModelProperty(
    value = "Are Lightening Arresters available and installed on building?"
  )
  private Boolean isLightingInstalled;

  /**
   * If available, where is it?At Hospital / At Oxygen Cryogenic Tank area
   */
  @ApiModelProperty(
    value = "If available, where is it?At Hospital / At Oxygen Cryogenic Tank area"
  )
  private String locLightningArrerstor;

  private String createdBy;

  private LocalDate createdDate;

  private String lastModifiedBy;

  private LocalDate lastModified;

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

  public Boolean getFireAuditDoneOrnot() {
    return fireAuditDoneOrnot;
  }

  public void setFireAuditDoneOrnot(Boolean fireAuditDoneOrnot) {
    this.fireAuditDoneOrnot = fireAuditDoneOrnot;
  }

  public LocalDate getFireAuditDate() {
    return fireAuditDate;
  }

  public void setFireAuditDate(LocalDate fireAuditDate) {
    this.fireAuditDate = fireAuditDate;
  }

  public String getFireFaults() {
    return fireFaults;
  }

  public void setFireFaults(String fireFaults) {
    this.fireFaults = fireFaults;
  }

  public String getFireCorrectiveAction() {
    return fireCorrectiveAction;
  }

  public void setFireCorrectiveAction(String fireCorrectiveAction) {
    this.fireCorrectiveAction = fireCorrectiveAction;
  }

  public String getFireAuditPlan() {
    return fireAuditPlan;
  }

  public void setFireAuditPlan(String fireAuditPlan) {
    this.fireAuditPlan = fireAuditPlan;
  }

  public Boolean getElectricalAuditDone() {
    return electricalAuditDone;
  }

  public void setElectricalAuditDone(Boolean electricalAuditDone) {
    this.electricalAuditDone = electricalAuditDone;
  }

  public LocalDate getElectricalAuditDate() {
    return electricalAuditDate;
  }

  public void setElectricalAuditDate(LocalDate electricalAuditDate) {
    this.electricalAuditDate = electricalAuditDate;
  }

  public String getElectricalFaults() {
    return electricalFaults;
  }

  public void setElectricalFaults(String electricalFaults) {
    this.electricalFaults = electricalFaults;
  }

  public String getElectricalCorrectiveAction() {
    return electricalCorrectiveAction;
  }

  public void setElectricalCorrectiveAction(String electricalCorrectiveAction) {
    this.electricalCorrectiveAction = electricalCorrectiveAction;
  }

  public String getElectricalAuditInspection() {
    return electricalAuditInspection;
  }

  public void setElectricalAuditInspection(String electricalAuditInspection) {
    this.electricalAuditInspection = electricalAuditInspection;
  }

  public Boolean getTechnicalPersonAppoint() {
    return technicalPersonAppoint;
  }

  public void setTechnicalPersonAppoint(Boolean technicalPersonAppoint) {
    this.technicalPersonAppoint = technicalPersonAppoint;
  }

  public String getTechPersonname() {
    return techPersonname;
  }

  public void setTechPersonname(String techPersonname) {
    this.techPersonname = techPersonname;
  }

  public String getTechPersonMobNo() {
    return techPersonMobNo;
  }

  public void setTechPersonMobNo(String techPersonMobNo) {
    this.techPersonMobNo = techPersonMobNo;
  }

  public String getTechnicalEngineerName() {
    return technicalEngineerName;
  }

  public void setTechnicalEngineerName(String technicalEngineerName) {
    this.technicalEngineerName = technicalEngineerName;
  }

  public String getTechnicalEngineerAddress() {
    return technicalEngineerAddress;
  }

  public void setTechnicalEngineerAddress(String technicalEngineerAddress) {
    this.technicalEngineerAddress = technicalEngineerAddress;
  }

  public String getTechnicalEngineerMob() {
    return technicalEngineerMob;
  }

  public void setTechnicalEngineerMob(String technicalEngineerMob) {
    this.technicalEngineerMob = technicalEngineerMob;
  }

  public String getTechnicalEngineerAlternateMob() {
    return technicalEngineerAlternateMob;
  }

  public void setTechnicalEngineerAlternateMob(
    String technicalEngineerAlternateMob
  ) {
    this.technicalEngineerAlternateMob = technicalEngineerAlternateMob;
  }

  public Double geto2HospRequirement() {
    return o2HospRequirement;
  }

  public void seto2HospRequirement(Double o2HospRequirement) {
    this.o2HospRequirement = o2HospRequirement;
  }

  public Double geto2HospProjectedRequirement() {
    return o2HospProjectedRequirement;
  }

  public void seto2HospProjectedRequirement(Double o2HospProjectedRequirement) {
    this.o2HospProjectedRequirement = o2HospProjectedRequirement;
  }

  public Double getSaveO2RequirementPossibleMT() {
    return saveO2RequirementPossibleMT;
  }

  public void setSaveO2RequirementPossibleMT(
    Double saveO2RequirementPossibleMT
  ) {
    this.saveO2RequirementPossibleMT = saveO2RequirementPossibleMT;
  }

  public Boolean getMonitoringO2ValvesPort() {
    return monitoringO2ValvesPort;
  }

  public void setMonitoringO2ValvesPort(Boolean monitoringO2ValvesPort) {
    this.monitoringO2ValvesPort = monitoringO2ValvesPort;
  }

  public Boolean getPortValvesShutDown() {
    return portValvesShutDown;
  }

  public void setPortValvesShutDown(Boolean portValvesShutDown) {
    this.portValvesShutDown = portValvesShutDown;
  }

  public Boolean getIdPatientDrillDone() {
    return idPatientDrillDone;
  }

  public void setIdPatientDrillDone(Boolean idPatientDrillDone) {
    this.idPatientDrillDone = idPatientDrillDone;
  }

  public Boolean getStaffCheckingLeakage() {
    return staffCheckingLeakage;
  }

  public void setStaffCheckingLeakage(Boolean staffCheckingLeakage) {
    this.staffCheckingLeakage = staffCheckingLeakage;
  }

  public Boolean getPatientO2ReqFinalized() {
    return patientO2ReqFinalized;
  }

  public void setPatientO2ReqFinalized(Boolean patientO2ReqFinalized) {
    this.patientO2ReqFinalized = patientO2ReqFinalized;
  }

  public String getTimeByDoctor() {
    return timeByDoctor;
  }

  public void setTimeByDoctor(String timeByDoctor) {
    this.timeByDoctor = timeByDoctor;
  }

  public Boolean getIsLightingInstalled() {
    return isLightingInstalled;
  }

  public void setIsLightingInstalled(Boolean isLightingInstalled) {
    this.isLightingInstalled = isLightingInstalled;
  }

  public String getLocLightningArrerstor() {
    return locLightningArrerstor;
  }

  public void setLocLightningArrerstor(String locLightningArrerstor) {
    this.locLightningArrerstor = locLightningArrerstor;
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
    if (!(o instanceof FireElectricalAudit)) {
      return false;
    }

    FireElectricalAudit fireElectricalAudit = (FireElectricalAudit) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, fireElectricalAudit.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FireElectricalAudit{" +
            "id=" + getId() +
            ", fireAuditDoneOrnot='" + getFireAuditDoneOrnot() + "'" +
            ", fireAuditDate='" + getFireAuditDate() + "'" +
            ", fireFaults='" + getFireFaults() + "'" +
            ", fireCorrectiveAction='" + getFireCorrectiveAction() + "'" +
            ", fireAuditPlan='" + getFireAuditPlan() + "'" +
            ", electricalAuditDone='" + getElectricalAuditDone() + "'" +
            ", electricalAuditDate='" + getElectricalAuditDate() + "'" +
            ", electricalFaults='" + getElectricalFaults() + "'" +
            ", electricalCorrectiveAction='" + getElectricalCorrectiveAction() + "'" +
            ", electricalAuditInspection='" + getElectricalAuditInspection() + "'" +
            ", technicalPersonAppoint='" + getTechnicalPersonAppoint() + "'" +
            ", techPersonname='" + getTechPersonname() + "'" +
            ", techPersonMobNo='" + getTechPersonMobNo() + "'" +
            ", technicalEngineerName='" + getTechnicalEngineerName() + "'" +
            ", technicalEngineerAddress='" + getTechnicalEngineerAddress() + "'" +
            ", technicalEngineerMob='" + getTechnicalEngineerMob() + "'" +
            ", technicalEngineerAlternateMob='" + getTechnicalEngineerAlternateMob() + "'" +
            ", o2HospRequirement=" + geto2HospRequirement() +
            ", o2HospProjectedRequirement=" + geto2HospProjectedRequirement() +
            ", saveO2RequirementPossibleMT=" + getSaveO2RequirementPossibleMT() +
            ", monitoringO2ValvesPort='" + getMonitoringO2ValvesPort() + "'" +
            ", portValvesShutDown='" + getPortValvesShutDown() + "'" +
            ", idPatientDrillDone='" + getIdPatientDrillDone() + "'" +
            ", staffCheckingLeakage='" + getStaffCheckingLeakage() + "'" +
            ", patientO2ReqFinalized='" + getPatientO2ReqFinalized() + "'" +
            ", timeByDoctor='" + getTimeByDoctor() + "'" +
            ", isLightingInstalled='" + getIsLightingInstalled() + "'" +
            ", locLightningArrerstor='" + getLocLightningArrerstor() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", audit=" + getAudit() +
            "}";
    }
}
