package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.OxygenConsumptionData;
import com.vgtech.auditapp.domain.TableDetails;
import com.vgtech.auditapp.repository.TableDetailsRepository;
import com.vgtech.auditapp.service.criteria.TableDetailsCriteria;
import com.vgtech.auditapp.service.dto.TableDetails;
import com.vgtech.auditapp.service.mapper.TableDetailsMapper;
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
 * Integration tests for the {@link TableDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TableDetailsResourceIT {

  private static final String DEFAULT_FORM_NAME = "AAAAAAAAAA";
  private static final String UPDATED_FORM_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_SUB_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_SUB_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_TABLE_NAME = "AAAAAAAAAA";
  private static final String UPDATED_TABLE_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

  private static final String DEFAULT_DESCRIPTION_PARAMETER = "AAAAAAAAAA";
  private static final String UPDATED_DESCRIPTION_PARAMETER = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

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

  private static final String ENTITY_API_URL = "/api/table-details";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private TableDetailsRepository tableDetailsRepository;

  @Autowired
  private TableDetailsMapper tableDetailsMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restTableDetailsMockMvc;

  private TableDetails tableDetails;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TableDetails createEntity(EntityManager em) {
    TableDetails tableDetails = new TableDetails()
      .formName(DEFAULT_FORM_NAME)
      .type(DEFAULT_TYPE)
      .subType(DEFAULT_SUB_TYPE)
      .tableName(DEFAULT_TABLE_NAME)
      .description(DEFAULT_DESCRIPTION)
      .descriptionParameter(DEFAULT_DESCRIPTION_PARAMETER)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .createdDate(DEFAULT_CREATED_DATE)
      .createdBy(DEFAULT_CREATED_BY)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
    return tableDetails;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static TableDetails createUpdatedEntity(EntityManager em) {
    TableDetails tableDetails = new TableDetails()
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .description(UPDATED_DESCRIPTION)
      .descriptionParameter(UPDATED_DESCRIPTION_PARAMETER)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    return tableDetails;
  }

  @BeforeEach
  public void initTest() {
    tableDetails = createEntity(em);
  }

  @Test
  @Transactional
  void createTableDetails() throws Exception {
    int databaseSizeBeforeCreate = tableDetailsRepository.findAll().size();
    // Create the TableDetails
    TableDetails tableDetails = tableDetailsMapper.toDto(tableDetails);
    restTableDetailsMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(tableDetails))
      )
      .andExpect(status().isCreated());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeCreate + 1);
    TableDetails testTableDetails = tableDetailsList.get(
      tableDetailsList.size() - 1
    );
    assertThat(testTableDetails.getFormName()).isEqualTo(DEFAULT_FORM_NAME);
    assertThat(testTableDetails.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testTableDetails.getSubType()).isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testTableDetails.getTableName()).isEqualTo(DEFAULT_TABLE_NAME);
    assertThat(testTableDetails.getDescription())
      .isEqualTo(DEFAULT_DESCRIPTION);
    assertThat(testTableDetails.getDescriptionParameter())
      .isEqualTo(DEFAULT_DESCRIPTION_PARAMETER);
    assertThat(testTableDetails.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testTableDetails.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testTableDetails.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testTableDetails.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testTableDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testTableDetails.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testTableDetails.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void createTableDetailsWithExistingId() throws Exception {
    // Create the TableDetails with an existing ID
    tableDetails.setId(1L);
    TableDetails tableDetails = tableDetailsMapper.toDto(tableDetails);

    int databaseSizeBeforeCreate = tableDetailsRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restTableDetailsMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(tableDetails))
      )
      .andExpect(status().isBadRequest());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllTableDetails() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList
    restTableDetailsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(tableDetails.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
      .andExpect(
        jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION))
      )
      .andExpect(
        jsonPath("$.[*].descriptionParameter")
          .value(hasItem(DEFAULT_DESCRIPTION_PARAMETER))
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
  void getTableDetails() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get the tableDetails
    restTableDetailsMockMvc
      .perform(get(ENTITY_API_URL_ID, tableDetails.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(tableDetails.getId().intValue()))
      .andExpect(jsonPath("$.formName").value(DEFAULT_FORM_NAME))
      .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
      .andExpect(jsonPath("$.subType").value(DEFAULT_SUB_TYPE))
      .andExpect(jsonPath("$.tableName").value(DEFAULT_TABLE_NAME))
      .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
      .andExpect(
        jsonPath("$.descriptionParameter").value(DEFAULT_DESCRIPTION_PARAMETER)
      )
      .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
      .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
      .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
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
  void getTableDetailsByIdFiltering() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    Long id = tableDetails.getId();

    defaultTableDetailsShouldBeFound("id.equals=" + id);
    defaultTableDetailsShouldNotBeFound("id.notEquals=" + id);

    defaultTableDetailsShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultTableDetailsShouldNotBeFound("id.greaterThan=" + id);

    defaultTableDetailsShouldBeFound("id.lessThanOrEqual=" + id);
    defaultTableDetailsShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllTableDetailsByFormNameIsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where formName equals to DEFAULT_FORM_NAME
    defaultTableDetailsShouldBeFound("formName.equals=" + DEFAULT_FORM_NAME);

    // Get all the tableDetailsList where formName equals to UPDATED_FORM_NAME
    defaultTableDetailsShouldNotBeFound("formName.equals=" + UPDATED_FORM_NAME);
  }

  @Test
  @Transactional
  void getAllTableDetailsByFormNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where formName not equals to DEFAULT_FORM_NAME
    defaultTableDetailsShouldNotBeFound(
      "formName.notEquals=" + DEFAULT_FORM_NAME
    );

    // Get all the tableDetailsList where formName not equals to UPDATED_FORM_NAME
    defaultTableDetailsShouldBeFound("formName.notEquals=" + UPDATED_FORM_NAME);
  }

  @Test
  @Transactional
  void getAllTableDetailsByFormNameIsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where formName in DEFAULT_FORM_NAME or UPDATED_FORM_NAME
    defaultTableDetailsShouldBeFound(
      "formName.in=" + DEFAULT_FORM_NAME + "," + UPDATED_FORM_NAME
    );

    // Get all the tableDetailsList where formName equals to UPDATED_FORM_NAME
    defaultTableDetailsShouldNotBeFound("formName.in=" + UPDATED_FORM_NAME);
  }

  @Test
  @Transactional
  void getAllTableDetailsByFormNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where formName is not null
    defaultTableDetailsShouldBeFound("formName.specified=true");

    // Get all the tableDetailsList where formName is null
    defaultTableDetailsShouldNotBeFound("formName.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByFormNameContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where formName contains DEFAULT_FORM_NAME
    defaultTableDetailsShouldBeFound("formName.contains=" + DEFAULT_FORM_NAME);

    // Get all the tableDetailsList where formName contains UPDATED_FORM_NAME
    defaultTableDetailsShouldNotBeFound(
      "formName.contains=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFormNameNotContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where formName does not contain DEFAULT_FORM_NAME
    defaultTableDetailsShouldNotBeFound(
      "formName.doesNotContain=" + DEFAULT_FORM_NAME
    );

    // Get all the tableDetailsList where formName does not contain UPDATED_FORM_NAME
    defaultTableDetailsShouldBeFound(
      "formName.doesNotContain=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where type equals to DEFAULT_TYPE
    defaultTableDetailsShouldBeFound("type.equals=" + DEFAULT_TYPE);

    // Get all the tableDetailsList where type equals to UPDATED_TYPE
    defaultTableDetailsShouldNotBeFound("type.equals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllTableDetailsByTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where type not equals to DEFAULT_TYPE
    defaultTableDetailsShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

    // Get all the tableDetailsList where type not equals to UPDATED_TYPE
    defaultTableDetailsShouldBeFound("type.notEquals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllTableDetailsByTypeIsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where type in DEFAULT_TYPE or UPDATED_TYPE
    defaultTableDetailsShouldBeFound(
      "type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE
    );

    // Get all the tableDetailsList where type equals to UPDATED_TYPE
    defaultTableDetailsShouldNotBeFound("type.in=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllTableDetailsByTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where type is not null
    defaultTableDetailsShouldBeFound("type.specified=true");

    // Get all the tableDetailsList where type is null
    defaultTableDetailsShouldNotBeFound("type.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByTypeContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where type contains DEFAULT_TYPE
    defaultTableDetailsShouldBeFound("type.contains=" + DEFAULT_TYPE);

    // Get all the tableDetailsList where type contains UPDATED_TYPE
    defaultTableDetailsShouldNotBeFound("type.contains=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllTableDetailsByTypeNotContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where type does not contain DEFAULT_TYPE
    defaultTableDetailsShouldNotBeFound("type.doesNotContain=" + DEFAULT_TYPE);

    // Get all the tableDetailsList where type does not contain UPDATED_TYPE
    defaultTableDetailsShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllTableDetailsBySubTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where subType equals to DEFAULT_SUB_TYPE
    defaultTableDetailsShouldBeFound("subType.equals=" + DEFAULT_SUB_TYPE);

    // Get all the tableDetailsList where subType equals to UPDATED_SUB_TYPE
    defaultTableDetailsShouldNotBeFound("subType.equals=" + UPDATED_SUB_TYPE);
  }

  @Test
  @Transactional
  void getAllTableDetailsBySubTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where subType not equals to DEFAULT_SUB_TYPE
    defaultTableDetailsShouldNotBeFound(
      "subType.notEquals=" + DEFAULT_SUB_TYPE
    );

    // Get all the tableDetailsList where subType not equals to UPDATED_SUB_TYPE
    defaultTableDetailsShouldBeFound("subType.notEquals=" + UPDATED_SUB_TYPE);
  }

  @Test
  @Transactional
  void getAllTableDetailsBySubTypeIsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where subType in DEFAULT_SUB_TYPE or UPDATED_SUB_TYPE
    defaultTableDetailsShouldBeFound(
      "subType.in=" + DEFAULT_SUB_TYPE + "," + UPDATED_SUB_TYPE
    );

    // Get all the tableDetailsList where subType equals to UPDATED_SUB_TYPE
    defaultTableDetailsShouldNotBeFound("subType.in=" + UPDATED_SUB_TYPE);
  }

  @Test
  @Transactional
  void getAllTableDetailsBySubTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where subType is not null
    defaultTableDetailsShouldBeFound("subType.specified=true");

    // Get all the tableDetailsList where subType is null
    defaultTableDetailsShouldNotBeFound("subType.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsBySubTypeContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where subType contains DEFAULT_SUB_TYPE
    defaultTableDetailsShouldBeFound("subType.contains=" + DEFAULT_SUB_TYPE);

    // Get all the tableDetailsList where subType contains UPDATED_SUB_TYPE
    defaultTableDetailsShouldNotBeFound("subType.contains=" + UPDATED_SUB_TYPE);
  }

  @Test
  @Transactional
  void getAllTableDetailsBySubTypeNotContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where subType does not contain DEFAULT_SUB_TYPE
    defaultTableDetailsShouldNotBeFound(
      "subType.doesNotContain=" + DEFAULT_SUB_TYPE
    );

    // Get all the tableDetailsList where subType does not contain UPDATED_SUB_TYPE
    defaultTableDetailsShouldBeFound(
      "subType.doesNotContain=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByTableNameIsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where tableName equals to DEFAULT_TABLE_NAME
    defaultTableDetailsShouldBeFound("tableName.equals=" + DEFAULT_TABLE_NAME);

    // Get all the tableDetailsList where tableName equals to UPDATED_TABLE_NAME
    defaultTableDetailsShouldNotBeFound(
      "tableName.equals=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByTableNameIsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where tableName not equals to DEFAULT_TABLE_NAME
    defaultTableDetailsShouldNotBeFound(
      "tableName.notEquals=" + DEFAULT_TABLE_NAME
    );

    // Get all the tableDetailsList where tableName not equals to UPDATED_TABLE_NAME
    defaultTableDetailsShouldBeFound(
      "tableName.notEquals=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByTableNameIsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where tableName in DEFAULT_TABLE_NAME or UPDATED_TABLE_NAME
    defaultTableDetailsShouldBeFound(
      "tableName.in=" + DEFAULT_TABLE_NAME + "," + UPDATED_TABLE_NAME
    );

    // Get all the tableDetailsList where tableName equals to UPDATED_TABLE_NAME
    defaultTableDetailsShouldNotBeFound("tableName.in=" + UPDATED_TABLE_NAME);
  }

  @Test
  @Transactional
  void getAllTableDetailsByTableNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where tableName is not null
    defaultTableDetailsShouldBeFound("tableName.specified=true");

    // Get all the tableDetailsList where tableName is null
    defaultTableDetailsShouldNotBeFound("tableName.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByTableNameContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where tableName contains DEFAULT_TABLE_NAME
    defaultTableDetailsShouldBeFound(
      "tableName.contains=" + DEFAULT_TABLE_NAME
    );

    // Get all the tableDetailsList where tableName contains UPDATED_TABLE_NAME
    defaultTableDetailsShouldNotBeFound(
      "tableName.contains=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByTableNameNotContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where tableName does not contain DEFAULT_TABLE_NAME
    defaultTableDetailsShouldNotBeFound(
      "tableName.doesNotContain=" + DEFAULT_TABLE_NAME
    );

    // Get all the tableDetailsList where tableName does not contain UPDATED_TABLE_NAME
    defaultTableDetailsShouldBeFound(
      "tableName.doesNotContain=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionIsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where description equals to DEFAULT_DESCRIPTION
    defaultTableDetailsShouldBeFound(
      "description.equals=" + DEFAULT_DESCRIPTION
    );

    // Get all the tableDetailsList where description equals to UPDATED_DESCRIPTION
    defaultTableDetailsShouldNotBeFound(
      "description.equals=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionIsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where description not equals to DEFAULT_DESCRIPTION
    defaultTableDetailsShouldNotBeFound(
      "description.notEquals=" + DEFAULT_DESCRIPTION
    );

    // Get all the tableDetailsList where description not equals to UPDATED_DESCRIPTION
    defaultTableDetailsShouldBeFound(
      "description.notEquals=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionIsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
    defaultTableDetailsShouldBeFound(
      "description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION
    );

    // Get all the tableDetailsList where description equals to UPDATED_DESCRIPTION
    defaultTableDetailsShouldNotBeFound(
      "description.in=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionIsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where description is not null
    defaultTableDetailsShouldBeFound("description.specified=true");

    // Get all the tableDetailsList where description is null
    defaultTableDetailsShouldNotBeFound("description.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where description contains DEFAULT_DESCRIPTION
    defaultTableDetailsShouldBeFound(
      "description.contains=" + DEFAULT_DESCRIPTION
    );

    // Get all the tableDetailsList where description contains UPDATED_DESCRIPTION
    defaultTableDetailsShouldNotBeFound(
      "description.contains=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionNotContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where description does not contain DEFAULT_DESCRIPTION
    defaultTableDetailsShouldNotBeFound(
      "description.doesNotContain=" + DEFAULT_DESCRIPTION
    );

    // Get all the tableDetailsList where description does not contain UPDATED_DESCRIPTION
    defaultTableDetailsShouldBeFound(
      "description.doesNotContain=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionParameterIsEqualToSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where descriptionParameter equals to DEFAULT_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldBeFound(
      "descriptionParameter.equals=" + DEFAULT_DESCRIPTION_PARAMETER
    );

    // Get all the tableDetailsList where descriptionParameter equals to UPDATED_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldNotBeFound(
      "descriptionParameter.equals=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionParameterIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where descriptionParameter not equals to DEFAULT_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldNotBeFound(
      "descriptionParameter.notEquals=" + DEFAULT_DESCRIPTION_PARAMETER
    );

    // Get all the tableDetailsList where descriptionParameter not equals to UPDATED_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldBeFound(
      "descriptionParameter.notEquals=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionParameterIsInShouldWork()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where descriptionParameter in DEFAULT_DESCRIPTION_PARAMETER or UPDATED_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldBeFound(
      "descriptionParameter.in=" +
      DEFAULT_DESCRIPTION_PARAMETER +
      "," +
      UPDATED_DESCRIPTION_PARAMETER
    );

    // Get all the tableDetailsList where descriptionParameter equals to UPDATED_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldNotBeFound(
      "descriptionParameter.in=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionParameterIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where descriptionParameter is not null
    defaultTableDetailsShouldBeFound("descriptionParameter.specified=true");

    // Get all the tableDetailsList where descriptionParameter is null
    defaultTableDetailsShouldNotBeFound("descriptionParameter.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionParameterContainsSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where descriptionParameter contains DEFAULT_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldBeFound(
      "descriptionParameter.contains=" + DEFAULT_DESCRIPTION_PARAMETER
    );

    // Get all the tableDetailsList where descriptionParameter contains UPDATED_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldNotBeFound(
      "descriptionParameter.contains=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByDescriptionParameterNotContainsSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where descriptionParameter does not contain DEFAULT_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldNotBeFound(
      "descriptionParameter.doesNotContain=" + DEFAULT_DESCRIPTION_PARAMETER
    );

    // Get all the tableDetailsList where descriptionParameter does not contain UPDATED_DESCRIPTION_PARAMETER
    defaultTableDetailsShouldBeFound(
      "descriptionParameter.doesNotContain=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField1IsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultTableDetailsShouldBeFound(
      "freeField1.equals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the tableDetailsList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultTableDetailsShouldNotBeFound(
      "freeField1.equals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField1IsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultTableDetailsShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the tableDetailsList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultTableDetailsShouldBeFound(
      "freeField1.notEquals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField1IsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultTableDetailsShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the tableDetailsList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultTableDetailsShouldNotBeFound(
      "freeField1.in=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField1IsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField1 is not null
    defaultTableDetailsShouldBeFound("freeField1.specified=true");

    // Get all the tableDetailsList where freeField1 is null
    defaultTableDetailsShouldNotBeFound("freeField1.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField1ContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultTableDetailsShouldBeFound(
      "freeField1.contains=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the tableDetailsList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultTableDetailsShouldNotBeFound(
      "freeField1.contains=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField1NotContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultTableDetailsShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the tableDetailsList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultTableDetailsShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField2IsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultTableDetailsShouldBeFound(
      "freeField2.equals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the tableDetailsList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultTableDetailsShouldNotBeFound(
      "freeField2.equals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField2IsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultTableDetailsShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the tableDetailsList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultTableDetailsShouldBeFound(
      "freeField2.notEquals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField2IsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultTableDetailsShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the tableDetailsList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultTableDetailsShouldNotBeFound(
      "freeField2.in=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField2IsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField2 is not null
    defaultTableDetailsShouldBeFound("freeField2.specified=true");

    // Get all the tableDetailsList where freeField2 is null
    defaultTableDetailsShouldNotBeFound("freeField2.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField2ContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultTableDetailsShouldBeFound(
      "freeField2.contains=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the tableDetailsList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultTableDetailsShouldNotBeFound(
      "freeField2.contains=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField2NotContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultTableDetailsShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the tableDetailsList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultTableDetailsShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField3IsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultTableDetailsShouldBeFound(
      "freeField3.equals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the tableDetailsList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultTableDetailsShouldNotBeFound(
      "freeField3.equals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField3IsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultTableDetailsShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the tableDetailsList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultTableDetailsShouldBeFound(
      "freeField3.notEquals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField3IsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultTableDetailsShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the tableDetailsList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultTableDetailsShouldNotBeFound(
      "freeField3.in=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField3IsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField3 is not null
    defaultTableDetailsShouldBeFound("freeField3.specified=true");

    // Get all the tableDetailsList where freeField3 is null
    defaultTableDetailsShouldNotBeFound("freeField3.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField3ContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultTableDetailsShouldBeFound(
      "freeField3.contains=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the tableDetailsList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultTableDetailsShouldNotBeFound(
      "freeField3.contains=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByFreeField3NotContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultTableDetailsShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the tableDetailsList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultTableDetailsShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedDateIsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdDate equals to DEFAULT_CREATED_DATE
    defaultTableDetailsShouldBeFound(
      "createdDate.equals=" + DEFAULT_CREATED_DATE
    );

    // Get all the tableDetailsList where createdDate equals to UPDATED_CREATED_DATE
    defaultTableDetailsShouldNotBeFound(
      "createdDate.equals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedDateIsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultTableDetailsShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the tableDetailsList where createdDate not equals to UPDATED_CREATED_DATE
    defaultTableDetailsShouldBeFound(
      "createdDate.notEquals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedDateIsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultTableDetailsShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the tableDetailsList where createdDate equals to UPDATED_CREATED_DATE
    defaultTableDetailsShouldNotBeFound(
      "createdDate.in=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdDate is not null
    defaultTableDetailsShouldBeFound("createdDate.specified=true");

    // Get all the tableDetailsList where createdDate is null
    defaultTableDetailsShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultTableDetailsShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the tableDetailsList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultTableDetailsShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultTableDetailsShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the tableDetailsList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultTableDetailsShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedDateIsLessThanSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdDate is less than DEFAULT_CREATED_DATE
    defaultTableDetailsShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the tableDetailsList where createdDate is less than UPDATED_CREATED_DATE
    defaultTableDetailsShouldBeFound(
      "createdDate.lessThan=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultTableDetailsShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the tableDetailsList where createdDate is greater than SMALLER_CREATED_DATE
    defaultTableDetailsShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedByIsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdBy equals to DEFAULT_CREATED_BY
    defaultTableDetailsShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

    // Get all the tableDetailsList where createdBy equals to UPDATED_CREATED_BY
    defaultTableDetailsShouldNotBeFound(
      "createdBy.equals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedByIsNotEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdBy not equals to DEFAULT_CREATED_BY
    defaultTableDetailsShouldNotBeFound(
      "createdBy.notEquals=" + DEFAULT_CREATED_BY
    );

    // Get all the tableDetailsList where createdBy not equals to UPDATED_CREATED_BY
    defaultTableDetailsShouldBeFound(
      "createdBy.notEquals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedByIsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultTableDetailsShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the tableDetailsList where createdBy equals to UPDATED_CREATED_BY
    defaultTableDetailsShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdBy is not null
    defaultTableDetailsShouldBeFound("createdBy.specified=true");

    // Get all the tableDetailsList where createdBy is null
    defaultTableDetailsShouldNotBeFound("createdBy.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedByContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdBy contains DEFAULT_CREATED_BY
    defaultTableDetailsShouldBeFound(
      "createdBy.contains=" + DEFAULT_CREATED_BY
    );

    // Get all the tableDetailsList where createdBy contains UPDATED_CREATED_BY
    defaultTableDetailsShouldNotBeFound(
      "createdBy.contains=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByCreatedByNotContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where createdBy does not contain DEFAULT_CREATED_BY
    defaultTableDetailsShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the tableDetailsList where createdBy does not contain UPDATED_CREATED_BY
    defaultTableDetailsShouldBeFound(
      "createdBy.doesNotContain=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedIsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultTableDetailsShouldBeFound(
      "lastModified.equals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the tableDetailsList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultTableDetailsShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultTableDetailsShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the tableDetailsList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultTableDetailsShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedIsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultTableDetailsShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the tableDetailsList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultTableDetailsShouldNotBeFound(
      "lastModified.in=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedIsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModified is not null
    defaultTableDetailsShouldBeFound("lastModified.specified=true");

    // Get all the tableDetailsList where lastModified is null
    defaultTableDetailsShouldNotBeFound("lastModified.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultTableDetailsShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the tableDetailsList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultTableDetailsShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultTableDetailsShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the tableDetailsList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultTableDetailsShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedIsLessThanSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultTableDetailsShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the tableDetailsList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultTableDetailsShouldBeFound(
      "lastModified.lessThan=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultTableDetailsShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the tableDetailsList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultTableDetailsShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedByIsEqualToSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultTableDetailsShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the tableDetailsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultTableDetailsShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultTableDetailsShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the tableDetailsList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultTableDetailsShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedByIsInShouldWork() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultTableDetailsShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the tableDetailsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultTableDetailsShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModifiedBy is not null
    defaultTableDetailsShouldBeFound("lastModifiedBy.specified=true");

    // Get all the tableDetailsList where lastModifiedBy is null
    defaultTableDetailsShouldNotBeFound("lastModifiedBy.specified=false");
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedByContainsSomething() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultTableDetailsShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the tableDetailsList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultTableDetailsShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByLastModifiedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    // Get all the tableDetailsList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultTableDetailsShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the tableDetailsList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultTableDetailsShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllTableDetailsByOxygenConsumptionDataIsEqualToSomething()
    throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);
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
    tableDetails.addOxygenConsumptionData(oxygenConsumptionData);
    tableDetailsRepository.saveAndFlush(tableDetails);
    Long oxygenConsumptionDataId = oxygenConsumptionData.getId();

    // Get all the tableDetailsList where oxygenConsumptionData equals to oxygenConsumptionDataId
    defaultTableDetailsShouldBeFound(
      "oxygenConsumptionDataId.equals=" + oxygenConsumptionDataId
    );

    // Get all the tableDetailsList where oxygenConsumptionData equals to (oxygenConsumptionDataId + 1)
    defaultTableDetailsShouldNotBeFound(
      "oxygenConsumptionDataId.equals=" + (oxygenConsumptionDataId + 1)
    );
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultTableDetailsShouldBeFound(String filter)
    throws Exception {
    restTableDetailsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(tableDetails.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
      .andExpect(
        jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION))
      )
      .andExpect(
        jsonPath("$.[*].descriptionParameter")
          .value(hasItem(DEFAULT_DESCRIPTION_PARAMETER))
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
    restTableDetailsMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultTableDetailsShouldNotBeFound(String filter)
    throws Exception {
    restTableDetailsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restTableDetailsMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingTableDetails() throws Exception {
    // Get the tableDetails
    restTableDetailsMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewTableDetails() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    int databaseSizeBeforeUpdate = tableDetailsRepository.findAll().size();

    // Update the tableDetails
    TableDetails updatedTableDetails = tableDetailsRepository
      .findById(tableDetails.getId())
      .get();
    // Disconnect from session so that the updates on updatedTableDetails are not directly saved in db
    em.detach(updatedTableDetails);
    updatedTableDetails
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .description(UPDATED_DESCRIPTION)
      .descriptionParameter(UPDATED_DESCRIPTION_PARAMETER)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    TableDetails tableDetails = tableDetailsMapper.toDto(updatedTableDetails);

    restTableDetailsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, tableDetailsDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(tableDetailsDTO))
      )
      .andExpect(status().isOk());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeUpdate);
    TableDetails testTableDetails = tableDetailsList.get(
      tableDetailsList.size() - 1
    );
    assertThat(testTableDetails.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    assertThat(testTableDetails.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testTableDetails.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testTableDetails.getTableName()).isEqualTo(UPDATED_TABLE_NAME);
    assertThat(testTableDetails.getDescription())
      .isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testTableDetails.getDescriptionParameter())
      .isEqualTo(UPDATED_DESCRIPTION_PARAMETER);
    assertThat(testTableDetails.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testTableDetails.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testTableDetails.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testTableDetails.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testTableDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testTableDetails.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testTableDetails.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void putNonExistingTableDetails() throws Exception {
    int databaseSizeBeforeUpdate = tableDetailsRepository.findAll().size();
    tableDetails.setId(count.incrementAndGet());

    // Create the TableDetails
    TableDetails tableDetails = tableDetailsMapper.toDto(tableDetails);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTableDetailsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, tableDetails.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(tableDetails))
      )
      .andExpect(status().isBadRequest());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchTableDetails() throws Exception {
    int databaseSizeBeforeUpdate = tableDetailsRepository.findAll().size();
    tableDetails.setId(count.incrementAndGet());

    // Create the TableDetails
    TableDetails tableDetails = tableDetailsMapper.toDto(tableDetails);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTableDetailsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(tableDetails))
      )
      .andExpect(status().isBadRequest());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamTableDetails() throws Exception {
    int databaseSizeBeforeUpdate = tableDetailsRepository.findAll().size();
    tableDetails.setId(count.incrementAndGet());

    // Create the TableDetails
    TableDetails tableDetails = tableDetailsMapper.toDto(tableDetails);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTableDetailsMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(tableDetails))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateTableDetailsWithPatch() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    int databaseSizeBeforeUpdate = tableDetailsRepository.findAll().size();

    // Update the tableDetails using partial update
    TableDetails partialUpdatedTableDetails = new TableDetails();
    partialUpdatedTableDetails.setId(tableDetails.getId());

    partialUpdatedTableDetails
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .description(UPDATED_DESCRIPTION)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdBy(UPDATED_CREATED_BY);

    restTableDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTableDetails.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedTableDetails)
          )
      )
      .andExpect(status().isOk());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeUpdate);
    TableDetails testTableDetails = tableDetailsList.get(
      tableDetailsList.size() - 1
    );
    assertThat(testTableDetails.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    assertThat(testTableDetails.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testTableDetails.getSubType()).isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testTableDetails.getTableName()).isEqualTo(DEFAULT_TABLE_NAME);
    assertThat(testTableDetails.getDescription())
      .isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testTableDetails.getDescriptionParameter())
      .isEqualTo(DEFAULT_DESCRIPTION_PARAMETER);
    assertThat(testTableDetails.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testTableDetails.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testTableDetails.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testTableDetails.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testTableDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testTableDetails.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testTableDetails.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void fullUpdateTableDetailsWithPatch() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    int databaseSizeBeforeUpdate = tableDetailsRepository.findAll().size();

    // Update the tableDetails using partial update
    TableDetails partialUpdatedTableDetails = new TableDetails();
    partialUpdatedTableDetails.setId(tableDetails.getId());

    partialUpdatedTableDetails
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .description(UPDATED_DESCRIPTION)
      .descriptionParameter(UPDATED_DESCRIPTION_PARAMETER)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

    restTableDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedTableDetails.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedTableDetails)
          )
      )
      .andExpect(status().isOk());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeUpdate);
    TableDetails testTableDetails = tableDetailsList.get(
      tableDetailsList.size() - 1
    );
    assertThat(testTableDetails.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    assertThat(testTableDetails.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testTableDetails.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testTableDetails.getTableName()).isEqualTo(UPDATED_TABLE_NAME);
    assertThat(testTableDetails.getDescription())
      .isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testTableDetails.getDescriptionParameter())
      .isEqualTo(UPDATED_DESCRIPTION_PARAMETER);
    assertThat(testTableDetails.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testTableDetails.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testTableDetails.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testTableDetails.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testTableDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testTableDetails.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testTableDetails.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void patchNonExistingTableDetails() throws Exception {
    int databaseSizeBeforeUpdate = tableDetailsRepository.findAll().size();
    tableDetails.setId(count.incrementAndGet());

    // Create the TableDetails
    TableDetailsDTO tableDetailsDTO = tableDetailsMapper.toDto(tableDetails);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restTableDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, tableDetailsDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(tableDetailsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchTableDetails() throws Exception {
    int databaseSizeBeforeUpdate = tableDetailsRepository.findAll().size();
    tableDetails.setId(count.incrementAndGet());

    // Create the TableDetails
    TableDetailsDTO tableDetailsDTO = tableDetailsMapper.toDto(tableDetails);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTableDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(tableDetailsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamTableDetails() throws Exception {
    int databaseSizeBeforeUpdate = tableDetailsRepository.findAll().size();
    tableDetails.setId(count.incrementAndGet());

    // Create the TableDetails
    TableDetailsDTO tableDetailsDTO = tableDetailsMapper.toDto(tableDetails);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restTableDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(tableDetailsDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the TableDetails in the database
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteTableDetails() throws Exception {
    // Initialize the database
    tableDetailsRepository.saveAndFlush(tableDetails);

    int databaseSizeBeforeDelete = tableDetailsRepository.findAll().size();

    // Delete the tableDetails
    restTableDetailsMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, tableDetails.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<TableDetails> tableDetailsList = tableDetailsRepository.findAll();
    assertThat(tableDetailsList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
