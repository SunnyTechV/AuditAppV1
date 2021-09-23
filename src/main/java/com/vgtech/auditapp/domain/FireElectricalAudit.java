package com.vgtech.auditapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FireElectricalAudit.
 */
@Entity
@Table(name = "fire_electrical_audit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FireElectricalAudit implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /**
   * Fire Audit of Hospital has been done?
   */
  @Column(name = "fire_audit_done_ornot")
  private Boolean fireAuditDoneOrnot;

  /**
   * If yes Pls.mention Date of fire Audit
   */
  @Column(name = "fire_audit_date")
  private LocalDate fireAuditDate;

  /**
   * Faults found (if any)
   */
  @Column(name = "fire_faults")
  private String fireFaults;

  /**
   * Corrective action taken
   */
  @Column(name = "fire_corrective_action")
  private String fireCorrectiveAction;

  /**
   * If No audit done, please mention What is the Plan for Audit?
   */
  @Column(name = "fire_audit_plan")
  private String fireAuditPlan;

  /**
   * Electrical Inspection of Hospital has been done?
   */
  @Column(name = "electrical_audit_done")
  private Boolean electricalAuditDone;

  /**
   * If yes Pls.mention Date of fire Audit
   */
  @Column(name = "electrical_audit_date")
  private LocalDate electricalAuditDate;

  /**
   * Faults found (if any)
   */
  @Column(name = "electrical_faults")
  private String electricalFaults;

  /**
   * Corrective action taken
   */
  @Column(name = "electrical_corrective_action")
  private String electricalCorrectiveAction;

  /**
   * If No electrical inspection done, please mention What is the Plan for inspection
   */
  @Column(name = "electrical_audit_inspection")
  private String electricalAuditInspection;

  /**
   * Appointment of dedicated technical person to check / Monitor Oxygen Pipeline, Cylinders & Tank\n(24 X 7)
   */
  @Column(name = "technical_person_appoint")
  private Boolean technicalPersonAppoint;

  @Column(name = "tech_personname")
  private String techPersonname;

  @Column(name = "tech_person_mob_no")
  private String techPersonMobNo;

  /**
   * Name of technical Engineer
   */
  @Column(name = "technical_engineer_name")
  private String technicalEngineerName;

  @Column(name = "technical_engineer_address")
  private String technicalEngineerAddress;

  @Column(name = "technical_engineer_mob")
  private String technicalEngineerMob;

  @Column(name = "technical_engineer_alternate_mob")
  private String technicalEngineerAlternateMob;

  /**
   * Daily Oxygen Requirement by Hospital (In MT) Before Audit
   */
  @Column(name = "o_2_hosp_requirement")
  private Double o2HospRequirement;

  /**
   * Projected requirement of Oxygen by Hospital (In MT) As per Audit
   */
  @Column(name = "o_2_hosp_projected_requirement")
  private Double o2HospProjectedRequirement;

  /**
   * Saving of Oxygen Requirement (In MT) which is possible
   */
  @Column(name = "save_o_2_requirement_possible_mt")
  private Double saveO2RequirementPossibleMT;

  /**
   * Whether ward boy / sister is appointed (24 X 7) for monitoring of Oxygen Valves / Ports and patient wise supply?
   */
  @Column(name = "monitoring_o_2_valves_port")
  private Boolean monitoringO2ValvesPort;

  /**
   * Whether Port / Valves is shut down, when patient goes to washroom / Eating
   */
  @Column(name = "port_valves_shut_down")
  private Boolean portValvesShutDown;

  /**
   * Is there patient drill taken for Oxygen Usage?
   */
  @Column(name = "id_patient_drill_done")
  private Boolean idPatientDrillDone;

  /**
   * Are staff Checking Carefully leakages of Oxygen Pipeline Cylinder & Cryogenic tank daily?
   */
  @Column(name = "staff_checking_leakage")
  private Boolean staffCheckingLeakage;

  /**
   * Is patient Oxygen requirement finalized carefully by using prone position after giving sufficient
   */
  @Column(name = "patient_o_2_req_finalized")
  private Boolean patientO2ReqFinalized;

  @Column(name = "time_by_doctor")
  private String timeByDoctor;

  /**
   * Are Lightening Arresters available and installed on building?
   */
  @Column(name = "is_lighting_installed")
  private Boolean isLightingInstalled;

  /**
   * If available, where is it?At Hospital / At Oxygen Cryogenic Tank area
   */
  @Column(name = "loc_lightning_arrerstor")
  private String locLightningArrerstor;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "created_date")
  private LocalDate createdDate;

  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @Column(name = "last_modified")
  private LocalDate lastModified;

  @Column(name = "free_field_1")
  private String freeField1;

  @Column(name = "free_field_2")
  private String freeField2;

  @Column(name = "free_field_3")
  private String freeField3;

  @Column(name = "free_field_4")
  private String freeField4;

  @ManyToOne
  @JsonIgnoreProperties(
    value = {
      "auditPatientMonitoringForm",
      "auditFormSHospGenInfos",
      "fireElectricalAudits",
      "annexureAnswers",
      "invetoryReports",
      "inventorySupplierDetails",
      "oxygenConsumptionData",
    },
    allowSetters = true
  )
  private Audit audit;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public FireElectricalAudit id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getFireAuditDoneOrnot() {
    return this.fireAuditDoneOrnot;
  }

  public FireElectricalAudit fireAuditDoneOrnot(Boolean fireAuditDoneOrnot) {
    this.setFireAuditDoneOrnot(fireAuditDoneOrnot);
    return this;
  }

  public void setFireAuditDoneOrnot(Boolean fireAuditDoneOrnot) {
    this.fireAuditDoneOrnot = fireAuditDoneOrnot;
  }

  public LocalDate getFireAuditDate() {
    return this.fireAuditDate;
  }

  public FireElectricalAudit fireAuditDate(LocalDate fireAuditDate) {
    this.setFireAuditDate(fireAuditDate);
    return this;
  }

  public void setFireAuditDate(LocalDate fireAuditDate) {
    this.fireAuditDate = fireAuditDate;
  }

  public String getFireFaults() {
    return this.fireFaults;
  }

  public FireElectricalAudit fireFaults(String fireFaults) {
    this.setFireFaults(fireFaults);
    return this;
  }

  public void setFireFaults(String fireFaults) {
    this.fireFaults = fireFaults;
  }

  public String getFireCorrectiveAction() {
    return this.fireCorrectiveAction;
  }

  public FireElectricalAudit fireCorrectiveAction(String fireCorrectiveAction) {
    this.setFireCorrectiveAction(fireCorrectiveAction);
    return this;
  }

  public void setFireCorrectiveAction(String fireCorrectiveAction) {
    this.fireCorrectiveAction = fireCorrectiveAction;
  }

  public String getFireAuditPlan() {
    return this.fireAuditPlan;
  }

  public FireElectricalAudit fireAuditPlan(String fireAuditPlan) {
    this.setFireAuditPlan(fireAuditPlan);
    return this;
  }

  public void setFireAuditPlan(String fireAuditPlan) {
    this.fireAuditPlan = fireAuditPlan;
  }

  public Boolean getElectricalAuditDone() {
    return this.electricalAuditDone;
  }

  public FireElectricalAudit electricalAuditDone(Boolean electricalAuditDone) {
    this.setElectricalAuditDone(electricalAuditDone);
    return this;
  }

  public void setElectricalAuditDone(Boolean electricalAuditDone) {
    this.electricalAuditDone = electricalAuditDone;
  }

  public LocalDate getElectricalAuditDate() {
    return this.electricalAuditDate;
  }

  public FireElectricalAudit electricalAuditDate(
    LocalDate electricalAuditDate
  ) {
    this.setElectricalAuditDate(electricalAuditDate);
    return this;
  }

  public void setElectricalAuditDate(LocalDate electricalAuditDate) {
    this.electricalAuditDate = electricalAuditDate;
  }

  public String getElectricalFaults() {
    return this.electricalFaults;
  }

  public FireElectricalAudit electricalFaults(String electricalFaults) {
    this.setElectricalFaults(electricalFaults);
    return this;
  }

  public void setElectricalFaults(String electricalFaults) {
    this.electricalFaults = electricalFaults;
  }

  public String getElectricalCorrectiveAction() {
    return this.electricalCorrectiveAction;
  }

  public FireElectricalAudit electricalCorrectiveAction(
    String electricalCorrectiveAction
  ) {
    this.setElectricalCorrectiveAction(electricalCorrectiveAction);
    return this;
  }

  public void setElectricalCorrectiveAction(String electricalCorrectiveAction) {
    this.electricalCorrectiveAction = electricalCorrectiveAction;
  }

  public String getElectricalAuditInspection() {
    return this.electricalAuditInspection;
  }

  public FireElectricalAudit electricalAuditInspection(
    String electricalAuditInspection
  ) {
    this.setElectricalAuditInspection(electricalAuditInspection);
    return this;
  }

  public void setElectricalAuditInspection(String electricalAuditInspection) {
    this.electricalAuditInspection = electricalAuditInspection;
  }

  public Boolean getTechnicalPersonAppoint() {
    return this.technicalPersonAppoint;
  }

  public FireElectricalAudit technicalPersonAppoint(
    Boolean technicalPersonAppoint
  ) {
    this.setTechnicalPersonAppoint(technicalPersonAppoint);
    return this;
  }

  public void setTechnicalPersonAppoint(Boolean technicalPersonAppoint) {
    this.technicalPersonAppoint = technicalPersonAppoint;
  }

  public String getTechPersonname() {
    return this.techPersonname;
  }

  public FireElectricalAudit techPersonname(String techPersonname) {
    this.setTechPersonname(techPersonname);
    return this;
  }

  public void setTechPersonname(String techPersonname) {
    this.techPersonname = techPersonname;
  }

  public String getTechPersonMobNo() {
    return this.techPersonMobNo;
  }

  public FireElectricalAudit techPersonMobNo(String techPersonMobNo) {
    this.setTechPersonMobNo(techPersonMobNo);
    return this;
  }

  public void setTechPersonMobNo(String techPersonMobNo) {
    this.techPersonMobNo = techPersonMobNo;
  }

  public String getTechnicalEngineerName() {
    return this.technicalEngineerName;
  }

  public FireElectricalAudit technicalEngineerName(
    String technicalEngineerName
  ) {
    this.setTechnicalEngineerName(technicalEngineerName);
    return this;
  }

  public void setTechnicalEngineerName(String technicalEngineerName) {
    this.technicalEngineerName = technicalEngineerName;
  }

  public String getTechnicalEngineerAddress() {
    return this.technicalEngineerAddress;
  }

  public FireElectricalAudit technicalEngineerAddress(
    String technicalEngineerAddress
  ) {
    this.setTechnicalEngineerAddress(technicalEngineerAddress);
    return this;
  }

  public void setTechnicalEngineerAddress(String technicalEngineerAddress) {
    this.technicalEngineerAddress = technicalEngineerAddress;
  }

  public String getTechnicalEngineerMob() {
    return this.technicalEngineerMob;
  }

  public FireElectricalAudit technicalEngineerMob(String technicalEngineerMob) {
    this.setTechnicalEngineerMob(technicalEngineerMob);
    return this;
  }

  public void setTechnicalEngineerMob(String technicalEngineerMob) {
    this.technicalEngineerMob = technicalEngineerMob;
  }

  public String getTechnicalEngineerAlternateMob() {
    return this.technicalEngineerAlternateMob;
  }

  public FireElectricalAudit technicalEngineerAlternateMob(
    String technicalEngineerAlternateMob
  ) {
    this.setTechnicalEngineerAlternateMob(technicalEngineerAlternateMob);
    return this;
  }

  public void setTechnicalEngineerAlternateMob(
    String technicalEngineerAlternateMob
  ) {
    this.technicalEngineerAlternateMob = technicalEngineerAlternateMob;
  }

  public Double geto2HospRequirement() {
    return this.o2HospRequirement;
  }

  public FireElectricalAudit o2HospRequirement(Double o2HospRequirement) {
    this.seto2HospRequirement(o2HospRequirement);
    return this;
  }

  public void seto2HospRequirement(Double o2HospRequirement) {
    this.o2HospRequirement = o2HospRequirement;
  }

  public Double geto2HospProjectedRequirement() {
    return this.o2HospProjectedRequirement;
  }

  public FireElectricalAudit o2HospProjectedRequirement(
    Double o2HospProjectedRequirement
  ) {
    this.seto2HospProjectedRequirement(o2HospProjectedRequirement);
    return this;
  }

  public void seto2HospProjectedRequirement(Double o2HospProjectedRequirement) {
    this.o2HospProjectedRequirement = o2HospProjectedRequirement;
  }

  public Double getSaveO2RequirementPossibleMT() {
    return this.saveO2RequirementPossibleMT;
  }

  public FireElectricalAudit saveO2RequirementPossibleMT(
    Double saveO2RequirementPossibleMT
  ) {
    this.setSaveO2RequirementPossibleMT(saveO2RequirementPossibleMT);
    return this;
  }

  public void setSaveO2RequirementPossibleMT(
    Double saveO2RequirementPossibleMT
  ) {
    this.saveO2RequirementPossibleMT = saveO2RequirementPossibleMT;
  }

  public Boolean getMonitoringO2ValvesPort() {
    return this.monitoringO2ValvesPort;
  }

  public FireElectricalAudit monitoringO2ValvesPort(
    Boolean monitoringO2ValvesPort
  ) {
    this.setMonitoringO2ValvesPort(monitoringO2ValvesPort);
    return this;
  }

  public void setMonitoringO2ValvesPort(Boolean monitoringO2ValvesPort) {
    this.monitoringO2ValvesPort = monitoringO2ValvesPort;
  }

  public Boolean getPortValvesShutDown() {
    return this.portValvesShutDown;
  }

  public FireElectricalAudit portValvesShutDown(Boolean portValvesShutDown) {
    this.setPortValvesShutDown(portValvesShutDown);
    return this;
  }

  public void setPortValvesShutDown(Boolean portValvesShutDown) {
    this.portValvesShutDown = portValvesShutDown;
  }

  public Boolean getIdPatientDrillDone() {
    return this.idPatientDrillDone;
  }

  public FireElectricalAudit idPatientDrillDone(Boolean idPatientDrillDone) {
    this.setIdPatientDrillDone(idPatientDrillDone);
    return this;
  }

  public void setIdPatientDrillDone(Boolean idPatientDrillDone) {
    this.idPatientDrillDone = idPatientDrillDone;
  }

  public Boolean getStaffCheckingLeakage() {
    return this.staffCheckingLeakage;
  }

  public FireElectricalAudit staffCheckingLeakage(
    Boolean staffCheckingLeakage
  ) {
    this.setStaffCheckingLeakage(staffCheckingLeakage);
    return this;
  }

  public void setStaffCheckingLeakage(Boolean staffCheckingLeakage) {
    this.staffCheckingLeakage = staffCheckingLeakage;
  }

  public Boolean getPatientO2ReqFinalized() {
    return this.patientO2ReqFinalized;
  }

  public FireElectricalAudit patientO2ReqFinalized(
    Boolean patientO2ReqFinalized
  ) {
    this.setPatientO2ReqFinalized(patientO2ReqFinalized);
    return this;
  }

  public void setPatientO2ReqFinalized(Boolean patientO2ReqFinalized) {
    this.patientO2ReqFinalized = patientO2ReqFinalized;
  }

  public String getTimeByDoctor() {
    return this.timeByDoctor;
  }

  public FireElectricalAudit timeByDoctor(String timeByDoctor) {
    this.setTimeByDoctor(timeByDoctor);
    return this;
  }

  public void setTimeByDoctor(String timeByDoctor) {
    this.timeByDoctor = timeByDoctor;
  }

  public Boolean getIsLightingInstalled() {
    return this.isLightingInstalled;
  }

  public FireElectricalAudit isLightingInstalled(Boolean isLightingInstalled) {
    this.setIsLightingInstalled(isLightingInstalled);
    return this;
  }

  public void setIsLightingInstalled(Boolean isLightingInstalled) {
    this.isLightingInstalled = isLightingInstalled;
  }

  public String getLocLightningArrerstor() {
    return this.locLightningArrerstor;
  }

  public FireElectricalAudit locLightningArrerstor(
    String locLightningArrerstor
  ) {
    this.setLocLightningArrerstor(locLightningArrerstor);
    return this;
  }

  public void setLocLightningArrerstor(String locLightningArrerstor) {
    this.locLightningArrerstor = locLightningArrerstor;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public FireElectricalAudit createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

  public FireElectricalAudit createdDate(LocalDate createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public FireElectricalAudit lastModifiedBy(String lastModifiedBy) {
    this.setLastModifiedBy(lastModifiedBy);
    return this;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public LocalDate getLastModified() {
    return this.lastModified;
  }

  public FireElectricalAudit lastModified(LocalDate lastModified) {
    this.setLastModified(lastModified);
    return this;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getFreeField1() {
    return this.freeField1;
  }

  public FireElectricalAudit freeField1(String freeField1) {
    this.setFreeField1(freeField1);
    return this;
  }

  public void setFreeField1(String freeField1) {
    this.freeField1 = freeField1;
  }

  public String getFreeField2() {
    return this.freeField2;
  }

  public FireElectricalAudit freeField2(String freeField2) {
    this.setFreeField2(freeField2);
    return this;
  }

  public void setFreeField2(String freeField2) {
    this.freeField2 = freeField2;
  }

  public String getFreeField3() {
    return this.freeField3;
  }

  public FireElectricalAudit freeField3(String freeField3) {
    this.setFreeField3(freeField3);
    return this;
  }

  public void setFreeField3(String freeField3) {
    this.freeField3 = freeField3;
  }

  public String getFreeField4() {
    return this.freeField4;
  }

  public FireElectricalAudit freeField4(String freeField4) {
    this.setFreeField4(freeField4);
    return this;
  }

  public void setFreeField4(String freeField4) {
    this.freeField4 = freeField4;
  }

  public Audit getAudit() {
    return this.audit;
  }

  public void setAudit(Audit audit) {
    this.audit = audit;
  }

  public FireElectricalAudit audit(Audit audit) {
    this.setAudit(audit);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FireElectricalAudit)) {
      return false;
    }
    return id != null && id.equals(((FireElectricalAudit) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
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
            "}";
    }
}
