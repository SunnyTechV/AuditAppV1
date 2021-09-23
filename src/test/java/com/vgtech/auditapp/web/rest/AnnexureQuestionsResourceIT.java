package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.AnnexureAnswers;
import com.vgtech.auditapp.domain.AnnexureQuestions;
import com.vgtech.auditapp.repository.AnnexureQuestionsRepository;
import com.vgtech.auditapp.service.criteria.AnnexureQuestionsCriteria;
import com.vgtech.auditapp.service.dto.AnnexureQuestions;
import com.vgtech.auditapp.service.mapper.AnnexureQuestionsMapper;
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
 * Integration tests for the {@link AnnexureQuestionsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnnexureQuestionsResourceIT {

  private static final String DEFAULT_FORM_NAME = "AAAAAAAAAA";
  private static final String UPDATED_FORM_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_SUB_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_SUB_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_4 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_4 = "BBBBBBBBBB";

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

  private static final String ENTITY_API_URL = "/api/annexure-questions";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private AnnexureQuestionsRepository annexureQuestionsRepository;

  @Autowired
  private AnnexureQuestionsMapper annexureQuestionsMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restAnnexureQuestionsMockMvc;

  private AnnexureQuestions annexureQuestions;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AnnexureQuestions createEntity(EntityManager em) {
    AnnexureQuestions annexureQuestions = new AnnexureQuestions()
      .formName(DEFAULT_FORM_NAME)
      .type(DEFAULT_TYPE)
      .subType(DEFAULT_SUB_TYPE)
      .description(DEFAULT_DESCRIPTION)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .freeField4(DEFAULT_FREE_FIELD_4)
      .createdDate(DEFAULT_CREATED_DATE)
      .createdBy(DEFAULT_CREATED_BY)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
    return annexureQuestions;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AnnexureQuestions createUpdatedEntity(EntityManager em) {
    AnnexureQuestions annexureQuestions = new AnnexureQuestions()
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .description(UPDATED_DESCRIPTION)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    return annexureQuestions;
  }

  @BeforeEach
  public void initTest() {
    annexureQuestions = createEntity(em);
  }

  @Test
  @Transactional
  void createAnnexureQuestions() throws Exception {
    int databaseSizeBeforeCreate = annexureQuestionsRepository.findAll().size();
    // Create the AnnexureQuestions
    AnnexureQuestions annexureQuestions = annexureQuestionsMapper.toDto(
      annexureQuestions
    );
    restAnnexureQuestionsMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureQuestions))
      )
      .andExpect(status().isCreated());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeCreate + 1);
    AnnexureQuestions testAnnexureQuestions = annexureQuestionsList.get(
      annexureQuestionsList.size() - 1
    );
    assertThat(testAnnexureQuestions.getFormName())
      .isEqualTo(DEFAULT_FORM_NAME);
    assertThat(testAnnexureQuestions.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testAnnexureQuestions.getSubType()).isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testAnnexureQuestions.getDescription())
      .isEqualTo(DEFAULT_DESCRIPTION);
    assertThat(testAnnexureQuestions.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testAnnexureQuestions.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testAnnexureQuestions.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testAnnexureQuestions.getFreeField4())
      .isEqualTo(DEFAULT_FREE_FIELD_4);
    assertThat(testAnnexureQuestions.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testAnnexureQuestions.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testAnnexureQuestions.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testAnnexureQuestions.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void createAnnexureQuestionsWithExistingId() throws Exception {
    // Create the AnnexureQuestions with an existing ID
    annexureQuestions.setId(1L);
    AnnexureQuestions annexureQuestions = annexureQuestionsMapper.toDto(
      annexureQuestions
    );

    int databaseSizeBeforeCreate = annexureQuestionsRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restAnnexureQuestionsMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureQuestions))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllAnnexureQuestions() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList
    restAnnexureQuestionsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(annexureQuestions.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(
        jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION))
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
  void getAnnexureQuestions() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get the annexureQuestions
    restAnnexureQuestionsMockMvc
      .perform(get(ENTITY_API_URL_ID, annexureQuestions.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(annexureQuestions.getId().intValue()))
      .andExpect(jsonPath("$.formName").value(DEFAULT_FORM_NAME))
      .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
      .andExpect(jsonPath("$.subType").value(DEFAULT_SUB_TYPE))
      .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
      .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
      .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
      .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
      .andExpect(jsonPath("$.freeField4").value(DEFAULT_FREE_FIELD_4))
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
  void getAnnexureQuestionsByIdFiltering() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    Long id = annexureQuestions.getId();

    defaultAnnexureQuestionsShouldBeFound("id.equals=" + id);
    defaultAnnexureQuestionsShouldNotBeFound("id.notEquals=" + id);

    defaultAnnexureQuestionsShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultAnnexureQuestionsShouldNotBeFound("id.greaterThan=" + id);

    defaultAnnexureQuestionsShouldBeFound("id.lessThanOrEqual=" + id);
    defaultAnnexureQuestionsShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFormNameIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where formName equals to DEFAULT_FORM_NAME
    defaultAnnexureQuestionsShouldBeFound(
      "formName.equals=" + DEFAULT_FORM_NAME
    );

    // Get all the annexureQuestionsList where formName equals to UPDATED_FORM_NAME
    defaultAnnexureQuestionsShouldNotBeFound(
      "formName.equals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFormNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where formName not equals to DEFAULT_FORM_NAME
    defaultAnnexureQuestionsShouldNotBeFound(
      "formName.notEquals=" + DEFAULT_FORM_NAME
    );

    // Get all the annexureQuestionsList where formName not equals to UPDATED_FORM_NAME
    defaultAnnexureQuestionsShouldBeFound(
      "formName.notEquals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFormNameIsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where formName in DEFAULT_FORM_NAME or UPDATED_FORM_NAME
    defaultAnnexureQuestionsShouldBeFound(
      "formName.in=" + DEFAULT_FORM_NAME + "," + UPDATED_FORM_NAME
    );

    // Get all the annexureQuestionsList where formName equals to UPDATED_FORM_NAME
    defaultAnnexureQuestionsShouldNotBeFound(
      "formName.in=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFormNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where formName is not null
    defaultAnnexureQuestionsShouldBeFound("formName.specified=true");

    // Get all the annexureQuestionsList where formName is null
    defaultAnnexureQuestionsShouldNotBeFound("formName.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFormNameContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where formName contains DEFAULT_FORM_NAME
    defaultAnnexureQuestionsShouldBeFound(
      "formName.contains=" + DEFAULT_FORM_NAME
    );

    // Get all the annexureQuestionsList where formName contains UPDATED_FORM_NAME
    defaultAnnexureQuestionsShouldNotBeFound(
      "formName.contains=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFormNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where formName does not contain DEFAULT_FORM_NAME
    defaultAnnexureQuestionsShouldNotBeFound(
      "formName.doesNotContain=" + DEFAULT_FORM_NAME
    );

    // Get all the annexureQuestionsList where formName does not contain UPDATED_FORM_NAME
    defaultAnnexureQuestionsShouldBeFound(
      "formName.doesNotContain=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where type equals to DEFAULT_TYPE
    defaultAnnexureQuestionsShouldBeFound("type.equals=" + DEFAULT_TYPE);

    // Get all the annexureQuestionsList where type equals to UPDATED_TYPE
    defaultAnnexureQuestionsShouldNotBeFound("type.equals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where type not equals to DEFAULT_TYPE
    defaultAnnexureQuestionsShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

    // Get all the annexureQuestionsList where type not equals to UPDATED_TYPE
    defaultAnnexureQuestionsShouldBeFound("type.notEquals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByTypeIsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where type in DEFAULT_TYPE or UPDATED_TYPE
    defaultAnnexureQuestionsShouldBeFound(
      "type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE
    );

    // Get all the annexureQuestionsList where type equals to UPDATED_TYPE
    defaultAnnexureQuestionsShouldNotBeFound("type.in=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where type is not null
    defaultAnnexureQuestionsShouldBeFound("type.specified=true");

    // Get all the annexureQuestionsList where type is null
    defaultAnnexureQuestionsShouldNotBeFound("type.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByTypeContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where type contains DEFAULT_TYPE
    defaultAnnexureQuestionsShouldBeFound("type.contains=" + DEFAULT_TYPE);

    // Get all the annexureQuestionsList where type contains UPDATED_TYPE
    defaultAnnexureQuestionsShouldNotBeFound("type.contains=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByTypeNotContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where type does not contain DEFAULT_TYPE
    defaultAnnexureQuestionsShouldNotBeFound(
      "type.doesNotContain=" + DEFAULT_TYPE
    );

    // Get all the annexureQuestionsList where type does not contain UPDATED_TYPE
    defaultAnnexureQuestionsShouldBeFound(
      "type.doesNotContain=" + UPDATED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsBySubTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where subType equals to DEFAULT_SUB_TYPE
    defaultAnnexureQuestionsShouldBeFound("subType.equals=" + DEFAULT_SUB_TYPE);

    // Get all the annexureQuestionsList where subType equals to UPDATED_SUB_TYPE
    defaultAnnexureQuestionsShouldNotBeFound(
      "subType.equals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsBySubTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where subType not equals to DEFAULT_SUB_TYPE
    defaultAnnexureQuestionsShouldNotBeFound(
      "subType.notEquals=" + DEFAULT_SUB_TYPE
    );

    // Get all the annexureQuestionsList where subType not equals to UPDATED_SUB_TYPE
    defaultAnnexureQuestionsShouldBeFound(
      "subType.notEquals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsBySubTypeIsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where subType in DEFAULT_SUB_TYPE or UPDATED_SUB_TYPE
    defaultAnnexureQuestionsShouldBeFound(
      "subType.in=" + DEFAULT_SUB_TYPE + "," + UPDATED_SUB_TYPE
    );

    // Get all the annexureQuestionsList where subType equals to UPDATED_SUB_TYPE
    defaultAnnexureQuestionsShouldNotBeFound("subType.in=" + UPDATED_SUB_TYPE);
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsBySubTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where subType is not null
    defaultAnnexureQuestionsShouldBeFound("subType.specified=true");

    // Get all the annexureQuestionsList where subType is null
    defaultAnnexureQuestionsShouldNotBeFound("subType.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsBySubTypeContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where subType contains DEFAULT_SUB_TYPE
    defaultAnnexureQuestionsShouldBeFound(
      "subType.contains=" + DEFAULT_SUB_TYPE
    );

    // Get all the annexureQuestionsList where subType contains UPDATED_SUB_TYPE
    defaultAnnexureQuestionsShouldNotBeFound(
      "subType.contains=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsBySubTypeNotContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where subType does not contain DEFAULT_SUB_TYPE
    defaultAnnexureQuestionsShouldNotBeFound(
      "subType.doesNotContain=" + DEFAULT_SUB_TYPE
    );

    // Get all the annexureQuestionsList where subType does not contain UPDATED_SUB_TYPE
    defaultAnnexureQuestionsShouldBeFound(
      "subType.doesNotContain=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByDescriptionIsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where description equals to DEFAULT_DESCRIPTION
    defaultAnnexureQuestionsShouldBeFound(
      "description.equals=" + DEFAULT_DESCRIPTION
    );

    // Get all the annexureQuestionsList where description equals to UPDATED_DESCRIPTION
    defaultAnnexureQuestionsShouldNotBeFound(
      "description.equals=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByDescriptionIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where description not equals to DEFAULT_DESCRIPTION
    defaultAnnexureQuestionsShouldNotBeFound(
      "description.notEquals=" + DEFAULT_DESCRIPTION
    );

    // Get all the annexureQuestionsList where description not equals to UPDATED_DESCRIPTION
    defaultAnnexureQuestionsShouldBeFound(
      "description.notEquals=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByDescriptionIsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
    defaultAnnexureQuestionsShouldBeFound(
      "description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION
    );

    // Get all the annexureQuestionsList where description equals to UPDATED_DESCRIPTION
    defaultAnnexureQuestionsShouldNotBeFound(
      "description.in=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByDescriptionIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where description is not null
    defaultAnnexureQuestionsShouldBeFound("description.specified=true");

    // Get all the annexureQuestionsList where description is null
    defaultAnnexureQuestionsShouldNotBeFound("description.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByDescriptionContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where description contains DEFAULT_DESCRIPTION
    defaultAnnexureQuestionsShouldBeFound(
      "description.contains=" + DEFAULT_DESCRIPTION
    );

    // Get all the annexureQuestionsList where description contains UPDATED_DESCRIPTION
    defaultAnnexureQuestionsShouldNotBeFound(
      "description.contains=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByDescriptionNotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where description does not contain DEFAULT_DESCRIPTION
    defaultAnnexureQuestionsShouldNotBeFound(
      "description.doesNotContain=" + DEFAULT_DESCRIPTION
    );

    // Get all the annexureQuestionsList where description does not contain UPDATED_DESCRIPTION
    defaultAnnexureQuestionsShouldBeFound(
      "description.doesNotContain=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField1IsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultAnnexureQuestionsShouldBeFound(
      "freeField1.equals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the annexureQuestionsList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField1.equals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField1IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the annexureQuestionsList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultAnnexureQuestionsShouldBeFound(
      "freeField1.notEquals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField1IsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultAnnexureQuestionsShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the annexureQuestionsList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField1.in=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField1IsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField1 is not null
    defaultAnnexureQuestionsShouldBeFound("freeField1.specified=true");

    // Get all the annexureQuestionsList where freeField1 is null
    defaultAnnexureQuestionsShouldNotBeFound("freeField1.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField1ContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultAnnexureQuestionsShouldBeFound(
      "freeField1.contains=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the annexureQuestionsList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField1.contains=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField1NotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the annexureQuestionsList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultAnnexureQuestionsShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField2IsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultAnnexureQuestionsShouldBeFound(
      "freeField2.equals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the annexureQuestionsList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField2.equals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField2IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the annexureQuestionsList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultAnnexureQuestionsShouldBeFound(
      "freeField2.notEquals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField2IsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultAnnexureQuestionsShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the annexureQuestionsList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField2.in=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField2IsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField2 is not null
    defaultAnnexureQuestionsShouldBeFound("freeField2.specified=true");

    // Get all the annexureQuestionsList where freeField2 is null
    defaultAnnexureQuestionsShouldNotBeFound("freeField2.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField2ContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultAnnexureQuestionsShouldBeFound(
      "freeField2.contains=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the annexureQuestionsList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField2.contains=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField2NotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the annexureQuestionsList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultAnnexureQuestionsShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField3IsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultAnnexureQuestionsShouldBeFound(
      "freeField3.equals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the annexureQuestionsList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField3.equals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField3IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the annexureQuestionsList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultAnnexureQuestionsShouldBeFound(
      "freeField3.notEquals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField3IsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultAnnexureQuestionsShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the annexureQuestionsList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField3.in=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField3IsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField3 is not null
    defaultAnnexureQuestionsShouldBeFound("freeField3.specified=true");

    // Get all the annexureQuestionsList where freeField3 is null
    defaultAnnexureQuestionsShouldNotBeFound("freeField3.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField3ContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultAnnexureQuestionsShouldBeFound(
      "freeField3.contains=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the annexureQuestionsList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField3.contains=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField3NotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the annexureQuestionsList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultAnnexureQuestionsShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField4IsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField4 equals to DEFAULT_FREE_FIELD_4
    defaultAnnexureQuestionsShouldBeFound(
      "freeField4.equals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the annexureQuestionsList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField4.equals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField4IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField4 not equals to DEFAULT_FREE_FIELD_4
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField4.notEquals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the annexureQuestionsList where freeField4 not equals to UPDATED_FREE_FIELD_4
    defaultAnnexureQuestionsShouldBeFound(
      "freeField4.notEquals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField4IsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
    defaultAnnexureQuestionsShouldBeFound(
      "freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4
    );

    // Get all the annexureQuestionsList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField4.in=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField4IsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField4 is not null
    defaultAnnexureQuestionsShouldBeFound("freeField4.specified=true");

    // Get all the annexureQuestionsList where freeField4 is null
    defaultAnnexureQuestionsShouldNotBeFound("freeField4.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField4ContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField4 contains DEFAULT_FREE_FIELD_4
    defaultAnnexureQuestionsShouldBeFound(
      "freeField4.contains=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the annexureQuestionsList where freeField4 contains UPDATED_FREE_FIELD_4
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField4.contains=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByFreeField4NotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where freeField4 does not contain DEFAULT_FREE_FIELD_4
    defaultAnnexureQuestionsShouldNotBeFound(
      "freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the annexureQuestionsList where freeField4 does not contain UPDATED_FREE_FIELD_4
    defaultAnnexureQuestionsShouldBeFound(
      "freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedDateIsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdDate equals to DEFAULT_CREATED_DATE
    defaultAnnexureQuestionsShouldBeFound(
      "createdDate.equals=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureQuestionsList where createdDate equals to UPDATED_CREATED_DATE
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdDate.equals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureQuestionsList where createdDate not equals to UPDATED_CREATED_DATE
    defaultAnnexureQuestionsShouldBeFound(
      "createdDate.notEquals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedDateIsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultAnnexureQuestionsShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the annexureQuestionsList where createdDate equals to UPDATED_CREATED_DATE
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdDate.in=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdDate is not null
    defaultAnnexureQuestionsShouldBeFound("createdDate.specified=true");

    // Get all the annexureQuestionsList where createdDate is null
    defaultAnnexureQuestionsShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultAnnexureQuestionsShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureQuestionsList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultAnnexureQuestionsShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureQuestionsList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdDate is less than DEFAULT_CREATED_DATE
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureQuestionsList where createdDate is less than UPDATED_CREATED_DATE
    defaultAnnexureQuestionsShouldBeFound(
      "createdDate.lessThan=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the annexureQuestionsList where createdDate is greater than SMALLER_CREATED_DATE
    defaultAnnexureQuestionsShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedByIsEqualToSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdBy equals to DEFAULT_CREATED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "createdBy.equals=" + DEFAULT_CREATED_BY
    );

    // Get all the annexureQuestionsList where createdBy equals to UPDATED_CREATED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdBy.equals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdBy not equals to DEFAULT_CREATED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdBy.notEquals=" + DEFAULT_CREATED_BY
    );

    // Get all the annexureQuestionsList where createdBy not equals to UPDATED_CREATED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "createdBy.notEquals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedByIsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the annexureQuestionsList where createdBy equals to UPDATED_CREATED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdBy.in=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdBy is not null
    defaultAnnexureQuestionsShouldBeFound("createdBy.specified=true");

    // Get all the annexureQuestionsList where createdBy is null
    defaultAnnexureQuestionsShouldNotBeFound("createdBy.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedByContainsSomething() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdBy contains DEFAULT_CREATED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "createdBy.contains=" + DEFAULT_CREATED_BY
    );

    // Get all the annexureQuestionsList where createdBy contains UPDATED_CREATED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdBy.contains=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByCreatedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where createdBy does not contain DEFAULT_CREATED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the annexureQuestionsList where createdBy does not contain UPDATED_CREATED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "createdBy.doesNotContain=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultAnnexureQuestionsShouldBeFound(
      "lastModified.equals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureQuestionsList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureQuestionsList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultAnnexureQuestionsShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedIsInShouldWork() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultAnnexureQuestionsShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the annexureQuestionsList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModified.in=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedIsNullOrNotNull() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModified is not null
    defaultAnnexureQuestionsShouldBeFound("lastModified.specified=true");

    // Get all the annexureQuestionsList where lastModified is null
    defaultAnnexureQuestionsShouldNotBeFound("lastModified.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultAnnexureQuestionsShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureQuestionsList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultAnnexureQuestionsShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureQuestionsList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedIsLessThanSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureQuestionsList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultAnnexureQuestionsShouldBeFound(
      "lastModified.lessThan=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the annexureQuestionsList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultAnnexureQuestionsShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the annexureQuestionsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the annexureQuestionsList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedByIsInShouldWork()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the annexureQuestionsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModifiedBy is not null
    defaultAnnexureQuestionsShouldBeFound("lastModifiedBy.specified=true");

    // Get all the annexureQuestionsList where lastModifiedBy is null
    defaultAnnexureQuestionsShouldNotBeFound("lastModifiedBy.specified=false");
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedByContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the annexureQuestionsList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByLastModifiedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    // Get all the annexureQuestionsList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the annexureQuestionsList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultAnnexureQuestionsShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAnnexureQuestionsByAnnexureAnswersIsEqualToSomething()
    throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);
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
    annexureQuestions.addAnnexureAnswers(annexureAnswers);
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);
    Long annexureAnswersId = annexureAnswers.getId();

    // Get all the annexureQuestionsList where annexureAnswers equals to annexureAnswersId
    defaultAnnexureQuestionsShouldBeFound(
      "annexureAnswersId.equals=" + annexureAnswersId
    );

    // Get all the annexureQuestionsList where annexureAnswers equals to (annexureAnswersId + 1)
    defaultAnnexureQuestionsShouldNotBeFound(
      "annexureAnswersId.equals=" + (annexureAnswersId + 1)
    );
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultAnnexureQuestionsShouldBeFound(String filter)
    throws Exception {
    restAnnexureQuestionsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(annexureQuestions.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(
        jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION))
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
    restAnnexureQuestionsMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultAnnexureQuestionsShouldNotBeFound(String filter)
    throws Exception {
    restAnnexureQuestionsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restAnnexureQuestionsMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingAnnexureQuestions() throws Exception {
    // Get the annexureQuestions
    restAnnexureQuestionsMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewAnnexureQuestions() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    int databaseSizeBeforeUpdate = annexureQuestionsRepository.findAll().size();

    // Update the annexureQuestions
    AnnexureQuestions updatedAnnexureQuestions = annexureQuestionsRepository
      .findById(annexureQuestions.getId())
      .get();
    // Disconnect from session so that the updates on updatedAnnexureQuestions are not directly saved in db
    em.detach(updatedAnnexureQuestions);
    updatedAnnexureQuestions
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .description(UPDATED_DESCRIPTION)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    AnnexureQuestions annexureQuestions = annexureQuestionsMapper.toDto(
      updatedAnnexureQuestions
    );

    restAnnexureQuestionsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, annexureQuestionsDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureQuestionsDTO))
      )
      .andExpect(status().isOk());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeUpdate);
    AnnexureQuestions testAnnexureQuestions = annexureQuestionsList.get(
      annexureQuestionsList.size() - 1
    );
    assertThat(testAnnexureQuestions.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testAnnexureQuestions.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testAnnexureQuestions.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testAnnexureQuestions.getDescription())
      .isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testAnnexureQuestions.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAnnexureQuestions.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAnnexureQuestions.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAnnexureQuestions.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
    assertThat(testAnnexureQuestions.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAnnexureQuestions.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAnnexureQuestions.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAnnexureQuestions.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void putNonExistingAnnexureQuestions() throws Exception {
    int databaseSizeBeforeUpdate = annexureQuestionsRepository.findAll().size();
    annexureQuestions.setId(count.incrementAndGet());

    // Create the AnnexureQuestions
    AnnexureQuestions annexureQuestions = annexureQuestionsMapper.toDto(
      annexureQuestions
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAnnexureQuestionsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, annexureQuestions.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureQuestions))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchAnnexureQuestions() throws Exception {
    int databaseSizeBeforeUpdate = annexureQuestionsRepository.findAll().size();
    annexureQuestions.setId(count.incrementAndGet());

    // Create the AnnexureQuestions
    AnnexureQuestions annexureQuestions = annexureQuestionsMapper.toDto(
      annexureQuestions
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAnnexureQuestionsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureQuestions))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamAnnexureQuestions() throws Exception {
    int databaseSizeBeforeUpdate = annexureQuestionsRepository.findAll().size();
    annexureQuestions.setId(count.incrementAndGet());

    // Create the AnnexureQuestions
    AnnexureQuestions annexureQuestions = annexureQuestionsMapper.toDto(
      annexureQuestions
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAnnexureQuestionsMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(annexureQuestions))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateAnnexureQuestionsWithPatch() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    int databaseSizeBeforeUpdate = annexureQuestionsRepository.findAll().size();

    // Update the annexureQuestions using partial update
    AnnexureQuestions partialUpdatedAnnexureQuestions = new AnnexureQuestions();
    partialUpdatedAnnexureQuestions.setId(annexureQuestions.getId());

    partialUpdatedAnnexureQuestions
      .subType(UPDATED_SUB_TYPE)
      .freeField4(UPDATED_FREE_FIELD_4)
      .createdDate(UPDATED_CREATED_DATE);

    restAnnexureQuestionsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAnnexureQuestions.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedAnnexureQuestions)
          )
      )
      .andExpect(status().isOk());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeUpdate);
    AnnexureQuestions testAnnexureQuestions = annexureQuestionsList.get(
      annexureQuestionsList.size() - 1
    );
    assertThat(testAnnexureQuestions.getFormName())
      .isEqualTo(DEFAULT_FORM_NAME);
    assertThat(testAnnexureQuestions.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testAnnexureQuestions.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testAnnexureQuestions.getDescription())
      .isEqualTo(DEFAULT_DESCRIPTION);
    assertThat(testAnnexureQuestions.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testAnnexureQuestions.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testAnnexureQuestions.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testAnnexureQuestions.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
    assertThat(testAnnexureQuestions.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAnnexureQuestions.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testAnnexureQuestions.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testAnnexureQuestions.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void fullUpdateAnnexureQuestionsWithPatch() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    int databaseSizeBeforeUpdate = annexureQuestionsRepository.findAll().size();

    // Update the annexureQuestions using partial update
    AnnexureQuestions partialUpdatedAnnexureQuestions = new AnnexureQuestions();
    partialUpdatedAnnexureQuestions.setId(annexureQuestions.getId());

    partialUpdatedAnnexureQuestions
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .description(UPDATED_DESCRIPTION)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

    restAnnexureQuestionsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAnnexureQuestions.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedAnnexureQuestions)
          )
      )
      .andExpect(status().isOk());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeUpdate);
    AnnexureQuestions testAnnexureQuestions = annexureQuestionsList.get(
      annexureQuestionsList.size() - 1
    );
    assertThat(testAnnexureQuestions.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testAnnexureQuestions.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testAnnexureQuestions.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testAnnexureQuestions.getDescription())
      .isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testAnnexureQuestions.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAnnexureQuestions.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAnnexureQuestions.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAnnexureQuestions.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
    assertThat(testAnnexureQuestions.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAnnexureQuestions.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAnnexureQuestions.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAnnexureQuestions.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void patchNonExistingAnnexureQuestions() throws Exception {
    int databaseSizeBeforeUpdate = annexureQuestionsRepository.findAll().size();
    annexureQuestions.setId(count.incrementAndGet());

    // Create the AnnexureQuestions
    AnnexureQuestionsDTO annexureQuestionsDTO = annexureQuestionsMapper.toDto(
      annexureQuestions
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAnnexureQuestionsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, annexureQuestionsDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(annexureQuestionsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchAnnexureQuestions() throws Exception {
    int databaseSizeBeforeUpdate = annexureQuestionsRepository.findAll().size();
    annexureQuestions.setId(count.incrementAndGet());

    // Create the AnnexureQuestions
    AnnexureQuestionsDTO annexureQuestionsDTO = annexureQuestionsMapper.toDto(
      annexureQuestions
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAnnexureQuestionsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(annexureQuestionsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamAnnexureQuestions() throws Exception {
    int databaseSizeBeforeUpdate = annexureQuestionsRepository.findAll().size();
    annexureQuestions.setId(count.incrementAndGet());

    // Create the AnnexureQuestions
    AnnexureQuestionsDTO annexureQuestionsDTO = annexureQuestionsMapper.toDto(
      annexureQuestions
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAnnexureQuestionsMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(annexureQuestionsDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the AnnexureQuestions in the database
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteAnnexureQuestions() throws Exception {
    // Initialize the database
    annexureQuestionsRepository.saveAndFlush(annexureQuestions);

    int databaseSizeBeforeDelete = annexureQuestionsRepository.findAll().size();

    // Delete the annexureQuestions
    restAnnexureQuestionsMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, annexureQuestions.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<AnnexureQuestions> annexureQuestionsList = annexureQuestionsRepository.findAll();
    assertThat(annexureQuestionsList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
