package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.AnnexureAnswers;
import com.vgtech.auditapp.domain.AnnexureQuestions;
import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.repository.AnnexureAnswersRepository;
import com.vgtech.auditapp.service.criteria.AnnexureAnswersCriteria;
import com.vgtech.auditapp.service.dto.AnnexureAnswers;
import com.vgtech.auditapp.service.mapper.AnnexureAnswersMapper;
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
 * Integration tests for the {@link AnnexureAnswersResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnnexureAnswersResourceIT {

  private static final String DEFAULT_FORM_NAME = "AAAAAAAAAA";
  private static final String UPDATED_FORM_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_SUB_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_SUB_TYPE = "BBBBBBBBBB";

  private static final Boolean DEFAULT_COMPLIANCE = false;
  private static final Boolean UPDATED_COMPLIANCE = true;

  private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
  private static final String UPDATED_COMMENT = "BBBBBBBBBB";

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

  private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_CREATED_DATE = LocalDate.ofEpochDay(
    -1L
  );

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

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

  private static final String ENTITY_API_URL = "/api/annexure-answers";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private AnnexureAnswersRepository annexureAnswersRepository;

  @Autowired
  private AnnexureAnswersMapper annexureAnswersMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restAnnexureAnswersMockMvc;

  private AnnexureAnswers annexureAnswers;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AnnexureAnswers createEntity(EntityManager em) {
    AnnexureAnswers annexureAnswers = new AnnexureAnswers()
      .formName(DEFAULT_FORM_NAME)
      .type(DEFAULT_TYPE)
      .subType(DEFAULT_SUB_TYPE)
      .compliance(DEFAULT_COMPLIANCE)
      .comment(DEFAULT_COMMENT)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .freeField4(DEFAULT_FREE_FIELD_4)
      .remark(DEFAULT_REMARK)
      .createdDate(DEFAULT_CREATED_DATE)
      .createdBy(DEFAULT_CREATED_BY)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
    return annexureAnswers;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AnnexureAnswers createUpdatedEntity(EntityManager em) {
    AnnexureAnswers annexureAnswers = new AnnexureAnswers()
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .compliance(UPDATED_COMPLIANCE)
      .comment(UPDATED_COMMENT)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .remark(UPDATED_REMARK)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    return annexureAnswers;
  }

  @BeforeEach
  public void initTest() {
    annexureAnswers = createEntity(em);
  }

  @Test
  @Transactional
  void createAnnexureAnswers() throws Exception {
    int databaseSizeBeforeCreate = annexureAnswersRepository.findAll().size();
    // Create the AnnexureAnswers
    AnnexureAnswers annexureAnswers = annexureAnswersMapper.toDto(
      annexureAnswers
    );
    restAnnexureAnswersMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureAnswers))
      )
      .andExpect(status().isCreated());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeCreate + 1);
    AnnexureAnswers testAnnexureAnswers = annexureAnswersList.get(
      annexureAnswersList.size() - 1
    );
    assertThat(testAnnexureAnswers.getFormName()).isEqualTo(DEFAULT_FORM_NAME);
    assertThat(testAnnexureAnswers.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testAnnexureAnswers.getSubType()).isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testAnnexureAnswers.getCompliance())
      .isEqualTo(DEFAULT_COMPLIANCE);
    assertThat(testAnnexureAnswers.getComment()).isEqualTo(DEFAULT_COMMENT);
    assertThat(testAnnexureAnswers.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testAnnexureAnswers.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testAnnexureAnswers.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testAnnexureAnswers.getFreeField4())
      .isEqualTo(DEFAULT_FREE_FIELD_4);
    assertThat(testAnnexureAnswers.getRemark()).isEqualTo(DEFAULT_REMARK);
    assertThat(testAnnexureAnswers.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testAnnexureAnswers.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testAnnexureAnswers.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testAnnexureAnswers.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void createAnnexureAnswersWithExistingId() throws Exception {
    // Create the AnnexureAnswers with an existing ID
    annexureAnswers.setId(1L);
    AnnexureAnswers annexureAnswers = annexureAnswersMapper.toDto(
      annexureAnswers
    );

    int databaseSizeBeforeCreate = annexureAnswersRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restAnnexureAnswersMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureAnswers))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswers() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList
    restAnnexureAnswersMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(annexureAnswers.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(
        jsonPath("$.[*].compliance")
          .value(hasItem(DEFAULT_COMPLIANCE.booleanValue()))
      )
      .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
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
      .andExpect(
        jsonPath("$.[*].createdDate")
          .value(hasItem(DEFAULT_CREATED_DATE.toString()))
      )
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
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
  void getAnnexureAnswers() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get the annexureAnswers
    restAnnexureAnswersMockMvc
      .perform(get(ENTITY_API_URL_ID, annexureAnswers.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(annexureAnswers.getId().intValue()))
      .andExpect(jsonPath("$.formName").value(DEFAULT_FORM_NAME))
      .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
      .andExpect(jsonPath("$.subType").value(DEFAULT_SUB_TYPE))
      .andExpect(
        jsonPath("$.compliance").value(DEFAULT_COMPLIANCE.booleanValue())
      )
      .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
      .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
      .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
      .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
      .andExpect(jsonPath("$.freeField4").value(DEFAULT_FREE_FIELD_4))
      .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
      .andExpect(
        jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString())
      )
      .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
      .andExpect(
        jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString())
      )
      .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
  }

  @Test
  @Transactional
  void getAnnexureAnswersByIdFiltering() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    Long id = annexureAnswers.getId();

    defaultAnnexureAnswersShouldBeFound("id.equals=" + id);
    defaultAnnexureAnswersShouldNotBeFound("id.notEquals=" + id);

    defaultAnnexureAnswersShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultAnnexureAnswersShouldNotBeFound("id.greaterThan=" + id);

    defaultAnnexureAnswersShouldBeFound("id.lessThanOrEqual=" + id);
    defaultAnnexureAnswersShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFormNameIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where formName equals to DEFAULT_FORM_NAME
    defaultAnnexureAnswersShouldBeFound("formName.equals=" + DEFAULT_FORM_NAME);

    // Get all the annexureAnswersList where formName equals to UPDATED_FORM_NAME
    defaultAnnexureAnswersShouldNotBeFound(
      "formName.equals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFormNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where formName not equals to DEFAULT_FORM_NAME
    defaultAnnexureAnswersShouldNotBeFound(
      "formName.notEquals=" + DEFAULT_FORM_NAME
    );

    // Get all the annexureAnswersList where formName not equals to UPDATED_FORM_NAME
    defaultAnnexureAnswersShouldBeFound(
      "formName.notEquals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFormNameIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where formName in DEFAULT_FORM_NAME or UPDATED_FORM_NAME
    defaultAnnexureAnswersShouldBeFound(
      "formName.in=" + DEFAULT_FORM_NAME + "," + UPDATED_FORM_NAME
    );

    // Get all the annexureAnswersList where formName equals to UPDATED_FORM_NAME
    defaultAnnexureAnswersShouldNotBeFound("formName.in=" + UPDATED_FORM_NAME);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFormNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where formName is not null
    defaultAnnexureAnswersShouldBeFound("formName.specified=true");

    // Get all the annexureAnswersList where formName is null
    defaultAnnexureAnswersShouldNotBeFound("formName.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFormNameContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where formName contains DEFAULT_FORM_NAME
    defaultAnnexureAnswersShouldBeFound(
      "formName.contains=" + DEFAULT_FORM_NAME
    );

    // Get all the annexureAnswersList where formName contains UPDATED_FORM_NAME
    defaultAnnexureAnswersShouldNotBeFound(
      "formName.contains=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFormNameNotContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where formName does not contain DEFAULT_FORM_NAME
    defaultAnnexureAnswersShouldNotBeFound(
      "formName.doesNotContain=" + DEFAULT_FORM_NAME
    );

    // Get all the annexureAnswersList where formName does not contain UPDATED_FORM_NAME
    defaultAnnexureAnswersShouldBeFound(
      "formName.doesNotContain=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where type equals to DEFAULT_TYPE
    defaultAnnexureAnswersShouldBeFound("type.equals=" + DEFAULT_TYPE);

    // Get all the annexureAnswersList where type equals to UPDATED_TYPE
    defaultAnnexureAnswersShouldNotBeFound("type.equals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where type not equals to DEFAULT_TYPE
    defaultAnnexureAnswersShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

    // Get all the annexureAnswersList where type not equals to UPDATED_TYPE
    defaultAnnexureAnswersShouldBeFound("type.notEquals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByTypeIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where type in DEFAULT_TYPE or UPDATED_TYPE
    defaultAnnexureAnswersShouldBeFound(
      "type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE
    );

    // Get all the annexureAnswersList where type equals to UPDATED_TYPE
    defaultAnnexureAnswersShouldNotBeFound("type.in=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where type is not null
    defaultAnnexureAnswersShouldBeFound("type.specified=true");

    // Get all the annexureAnswersList where type is null
    defaultAnnexureAnswersShouldNotBeFound("type.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByTypeContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where type contains DEFAULT_TYPE
    defaultAnnexureAnswersShouldBeFound("type.contains=" + DEFAULT_TYPE);

    // Get all the annexureAnswersList where type contains UPDATED_TYPE
    defaultAnnexureAnswersShouldNotBeFound("type.contains=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByTypeNotContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where type does not contain DEFAULT_TYPE
    defaultAnnexureAnswersShouldNotBeFound(
      "type.doesNotContain=" + DEFAULT_TYPE
    );

    // Get all the annexureAnswersList where type does not contain UPDATED_TYPE
    defaultAnnexureAnswersShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersBySubTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where subType equals to DEFAULT_SUB_TYPE
    defaultAnnexureAnswersShouldBeFound("subType.equals=" + DEFAULT_SUB_TYPE);

    // Get all the annexureAnswersList where subType equals to UPDATED_SUB_TYPE
    defaultAnnexureAnswersShouldNotBeFound(
      "subType.equals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersBySubTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where subType not equals to DEFAULT_SUB_TYPE
    defaultAnnexureAnswersShouldNotBeFound(
      "subType.notEquals=" + DEFAULT_SUB_TYPE
    );

    // Get all the annexureAnswersList where subType not equals to UPDATED_SUB_TYPE
    defaultAnnexureAnswersShouldBeFound(
      "subType.notEquals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersBySubTypeIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where subType in DEFAULT_SUB_TYPE or UPDATED_SUB_TYPE
    defaultAnnexureAnswersShouldBeFound(
      "subType.in=" + DEFAULT_SUB_TYPE + "," + UPDATED_SUB_TYPE
    );

    // Get all the annexureAnswersList where subType equals to UPDATED_SUB_TYPE
    defaultAnnexureAnswersShouldNotBeFound("subType.in=" + UPDATED_SUB_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersBySubTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where subType is not null
    defaultAnnexureAnswersShouldBeFound("subType.specified=true");

    // Get all the annexureAnswersList where subType is null
    defaultAnnexureAnswersShouldNotBeFound("subType.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersBySubTypeContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where subType contains DEFAULT_SUB_TYPE
    defaultAnnexureAnswersShouldBeFound("subType.contains=" + DEFAULT_SUB_TYPE);

    // Get all the annexureAnswersList where subType contains UPDATED_SUB_TYPE
    defaultAnnexureAnswersShouldNotBeFound(
      "subType.contains=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersBySubTypeNotContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where subType does not contain DEFAULT_SUB_TYPE
    defaultAnnexureAnswersShouldNotBeFound(
      "subType.doesNotContain=" + DEFAULT_SUB_TYPE
    );

    // Get all the annexureAnswersList where subType does not contain UPDATED_SUB_TYPE
    defaultAnnexureAnswersShouldBeFound(
      "subType.doesNotContain=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByComplianceIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where compliance equals to DEFAULT_COMPLIANCE
    defaultAnnexureAnswersShouldBeFound(
      "compliance.equals=" + DEFAULT_COMPLIANCE
    );

    // Get all the annexureAnswersList where compliance equals to UPDATED_COMPLIANCE
    defaultAnnexureAnswersShouldNotBeFound(
      "compliance.equals=" + UPDATED_COMPLIANCE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByComplianceIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where compliance not equals to DEFAULT_COMPLIANCE
    defaultAnnexureAnswersShouldNotBeFound(
      "compliance.notEquals=" + DEFAULT_COMPLIANCE
    );

    // Get all the annexureAnswersList where compliance not equals to UPDATED_COMPLIANCE
    defaultAnnexureAnswersShouldBeFound(
      "compliance.notEquals=" + UPDATED_COMPLIANCE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByComplianceIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where compliance in DEFAULT_COMPLIANCE or UPDATED_COMPLIANCE
    defaultAnnexureAnswersShouldBeFound(
      "compliance.in=" + DEFAULT_COMPLIANCE + "," + UPDATED_COMPLIANCE
    );

    // Get all the annexureAnswersList where compliance equals to UPDATED_COMPLIANCE
    defaultAnnexureAnswersShouldNotBeFound(
      "compliance.in=" + UPDATED_COMPLIANCE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByComplianceIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where compliance is not null
    defaultAnnexureAnswersShouldBeFound("compliance.specified=true");

    // Get all the annexureAnswersList where compliance is null
    defaultAnnexureAnswersShouldNotBeFound("compliance.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCommentIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where comment equals to DEFAULT_COMMENT
    defaultAnnexureAnswersShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

    // Get all the annexureAnswersList where comment equals to UPDATED_COMMENT
    defaultAnnexureAnswersShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCommentIsNotEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where comment not equals to DEFAULT_COMMENT
    defaultAnnexureAnswersShouldNotBeFound(
      "comment.notEquals=" + DEFAULT_COMMENT
    );

    // Get all the annexureAnswersList where comment not equals to UPDATED_COMMENT
    defaultAnnexureAnswersShouldBeFound("comment.notEquals=" + UPDATED_COMMENT);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCommentIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
    defaultAnnexureAnswersShouldBeFound(
      "comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT
    );

    // Get all the annexureAnswersList where comment equals to UPDATED_COMMENT
    defaultAnnexureAnswersShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCommentIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where comment is not null
    defaultAnnexureAnswersShouldBeFound("comment.specified=true");

    // Get all the annexureAnswersList where comment is null
    defaultAnnexureAnswersShouldNotBeFound("comment.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCommentContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where comment contains DEFAULT_COMMENT
    defaultAnnexureAnswersShouldBeFound("comment.contains=" + DEFAULT_COMMENT);

    // Get all the annexureAnswersList where comment contains UPDATED_COMMENT
    defaultAnnexureAnswersShouldNotBeFound(
      "comment.contains=" + UPDATED_COMMENT
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCommentNotContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where comment does not contain DEFAULT_COMMENT
    defaultAnnexureAnswersShouldNotBeFound(
      "comment.doesNotContain=" + DEFAULT_COMMENT
    );

    // Get all the annexureAnswersList where comment does not contain UPDATED_COMMENT
    defaultAnnexureAnswersShouldBeFound(
      "comment.doesNotContain=" + UPDATED_COMMENT
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField1IsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultAnnexureAnswersShouldBeFound(
      "freeField1.equals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the annexureAnswersList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField1.equals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField1IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the annexureAnswersList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultAnnexureAnswersShouldBeFound(
      "freeField1.notEquals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField1IsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultAnnexureAnswersShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the annexureAnswersList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField1.in=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField1IsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField1 is not null
    defaultAnnexureAnswersShouldBeFound("freeField1.specified=true");

    // Get all the annexureAnswersList where freeField1 is null
    defaultAnnexureAnswersShouldNotBeFound("freeField1.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField1ContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultAnnexureAnswersShouldBeFound(
      "freeField1.contains=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the annexureAnswersList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField1.contains=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField1NotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the annexureAnswersList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultAnnexureAnswersShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField2IsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultAnnexureAnswersShouldBeFound(
      "freeField2.equals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the annexureAnswersList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField2.equals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField2IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the annexureAnswersList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultAnnexureAnswersShouldBeFound(
      "freeField2.notEquals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField2IsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultAnnexureAnswersShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the annexureAnswersList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField2.in=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField2IsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField2 is not null
    defaultAnnexureAnswersShouldBeFound("freeField2.specified=true");

    // Get all the annexureAnswersList where freeField2 is null
    defaultAnnexureAnswersShouldNotBeFound("freeField2.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField2ContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultAnnexureAnswersShouldBeFound(
      "freeField2.contains=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the annexureAnswersList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField2.contains=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField2NotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the annexureAnswersList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultAnnexureAnswersShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField3IsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultAnnexureAnswersShouldBeFound(
      "freeField3.equals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the annexureAnswersList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField3.equals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField3IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the annexureAnswersList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultAnnexureAnswersShouldBeFound(
      "freeField3.notEquals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField3IsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultAnnexureAnswersShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the annexureAnswersList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField3.in=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField3IsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField3 is not null
    defaultAnnexureAnswersShouldBeFound("freeField3.specified=true");

    // Get all the annexureAnswersList where freeField3 is null
    defaultAnnexureAnswersShouldNotBeFound("freeField3.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField3ContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultAnnexureAnswersShouldBeFound(
      "freeField3.contains=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the annexureAnswersList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField3.contains=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField3NotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the annexureAnswersList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultAnnexureAnswersShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField4IsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField4 equals to DEFAULT_FREE_FIELD_4
    defaultAnnexureAnswersShouldBeFound(
      "freeField4.equals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the annexureAnswersList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField4.equals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField4IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField4 not equals to DEFAULT_FREE_FIELD_4
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField4.notEquals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the annexureAnswersList where freeField4 not equals to UPDATED_FREE_FIELD_4
    defaultAnnexureAnswersShouldBeFound(
      "freeField4.notEquals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField4IsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
    defaultAnnexureAnswersShouldBeFound(
      "freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4
    );

    // Get all the annexureAnswersList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField4.in=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField4IsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField4 is not null
    defaultAnnexureAnswersShouldBeFound("freeField4.specified=true");

    // Get all the annexureAnswersList where freeField4 is null
    defaultAnnexureAnswersShouldNotBeFound("freeField4.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField4ContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField4 contains DEFAULT_FREE_FIELD_4
    defaultAnnexureAnswersShouldBeFound(
      "freeField4.contains=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the annexureAnswersList where freeField4 contains UPDATED_FREE_FIELD_4
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField4.contains=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByFreeField4NotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where freeField4 does not contain DEFAULT_FREE_FIELD_4
    defaultAnnexureAnswersShouldNotBeFound(
      "freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the annexureAnswersList where freeField4 does not contain UPDATED_FREE_FIELD_4
    defaultAnnexureAnswersShouldBeFound(
      "freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByRemarkIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where remark equals to DEFAULT_REMARK
    defaultAnnexureAnswersShouldBeFound("remark.equals=" + DEFAULT_REMARK);

    // Get all the annexureAnswersList where remark equals to UPDATED_REMARK
    defaultAnnexureAnswersShouldNotBeFound("remark.equals=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByRemarkIsNotEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where remark not equals to DEFAULT_REMARK
    defaultAnnexureAnswersShouldNotBeFound(
      "remark.notEquals=" + DEFAULT_REMARK
    );

    // Get all the annexureAnswersList where remark not equals to UPDATED_REMARK
    defaultAnnexureAnswersShouldBeFound("remark.notEquals=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByRemarkIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where remark in DEFAULT_REMARK or UPDATED_REMARK
    defaultAnnexureAnswersShouldBeFound(
      "remark.in=" + DEFAULT_REMARK + "," + UPDATED_REMARK
    );

    // Get all the annexureAnswersList where remark equals to UPDATED_REMARK
    defaultAnnexureAnswersShouldNotBeFound("remark.in=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByRemarkIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where remark is not null
    defaultAnnexureAnswersShouldBeFound("remark.specified=true");

    // Get all the annexureAnswersList where remark is null
    defaultAnnexureAnswersShouldNotBeFound("remark.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByRemarkContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where remark contains DEFAULT_REMARK
    defaultAnnexureAnswersShouldBeFound("remark.contains=" + DEFAULT_REMARK);

    // Get all the annexureAnswersList where remark contains UPDATED_REMARK
    defaultAnnexureAnswersShouldNotBeFound("remark.contains=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByRemarkNotContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where remark does not contain DEFAULT_REMARK
    defaultAnnexureAnswersShouldNotBeFound(
      "remark.doesNotContain=" + DEFAULT_REMARK
    );

    // Get all the annexureAnswersList where remark does not contain UPDATED_REMARK
    defaultAnnexureAnswersShouldBeFound(
      "remark.doesNotContain=" + UPDATED_REMARK
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedDateIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdDate equals to DEFAULT_CREATED_DATE
    defaultAnnexureAnswersShouldBeFound(
      "createdDate.equals=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureAnswersList where createdDate equals to UPDATED_CREATED_DATE
    defaultAnnexureAnswersShouldNotBeFound(
      "createdDate.equals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultAnnexureAnswersShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureAnswersList where createdDate not equals to UPDATED_CREATED_DATE
    defaultAnnexureAnswersShouldBeFound(
      "createdDate.notEquals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedDateIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultAnnexureAnswersShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the annexureAnswersList where createdDate equals to UPDATED_CREATED_DATE
    defaultAnnexureAnswersShouldNotBeFound(
      "createdDate.in=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdDate is not null
    defaultAnnexureAnswersShouldBeFound("createdDate.specified=true");

    // Get all the annexureAnswersList where createdDate is null
    defaultAnnexureAnswersShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultAnnexureAnswersShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureAnswersList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultAnnexureAnswersShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultAnnexureAnswersShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureAnswersList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultAnnexureAnswersShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdDate is less than DEFAULT_CREATED_DATE
    defaultAnnexureAnswersShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureAnswersList where createdDate is less than UPDATED_CREATED_DATE
    defaultAnnexureAnswersShouldBeFound(
      "createdDate.lessThan=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultAnnexureAnswersShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureAnswersList where createdDate is greater than SMALLER_CREATED_DATE
    defaultAnnexureAnswersShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedByIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdBy equals to DEFAULT_CREATED_BY
    defaultAnnexureAnswersShouldBeFound(
      "createdBy.equals=" + DEFAULT_CREATED_BY
    );

    // Get all the annexureAnswersList where createdBy equals to UPDATED_CREATED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "createdBy.equals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdBy not equals to DEFAULT_CREATED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "createdBy.notEquals=" + DEFAULT_CREATED_BY
    );

    // Get all the annexureAnswersList where createdBy not equals to UPDATED_CREATED_BY
    defaultAnnexureAnswersShouldBeFound(
      "createdBy.notEquals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedByIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultAnnexureAnswersShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the annexureAnswersList where createdBy equals to UPDATED_CREATED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "createdBy.in=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdBy is not null
    defaultAnnexureAnswersShouldBeFound("createdBy.specified=true");

    // Get all the annexureAnswersList where createdBy is null
    defaultAnnexureAnswersShouldNotBeFound("createdBy.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedByContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdBy contains DEFAULT_CREATED_BY
    defaultAnnexureAnswersShouldBeFound(
      "createdBy.contains=" + DEFAULT_CREATED_BY
    );

    // Get all the annexureAnswersList where createdBy contains UPDATED_CREATED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "createdBy.contains=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByCreatedByNotContainsSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where createdBy does not contain DEFAULT_CREATED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the annexureAnswersList where createdBy does not contain UPDATED_CREATED_BY
    defaultAnnexureAnswersShouldBeFound(
      "createdBy.doesNotContain=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultAnnexureAnswersShouldBeFound(
      "lastModified.equals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureAnswersList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureAnswersList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultAnnexureAnswersShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultAnnexureAnswersShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the annexureAnswersList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModified.in=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModified is not null
    defaultAnnexureAnswersShouldBeFound("lastModified.specified=true");

    // Get all the annexureAnswersList where lastModified is null
    defaultAnnexureAnswersShouldNotBeFound("lastModified.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultAnnexureAnswersShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureAnswersList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultAnnexureAnswersShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureAnswersList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedIsLessThanSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureAnswersList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultAnnexureAnswersShouldBeFound(
      "lastModified.lessThan=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureAnswersList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultAnnexureAnswersShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the annexureAnswersList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the annexureAnswersList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedByIsInShouldWork() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the annexureAnswersList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModifiedBy is not null
    defaultAnnexureAnswersShouldBeFound("lastModifiedBy.specified=true");

    // Get all the annexureAnswersList where lastModifiedBy is null
    defaultAnnexureAnswersShouldNotBeFound("lastModifiedBy.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedByContainsSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the annexureAnswersList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByLastModifiedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    // Get all the annexureAnswersList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the annexureAnswersList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultAnnexureAnswersShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByAuditIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);
    Audit audit;
    if (TestUtil.findAll(em, Audit.class).isEmpty()) {
      audit = AuditResourceIT.createEntity(em);
      em.persist(audit);
      em.flush();
    } else {
      audit = TestUtil.findAll(em, Audit.class).get(0);
    }
    em.persist(audit);
    em.flush();
    annexureAnswers.setAudit(audit);
    annexureAnswersRepository.saveAndFlush(annexureAnswers);
    Long auditId = audit.getId();

    // Get all the annexureAnswersList where audit equals to auditId
    defaultAnnexureAnswersShouldBeFound("auditId.equals=" + auditId);

    // Get all the annexureAnswersList where audit equals to (auditId + 1)
    defaultAnnexureAnswersShouldNotBeFound("auditId.equals=" + (auditId + 1));
  }

  @Test
  @Transactional
  void getAllAnnexureAnswersByAnnexureQuestionsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);
    AnnexureQuestions annexureQuestions;
    if (TestUtil.findAll(em, AnnexureQuestions.class).isEmpty()) {
      annexureQuestions = AnnexureQuestionsResourceIT.createEntity(em);
      em.persist(annexureQuestions);
      em.flush();
    } else {
      annexureQuestions = TestUtil.findAll(em, AnnexureQuestions.class).get(0);
    }
    em.persist(annexureQuestions);
    em.flush();
    annexureAnswers.setAnnexureQuestions(annexureQuestions);
    annexureAnswersRepository.saveAndFlush(annexureAnswers);
    Long annexureQuestionsId = annexureQuestions.getId();

    // Get all the annexureAnswersList where annexureQuestions equals to annexureQuestionsId
    defaultAnnexureAnswersShouldBeFound(
      "annexureQuestionsId.equals=" + annexureQuestionsId
    );

    // Get all the annexureAnswersList where annexureQuestions equals to (annexureQuestionsId + 1)
    defaultAnnexureAnswersShouldNotBeFound(
      "annexureQuestionsId.equals=" + (annexureQuestionsId + 1)
    );
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultAnnexureAnswersShouldBeFound(String filter)
    throws Exception {
    restAnnexureAnswersMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(annexureAnswers.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(
        jsonPath("$.[*].compliance")
          .value(hasItem(DEFAULT_COMPLIANCE.booleanValue()))
      )
      .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
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
      .andExpect(
        jsonPath("$.[*].createdDate")
          .value(hasItem(DEFAULT_CREATED_DATE.toString()))
      )
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(
        jsonPath("$.[*].lastModified")
          .value(hasItem(DEFAULT_LAST_MODIFIED.toString()))
      )
      .andExpect(
        jsonPath("$.[*].lastModifiedBy")
          .value(hasItem(DEFAULT_LAST_MODIFIED_BY))
      );

    // Check, that the count call also returns 1
    restAnnexureAnswersMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultAnnexureAnswersShouldNotBeFound(String filter)
    throws Exception {
    restAnnexureAnswersMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restAnnexureAnswersMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingAnnexureAnswers() throws Exception {
    // Get the annexureAnswers
    restAnnexureAnswersMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewAnnexureAnswers() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    int databaseSizeBeforeUpdate = annexureAnswersRepository.findAll().size();

    // Update the annexureAnswers
    AnnexureAnswers updatedAnnexureAnswers = annexureAnswersRepository
      .findById(annexureAnswers.getId())
      .get();
    // Disconnect from session so that the updates on updatedAnnexureAnswers are not directly saved in db
    em.detach(updatedAnnexureAnswers);
    updatedAnnexureAnswers
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .compliance(UPDATED_COMPLIANCE)
      .comment(UPDATED_COMMENT)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .remark(UPDATED_REMARK)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    AnnexureAnswers annexureAnswers = annexureAnswersMapper.toDto(
      updatedAnnexureAnswers
    );

    restAnnexureAnswersMockMvc
      .perform(
        put(ENTITY_API_URL_ID, annexureAnswersDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureAnswersDTO))
      )
      .andExpect(status().isOk());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeUpdate);
    AnnexureAnswers testAnnexureAnswers = annexureAnswersList.get(
      annexureAnswersList.size() - 1
    );
    assertThat(testAnnexureAnswers.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    assertThat(testAnnexureAnswers.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testAnnexureAnswers.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testAnnexureAnswers.getCompliance())
      .isEqualTo(UPDATED_COMPLIANCE);
    assertThat(testAnnexureAnswers.getComment()).isEqualTo(UPDATED_COMMENT);
    assertThat(testAnnexureAnswers.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAnnexureAnswers.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAnnexureAnswers.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAnnexureAnswers.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
    assertThat(testAnnexureAnswers.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testAnnexureAnswers.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAnnexureAnswers.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAnnexureAnswers.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAnnexureAnswers.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void putNonExistingAnnexureAnswers() throws Exception {
    int databaseSizeBeforeUpdate = annexureAnswersRepository.findAll().size();
    annexureAnswers.setId(count.incrementAndGet());

    // Create the AnnexureAnswers
    AnnexureAnswers annexureAnswers = annexureAnswersMapper.toDto(
      annexureAnswers
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAnnexureAnswersMockMvc
      .perform(
        put(ENTITY_API_URL_ID, annexureAnswers.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureAnswers))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchAnnexureAnswers() throws Exception {
    int databaseSizeBeforeUpdate = annexureAnswersRepository.findAll().size();
    annexureAnswers.setId(count.incrementAndGet());

    // Create the AnnexureAnswers
    AnnexureAnswers annexureAnswers = annexureAnswersMapper.toDto(
      annexureAnswers
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAnnexureAnswersMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureAnswers))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamAnnexureAnswers() throws Exception {
    int databaseSizeBeforeUpdate = annexureAnswersRepository.findAll().size();
    annexureAnswers.setId(count.incrementAndGet());

    // Create the AnnexureAnswers
    AnnexureAnswers annexureAnswers = annexureAnswersMapper.toDto(
      annexureAnswers
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAnnexureAnswersMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureAnswers))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateAnnexureAnswersWithPatch() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    int databaseSizeBeforeUpdate = annexureAnswersRepository.findAll().size();

    // Update the annexureAnswers using partial update
    AnnexureAnswers partialUpdatedAnnexureAnswers = new AnnexureAnswers();
    partialUpdatedAnnexureAnswers.setId(annexureAnswers.getId());

    partialUpdatedAnnexureAnswers
      .formName(UPDATED_FORM_NAME)
      .subType(UPDATED_SUB_TYPE)
      .compliance(UPDATED_COMPLIANCE)
      .comment(UPDATED_COMMENT)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .remark(UPDATED_REMARK)
      .createdDate(UPDATED_CREATED_DATE);

    restAnnexureAnswersMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAnnexureAnswers.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedAnnexureAnswers)
          )
      )
      .andExpect(status().isOk());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeUpdate);
    AnnexureAnswers testAnnexureAnswers = annexureAnswersList.get(
      annexureAnswersList.size() - 1
    );
    assertThat(testAnnexureAnswers.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    assertThat(testAnnexureAnswers.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testAnnexureAnswers.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testAnnexureAnswers.getCompliance())
      .isEqualTo(UPDATED_COMPLIANCE);
    assertThat(testAnnexureAnswers.getComment()).isEqualTo(UPDATED_COMMENT);
    assertThat(testAnnexureAnswers.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testAnnexureAnswers.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAnnexureAnswers.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAnnexureAnswers.getFreeField4())
      .isEqualTo(DEFAULT_FREE_FIELD_4);
    assertThat(testAnnexureAnswers.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testAnnexureAnswers.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAnnexureAnswers.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testAnnexureAnswers.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testAnnexureAnswers.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void fullUpdateAnnexureAnswersWithPatch() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    int databaseSizeBeforeUpdate = annexureAnswersRepository.findAll().size();

    // Update the annexureAnswers using partial update
    AnnexureAnswers partialUpdatedAnnexureAnswers = new AnnexureAnswers();
    partialUpdatedAnnexureAnswers.setId(annexureAnswers.getId());

    partialUpdatedAnnexureAnswers
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .compliance(UPDATED_COMPLIANCE)
      .comment(UPDATED_COMMENT)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .remark(UPDATED_REMARK)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

    restAnnexureAnswersMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAnnexureAnswers.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedAnnexureAnswers)
          )
      )
      .andExpect(status().isOk());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeUpdate);
    AnnexureAnswers testAnnexureAnswers = annexureAnswersList.get(
      annexureAnswersList.size() - 1
    );
    assertThat(testAnnexureAnswers.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    assertThat(testAnnexureAnswers.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testAnnexureAnswers.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testAnnexureAnswers.getCompliance())
      .isEqualTo(UPDATED_COMPLIANCE);
    assertThat(testAnnexureAnswers.getComment()).isEqualTo(UPDATED_COMMENT);
    assertThat(testAnnexureAnswers.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAnnexureAnswers.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAnnexureAnswers.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAnnexureAnswers.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
    assertThat(testAnnexureAnswers.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testAnnexureAnswers.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAnnexureAnswers.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAnnexureAnswers.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAnnexureAnswers.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void patchNonExistingAnnexureAnswers() throws Exception {
    int databaseSizeBeforeUpdate = annexureAnswersRepository.findAll().size();
    annexureAnswers.setId(count.incrementAndGet());

    // Create the AnnexureAnswers
    AnnexureAnswersDTO annexureAnswersDTO = annexureAnswersMapper.toDto(
      annexureAnswers
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAnnexureAnswersMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, annexureAnswersDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(annexureAnswersDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchAnnexureAnswers() throws Exception {
    int databaseSizeBeforeUpdate = annexureAnswersRepository.findAll().size();
    annexureAnswers.setId(count.incrementAndGet());

    // Create the AnnexureAnswers
    AnnexureAnswersDTO annexureAnswersDTO = annexureAnswersMapper.toDto(
      annexureAnswers
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAnnexureAnswersMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(annexureAnswersDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamAnnexureAnswers() throws Exception {
    int databaseSizeBeforeUpdate = annexureAnswersRepository.findAll().size();
    annexureAnswers.setId(count.incrementAndGet());

    // Create the AnnexureAnswers
    AnnexureAnswersDTO annexureAnswersDTO = annexureAnswersMapper.toDto(
      annexureAnswers
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAnnexureAnswersMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(annexureAnswersDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the AnnexureAnswers in the database
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteAnnexureAnswers() throws Exception {
    // Initialize the database
    annexureAnswersRepository.saveAndFlush(annexureAnswers);

    int databaseSizeBeforeDelete = annexureAnswersRepository.findAll().size();

    // Delete the annexureAnswers
    restAnnexureAnswersMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, annexureAnswers.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<AnnexureAnswers> annexureAnswersList = annexureAnswersRepository.findAll();
    assertThat(annexureAnswersList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
