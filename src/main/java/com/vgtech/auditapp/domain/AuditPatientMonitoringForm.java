package com.vgtech.auditapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AuditPatientMonitoringForm.
 */
@Entity
@Table(name = "audit_patient_monitoring_form")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuditPatientMonitoringForm implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "ward_no")
  private Integer wardNo;

  @Column(name = "patient_name")
  private String patientName;

  @Column(name = "bed_no")
  private Integer bedNo;

  @Column(name = "date_of_admission")
  private LocalDate dateOfAdmission;

  @Column(name = "oxygen_type")
  private String oxygenType;

  @Column(name = "six_to_eight_am")
  private Double sixToEightAM;

  @Column(name = "oxy_six_to_eight_am")
  private Double oxySixToEightAM;

  @Column(name = "eight_to_ten_am")
  private Double eightToTenAM;

  @Column(name = "oxy_eight_to_ten_am")
  private Double oxyEightToTenAM;

  @Column(name = "ten_to_twelve_am")
  private Double tenToTwelveAM;

  @Column(name = "oxy_ten_to_twelve_am")
  private Double oxyTenToTwelveAM;

  @Column(name = "twelve_to_tow_pm")
  private Double twelveToTowPM;

  @Column(name = "oxy_twelve_to_tow_pm")
  private Double oxyTwelveToTowPM;

  @Column(name = "two_to_four_pm")
  private Double twoToFourPM;

  @Column(name = "oxy_two_to_four_pm")
  private Double oxyTwoToFourPM;

  @Column(name = "four_to_six_pm")
  private Double fourToSixPM;

  @Column(name = "oxy_four_to_six_pm")
  private Double oxyFourToSixPM;

  @Column(name = "six_to_eight_pm")
  private Double sixToEightPM;

  @Column(name = "oxy_six_to_eight_pm")
  private Double oxySixToEightPM;

  @Column(name = "eight_to_ten_pm")
  private Double eightToTenPM;

  @Column(name = "oxy_eight_to_ten_pm")
  private Double oxyEightToTenPM;

  @Column(name = "ten_to_twelve_pm")
  private Double tenToTwelvePM;

  @Column(name = "oxy_ten_to_twelve_pm")
  private Double oxyTenToTwelvePM;

  @Column(name = "twelve_to_two_am")
  private Double twelveToTwoAM;

  @Column(name = "oxy_twelve_to_two_am")
  private Double oxyTwelveToTwoAM;

  @Column(name = "two_to_four_am")
  private Double twoToFourAM;

  @Column(name = "oxy_two_to_four_am")
  private Double oxyTwoToFourAM;

  @Column(name = "four_to_six_am")
  private Double fourToSixAM;

  @Column(name = "oxy_four_to_six_am")
  private Double oxyFourToSixAM;

  @Column(name = "dr_name")
  private String drName;

  @Column(name = "nurse_name")
  private String nurseName;

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
  @OneToOne(mappedBy = "auditPatientMonitoringForm")
  private Audit audit;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public AuditPatientMonitoringForm id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getWardNo() {
    return this.wardNo;
  }

  public AuditPatientMonitoringForm wardNo(Integer wardNo) {
    this.setWardNo(wardNo);
    return this;
  }

  public void setWardNo(Integer wardNo) {
    this.wardNo = wardNo;
  }

  public String getPatientName() {
    return this.patientName;
  }

  public AuditPatientMonitoringForm patientName(String patientName) {
    this.setPatientName(patientName);
    return this;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public Integer getBedNo() {
    return this.bedNo;
  }

  public AuditPatientMonitoringForm bedNo(Integer bedNo) {
    this.setBedNo(bedNo);
    return this;
  }

  public void setBedNo(Integer bedNo) {
    this.bedNo = bedNo;
  }

  public LocalDate getDateOfAdmission() {
    return this.dateOfAdmission;
  }

  public AuditPatientMonitoringForm dateOfAdmission(LocalDate dateOfAdmission) {
    this.setDateOfAdmission(dateOfAdmission);
    return this;
  }

  public void setDateOfAdmission(LocalDate dateOfAdmission) {
    this.dateOfAdmission = dateOfAdmission;
  }

  public String getOxygenType() {
    return this.oxygenType;
  }

  public AuditPatientMonitoringForm oxygenType(String oxygenType) {
    this.setOxygenType(oxygenType);
    return this;
  }

  public void setOxygenType(String oxygenType) {
    this.oxygenType = oxygenType;
  }

  public Double getSixToEightAM() {
    return this.sixToEightAM;
  }

  public AuditPatientMonitoringForm sixToEightAM(Double sixToEightAM) {
    this.setSixToEightAM(sixToEightAM);
    return this;
  }

  public void setSixToEightAM(Double sixToEightAM) {
    this.sixToEightAM = sixToEightAM;
  }

  public Double getOxySixToEightAM() {
    return this.oxySixToEightAM;
  }

  public AuditPatientMonitoringForm oxySixToEightAM(Double oxySixToEightAM) {
    this.setOxySixToEightAM(oxySixToEightAM);
    return this;
  }

  public void setOxySixToEightAM(Double oxySixToEightAM) {
    this.oxySixToEightAM = oxySixToEightAM;
  }

  public Double getEightToTenAM() {
    return this.eightToTenAM;
  }

  public AuditPatientMonitoringForm eightToTenAM(Double eightToTenAM) {
    this.setEightToTenAM(eightToTenAM);
    return this;
  }

  public void setEightToTenAM(Double eightToTenAM) {
    this.eightToTenAM = eightToTenAM;
  }

  public Double getOxyEightToTenAM() {
    return this.oxyEightToTenAM;
  }

  public AuditPatientMonitoringForm oxyEightToTenAM(Double oxyEightToTenAM) {
    this.setOxyEightToTenAM(oxyEightToTenAM);
    return this;
  }

  public void setOxyEightToTenAM(Double oxyEightToTenAM) {
    this.oxyEightToTenAM = oxyEightToTenAM;
  }

  public Double getTenToTwelveAM() {
    return this.tenToTwelveAM;
  }

  public AuditPatientMonitoringForm tenToTwelveAM(Double tenToTwelveAM) {
    this.setTenToTwelveAM(tenToTwelveAM);
    return this;
  }

  public void setTenToTwelveAM(Double tenToTwelveAM) {
    this.tenToTwelveAM = tenToTwelveAM;
  }

  public Double getOxyTenToTwelveAM() {
    return this.oxyTenToTwelveAM;
  }

  public AuditPatientMonitoringForm oxyTenToTwelveAM(Double oxyTenToTwelveAM) {
    this.setOxyTenToTwelveAM(oxyTenToTwelveAM);
    return this;
  }

  public void setOxyTenToTwelveAM(Double oxyTenToTwelveAM) {
    this.oxyTenToTwelveAM = oxyTenToTwelveAM;
  }

  public Double getTwelveToTowPM() {
    return this.twelveToTowPM;
  }

  public AuditPatientMonitoringForm twelveToTowPM(Double twelveToTowPM) {
    this.setTwelveToTowPM(twelveToTowPM);
    return this;
  }

  public void setTwelveToTowPM(Double twelveToTowPM) {
    this.twelveToTowPM = twelveToTowPM;
  }

  public Double getOxyTwelveToTowPM() {
    return this.oxyTwelveToTowPM;
  }

  public AuditPatientMonitoringForm oxyTwelveToTowPM(Double oxyTwelveToTowPM) {
    this.setOxyTwelveToTowPM(oxyTwelveToTowPM);
    return this;
  }

  public void setOxyTwelveToTowPM(Double oxyTwelveToTowPM) {
    this.oxyTwelveToTowPM = oxyTwelveToTowPM;
  }

  public Double getTwoToFourPM() {
    return this.twoToFourPM;
  }

  public AuditPatientMonitoringForm twoToFourPM(Double twoToFourPM) {
    this.setTwoToFourPM(twoToFourPM);
    return this;
  }

  public void setTwoToFourPM(Double twoToFourPM) {
    this.twoToFourPM = twoToFourPM;
  }

  public Double getOxyTwoToFourPM() {
    return this.oxyTwoToFourPM;
  }

  public AuditPatientMonitoringForm oxyTwoToFourPM(Double oxyTwoToFourPM) {
    this.setOxyTwoToFourPM(oxyTwoToFourPM);
    return this;
  }

  public void setOxyTwoToFourPM(Double oxyTwoToFourPM) {
    this.oxyTwoToFourPM = oxyTwoToFourPM;
  }

  public Double getFourToSixPM() {
    return this.fourToSixPM;
  }

  public AuditPatientMonitoringForm fourToSixPM(Double fourToSixPM) {
    this.setFourToSixPM(fourToSixPM);
    return this;
  }

  public void setFourToSixPM(Double fourToSixPM) {
    this.fourToSixPM = fourToSixPM;
  }

  public Double getOxyFourToSixPM() {
    return this.oxyFourToSixPM;
  }

  public AuditPatientMonitoringForm oxyFourToSixPM(Double oxyFourToSixPM) {
    this.setOxyFourToSixPM(oxyFourToSixPM);
    return this;
  }

  public void setOxyFourToSixPM(Double oxyFourToSixPM) {
    this.oxyFourToSixPM = oxyFourToSixPM;
  }

  public Double getSixToEightPM() {
    return this.sixToEightPM;
  }

  public AuditPatientMonitoringForm sixToEightPM(Double sixToEightPM) {
    this.setSixToEightPM(sixToEightPM);
    return this;
  }

  public void setSixToEightPM(Double sixToEightPM) {
    this.sixToEightPM = sixToEightPM;
  }

  public Double getOxySixToEightPM() {
    return this.oxySixToEightPM;
  }

  public AuditPatientMonitoringForm oxySixToEightPM(Double oxySixToEightPM) {
    this.setOxySixToEightPM(oxySixToEightPM);
    return this;
  }

  public void setOxySixToEightPM(Double oxySixToEightPM) {
    this.oxySixToEightPM = oxySixToEightPM;
  }

  public Double getEightToTenPM() {
    return this.eightToTenPM;
  }

  public AuditPatientMonitoringForm eightToTenPM(Double eightToTenPM) {
    this.setEightToTenPM(eightToTenPM);
    return this;
  }

  public void setEightToTenPM(Double eightToTenPM) {
    this.eightToTenPM = eightToTenPM;
  }

  public Double getOxyEightToTenPM() {
    return this.oxyEightToTenPM;
  }

  public AuditPatientMonitoringForm oxyEightToTenPM(Double oxyEightToTenPM) {
    this.setOxyEightToTenPM(oxyEightToTenPM);
    return this;
  }

  public void setOxyEightToTenPM(Double oxyEightToTenPM) {
    this.oxyEightToTenPM = oxyEightToTenPM;
  }

  public Double getTenToTwelvePM() {
    return this.tenToTwelvePM;
  }

  public AuditPatientMonitoringForm tenToTwelvePM(Double tenToTwelvePM) {
    this.setTenToTwelvePM(tenToTwelvePM);
    return this;
  }

  public void setTenToTwelvePM(Double tenToTwelvePM) {
    this.tenToTwelvePM = tenToTwelvePM;
  }

  public Double getOxyTenToTwelvePM() {
    return this.oxyTenToTwelvePM;
  }

  public AuditPatientMonitoringForm oxyTenToTwelvePM(Double oxyTenToTwelvePM) {
    this.setOxyTenToTwelvePM(oxyTenToTwelvePM);
    return this;
  }

  public void setOxyTenToTwelvePM(Double oxyTenToTwelvePM) {
    this.oxyTenToTwelvePM = oxyTenToTwelvePM;
  }

  public Double getTwelveToTwoAM() {
    return this.twelveToTwoAM;
  }

  public AuditPatientMonitoringForm twelveToTwoAM(Double twelveToTwoAM) {
    this.setTwelveToTwoAM(twelveToTwoAM);
    return this;
  }

  public void setTwelveToTwoAM(Double twelveToTwoAM) {
    this.twelveToTwoAM = twelveToTwoAM;
  }

  public Double getOxyTwelveToTwoAM() {
    return this.oxyTwelveToTwoAM;
  }

  public AuditPatientMonitoringForm oxyTwelveToTwoAM(Double oxyTwelveToTwoAM) {
    this.setOxyTwelveToTwoAM(oxyTwelveToTwoAM);
    return this;
  }

  public void setOxyTwelveToTwoAM(Double oxyTwelveToTwoAM) {
    this.oxyTwelveToTwoAM = oxyTwelveToTwoAM;
  }

  public Double getTwoToFourAM() {
    return this.twoToFourAM;
  }

  public AuditPatientMonitoringForm twoToFourAM(Double twoToFourAM) {
    this.setTwoToFourAM(twoToFourAM);
    return this;
  }

  public void setTwoToFourAM(Double twoToFourAM) {
    this.twoToFourAM = twoToFourAM;
  }

  public Double getOxyTwoToFourAM() {
    return this.oxyTwoToFourAM;
  }

  public AuditPatientMonitoringForm oxyTwoToFourAM(Double oxyTwoToFourAM) {
    this.setOxyTwoToFourAM(oxyTwoToFourAM);
    return this;
  }

  public void setOxyTwoToFourAM(Double oxyTwoToFourAM) {
    this.oxyTwoToFourAM = oxyTwoToFourAM;
  }

  public Double getFourToSixAM() {
    return this.fourToSixAM;
  }

  public AuditPatientMonitoringForm fourToSixAM(Double fourToSixAM) {
    this.setFourToSixAM(fourToSixAM);
    return this;
  }

  public void setFourToSixAM(Double fourToSixAM) {
    this.fourToSixAM = fourToSixAM;
  }

  public Double getOxyFourToSixAM() {
    return this.oxyFourToSixAM;
  }

  public AuditPatientMonitoringForm oxyFourToSixAM(Double oxyFourToSixAM) {
    this.setOxyFourToSixAM(oxyFourToSixAM);
    return this;
  }

  public void setOxyFourToSixAM(Double oxyFourToSixAM) {
    this.oxyFourToSixAM = oxyFourToSixAM;
  }

  public String getDrName() {
    return this.drName;
  }

  public AuditPatientMonitoringForm drName(String drName) {
    this.setDrName(drName);
    return this;
  }

  public void setDrName(String drName) {
    this.drName = drName;
  }

  public String getNurseName() {
    return this.nurseName;
  }

  public AuditPatientMonitoringForm nurseName(String nurseName) {
    this.setNurseName(nurseName);
    return this;
  }

  public void setNurseName(String nurseName) {
    this.nurseName = nurseName;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public AuditPatientMonitoringForm createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

  public AuditPatientMonitoringForm createdDate(LocalDate createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public AuditPatientMonitoringForm lastModifiedBy(String lastModifiedBy) {
    this.setLastModifiedBy(lastModifiedBy);
    return this;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public LocalDate getLastModified() {
    return this.lastModified;
  }

  public AuditPatientMonitoringForm lastModified(LocalDate lastModified) {
    this.setLastModified(lastModified);
    return this;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getFreeField1() {
    return this.freeField1;
  }

  public AuditPatientMonitoringForm freeField1(String freeField1) {
    this.setFreeField1(freeField1);
    return this;
  }

  public void setFreeField1(String freeField1) {
    this.freeField1 = freeField1;
  }

  public String getFreeField2() {
    return this.freeField2;
  }

  public AuditPatientMonitoringForm freeField2(String freeField2) {
    this.setFreeField2(freeField2);
    return this;
  }

  public void setFreeField2(String freeField2) {
    this.freeField2 = freeField2;
  }

  public String getFreeField3() {
    return this.freeField3;
  }

  public AuditPatientMonitoringForm freeField3(String freeField3) {
    this.setFreeField3(freeField3);
    return this;
  }

  public void setFreeField3(String freeField3) {
    this.freeField3 = freeField3;
  }

  public String getFreeField4() {
    return this.freeField4;
  }

  public AuditPatientMonitoringForm freeField4(String freeField4) {
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
    if (this.audit != null) {
      this.audit.setAuditPatientMonitoringForm(null);
    }
    if (audit != null) {
      audit.setAuditPatientMonitoringForm(this);
    }
    this.audit = audit;
  }

  public AuditPatientMonitoringForm audit(Audit audit) {
    this.setAudit(audit);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AuditPatientMonitoringForm)) {
      return false;
    }
    return id != null && id.equals(((AuditPatientMonitoringForm) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
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
