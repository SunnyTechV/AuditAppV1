package com.vgtech.auditapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.vgtech.auditapp.domain.AnnexureAnswers} entity.
 */
public class AnnexureAnswers implements Serializable {

  private Long id;

  private String formName;

  private String type;

  private String subType;

  private Boolean compliance;

  private String comment;

  private String freeField1;

  private String freeField2;

  private String freeField3;

  private String freeField4;

  private String remark;

  private LocalDate createdDate;

  private String createdBy;

  private LocalDate lastModified;

  private String lastModifiedBy;

  private AuditDTO audit;

  private AnnexureQuestionsDTO annexureQuestions;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubType() {
    return subType;
  }

  public void setSubType(String subType) {
    this.subType = subType;
  }

  public Boolean getCompliance() {
    return compliance;
  }

  public void setCompliance(Boolean compliance) {
    this.compliance = compliance;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
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

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDate getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDate lastModified) {
    this.lastModified = lastModified;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public AuditDTO getAudit() {
    return audit;
  }

  public void setAudit(AuditDTO audit) {
    this.audit = audit;
  }

  public AnnexureQuestionsDTO getAnnexureQuestions() {
    return annexureQuestions;
  }

  public void setAnnexureQuestions(AnnexureQuestionsDTO annexureQuestions) {
    this.annexureQuestions = annexureQuestions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AnnexureAnswers)) {
      return false;
    }

    AnnexureAnswers annexureAnswers = (AnnexureAnswers) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, annexureAnswers.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
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
            ", audit=" + getAudit() +
            ", annexureQuestions=" + getAnnexureQuestions() +
            "}";
    }
}
