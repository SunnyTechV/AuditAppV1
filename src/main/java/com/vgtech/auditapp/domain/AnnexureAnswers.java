package com.vgtech.auditapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AnnexureAnswers.
 */
@Entity
@Table(name = "annexure_answers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnnexureAnswers implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "form_name")
  private String formName;

  @Column(name = "type")
  private String type;

  @Column(name = "sub_type")
  private String subType;

  @Column(name = "compliance")
  private Boolean compliance;

  @Column(name = "comment")
  private String comment;

  @Column(name = "free_field_1")
  private String freeField1;

  @Column(name = "free_field_2")
  private String freeField2;

  @Column(name = "free_field_3")
  private String freeField3;

  @Column(name = "free_field_4")
  private String freeField4;

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

  @ManyToOne
  @JsonIgnoreProperties(value = { "annexureAnswers" }, allowSetters = true)
  private AnnexureQuestions annexureQuestions;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public AnnexureAnswers id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFormName() {
    return this.formName;
  }

  public AnnexureAnswers formName(String formName) {
    this.setFormName(formName);
    return this;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getType() {
    return this.type;
  }

  public AnnexureAnswers type(String type) {
    this.setType(type);
    return this;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return this.subType;
  }

  public AnnexureAnswers subType(String subType) {
    this.setSubType(subType);
    return this;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public Boolean getCompliance() {
    return this.compliance;
  }

  public AnnexureAnswers compliance(Boolean compliance) {
    this.setCompliance(compliance);
    return this;
  }

  public void setCompliance(Boolean compliance) {
    this.compliance = compliance;
  }

  public String getComment() {
    return this.comment;
  }

  public AnnexureAnswers comment(String comment) {
    this.setComment(comment);
    return this;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getFreeField1() {
    return this.freeField1;
  }

  public AnnexureAnswers freeField1(String freeField1) {
    this.setFreeField1(freeField1);
    return this;
  }

  public void setFreeField1(String freeField1) {
    this.freeField1 = freeField1;
  }

  public String getFreeField2() {
    return this.freeField2;
  }

  public AnnexureAnswers freeField2(String freeField2) {
    this.setFreeField2(freeField2);
    return this;
  }

  public void setFreeField2(String freeField2) {
    this.freeField2 = freeField2;
  }

  public String getFreeField3() {
    return this.freeField3;
  }

  public AnnexureAnswers freeField3(String freeField3) {
    this.setFreeField3(freeField3);
    return this;
  }

  public void setFreeField3(String freeField3) {
    this.freeField3 = freeField3;
  }

  public String getFreeField4() {
    return this.freeField4;
  }

  public AnnexureAnswers freeField4(String freeField4) {
    this.setFreeField4(freeField4);
    return this;
  }

  public void setFreeField4(String freeField4) {
    this.freeField4 = freeField4;
  }

  public String getRemark() {
    return this.remark;
  }

  public AnnexureAnswers remark(String remark) {
    this.setRemark(remark);
    return this;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

  public AnnexureAnswers createdDate(LocalDate createdDate) {
    this.setCreatedDate(createdDate);
    return this;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public AnnexureAnswers createdBy(String createdBy) {
    this.setCreatedBy(createdBy);
    return this;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getLastModified() {
    return this.lastModified;
  }

  public AnnexureAnswers lastModified(LocalDate lastModified) {
    this.setLastModified(lastModified);
    return this;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getLastModifiedBy() {
    return this.lastModifiedBy;
  }

  public AnnexureAnswers lastModifiedBy(String lastModifiedBy) {
    this.setLastModifiedBy(lastModifiedBy);
    return this;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Audit getAudit() {
    return this.audit;
  }

  public void setAudit(Audit audit) {
    this.audit = audit;
  }

  public AnnexureAnswers audit(Audit audit) {
    this.setAudit(audit);
    return this;
  }

  public AnnexureQuestions getAnnexureQuestions() {
    return this.annexureQuestions;
  }

  public void setAnnexureQuestions(AnnexureQuestions annexureQuestions) {
    this.annexureQuestions = annexureQuestions;
  }

  public AnnexureAnswers annexureQuestions(
    AnnexureQuestions annexureQuestions
  ) {
    this.setAnnexureQuestions(annexureQuestions);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AnnexureAnswers)) {
      return false;
    }
    return id != null && id.equals(((AnnexureAnswers) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AnnexureAnswers{" +
            "id=" + getId() +
            ", formName='" + getFormName() + "'" +
            ", type='" + getType() + "'" +
            ", subType='" + getSubType() + "'" +
            ", compliance='" + getCompliance() + "'" +
            ", comment='" + getComment() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
