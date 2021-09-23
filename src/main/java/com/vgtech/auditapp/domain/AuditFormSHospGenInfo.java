package com.vgtech.auditapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AuditFormSHospGenInfo.
 */
@Entity
@Table(name = "audit_form_s_hosp_gen_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuditFormSHospGenInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "hosp_name")
  private String hospName;

  @Column(name = "hosp_type")
  private String hospType;

  @Column(name = "type")
  private String type;

  @Column(name = "sub_type")
  private String subType;

  @Column(name = "form_name")
  private String formName;

  @Column(name = "incharge_name")
  private String inchargeName;

  @Column(name = "hosp_address")
  private String hospAddress;

  @Column(name = "hosp_phone_no")
  private String hospPhoneNo;

  @Column(name = "normal_beds")
  private Integer normalBeds;

  @Column(name = "oxygen_beds")
  private Integer oxygenBeds;

  @Column(name = "ventilator_beds")
  private Integer ventilatorBeds;

  @Column(name = "icu_beds")
  private Integer icuBeds;

  @Column(name = "on_cylinder_patient")
  private Integer onCylinderPatient;

  @Column(name = "on_piped_beds_patient")
  private Integer onPipedBedsPatient;

  @Column(name = "on_niv")
  private Integer onNIV;

  @Column(name = "on_intubated")
  private Integer onIntubated;

  @Column(name = "jumbo_system_installed_type")
  private String jumboSystemInstalledType;

  @Column(name = "available_cylinder_type_d_7")
  private Integer availableCylinderTypeD7;

  @Column(name = "available_cylinder_type_b")
  private Integer availableCylinderTypeB;

  @Column(name = "cylinder_agency_name")
  private String cylinderAgencyName;

  @Column(name = "cylinder_agency_address")
  private String cylinderAgencyAddress;

  @Column(name = "available_lmo_capacity_kl")
  private Double availableLMOCapacityKL;

  @Column(name = "lmo_supplier_name")
  private String lmoSupplierName;

  @Column(name = "lmo_supplier_frequency")
  private Integer lmoSupplierFrequency;

  @Column(name = "last_lmo_supplied_quantity")
  private Double lastLmoSuppliedQuantity;

  @Column(name = "remark")
  private String remark;

  @Column(name = "created_date")
  private LocalDate createdDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "last_modified")
  private LocalDate lastModified;

  @Column(name = "last_modified_by")
  private String lastModifiedBy;

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

  public AuditFormSHospGenInfo id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getHospName() {
    return this.hospName;
  }

  public AuditFormSHospGenInfo hospName(String hospName) {
    this.setHospName(hospName);
    return this;
  }

  public void setHospName(String hospName) {
    this.hospName = hospName;
  }

  public String getHospType() {
    return this.hospType;
  }

  public AuditFormSHospGenInfo hospType(String hospType) {
    this.setHospType(hospType);
    return this;
  }

  public void setHospType(String hospType) {
    this.hospType = hospType;
  }

  public String getType() {
    return this.type;
  }

  public AuditFormSHospGenInfo type(String type) {
    this.setType(type);
    return this;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return this.subType;
  }

  public AuditFormSHospGenInfo subType(String subType) {
    this.setSubType(subType);
    return this;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public String getFormName() {
    return this.formName;
  }

  public AuditFormSHospGenInfo formName(String formName) {
    this.setFormName(formName);
    return this;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getInchargeName() {
    return this.inchargeName;
  }

  public AuditFormSHospGenInfo inchargeName(String inchargeName) {
    this.setInchargeName(inchargeName);
    return this;
  }

  public void setInchargeName(String inchargeName) {
    this.inchargeName = inchargeName;
  }

  public String getHospAddress() {
    return this.hospAddress;
  }

  public AuditFormSHospGenInfo hospAddress(String hospAddress) {
    this.setHospAddress(hospAddress);
    return this;
  }

  public void setHospAddress(String hospAddress) {
    this.hospAddress = hospAddress;
  }

  public String getHospPhoneNo() {
    return this.hospPhoneNo;
  }

  public AuditFormSHospGenInfo hospPhoneNo(String hospPhoneNo) {
    this.setHospPhoneNo(hospPhoneNo);
    return this;
  }

  public void setHospPhoneNo(String hospPhoneNo) {
    this.hospPhoneNo = hospPhoneNo;
  }

  public Integer getNormalBeds() {
    return this.normalBeds;
  }

  public AuditFormSHospGenInfo normalBeds(Integer normalBeds) {
    this.setNormalBeds(normalBeds);
    return this;
  }

  public void setNormalBeds(Integer normalBeds) {
    this.normalBeds = normalBeds;
  }

  public Integer getOxygenBeds() {
    return this.oxygenBeds;
  }

  public AuditFormSHospGenInfo oxygenBeds(Integer oxygenBeds) {
    this.setOxygenBeds(oxygenBeds);
    return this;
  }

  public void setOxygenBeds(Integer oxygenBeds) {
    this.oxygenBeds = oxygenBeds;
  }

  public Integer getVentilatorBeds() {
    return this.ventilatorBeds;
  }

  public AuditFormSHospGenInfo ventilatorBeds(Integer ventilatorBeds) {
    this.setVentilatorBeds(ventilatorBeds);
    return this;
  }

  public void setVentilatorBeds(Integer ventilatorBeds) {
    this.ventilatorBeds = ventilatorBeds;
  }

  public Integer getIcuBeds() {
    return this.icuBeds;
  }

  public AuditFormSHospGenInfo icuBeds(Integer icuBeds) {
    this.setIcuBeds(icuBeds);
    return this;
  }

  public void setIcuBeds(Integer icuBeds) {
    this.icuBeds = icuBeds;
  }

  public Integer getOnCylinderPatient() {
    return this.onCylinderPatient;
  }

  public AuditFormSHospGenInfo onCylinderPatient(Integer onCylinderPatient) {
    this.setOnCylinderPatient(onCylinderPatient);
    return this;
  }

  public void setOnCylinderPatient(Integer onCylinderPatient) {
    this.onCylinderPatient = onCylinderPatient;
  }

  public Integer getOnPipedBedsPatient() {
    return this.onPipedBedsPatient;
  }

  public AuditFormSHospGenInfo onPipedBedsPatient(Integer onPipedBedsPatient) {
    this.setOnPipedBedsPatient(onPipedBedsPatient);
    return this;
  }

  public void setOnPipedBedsPatient(Integer onPipedBedsPatient) {
    this.onPipedBedsPatient = onPipedBedsPatient;
  }

  public Integer getOnNIV() {
    return this.onNIV;
  }

  public AuditFormSHospGenInfo onNIV(Integer onNIV) {
    this.setOnNIV(onNIV);
    return this;
  }

  public void setOnNIV(Integer onNIV) {
    this.onNIV = onNIV;
  }

  public Integer getOnIntubated() {
    return this.onIntubated;
  }

  public AuditFormSHospGenInfo onIntubated(Integer onIntubated) {
    this.setOnIntubated(onIntubated);
    return this;
  }

  public void setOnIntubated(Integer onIntubated) {
    this.onIntubated = onIntubated;
  }

  public String getJumboSystemInstalledType() {
    return this.jumboSystemInstalledType;
  }

  public AuditFormSHospGenInfo jumboSystemInstalledType(
    String jumboSystemInstalledType
  ) {
    this.setJumboSystemInstalledType(jumboSystemInstalledType);
    return this;
  }

  public void setJumboSystemInstalledType(String jumboSystemInstalledType) {
    this.jumboSystemInstalledType = jumboSystemInstalledType;
  }

  public Integer getAvailableCylinderTypeD7() {
    return this.availableCylinderTypeD7;
  }

  public AuditFormSHospGenInfo availableCylinderTypeD7(
    Integer availableCylinderTypeD7
  ) {
    this.setAvailableCylinderTypeD7(availableCylinderTypeD7);
    return this;
  }

  public void setAvailableCylinderTypeD7(Integer availableCylinderTypeD7) {
    this.availableCylinderTypeD7 = availableCylinderTypeD7;
  }

  public Integer getAvailableCylinderTypeB() {
    return this.availableCylinderTypeB;
  }

  public AuditFormSHospGenInfo availableCylinderTypeB(
    Integer availableCylinderTypeB
  ) {
    this.setAvailableCylinderTypeB(availableCylinderTypeB);
    return this;
  }

  public void setAvailableCylinderTypeB(Integer availableCylinderTypeB) {
    this.availableCylinderTypeB = availableCylinderTypeB;
  }

  public String getCylinderAgencyName() {
    return this.cylinderAgencyName;
  }

  public AuditFormSHospGenInfo cylinderAgencyName(String cylinderAgencyName) {
    this.setCylinderAgencyName(cylinderAgencyName);
    return this;
  }

  public void setCylinderAgencyName(String cylinderAgencyName) {
    this.cylinderAgencyName = cylinderAgencyName;
  }

  public String getCylinderAgencyAddress() {
    return this.cylinderAgencyAddress;
  }

  public AuditFormSHospGenInfo cylinderAgencyAddress(
    String cylinderAgencyAddress
  ) {
    this.setCylinderAgencyAddress(cylinderAgencyAddress);
    return this;
  }

  public void setCylinderAgencyAddress(String cylinderAgencyAddress) {
    this.cylinderAgencyAddress = cylinderAgencyAddress;
  }

  public Double getAvailableLMOCapacityKL() {
    return this.availableLMOCapacityKL;
  }

  public AuditFormSHospGenInfo availableLMOCapacityKL(
    Double availableLMOCapacityKL
  ) {
    this.setAvailableLMOCapacityKL(availableLMOCapacityKL);
    return this;
  }

  public void setAvailableLMOCapacityKL(Double availableLMOCapacityKL) {
    this.availableLMOCapacityKL = availableLMOCapacityKL;
  }

  public String getLmoSupplierName() {
    return this.lmoSupplierName;
  }

  public AuditFormSHospGenInfo lmoSupplierName(String lmoSupplierName) {
    this.setLmoSupplierName(lmoSupplierName);
    return this;
  }

  public void setLmoSupplierName(String lmoSupplierName) {
    this.lmoSupplierName = lmoSupplierName;
  }

  public Integer getLmoSupplierFrequency() {
    return this.lmoSupplierFrequency;
  }

  public AuditFormSHospGenInfo lmoSupplierFrequency(
    Integer lmoSupplierFrequency
  ) {
    this.setLmoSupplierFrequency(lmoSupplierFrequency);
    return this;
  }

  public void setLmoSupplierFrequency(Integer lmoSupplierFrequency) {
    this.lmoSupplierFrequency = lmoSupplierFrequency;
  }

  public Double getLastLmoSuppliedQuantity() {
    return this.lastLmoSuppliedQuantity;
  }

  public AuditFormSHospGenInfo lastLmoSuppliedQuantity(
    Double lastLmoSuppliedQuantity
  ) {
    this.setLastLmoSuppliedQuantity(lastLmoSuppliedQuantity);
    return this;
  }

  public void setLastLmoSuppliedQuantity(Double lastLmoSuppliedQuantity) {
    this.lastLmoSuppliedQuantity = lastLmoSuppliedQuantity;
  }

  public String getRemark() {
    return this.remark;
  }

  public AuditFormSHospGenInfo remark(String remark) {
    this.setRemark(remark);
    return this;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

  public AuditFormSHospGenInfo createdDate(LocalDate createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public AuditFormSHospGenInfo createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getLastModified() {
    return this.lastModified;
  }

  public AuditFormSHospGenInfo lastModified(LocalDate lastModified) {
    this.setLastModified(lastModified);
    return this;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public AuditFormSHospGenInfo lastModifiedBy(String lastModifiedBy) {
    this.setLastModifiedBy(lastModifiedBy);
    return this;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public String getFreeField1() {
    return this.freeField1;
  }

  public AuditFormSHospGenInfo freeField1(String freeField1) {
    this.setFreeField1(freeField1);
    return this;
  }

  public void setFreeField1(String freeField1) {
    this.freeField1 = freeField1;
  }

  public String getFreeField2() {
    return this.freeField2;
  }

  public AuditFormSHospGenInfo freeField2(String freeField2) {
    this.setFreeField2(freeField2);
    return this;
  }

  public void setFreeField2(String freeField2) {
    this.freeField2 = freeField2;
  }

  public String getFreeField3() {
    return this.freeField3;
  }

  public AuditFormSHospGenInfo freeField3(String freeField3) {
    this.setFreeField3(freeField3);
    return this;
  }

  public void setFreeField3(String freeField3) {
    this.freeField3 = freeField3;
  }

  public String getFreeField4() {
    return this.freeField4;
  }

  public AuditFormSHospGenInfo freeField4(String freeField4) {
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

  public AuditFormSHospGenInfo audit(Audit audit) {
    this.setAudit(audit);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AuditFormSHospGenInfo)) {
      return false;
    }
    return id != null && id.equals(((AuditFormSHospGenInfo) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
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
            "}";
    }
}
