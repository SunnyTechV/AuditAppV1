package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.AnnexureAnswers;
import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.domain.AuditFormSHospGenInfo;
import com.vgtech.auditapp.domain.AuditPatientMonitoringForm;
import com.vgtech.auditapp.domain.FireElectricalAudit;
import com.vgtech.auditapp.domain.InventoryReport;
import com.vgtech.auditapp.domain.InventorySupplierDetails;
import com.vgtech.auditapp.domain.OxygenConsumptionData;
import com.vgtech.auditapp.repository.AuditRepository;
import com.vgtech.auditapp.service.criteria.AuditCriteria;
import com.vgtech.auditapp.service.dto.Audit;
import com.vgtech.auditapp.service.mapper.AuditMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AuditResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AuditResourceIT {

  private static final LocalDate DEFAULT_AUDIT_DATE = LocalDate.ofEpochDay(0L);
  private static final LocalDate UPDATED_AUDIT_DATE = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_AUDIT_DATE = LocalDate.ofEpochDay(-1L);

  private static final String DEFAULT_HOSP_NAME = "AAAAAAAAAA";
  private static final String UPDATED_HOSP_NAME = "BBBBBBBBBB";

  private static final Boolean DEFAULT_IS_AUDIT_COMPLETE = false;
  private static final Boolean UPDATED_IS_AUDIT_COMPLETE = true;

  private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_4 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_4 = "BBBBBBBBBB";

  private static final String DEFAULT_REMARK = "AAAAAAAAAA";
  private static final String UPDATED_REMARK = "BBBBBBBBBB";

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

  private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_CREATED_DATE = LocalDate.ofEpochDay(
    -1L
  );

  private static final LocalDate DEFAULT_LAST_MODIFIED = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_LAST_MODIFIED = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_LAST_MODIFIED = LocalDate.ofEpochDay(
    -1L
  );

  private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
  private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

  private static final String ENTITY_API_URL = "/api/audits";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private AuditRepository auditRepository;

  @Autowired
  private AuditMapper auditMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restAuditMockMvc;

  private Audit audit;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Audit createEntity(EntityManager em) {
    Audit audit = new Audit()
      .auditDate(DEFAULT_AUDIT_DATE)
      .hospName(DEFAULT_HOSP_NAME)
      .isAuditComplete(DEFAULT_IS_AUDIT_COMPLETE)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .freeField4(DEFAULT_FREE_FIELD_4)
      .remark(DEFAULT_REMARK)
      .createdBy(DEFAULT_CREATED_BY)
      .createdDate(DEFAULT_CREATED_DATE)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
    return audit;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Audit createUpdatedEntity(EntityManager em) {
    Audit audit = new Audit()
      .auditDate(UPDATED_AUDIT_DATE)
      .hospName(UPDATED_HOSP_NAME)
      .isAuditComplete(UPDATED_IS_AUDIT_COMPLETE)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .remark(UPDATED_REMARK)
      .createdBy(UPDATED_CREATED_BY)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    return audit;
  }

  @BeforeEach
  public void initTest() {
    audit = createEntity(em);
  }

  @Test
  @Transactional
  void createAudit() throws Exception {
    int databaseSizeBeforeCreate = auditRepository.findAll().size();
    // Create the Audit
    Audit audit = auditMapper.toDto(audit);
    restAuditMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(audit))
      )
      .andExpect(status().isCreated());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeCreate + 1);
    Audit testAudit = auditList.get(auditList.size() - 1);
    assertThat(testAudit.getAuditDate()).isEqualTo(DEFAULT_AUDIT_DATE);
    assertThat(testAudit.getHospName()).isEqualTo(DEFAULT_HOSP_NAME);
    assertThat(testAudit.getIsAuditComplete())
      .isEqualTo(DEFAULT_IS_AUDIT_COMPLETE);
    assertThat(testAudit.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testAudit.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testAudit.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testAudit.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
    assertThat(testAudit.getRemark()).isEqualTo(DEFAULT_REMARK);
    assertThat(testAudit.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testAudit.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testAudit.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testAudit.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void createAuditWithExistingId() throws Exception {
    // Create the Audit with an existing ID
    audit.setId(1L);
    Audit audit = auditMapper.toDto(audit);

    int databaseSizeBeforeCreate = auditRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restAuditMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(audit))
      )
      .andExpect(status().isBadRequest());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllAudits() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList
    restAuditMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(audit.getId().intValue())))
      .andExpect(
        jsonPath("$.[*].auditDate")
          .value(hasItem(DEFAULT_AUDIT_DATE.toString()))
      )
      .andExpect(jsonPath("$.[*].hospName").value(hasItem(DEFAULT_HOSP_NAME)))
      .andExpect(
        jsonPath("$.[*].isAuditComplete")
          .value(hasItem(DEFAULT_IS_AUDIT_COMPLETE.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1))
      )
      .andExpect(
        jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2))
      )
      .andExpect(
        jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3))
      )
      .andExpect(
        jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4))
      )
      .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(
        jsonPath("$.[*].createdDate")
          .value(hasItem(DEFAULT_CREATED_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].lastModified")
          .value(hasItem(DEFAULT_LAST_MODIFIED.toString()))
      )
      .andExpect(
        jsonPath("$.[*].lastModifiedBy")
          .value(hasItem(DEFAULT_LAST_MODIFIED_BY))
      );
  }

  @Test
  @Transactional
  void getAudit() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get the audit
    restAuditMockMvc
      .perform(get(ENTITY_API_URL_ID, audit.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(audit.getId().intValue()))
      .andExpect(jsonPath("$.auditDate").value(DEFAULT_AUDIT_DATE.toString()))
      .andExpect(jsonPath("$.hospName").value(DEFAULT_HOSP_NAME))
      .andExpect(
        jsonPath("$.isAuditComplete")
          .value(DEFAULT_IS_AUDIT_COMPLETE.booleanValue())
      )
      .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
      .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
      .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
      .andExpect(jsonPath("$.freeField4").value(DEFAULT_FREE_FIELD_4))
      .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
      .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
      .andExpect(
        jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString())
      )
      .andExpect(
        jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString())
      )
      .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
  }

  @Test
  @Transactional
  void getAuditsByIdFiltering() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    Long id = audit.getId();

    defaultAuditShouldBeFound("id.equals=" + id);
    defaultAuditShouldNotBeFound("id.notEquals=" + id);

    defaultAuditShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultAuditShouldNotBeFound("id.greaterThan=" + id);

    defaultAuditShouldBeFound("id.lessThanOrEqual=" + id);
    defaultAuditShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllAuditsByAuditDateIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where auditDate equals to DEFAULT_AUDIT_DATE
    defaultAuditShouldBeFound("auditDate.equals=" + DEFAULT_AUDIT_DATE);

    // Get all the auditList where auditDate equals to UPDATED_AUDIT_DATE
    defaultAuditShouldNotBeFound("auditDate.equals=" + UPDATED_AUDIT_DATE);
  }

  @Test
  @Transactional
  void getAllAuditsByAuditDateIsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where auditDate not equals to DEFAULT_AUDIT_DATE
    defaultAuditShouldNotBeFound("auditDate.notEquals=" + DEFAULT_AUDIT_DATE);

    // Get all the auditList where auditDate not equals to UPDATED_AUDIT_DATE
    defaultAuditShouldBeFound("auditDate.notEquals=" + UPDATED_AUDIT_DATE);
  }

  @Test
  @Transactional
  void getAllAuditsByAuditDateIsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where auditDate in DEFAULT_AUDIT_DATE or UPDATED_AUDIT_DATE
    defaultAuditShouldBeFound(
      "auditDate.in=" + DEFAULT_AUDIT_DATE + "," + UPDATED_AUDIT_DATE
    );

    // Get all the auditList where auditDate equals to UPDATED_AUDIT_DATE
    defaultAuditShouldNotBeFound("auditDate.in=" + UPDATED_AUDIT_DATE);
  }

  @Test
  @Transactional
  void getAllAuditsByAuditDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where auditDate is not null
    defaultAuditShouldBeFound("auditDate.specified=true");

    // Get all the auditList where auditDate is null
    defaultAuditShouldNotBeFound("auditDate.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByAuditDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where auditDate is greater than or equal to DEFAULT_AUDIT_DATE
    defaultAuditShouldBeFound(
      "auditDate.greaterThanOrEqual=" + DEFAULT_AUDIT_DATE
    );

    // Get all the auditList where auditDate is greater than or equal to UPDATED_AUDIT_DATE
    defaultAuditShouldNotBeFound(
      "auditDate.greaterThanOrEqual=" + UPDATED_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditsByAuditDateIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where auditDate is less than or equal to DEFAULT_AUDIT_DATE
    defaultAuditShouldBeFound(
      "auditDate.lessThanOrEqual=" + DEFAULT_AUDIT_DATE
    );

    // Get all the auditList where auditDate is less than or equal to SMALLER_AUDIT_DATE
    defaultAuditShouldNotBeFound(
      "auditDate.lessThanOrEqual=" + SMALLER_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditsByAuditDateIsLessThanSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where auditDate is less than DEFAULT_AUDIT_DATE
    defaultAuditShouldNotBeFound("auditDate.lessThan=" + DEFAULT_AUDIT_DATE);

    // Get all the auditList where auditDate is less than UPDATED_AUDIT_DATE
    defaultAuditShouldBeFound("auditDate.lessThan=" + UPDATED_AUDIT_DATE);
  }

  @Test
  @Transactional
  void getAllAuditsByAuditDateIsGreaterThanSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where auditDate is greater than DEFAULT_AUDIT_DATE
    defaultAuditShouldNotBeFound("auditDate.greaterThan=" + DEFAULT_AUDIT_DATE);

    // Get all the auditList where auditDate is greater than SMALLER_AUDIT_DATE
    defaultAuditShouldBeFound("auditDate.greaterThan=" + SMALLER_AUDIT_DATE);
  }

  @Test
  @Transactional
  void getAllAuditsByHospNameIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where hospName equals to DEFAULT_HOSP_NAME
    defaultAuditShouldBeFound("hospName.equals=" + DEFAULT_HOSP_NAME);

    // Get all the auditList where hospName equals to UPDATED_HOSP_NAME
    defaultAuditShouldNotBeFound("hospName.equals=" + UPDATED_HOSP_NAME);
  }

  @Test
  @Transactional
  void getAllAuditsByHospNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where hospName not equals to DEFAULT_HOSP_NAME
    defaultAuditShouldNotBeFound("hospName.notEquals=" + DEFAULT_HOSP_NAME);

    // Get all the auditList where hospName not equals to UPDATED_HOSP_NAME
    defaultAuditShouldBeFound("hospName.notEquals=" + UPDATED_HOSP_NAME);
  }

  @Test
  @Transactional
  void getAllAuditsByHospNameIsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where hospName in DEFAULT_HOSP_NAME or UPDATED_HOSP_NAME
    defaultAuditShouldBeFound(
      "hospName.in=" + DEFAULT_HOSP_NAME + "," + UPDATED_HOSP_NAME
    );

    // Get all the auditList where hospName equals to UPDATED_HOSP_NAME
    defaultAuditShouldNotBeFound("hospName.in=" + UPDATED_HOSP_NAME);
  }

  @Test
  @Transactional
  void getAllAuditsByHospNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where hospName is not null
    defaultAuditShouldBeFound("hospName.specified=true");

    // Get all the auditList where hospName is null
    defaultAuditShouldNotBeFound("hospName.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByHospNameContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where hospName contains DEFAULT_HOSP_NAME
    defaultAuditShouldBeFound("hospName.contains=" + DEFAULT_HOSP_NAME);

    // Get all the auditList where hospName contains UPDATED_HOSP_NAME
    defaultAuditShouldNotBeFound("hospName.contains=" + UPDATED_HOSP_NAME);
  }

  @Test
  @Transactional
  void getAllAuditsByHospNameNotContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where hospName does not contain DEFAULT_HOSP_NAME
    defaultAuditShouldNotBeFound(
      "hospName.doesNotContain=" + DEFAULT_HOSP_NAME
    );

    // Get all the auditList where hospName does not contain UPDATED_HOSP_NAME
    defaultAuditShouldBeFound("hospName.doesNotContain=" + UPDATED_HOSP_NAME);
  }

  @Test
  @Transactional
  void getAllAuditsByIsAuditCompleteIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where isAuditComplete equals to DEFAULT_IS_AUDIT_COMPLETE
    defaultAuditShouldBeFound(
      "isAuditComplete.equals=" + DEFAULT_IS_AUDIT_COMPLETE
    );

    // Get all the auditList where isAuditComplete equals to UPDATED_IS_AUDIT_COMPLETE
    defaultAuditShouldNotBeFound(
      "isAuditComplete.equals=" + UPDATED_IS_AUDIT_COMPLETE
    );
  }

  @Test
  @Transactional
  void getAllAuditsByIsAuditCompleteIsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where isAuditComplete not equals to DEFAULT_IS_AUDIT_COMPLETE
    defaultAuditShouldNotBeFound(
      "isAuditComplete.notEquals=" + DEFAULT_IS_AUDIT_COMPLETE
    );

    // Get all the auditList where isAuditComplete not equals to UPDATED_IS_AUDIT_COMPLETE
    defaultAuditShouldBeFound(
      "isAuditComplete.notEquals=" + UPDATED_IS_AUDIT_COMPLETE
    );
  }

  @Test
  @Transactional
  void getAllAuditsByIsAuditCompleteIsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where isAuditComplete in DEFAULT_IS_AUDIT_COMPLETE or UPDATED_IS_AUDIT_COMPLETE
    defaultAuditShouldBeFound(
      "isAuditComplete.in=" +
      DEFAULT_IS_AUDIT_COMPLETE +
      "," +
      UPDATED_IS_AUDIT_COMPLETE
    );

    // Get all the auditList where isAuditComplete equals to UPDATED_IS_AUDIT_COMPLETE
    defaultAuditShouldNotBeFound(
      "isAuditComplete.in=" + UPDATED_IS_AUDIT_COMPLETE
    );
  }

  @Test
  @Transactional
  void getAllAuditsByIsAuditCompleteIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where isAuditComplete is not null
    defaultAuditShouldBeFound("isAuditComplete.specified=true");

    // Get all the auditList where isAuditComplete is null
    defaultAuditShouldNotBeFound("isAuditComplete.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField1IsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultAuditShouldBeFound("freeField1.equals=" + DEFAULT_FREE_FIELD_1);

    // Get all the auditList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAuditShouldNotBeFound("freeField1.equals=" + UPDATED_FREE_FIELD_1);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField1IsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultAuditShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultAuditShouldBeFound("freeField1.notEquals=" + UPDATED_FREE_FIELD_1);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField1IsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultAuditShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the auditList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAuditShouldNotBeFound("freeField1.in=" + UPDATED_FREE_FIELD_1);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField1IsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField1 is not null
    defaultAuditShouldBeFound("freeField1.specified=true");

    // Get all the auditList where freeField1 is null
    defaultAuditShouldNotBeFound("freeField1.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField1ContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultAuditShouldBeFound("freeField1.contains=" + DEFAULT_FREE_FIELD_1);

    // Get all the auditList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultAuditShouldNotBeFound("freeField1.contains=" + UPDATED_FREE_FIELD_1);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField1NotContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultAuditShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultAuditShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField2IsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultAuditShouldBeFound("freeField2.equals=" + DEFAULT_FREE_FIELD_2);

    // Get all the auditList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAuditShouldNotBeFound("freeField2.equals=" + UPDATED_FREE_FIELD_2);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField2IsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultAuditShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultAuditShouldBeFound("freeField2.notEquals=" + UPDATED_FREE_FIELD_2);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField2IsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultAuditShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the auditList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAuditShouldNotBeFound("freeField2.in=" + UPDATED_FREE_FIELD_2);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField2IsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField2 is not null
    defaultAuditShouldBeFound("freeField2.specified=true");

    // Get all the auditList where freeField2 is null
    defaultAuditShouldNotBeFound("freeField2.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField2ContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultAuditShouldBeFound("freeField2.contains=" + DEFAULT_FREE_FIELD_2);

    // Get all the auditList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultAuditShouldNotBeFound("freeField2.contains=" + UPDATED_FREE_FIELD_2);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField2NotContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultAuditShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultAuditShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField3IsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultAuditShouldBeFound("freeField3.equals=" + DEFAULT_FREE_FIELD_3);

    // Get all the auditList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAuditShouldNotBeFound("freeField3.equals=" + UPDATED_FREE_FIELD_3);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField3IsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultAuditShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultAuditShouldBeFound("freeField3.notEquals=" + UPDATED_FREE_FIELD_3);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField3IsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultAuditShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the auditList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAuditShouldNotBeFound("freeField3.in=" + UPDATED_FREE_FIELD_3);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField3IsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField3 is not null
    defaultAuditShouldBeFound("freeField3.specified=true");

    // Get all the auditList where freeField3 is null
    defaultAuditShouldNotBeFound("freeField3.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField3ContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultAuditShouldBeFound("freeField3.contains=" + DEFAULT_FREE_FIELD_3);

    // Get all the auditList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultAuditShouldNotBeFound("freeField3.contains=" + UPDATED_FREE_FIELD_3);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField3NotContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultAuditShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultAuditShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField4IsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField4 equals to DEFAULT_FREE_FIELD_4
    defaultAuditShouldBeFound("freeField4.equals=" + DEFAULT_FREE_FIELD_4);

    // Get all the auditList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAuditShouldNotBeFound("freeField4.equals=" + UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField4IsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField4 not equals to DEFAULT_FREE_FIELD_4
    defaultAuditShouldNotBeFound(
      "freeField4.notEquals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditList where freeField4 not equals to UPDATED_FREE_FIELD_4
    defaultAuditShouldBeFound("freeField4.notEquals=" + UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField4IsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
    defaultAuditShouldBeFound(
      "freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4
    );

    // Get all the auditList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAuditShouldNotBeFound("freeField4.in=" + UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField4IsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField4 is not null
    defaultAuditShouldBeFound("freeField4.specified=true");

    // Get all the auditList where freeField4 is null
    defaultAuditShouldNotBeFound("freeField4.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField4ContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField4 contains DEFAULT_FREE_FIELD_4
    defaultAuditShouldBeFound("freeField4.contains=" + DEFAULT_FREE_FIELD_4);

    // Get all the auditList where freeField4 contains UPDATED_FREE_FIELD_4
    defaultAuditShouldNotBeFound("freeField4.contains=" + UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void getAllAuditsByFreeField4NotContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where freeField4 does not contain DEFAULT_FREE_FIELD_4
    defaultAuditShouldNotBeFound(
      "freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditList where freeField4 does not contain UPDATED_FREE_FIELD_4
    defaultAuditShouldBeFound(
      "freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditsByRemarkIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where remark equals to DEFAULT_REMARK
    defaultAuditShouldBeFound("remark.equals=" + DEFAULT_REMARK);

    // Get all the auditList where remark equals to UPDATED_REMARK
    defaultAuditShouldNotBeFound("remark.equals=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAuditsByRemarkIsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where remark not equals to DEFAULT_REMARK
    defaultAuditShouldNotBeFound("remark.notEquals=" + DEFAULT_REMARK);

    // Get all the auditList where remark not equals to UPDATED_REMARK
    defaultAuditShouldBeFound("remark.notEquals=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAuditsByRemarkIsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where remark in DEFAULT_REMARK or UPDATED_REMARK
    defaultAuditShouldBeFound(
      "remark.in=" + DEFAULT_REMARK + "," + UPDATED_REMARK
    );

    // Get all the auditList where remark equals to UPDATED_REMARK
    defaultAuditShouldNotBeFound("remark.in=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAuditsByRemarkIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where remark is not null
    defaultAuditShouldBeFound("remark.specified=true");

    // Get all the auditList where remark is null
    defaultAuditShouldNotBeFound("remark.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByRemarkContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where remark contains DEFAULT_REMARK
    defaultAuditShouldBeFound("remark.contains=" + DEFAULT_REMARK);

    // Get all the auditList where remark contains UPDATED_REMARK
    defaultAuditShouldNotBeFound("remark.contains=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAuditsByRemarkNotContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where remark does not contain DEFAULT_REMARK
    defaultAuditShouldNotBeFound("remark.doesNotContain=" + DEFAULT_REMARK);

    // Get all the auditList where remark does not contain UPDATED_REMARK
    defaultAuditShouldBeFound("remark.doesNotContain=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedByIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdBy equals to DEFAULT_CREATED_BY
    defaultAuditShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

    // Get all the auditList where createdBy equals to UPDATED_CREATED_BY
    defaultAuditShouldNotBeFound("createdBy.equals=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedByIsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdBy not equals to DEFAULT_CREATED_BY
    defaultAuditShouldNotBeFound("createdBy.notEquals=" + DEFAULT_CREATED_BY);

    // Get all the auditList where createdBy not equals to UPDATED_CREATED_BY
    defaultAuditShouldBeFound("createdBy.notEquals=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedByIsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultAuditShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the auditList where createdBy equals to UPDATED_CREATED_BY
    defaultAuditShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdBy is not null
    defaultAuditShouldBeFound("createdBy.specified=true");

    // Get all the auditList where createdBy is null
    defaultAuditShouldNotBeFound("createdBy.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedByContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdBy contains DEFAULT_CREATED_BY
    defaultAuditShouldBeFound("createdBy.contains=" + DEFAULT_CREATED_BY);

    // Get all the auditList where createdBy contains UPDATED_CREATED_BY
    defaultAuditShouldNotBeFound("createdBy.contains=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedByNotContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdBy does not contain DEFAULT_CREATED_BY
    defaultAuditShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the auditList where createdBy does not contain UPDATED_CREATED_BY
    defaultAuditShouldBeFound("createdBy.doesNotContain=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedDateIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdDate equals to DEFAULT_CREATED_DATE
    defaultAuditShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

    // Get all the auditList where createdDate equals to UPDATED_CREATED_DATE
    defaultAuditShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedDateIsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultAuditShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditList where createdDate not equals to UPDATED_CREATED_DATE
    defaultAuditShouldBeFound("createdDate.notEquals=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedDateIsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultAuditShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the auditList where createdDate equals to UPDATED_CREATED_DATE
    defaultAuditShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdDate is not null
    defaultAuditShouldBeFound("createdDate.specified=true");

    // Get all the auditList where createdDate is null
    defaultAuditShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultAuditShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultAuditShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultAuditShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultAuditShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedDateIsLessThanSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdDate is less than DEFAULT_CREATED_DATE
    defaultAuditShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditList where createdDate is less than UPDATED_CREATED_DATE
    defaultAuditShouldBeFound("createdDate.lessThan=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllAuditsByCreatedDateIsGreaterThanSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultAuditShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditList where createdDate is greater than SMALLER_CREATED_DATE
    defaultAuditShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultAuditShouldBeFound("lastModified.equals=" + DEFAULT_LAST_MODIFIED);

    // Get all the auditList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAuditShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedIsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultAuditShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultAuditShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedIsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultAuditShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the auditList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAuditShouldNotBeFound("lastModified.in=" + UPDATED_LAST_MODIFIED);
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModified is not null
    defaultAuditShouldBeFound("lastModified.specified=true");

    // Get all the auditList where lastModified is null
    defaultAuditShouldNotBeFound("lastModified.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultAuditShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultAuditShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultAuditShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultAuditShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedIsLessThanSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultAuditShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultAuditShouldBeFound("lastModified.lessThan=" + UPDATED_LAST_MODIFIED);
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedIsGreaterThanSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultAuditShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultAuditShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedByIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultAuditShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAuditShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedByIsNotEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultAuditShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultAuditShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedByIsInShouldWork() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultAuditShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the auditList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAuditShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModifiedBy is not null
    defaultAuditShouldBeFound("lastModifiedBy.specified=true");

    // Get all the auditList where lastModifiedBy is null
    defaultAuditShouldNotBeFound("lastModifiedBy.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedByContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultAuditShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultAuditShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditsByLastModifiedByNotContainsSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    // Get all the auditList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultAuditShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultAuditShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditsByAuditPatientMonitoringFormIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);
    AuditPatientMonitoringForm auditPatientMonitoringForm;
    if (TestUtil.findAll(em, AuditPatientMonitoringForm.class).isEmpty()) {
      auditPatientMonitoringForm =
        AuditPatientMonitoringFormResourceIT.createEntity(em);
      em.persist(auditPatientMonitoringForm);
      em.flush();
    } else {
      auditPatientMonitoringForm =
        TestUtil.findAll(em, AuditPatientMonitoringForm.class).get(0);
    }
    em.persist(auditPatientMonitoringForm);
    em.flush();
    audit.setAuditPatientMonitoringForm(auditPatientMonitoringForm);
    auditRepository.saveAndFlush(audit);
    Long auditPatientMonitoringFormId = auditPatientMonitoringForm.getId();

    // Get all the auditList where auditPatientMonitoringForm equals to auditPatientMonitoringFormId
    defaultAuditShouldBeFound(
      "auditPatientMonitoringFormId.equals=" + auditPatientMonitoringFormId
    );

    // Get all the auditList where auditPatientMonitoringForm equals to (auditPatientMonitoringFormId + 1)
    defaultAuditShouldNotBeFound(
      "auditPatientMonitoringFormId.equals=" +
      (auditPatientMonitoringFormId + 1)
    );
  }

  @Test
  @Transactional
  void getAllAuditsByAuditFormSHospGenInfoIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);
    AuditFormSHospGenInfo auditFormSHospGenInfo;
    if (TestUtil.findAll(em, AuditFormSHospGenInfo.class).isEmpty()) {
      auditFormSHospGenInfo = AuditFormSHospGenInfoResourceIT.createEntity(em);
      em.persist(auditFormSHospGenInfo);
      em.flush();
    } else {
      auditFormSHospGenInfo =
        TestUtil.findAll(em, AuditFormSHospGenInfo.class).get(0);
    }
    em.persist(auditFormSHospGenInfo);
    em.flush();
    audit.addAuditFormSHospGenInfo(auditFormSHospGenInfo);
    auditRepository.saveAndFlush(audit);
    Long auditFormSHospGenInfoId = auditFormSHospGenInfo.getId();

    // Get all the auditList where auditFormSHospGenInfo equals to auditFormSHospGenInfoId
    defaultAuditShouldBeFound(
      "auditFormSHospGenInfoId.equals=" + auditFormSHospGenInfoId
    );

    // Get all the auditList where auditFormSHospGenInfo equals to (auditFormSHospGenInfoId + 1)
    defaultAuditShouldNotBeFound(
      "auditFormSHospGenInfoId.equals=" + (auditFormSHospGenInfoId + 1)
    );
  }

  @Test
  @Transactional
  void getAllAuditsByFireElectricalAuditIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);
    FireElectricalAudit fireElectricalAudit;
    if (TestUtil.findAll(em, FireElectricalAudit.class).isEmpty()) {
      fireElectricalAudit = FireElectricalAuditResourceIT.createEntity(em);
      em.persist(fireElectricalAudit);
      em.flush();
    } else {
      fireElectricalAudit =
        TestUtil.findAll(em, FireElectricalAudit.class).get(0);
    }
    em.persist(fireElectricalAudit);
    em.flush();
    audit.addFireElectricalAudit(fireElectricalAudit);
    auditRepository.saveAndFlush(audit);
    Long fireElectricalAuditId = fireElectricalAudit.getId();

    // Get all the auditList where fireElectricalAudit equals to fireElectricalAuditId
    defaultAuditShouldBeFound(
      "fireElectricalAuditId.equals=" + fireElectricalAuditId
    );

    // Get all the auditList where fireElectricalAudit equals to (fireElectricalAuditId + 1)
    defaultAuditShouldNotBeFound(
      "fireElectricalAuditId.equals=" + (fireElectricalAuditId + 1)
    );
  }

  @Test
  @Transactional
  void getAllAuditsByAnnexureAnswersIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);
    AnnexureAnswers annexureAnswers;
    if (TestUtil.findAll(em, AnnexureAnswers.class).isEmpty()) {
      annexureAnswers = AnnexureAnswersResourceIT.createEntity(em);
      em.persist(annexureAnswers);
      em.flush();
    } else {
      annexureAnswers = TestUtil.findAll(em, AnnexureAnswers.class).get(0);
    }
    em.persist(annexureAnswers);
    em.flush();
    audit.addAnnexureAnswers(annexureAnswers);
    auditRepository.saveAndFlush(audit);
    Long annexureAnswersId = annexureAnswers.getId();

    // Get all the auditList where annexureAnswers equals to annexureAnswersId
    defaultAuditShouldBeFound("annexureAnswersId.equals=" + annexureAnswersId);

    // Get all the auditList where annexureAnswers equals to (annexureAnswersId + 1)
    defaultAuditShouldNotBeFound(
      "annexureAnswersId.equals=" + (annexureAnswersId + 1)
    );
  }

  @Test
  @Transactional
  void getAllAuditsByInvetoryReportIsEqualToSomething() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);
    InventoryReport invetoryReport;
    if (TestUtil.findAll(em, InventoryReport.class).isEmpty()) {
      invetoryReport = InventoryReportResourceIT.createEntity(em);
      em.persist(invetoryReport);
      em.flush();
    } else {
      invetoryReport = TestUtil.findAll(em, InventoryReport.class).get(0);
    }
    em.persist(invetoryReport);
    em.flush();
    audit.addInvetoryReport(invetoryReport);
    auditRepository.saveAndFlush(audit);
    Long invetoryReportId = invetoryReport.getId();

    // Get all the auditList where invetoryReport equals to invetoryReportId
    defaultAuditShouldBeFound("invetoryReportId.equals=" + invetoryReportId);

    // Get all the auditList where invetoryReport equals to (invetoryReportId + 1)
    defaultAuditShouldNotBeFound(
      "invetoryReportId.equals=" + (invetoryReportId + 1)
    );
  }

  @Test
  @Transactional
  void getAllAuditsByInventorySupplierDetailsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);
    InventorySupplierDetails inventorySupplierDetails;
    if (TestUtil.findAll(em, InventorySupplierDetails.class).isEmpty()) {
      inventorySupplierDetails =
        InventorySupplierDetailsResourceIT.createEntity(em);
      em.persist(inventorySupplierDetails);
      em.flush();
    } else {
      inventorySupplierDetails =
        TestUtil.findAll(em, InventorySupplierDetails.class).get(0);
    }
    em.persist(inventorySupplierDetails);
    em.flush();
    audit.addInventorySupplierDetails(inventorySupplierDetails);
    auditRepository.saveAndFlush(audit);
    Long inventorySupplierDetailsId = inventorySupplierDetails.getId();

    // Get all the auditList where inventorySupplierDetails equals to inventorySupplierDetailsId
    defaultAuditShouldBeFound(
      "inventorySupplierDetailsId.equals=" + inventorySupplierDetailsId
    );

    // Get all the auditList where inventorySupplierDetails equals to (inventorySupplierDetailsId + 1)
    defaultAuditShouldNotBeFound(
      "inventorySupplierDetailsId.equals=" + (inventorySupplierDetailsId + 1)
    );
  }

  @Test
  @Transactional
  void getAllAuditsByOxygenConsumptionDataIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);
    OxygenConsumptionData oxygenConsumptionData;
    if (TestUtil.findAll(em, OxygenConsumptionData.class).isEmpty()) {
      oxygenConsumptionData = OxygenConsumptionDataResourceIT.createEntity(em);
      em.persist(oxygenConsumptionData);
      em.flush();
    } else {
      oxygenConsumptionData =
        TestUtil.findAll(em, OxygenConsumptionData.class).get(0);
    }
    em.persist(oxygenConsumptionData);
    em.flush();
    audit.addOxygenConsumptionData(oxygenConsumptionData);
    auditRepository.saveAndFlush(audit);
    Long oxygenConsumptionDataId = oxygenConsumptionData.getId();

    // Get all the auditList where oxygenConsumptionData equals to oxygenConsumptionDataId
    defaultAuditShouldBeFound(
      "oxygenConsumptionDataId.equals=" + oxygenConsumptionDataId
    );

    // Get all the auditList where oxygenConsumptionData equals to (oxygenConsumptionDataId + 1)
    defaultAuditShouldNotBeFound(
      "oxygenConsumptionDataId.equals=" + (oxygenConsumptionDataId + 1)
    );
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultAuditShouldBeFound(String filter) throws Exception {
    restAuditMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(audit.getId().intValue())))
      .andExpect(
        jsonPath("$.[*].auditDate")
          .value(hasItem(DEFAULT_AUDIT_DATE.toString()))
      )
      .andExpect(jsonPath("$.[*].hospName").value(hasItem(DEFAULT_HOSP_NAME)))
      .andExpect(
        jsonPath("$.[*].isAuditComplete")
          .value(hasItem(DEFAULT_IS_AUDIT_COMPLETE.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1))
      )
      .andExpect(
        jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2))
      )
      .andExpect(
        jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3))
      )
      .andExpect(
        jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4))
      )
      .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(
        jsonPath("$.[*].createdDate")
          .value(hasItem(DEFAULT_CREATED_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].lastModified")
          .value(hasItem(DEFAULT_LAST_MODIFIED.toString()))
      )
      .andExpect(
        jsonPath("$.[*].lastModifiedBy")
          .value(hasItem(DEFAULT_LAST_MODIFIED_BY))
      );

    // Check, that the count call also returns 1
    restAuditMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultAuditShouldNotBeFound(String filter) throws Exception {
    restAuditMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restAuditMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingAudit() throws Exception {
    // Get the audit
    restAuditMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewAudit() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    int databaseSizeBeforeUpdate = auditRepository.findAll().size();

    // Update the audit
    Audit updatedAudit = auditRepository.findById(audit.getId()).get();
    // Disconnect from session so that the updates on updatedAudit are not directly saved in db
    em.detach(updatedAudit);
    updatedAudit
      .auditDate(UPDATED_AUDIT_DATE)
      .hospName(UPDATED_HOSP_NAME)
      .isAuditComplete(UPDATED_IS_AUDIT_COMPLETE)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .remark(UPDATED_REMARK)
      .createdBy(UPDATED_CREATED_BY)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    Audit audit = auditMapper.toDto(updatedAudit);

    restAuditMockMvc
      .perform(
        put(ENTITY_API_URL_ID, auditDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(auditDTO))
      )
      .andExpect(status().isOk());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
    Audit testAudit = auditList.get(auditList.size() - 1);
    assertThat(testAudit.getAuditDate()).isEqualTo(UPDATED_AUDIT_DATE);
    assertThat(testAudit.getHospName()).isEqualTo(UPDATED_HOSP_NAME);
    assertThat(testAudit.getIsAuditComplete())
      .isEqualTo(UPDATED_IS_AUDIT_COMPLETE);
    assertThat(testAudit.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAudit.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAudit.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAudit.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
    assertThat(testAudit.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testAudit.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAudit.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAudit.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAudit.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void putNonExistingAudit() throws Exception {
    int databaseSizeBeforeUpdate = auditRepository.findAll().size();
    audit.setId(count.incrementAndGet());

    // Create the Audit
    Audit audit = auditMapper.toDto(audit);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAuditMockMvc
      .perform(
        put(ENTITY_API_URL_ID, audit.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(audit))
      )
      .andExpect(status().isBadRequest());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchAudit() throws Exception {
    int databaseSizeBeforeUpdate = auditRepository.findAll().size();
    audit.setId(count.incrementAndGet());

    // Create the Audit
    Audit audit = auditMapper.toDto(audit);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(audit))
      )
      .andExpect(status().isBadRequest());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamAudit() throws Exception {
    int databaseSizeBeforeUpdate = auditRepository.findAll().size();
    audit.setId(count.incrementAndGet());

    // Create the Audit
    Audit audit = auditMapper.toDto(audit);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(audit))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateAuditWithPatch() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    int databaseSizeBeforeUpdate = auditRepository.findAll().size();

    // Update the audit using partial update
    Audit partialUpdatedAudit = new Audit();
    partialUpdatedAudit.setId(audit.getId());

    partialUpdatedAudit
      .hospName(UPDATED_HOSP_NAME)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .createdDate(UPDATED_CREATED_DATE);

    restAuditMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAudit.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAudit))
      )
      .andExpect(status().isOk());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
    Audit testAudit = auditList.get(auditList.size() - 1);
    assertThat(testAudit.getAuditDate()).isEqualTo(DEFAULT_AUDIT_DATE);
    assertThat(testAudit.getHospName()).isEqualTo(UPDATED_HOSP_NAME);
    assertThat(testAudit.getIsAuditComplete())
      .isEqualTo(DEFAULT_IS_AUDIT_COMPLETE);
    assertThat(testAudit.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testAudit.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testAudit.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAudit.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
    assertThat(testAudit.getRemark()).isEqualTo(DEFAULT_REMARK);
    assertThat(testAudit.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testAudit.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAudit.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testAudit.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void fullUpdateAuditWithPatch() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    int databaseSizeBeforeUpdate = auditRepository.findAll().size();

    // Update the audit using partial update
    Audit partialUpdatedAudit = new Audit();
    partialUpdatedAudit.setId(audit.getId());

    partialUpdatedAudit
      .auditDate(UPDATED_AUDIT_DATE)
      .hospName(UPDATED_HOSP_NAME)
      .isAuditComplete(UPDATED_IS_AUDIT_COMPLETE)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .remark(UPDATED_REMARK)
      .createdBy(UPDATED_CREATED_BY)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

    restAuditMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAudit.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAudit))
      )
      .andExpect(status().isOk());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
    Audit testAudit = auditList.get(auditList.size() - 1);
    assertThat(testAudit.getAuditDate()).isEqualTo(UPDATED_AUDIT_DATE);
    assertThat(testAudit.getHospName()).isEqualTo(UPDATED_HOSP_NAME);
    assertThat(testAudit.getIsAuditComplete())
      .isEqualTo(UPDATED_IS_AUDIT_COMPLETE);
    assertThat(testAudit.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAudit.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAudit.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAudit.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
    assertThat(testAudit.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testAudit.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAudit.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAudit.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAudit.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void patchNonExistingAudit() throws Exception {
    int databaseSizeBeforeUpdate = auditRepository.findAll().size();
    audit.setId(count.incrementAndGet());

    // Create the Audit
    AuditDTO auditDTO = auditMapper.toDto(audit);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAuditMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, auditDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(auditDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchAudit() throws Exception {
    int databaseSizeBeforeUpdate = auditRepository.findAll().size();
    audit.setId(count.incrementAndGet());

    // Create the Audit
    AuditDTO auditDTO = auditMapper.toDto(audit);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(auditDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamAudit() throws Exception {
    int databaseSizeBeforeUpdate = auditRepository.findAll().size();
    audit.setId(count.incrementAndGet());

    // Create the Audit
    AuditDTO auditDTO = auditMapper.toDto(audit);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(auditDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the Audit in the database
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteAudit() throws Exception {
    // Initialize the database
    auditRepository.saveAndFlush(audit);

    int databaseSizeBeforeDelete = auditRepository.findAll().size();

    // Delete the audit
    restAuditMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, audit.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<Audit> auditList = auditRepository.findAll();
    assertThat(auditList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
