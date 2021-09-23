package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.domain.InventoryReport;
import com.vgtech.auditapp.repository.InventoryReportRepository;
import com.vgtech.auditapp.service.criteria.InventoryReportCriteria;
import com.vgtech.auditapp.service.dto.InventoryReport;
import com.vgtech.auditapp.service.mapper.InventoryReportMapper;
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
 * Integration tests for the {@link InventoryReportResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InventoryReportResourceIT {

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

  private static final String DEFAULT_ACTUAL_AVAILABLE = "AAAAAAAAAA";
  private static final String UPDATED_ACTUAL_AVAILABLE = "BBBBBBBBBB";

  private static final String DEFAULT_REMARK = "AAAAAAAAAA";
  private static final String UPDATED_REMARK = "BBBBBBBBBB";

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

  private static final String ENTITY_API_URL = "/api/inventory-reports";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private InventoryReportRepository inventoryReportRepository;

  @Autowired
  private InventoryReportMapper inventoryReportMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restInventoryReportMockMvc;

  private InventoryReport inventoryReport;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static InventoryReport createEntity(EntityManager em) {
    InventoryReport inventoryReport = new InventoryReport()
      .formName(DEFAULT_FORM_NAME)
      .type(DEFAULT_TYPE)
      .subType(DEFAULT_SUB_TYPE)
      .tableName(DEFAULT_TABLE_NAME)
      .description(DEFAULT_DESCRIPTION)
      .descriptionParameter(DEFAULT_DESCRIPTION_PARAMETER)
      .actualAvailable(DEFAULT_ACTUAL_AVAILABLE)
      .remark(DEFAULT_REMARK)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .createdDate(DEFAULT_CREATED_DATE)
      .createdBy(DEFAULT_CREATED_BY)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
    return inventoryReport;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static InventoryReport createUpdatedEntity(EntityManager em) {
    InventoryReport inventoryReport = new InventoryReport()
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .description(UPDATED_DESCRIPTION)
      .descriptionParameter(UPDATED_DESCRIPTION_PARAMETER)
      .actualAvailable(UPDATED_ACTUAL_AVAILABLE)
      .remark(UPDATED_REMARK)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    return inventoryReport;
  }

  @BeforeEach
  public void initTest() {
    inventoryReport = createEntity(em);
  }

  @Test
  @Transactional
  void createInventoryReport() throws Exception {
    int databaseSizeBeforeCreate = inventoryReportRepository.findAll().size();
    // Create the InventoryReport
    InventoryReport inventoryReport = inventoryReportMapper.toDto(
      inventoryReport
    );
    restInventoryReportMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventoryReport))
      )
      .andExpect(status().isCreated());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeCreate + 1);
    InventoryReport testInventoryReport = inventoryReportList.get(
      inventoryReportList.size() - 1
    );
    assertThat(testInventoryReport.getFormName()).isEqualTo(DEFAULT_FORM_NAME);
    assertThat(testInventoryReport.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testInventoryReport.getSubType()).isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testInventoryReport.getTableName())
      .isEqualTo(DEFAULT_TABLE_NAME);
    assertThat(testInventoryReport.getDescription())
      .isEqualTo(DEFAULT_DESCRIPTION);
    assertThat(testInventoryReport.getDescriptionParameter())
      .isEqualTo(DEFAULT_DESCRIPTION_PARAMETER);
    assertThat(testInventoryReport.getActualAvailable())
      .isEqualTo(DEFAULT_ACTUAL_AVAILABLE);
    assertThat(testInventoryReport.getRemark()).isEqualTo(DEFAULT_REMARK);
    assertThat(testInventoryReport.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testInventoryReport.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testInventoryReport.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testInventoryReport.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testInventoryReport.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testInventoryReport.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testInventoryReport.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void createInventoryReportWithExistingId() throws Exception {
    // Create the InventoryReport with an existing ID
    inventoryReport.setId(1L);
    InventoryReport inventoryReport = inventoryReportMapper.toDto(
      inventoryReport
    );

    int databaseSizeBeforeCreate = inventoryReportRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restInventoryReportMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventoryReport))
      )
      .andExpect(status().isBadRequest());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllInventoryReports() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList
    restInventoryReportMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(inventoryReport.getId().intValue()))
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
        jsonPath("$.[*].actualAvailable")
          .value(hasItem(DEFAULT_ACTUAL_AVAILABLE))
      )
      .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
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
  void getInventoryReport() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get the inventoryReport
    restInventoryReportMockMvc
      .perform(get(ENTITY_API_URL_ID, inventoryReport.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(inventoryReport.getId().intValue()))
      .andExpect(jsonPath("$.formName").value(DEFAULT_FORM_NAME))
      .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
      .andExpect(jsonPath("$.subType").value(DEFAULT_SUB_TYPE))
      .andExpect(jsonPath("$.tableName").value(DEFAULT_TABLE_NAME))
      .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
      .andExpect(
        jsonPath("$.descriptionParameter").value(DEFAULT_DESCRIPTION_PARAMETER)
      )
      .andExpect(jsonPath("$.actualAvailable").value(DEFAULT_ACTUAL_AVAILABLE))
      .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
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
  void getInventoryReportsByIdFiltering() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    Long id = inventoryReport.getId();

    defaultInventoryReportShouldBeFound("id.equals=" + id);
    defaultInventoryReportShouldNotBeFound("id.notEquals=" + id);

    defaultInventoryReportShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultInventoryReportShouldNotBeFound("id.greaterThan=" + id);

    defaultInventoryReportShouldBeFound("id.lessThanOrEqual=" + id);
    defaultInventoryReportShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFormNameIsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where formName equals to DEFAULT_FORM_NAME
    defaultInventoryReportShouldBeFound("formName.equals=" + DEFAULT_FORM_NAME);

    // Get all the inventoryReportList where formName equals to UPDATED_FORM_NAME
    defaultInventoryReportShouldNotBeFound(
      "formName.equals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFormNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where formName not equals to DEFAULT_FORM_NAME
    defaultInventoryReportShouldNotBeFound(
      "formName.notEquals=" + DEFAULT_FORM_NAME
    );

    // Get all the inventoryReportList where formName not equals to UPDATED_FORM_NAME
    defaultInventoryReportShouldBeFound(
      "formName.notEquals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFormNameIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where formName in DEFAULT_FORM_NAME or UPDATED_FORM_NAME
    defaultInventoryReportShouldBeFound(
      "formName.in=" + DEFAULT_FORM_NAME + "," + UPDATED_FORM_NAME
    );

    // Get all the inventoryReportList where formName equals to UPDATED_FORM_NAME
    defaultInventoryReportShouldNotBeFound("formName.in=" + UPDATED_FORM_NAME);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFormNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where formName is not null
    defaultInventoryReportShouldBeFound("formName.specified=true");

    // Get all the inventoryReportList where formName is null
    defaultInventoryReportShouldNotBeFound("formName.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFormNameContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where formName contains DEFAULT_FORM_NAME
    defaultInventoryReportShouldBeFound(
      "formName.contains=" + DEFAULT_FORM_NAME
    );

    // Get all the inventoryReportList where formName contains UPDATED_FORM_NAME
    defaultInventoryReportShouldNotBeFound(
      "formName.contains=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFormNameNotContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where formName does not contain DEFAULT_FORM_NAME
    defaultInventoryReportShouldNotBeFound(
      "formName.doesNotContain=" + DEFAULT_FORM_NAME
    );

    // Get all the inventoryReportList where formName does not contain UPDATED_FORM_NAME
    defaultInventoryReportShouldBeFound(
      "formName.doesNotContain=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where type equals to DEFAULT_TYPE
    defaultInventoryReportShouldBeFound("type.equals=" + DEFAULT_TYPE);

    // Get all the inventoryReportList where type equals to UPDATED_TYPE
    defaultInventoryReportShouldNotBeFound("type.equals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where type not equals to DEFAULT_TYPE
    defaultInventoryReportShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

    // Get all the inventoryReportList where type not equals to UPDATED_TYPE
    defaultInventoryReportShouldBeFound("type.notEquals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTypeIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where type in DEFAULT_TYPE or UPDATED_TYPE
    defaultInventoryReportShouldBeFound(
      "type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE
    );

    // Get all the inventoryReportList where type equals to UPDATED_TYPE
    defaultInventoryReportShouldNotBeFound("type.in=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where type is not null
    defaultInventoryReportShouldBeFound("type.specified=true");

    // Get all the inventoryReportList where type is null
    defaultInventoryReportShouldNotBeFound("type.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTypeContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where type contains DEFAULT_TYPE
    defaultInventoryReportShouldBeFound("type.contains=" + DEFAULT_TYPE);

    // Get all the inventoryReportList where type contains UPDATED_TYPE
    defaultInventoryReportShouldNotBeFound("type.contains=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTypeNotContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where type does not contain DEFAULT_TYPE
    defaultInventoryReportShouldNotBeFound(
      "type.doesNotContain=" + DEFAULT_TYPE
    );

    // Get all the inventoryReportList where type does not contain UPDATED_TYPE
    defaultInventoryReportShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllInventoryReportsBySubTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where subType equals to DEFAULT_SUB_TYPE
    defaultInventoryReportShouldBeFound("subType.equals=" + DEFAULT_SUB_TYPE);

    // Get all the inventoryReportList where subType equals to UPDATED_SUB_TYPE
    defaultInventoryReportShouldNotBeFound(
      "subType.equals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsBySubTypeIsNotEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where subType not equals to DEFAULT_SUB_TYPE
    defaultInventoryReportShouldNotBeFound(
      "subType.notEquals=" + DEFAULT_SUB_TYPE
    );

    // Get all the inventoryReportList where subType not equals to UPDATED_SUB_TYPE
    defaultInventoryReportShouldBeFound(
      "subType.notEquals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsBySubTypeIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where subType in DEFAULT_SUB_TYPE or UPDATED_SUB_TYPE
    defaultInventoryReportShouldBeFound(
      "subType.in=" + DEFAULT_SUB_TYPE + "," + UPDATED_SUB_TYPE
    );

    // Get all the inventoryReportList where subType equals to UPDATED_SUB_TYPE
    defaultInventoryReportShouldNotBeFound("subType.in=" + UPDATED_SUB_TYPE);
  }

  @Test
  @Transactional
  void getAllInventoryReportsBySubTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where subType is not null
    defaultInventoryReportShouldBeFound("subType.specified=true");

    // Get all the inventoryReportList where subType is null
    defaultInventoryReportShouldNotBeFound("subType.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsBySubTypeContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where subType contains DEFAULT_SUB_TYPE
    defaultInventoryReportShouldBeFound("subType.contains=" + DEFAULT_SUB_TYPE);

    // Get all the inventoryReportList where subType contains UPDATED_SUB_TYPE
    defaultInventoryReportShouldNotBeFound(
      "subType.contains=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsBySubTypeNotContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where subType does not contain DEFAULT_SUB_TYPE
    defaultInventoryReportShouldNotBeFound(
      "subType.doesNotContain=" + DEFAULT_SUB_TYPE
    );

    // Get all the inventoryReportList where subType does not contain UPDATED_SUB_TYPE
    defaultInventoryReportShouldBeFound(
      "subType.doesNotContain=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTableNameIsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where tableName equals to DEFAULT_TABLE_NAME
    defaultInventoryReportShouldBeFound(
      "tableName.equals=" + DEFAULT_TABLE_NAME
    );

    // Get all the inventoryReportList where tableName equals to UPDATED_TABLE_NAME
    defaultInventoryReportShouldNotBeFound(
      "tableName.equals=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTableNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where tableName not equals to DEFAULT_TABLE_NAME
    defaultInventoryReportShouldNotBeFound(
      "tableName.notEquals=" + DEFAULT_TABLE_NAME
    );

    // Get all the inventoryReportList where tableName not equals to UPDATED_TABLE_NAME
    defaultInventoryReportShouldBeFound(
      "tableName.notEquals=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTableNameIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where tableName in DEFAULT_TABLE_NAME or UPDATED_TABLE_NAME
    defaultInventoryReportShouldBeFound(
      "tableName.in=" + DEFAULT_TABLE_NAME + "," + UPDATED_TABLE_NAME
    );

    // Get all the inventoryReportList where tableName equals to UPDATED_TABLE_NAME
    defaultInventoryReportShouldNotBeFound(
      "tableName.in=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTableNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where tableName is not null
    defaultInventoryReportShouldBeFound("tableName.specified=true");

    // Get all the inventoryReportList where tableName is null
    defaultInventoryReportShouldNotBeFound("tableName.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTableNameContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where tableName contains DEFAULT_TABLE_NAME
    defaultInventoryReportShouldBeFound(
      "tableName.contains=" + DEFAULT_TABLE_NAME
    );

    // Get all the inventoryReportList where tableName contains UPDATED_TABLE_NAME
    defaultInventoryReportShouldNotBeFound(
      "tableName.contains=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByTableNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where tableName does not contain DEFAULT_TABLE_NAME
    defaultInventoryReportShouldNotBeFound(
      "tableName.doesNotContain=" + DEFAULT_TABLE_NAME
    );

    // Get all the inventoryReportList where tableName does not contain UPDATED_TABLE_NAME
    defaultInventoryReportShouldBeFound(
      "tableName.doesNotContain=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where description equals to DEFAULT_DESCRIPTION
    defaultInventoryReportShouldBeFound(
      "description.equals=" + DEFAULT_DESCRIPTION
    );

    // Get all the inventoryReportList where description equals to UPDATED_DESCRIPTION
    defaultInventoryReportShouldNotBeFound(
      "description.equals=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where description not equals to DEFAULT_DESCRIPTION
    defaultInventoryReportShouldNotBeFound(
      "description.notEquals=" + DEFAULT_DESCRIPTION
    );

    // Get all the inventoryReportList where description not equals to UPDATED_DESCRIPTION
    defaultInventoryReportShouldBeFound(
      "description.notEquals=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
    defaultInventoryReportShouldBeFound(
      "description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION
    );

    // Get all the inventoryReportList where description equals to UPDATED_DESCRIPTION
    defaultInventoryReportShouldNotBeFound(
      "description.in=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where description is not null
    defaultInventoryReportShouldBeFound("description.specified=true");

    // Get all the inventoryReportList where description is null
    defaultInventoryReportShouldNotBeFound("description.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where description contains DEFAULT_DESCRIPTION
    defaultInventoryReportShouldBeFound(
      "description.contains=" + DEFAULT_DESCRIPTION
    );

    // Get all the inventoryReportList where description contains UPDATED_DESCRIPTION
    defaultInventoryReportShouldNotBeFound(
      "description.contains=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where description does not contain DEFAULT_DESCRIPTION
    defaultInventoryReportShouldNotBeFound(
      "description.doesNotContain=" + DEFAULT_DESCRIPTION
    );

    // Get all the inventoryReportList where description does not contain UPDATED_DESCRIPTION
    defaultInventoryReportShouldBeFound(
      "description.doesNotContain=" + UPDATED_DESCRIPTION
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionParameterIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where descriptionParameter equals to DEFAULT_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldBeFound(
      "descriptionParameter.equals=" + DEFAULT_DESCRIPTION_PARAMETER
    );

    // Get all the inventoryReportList where descriptionParameter equals to UPDATED_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldNotBeFound(
      "descriptionParameter.equals=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionParameterIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where descriptionParameter not equals to DEFAULT_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldNotBeFound(
      "descriptionParameter.notEquals=" + DEFAULT_DESCRIPTION_PARAMETER
    );

    // Get all the inventoryReportList where descriptionParameter not equals to UPDATED_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldBeFound(
      "descriptionParameter.notEquals=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionParameterIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where descriptionParameter in DEFAULT_DESCRIPTION_PARAMETER or UPDATED_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldBeFound(
      "descriptionParameter.in=" +
      DEFAULT_DESCRIPTION_PARAMETER +
      "," +
      UPDATED_DESCRIPTION_PARAMETER
    );

    // Get all the inventoryReportList where descriptionParameter equals to UPDATED_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldNotBeFound(
      "descriptionParameter.in=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionParameterIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where descriptionParameter is not null
    defaultInventoryReportShouldBeFound("descriptionParameter.specified=true");

    // Get all the inventoryReportList where descriptionParameter is null
    defaultInventoryReportShouldNotBeFound(
      "descriptionParameter.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionParameterContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where descriptionParameter contains DEFAULT_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldBeFound(
      "descriptionParameter.contains=" + DEFAULT_DESCRIPTION_PARAMETER
    );

    // Get all the inventoryReportList where descriptionParameter contains UPDATED_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldNotBeFound(
      "descriptionParameter.contains=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByDescriptionParameterNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where descriptionParameter does not contain DEFAULT_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldNotBeFound(
      "descriptionParameter.doesNotContain=" + DEFAULT_DESCRIPTION_PARAMETER
    );

    // Get all the inventoryReportList where descriptionParameter does not contain UPDATED_DESCRIPTION_PARAMETER
    defaultInventoryReportShouldBeFound(
      "descriptionParameter.doesNotContain=" + UPDATED_DESCRIPTION_PARAMETER
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByActualAvailableIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where actualAvailable equals to DEFAULT_ACTUAL_AVAILABLE
    defaultInventoryReportShouldBeFound(
      "actualAvailable.equals=" + DEFAULT_ACTUAL_AVAILABLE
    );

    // Get all the inventoryReportList where actualAvailable equals to UPDATED_ACTUAL_AVAILABLE
    defaultInventoryReportShouldNotBeFound(
      "actualAvailable.equals=" + UPDATED_ACTUAL_AVAILABLE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByActualAvailableIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where actualAvailable not equals to DEFAULT_ACTUAL_AVAILABLE
    defaultInventoryReportShouldNotBeFound(
      "actualAvailable.notEquals=" + DEFAULT_ACTUAL_AVAILABLE
    );

    // Get all the inventoryReportList where actualAvailable not equals to UPDATED_ACTUAL_AVAILABLE
    defaultInventoryReportShouldBeFound(
      "actualAvailable.notEquals=" + UPDATED_ACTUAL_AVAILABLE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByActualAvailableIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where actualAvailable in DEFAULT_ACTUAL_AVAILABLE or UPDATED_ACTUAL_AVAILABLE
    defaultInventoryReportShouldBeFound(
      "actualAvailable.in=" +
      DEFAULT_ACTUAL_AVAILABLE +
      "," +
      UPDATED_ACTUAL_AVAILABLE
    );

    // Get all the inventoryReportList where actualAvailable equals to UPDATED_ACTUAL_AVAILABLE
    defaultInventoryReportShouldNotBeFound(
      "actualAvailable.in=" + UPDATED_ACTUAL_AVAILABLE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByActualAvailableIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where actualAvailable is not null
    defaultInventoryReportShouldBeFound("actualAvailable.specified=true");

    // Get all the inventoryReportList where actualAvailable is null
    defaultInventoryReportShouldNotBeFound("actualAvailable.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByActualAvailableContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where actualAvailable contains DEFAULT_ACTUAL_AVAILABLE
    defaultInventoryReportShouldBeFound(
      "actualAvailable.contains=" + DEFAULT_ACTUAL_AVAILABLE
    );

    // Get all the inventoryReportList where actualAvailable contains UPDATED_ACTUAL_AVAILABLE
    defaultInventoryReportShouldNotBeFound(
      "actualAvailable.contains=" + UPDATED_ACTUAL_AVAILABLE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByActualAvailableNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where actualAvailable does not contain DEFAULT_ACTUAL_AVAILABLE
    defaultInventoryReportShouldNotBeFound(
      "actualAvailable.doesNotContain=" + DEFAULT_ACTUAL_AVAILABLE
    );

    // Get all the inventoryReportList where actualAvailable does not contain UPDATED_ACTUAL_AVAILABLE
    defaultInventoryReportShouldBeFound(
      "actualAvailable.doesNotContain=" + UPDATED_ACTUAL_AVAILABLE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByRemarkIsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where remark equals to DEFAULT_REMARK
    defaultInventoryReportShouldBeFound("remark.equals=" + DEFAULT_REMARK);

    // Get all the inventoryReportList where remark equals to UPDATED_REMARK
    defaultInventoryReportShouldNotBeFound("remark.equals=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByRemarkIsNotEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where remark not equals to DEFAULT_REMARK
    defaultInventoryReportShouldNotBeFound(
      "remark.notEquals=" + DEFAULT_REMARK
    );

    // Get all the inventoryReportList where remark not equals to UPDATED_REMARK
    defaultInventoryReportShouldBeFound("remark.notEquals=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByRemarkIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where remark in DEFAULT_REMARK or UPDATED_REMARK
    defaultInventoryReportShouldBeFound(
      "remark.in=" + DEFAULT_REMARK + "," + UPDATED_REMARK
    );

    // Get all the inventoryReportList where remark equals to UPDATED_REMARK
    defaultInventoryReportShouldNotBeFound("remark.in=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByRemarkIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where remark is not null
    defaultInventoryReportShouldBeFound("remark.specified=true");

    // Get all the inventoryReportList where remark is null
    defaultInventoryReportShouldNotBeFound("remark.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByRemarkContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where remark contains DEFAULT_REMARK
    defaultInventoryReportShouldBeFound("remark.contains=" + DEFAULT_REMARK);

    // Get all the inventoryReportList where remark contains UPDATED_REMARK
    defaultInventoryReportShouldNotBeFound("remark.contains=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllInventoryReportsByRemarkNotContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where remark does not contain DEFAULT_REMARK
    defaultInventoryReportShouldNotBeFound(
      "remark.doesNotContain=" + DEFAULT_REMARK
    );

    // Get all the inventoryReportList where remark does not contain UPDATED_REMARK
    defaultInventoryReportShouldBeFound(
      "remark.doesNotContain=" + UPDATED_REMARK
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField1IsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultInventoryReportShouldBeFound(
      "freeField1.equals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the inventoryReportList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultInventoryReportShouldNotBeFound(
      "freeField1.equals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField1IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultInventoryReportShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the inventoryReportList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultInventoryReportShouldBeFound(
      "freeField1.notEquals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField1IsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultInventoryReportShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the inventoryReportList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultInventoryReportShouldNotBeFound(
      "freeField1.in=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField1IsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField1 is not null
    defaultInventoryReportShouldBeFound("freeField1.specified=true");

    // Get all the inventoryReportList where freeField1 is null
    defaultInventoryReportShouldNotBeFound("freeField1.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField1ContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultInventoryReportShouldBeFound(
      "freeField1.contains=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the inventoryReportList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultInventoryReportShouldNotBeFound(
      "freeField1.contains=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField1NotContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultInventoryReportShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the inventoryReportList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultInventoryReportShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField2IsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultInventoryReportShouldBeFound(
      "freeField2.equals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the inventoryReportList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultInventoryReportShouldNotBeFound(
      "freeField2.equals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField2IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultInventoryReportShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the inventoryReportList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultInventoryReportShouldBeFound(
      "freeField2.notEquals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField2IsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultInventoryReportShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the inventoryReportList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultInventoryReportShouldNotBeFound(
      "freeField2.in=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField2IsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField2 is not null
    defaultInventoryReportShouldBeFound("freeField2.specified=true");

    // Get all the inventoryReportList where freeField2 is null
    defaultInventoryReportShouldNotBeFound("freeField2.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField2ContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultInventoryReportShouldBeFound(
      "freeField2.contains=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the inventoryReportList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultInventoryReportShouldNotBeFound(
      "freeField2.contains=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField2NotContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultInventoryReportShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the inventoryReportList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultInventoryReportShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField3IsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultInventoryReportShouldBeFound(
      "freeField3.equals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the inventoryReportList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultInventoryReportShouldNotBeFound(
      "freeField3.equals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField3IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultInventoryReportShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the inventoryReportList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultInventoryReportShouldBeFound(
      "freeField3.notEquals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField3IsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultInventoryReportShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the inventoryReportList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultInventoryReportShouldNotBeFound(
      "freeField3.in=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField3IsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField3 is not null
    defaultInventoryReportShouldBeFound("freeField3.specified=true");

    // Get all the inventoryReportList where freeField3 is null
    defaultInventoryReportShouldNotBeFound("freeField3.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField3ContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultInventoryReportShouldBeFound(
      "freeField3.contains=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the inventoryReportList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultInventoryReportShouldNotBeFound(
      "freeField3.contains=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByFreeField3NotContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultInventoryReportShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the inventoryReportList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultInventoryReportShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedDateIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdDate equals to DEFAULT_CREATED_DATE
    defaultInventoryReportShouldBeFound(
      "createdDate.equals=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventoryReportList where createdDate equals to UPDATED_CREATED_DATE
    defaultInventoryReportShouldNotBeFound(
      "createdDate.equals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultInventoryReportShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventoryReportList where createdDate not equals to UPDATED_CREATED_DATE
    defaultInventoryReportShouldBeFound(
      "createdDate.notEquals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedDateIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultInventoryReportShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the inventoryReportList where createdDate equals to UPDATED_CREATED_DATE
    defaultInventoryReportShouldNotBeFound(
      "createdDate.in=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdDate is not null
    defaultInventoryReportShouldBeFound("createdDate.specified=true");

    // Get all the inventoryReportList where createdDate is null
    defaultInventoryReportShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultInventoryReportShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventoryReportList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultInventoryReportShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultInventoryReportShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventoryReportList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultInventoryReportShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdDate is less than DEFAULT_CREATED_DATE
    defaultInventoryReportShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventoryReportList where createdDate is less than UPDATED_CREATED_DATE
    defaultInventoryReportShouldBeFound(
      "createdDate.lessThan=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultInventoryReportShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventoryReportList where createdDate is greater than SMALLER_CREATED_DATE
    defaultInventoryReportShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedByIsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdBy equals to DEFAULT_CREATED_BY
    defaultInventoryReportShouldBeFound(
      "createdBy.equals=" + DEFAULT_CREATED_BY
    );

    // Get all the inventoryReportList where createdBy equals to UPDATED_CREATED_BY
    defaultInventoryReportShouldNotBeFound(
      "createdBy.equals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdBy not equals to DEFAULT_CREATED_BY
    defaultInventoryReportShouldNotBeFound(
      "createdBy.notEquals=" + DEFAULT_CREATED_BY
    );

    // Get all the inventoryReportList where createdBy not equals to UPDATED_CREATED_BY
    defaultInventoryReportShouldBeFound(
      "createdBy.notEquals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedByIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultInventoryReportShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the inventoryReportList where createdBy equals to UPDATED_CREATED_BY
    defaultInventoryReportShouldNotBeFound(
      "createdBy.in=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdBy is not null
    defaultInventoryReportShouldBeFound("createdBy.specified=true");

    // Get all the inventoryReportList where createdBy is null
    defaultInventoryReportShouldNotBeFound("createdBy.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedByContainsSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdBy contains DEFAULT_CREATED_BY
    defaultInventoryReportShouldBeFound(
      "createdBy.contains=" + DEFAULT_CREATED_BY
    );

    // Get all the inventoryReportList where createdBy contains UPDATED_CREATED_BY
    defaultInventoryReportShouldNotBeFound(
      "createdBy.contains=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByCreatedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where createdBy does not contain DEFAULT_CREATED_BY
    defaultInventoryReportShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the inventoryReportList where createdBy does not contain UPDATED_CREATED_BY
    defaultInventoryReportShouldBeFound(
      "createdBy.doesNotContain=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultInventoryReportShouldBeFound(
      "lastModified.equals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventoryReportList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultInventoryReportShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultInventoryReportShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventoryReportList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultInventoryReportShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultInventoryReportShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the inventoryReportList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultInventoryReportShouldNotBeFound(
      "lastModified.in=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModified is not null
    defaultInventoryReportShouldBeFound("lastModified.specified=true");

    // Get all the inventoryReportList where lastModified is null
    defaultInventoryReportShouldNotBeFound("lastModified.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultInventoryReportShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventoryReportList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultInventoryReportShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultInventoryReportShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventoryReportList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultInventoryReportShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedIsLessThanSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultInventoryReportShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventoryReportList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultInventoryReportShouldBeFound(
      "lastModified.lessThan=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultInventoryReportShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventoryReportList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultInventoryReportShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultInventoryReportShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the inventoryReportList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultInventoryReportShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultInventoryReportShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the inventoryReportList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultInventoryReportShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedByIsInShouldWork() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultInventoryReportShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the inventoryReportList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultInventoryReportShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModifiedBy is not null
    defaultInventoryReportShouldBeFound("lastModifiedBy.specified=true");

    // Get all the inventoryReportList where lastModifiedBy is null
    defaultInventoryReportShouldNotBeFound("lastModifiedBy.specified=false");
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedByContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultInventoryReportShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the inventoryReportList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultInventoryReportShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByLastModifiedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    // Get all the inventoryReportList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultInventoryReportShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the inventoryReportList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultInventoryReportShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventoryReportsByAuditIsEqualToSomething() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);
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
    inventoryReport.setAudit(audit);
    inventoryReportRepository.saveAndFlush(inventoryReport);
    Long auditId = audit.getId();

    // Get all the inventoryReportList where audit equals to auditId
    defaultInventoryReportShouldBeFound("auditId.equals=" + auditId);

    // Get all the inventoryReportList where audit equals to (auditId + 1)
    defaultInventoryReportShouldNotBeFound("auditId.equals=" + (auditId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultInventoryReportShouldBeFound(String filter)
    throws Exception {
    restInventoryReportMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id").value(hasItem(inventoryReport.getId().intValue()))
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
        jsonPath("$.[*].actualAvailable")
          .value(hasItem(DEFAULT_ACTUAL_AVAILABLE))
      )
      .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
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
    restInventoryReportMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultInventoryReportShouldNotBeFound(String filter)
    throws Exception {
    restInventoryReportMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restInventoryReportMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingInventoryReport() throws Exception {
    // Get the inventoryReport
    restInventoryReportMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewInventoryReport() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    int databaseSizeBeforeUpdate = inventoryReportRepository.findAll().size();

    // Update the inventoryReport
    InventoryReport updatedInventoryReport = inventoryReportRepository
      .findById(inventoryReport.getId())
      .get();
    // Disconnect from session so that the updates on updatedInventoryReport are not directly saved in db
    em.detach(updatedInventoryReport);
    updatedInventoryReport
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .description(UPDATED_DESCRIPTION)
      .descriptionParameter(UPDATED_DESCRIPTION_PARAMETER)
      .actualAvailable(UPDATED_ACTUAL_AVAILABLE)
      .remark(UPDATED_REMARK)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    InventoryReport inventoryReport = inventoryReportMapper.toDto(
      updatedInventoryReport
    );

    restInventoryReportMockMvc
      .perform(
        put(ENTITY_API_URL_ID, inventoryReportDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventoryReportDTO))
      )
      .andExpect(status().isOk());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeUpdate);
    InventoryReport testInventoryReport = inventoryReportList.get(
      inventoryReportList.size() - 1
    );
    assertThat(testInventoryReport.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    assertThat(testInventoryReport.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testInventoryReport.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testInventoryReport.getTableName())
      .isEqualTo(UPDATED_TABLE_NAME);
    assertThat(testInventoryReport.getDescription())
      .isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testInventoryReport.getDescriptionParameter())
      .isEqualTo(UPDATED_DESCRIPTION_PARAMETER);
    assertThat(testInventoryReport.getActualAvailable())
      .isEqualTo(UPDATED_ACTUAL_AVAILABLE);
    assertThat(testInventoryReport.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testInventoryReport.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testInventoryReport.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testInventoryReport.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testInventoryReport.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testInventoryReport.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testInventoryReport.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testInventoryReport.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void putNonExistingInventoryReport() throws Exception {
    int databaseSizeBeforeUpdate = inventoryReportRepository.findAll().size();
    inventoryReport.setId(count.incrementAndGet());

    // Create the InventoryReport
    InventoryReport inventoryReport = inventoryReportMapper.toDto(
      inventoryReport
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restInventoryReportMockMvc
      .perform(
        put(ENTITY_API_URL_ID, inventoryReport.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventoryReport))
      )
      .andExpect(status().isBadRequest());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchInventoryReport() throws Exception {
    int databaseSizeBeforeUpdate = inventoryReportRepository.findAll().size();
    inventoryReport.setId(count.incrementAndGet());

    // Create the InventoryReport
    InventoryReport inventoryReport = inventoryReportMapper.toDto(
      inventoryReport
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restInventoryReportMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventoryReport))
      )
      .andExpect(status().isBadRequest());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamInventoryReport() throws Exception {
    int databaseSizeBeforeUpdate = inventoryReportRepository.findAll().size();
    inventoryReport.setId(count.incrementAndGet());

    // Create the InventoryReport
    InventoryReport inventoryReport = inventoryReportMapper.toDto(
      inventoryReport
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restInventoryReportMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventoryReport))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateInventoryReportWithPatch() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    int databaseSizeBeforeUpdate = inventoryReportRepository.findAll().size();

    // Update the inventoryReport using partial update
    InventoryReport partialUpdatedInventoryReport = new InventoryReport();
    partialUpdatedInventoryReport.setId(inventoryReport.getId());

    partialUpdatedInventoryReport
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .description(UPDATED_DESCRIPTION)
      .descriptionParameter(UPDATED_DESCRIPTION_PARAMETER)
      .actualAvailable(UPDATED_ACTUAL_AVAILABLE)
      .remark(UPDATED_REMARK)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY);

    restInventoryReportMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedInventoryReport.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedInventoryReport)
          )
      )
      .andExpect(status().isOk());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeUpdate);
    InventoryReport testInventoryReport = inventoryReportList.get(
      inventoryReportList.size() - 1
    );
    assertThat(testInventoryReport.getFormName()).isEqualTo(DEFAULT_FORM_NAME);
    assertThat(testInventoryReport.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testInventoryReport.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testInventoryReport.getTableName())
      .isEqualTo(UPDATED_TABLE_NAME);
    assertThat(testInventoryReport.getDescription())
      .isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testInventoryReport.getDescriptionParameter())
      .isEqualTo(UPDATED_DESCRIPTION_PARAMETER);
    assertThat(testInventoryReport.getActualAvailable())
      .isEqualTo(UPDATED_ACTUAL_AVAILABLE);
    assertThat(testInventoryReport.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testInventoryReport.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testInventoryReport.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testInventoryReport.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testInventoryReport.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testInventoryReport.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testInventoryReport.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testInventoryReport.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void fullUpdateInventoryReportWithPatch() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    int databaseSizeBeforeUpdate = inventoryReportRepository.findAll().size();

    // Update the inventoryReport using partial update
    InventoryReport partialUpdatedInventoryReport = new InventoryReport();
    partialUpdatedInventoryReport.setId(inventoryReport.getId());

    partialUpdatedInventoryReport
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .description(UPDATED_DESCRIPTION)
      .descriptionParameter(UPDATED_DESCRIPTION_PARAMETER)
      .actualAvailable(UPDATED_ACTUAL_AVAILABLE)
      .remark(UPDATED_REMARK)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

    restInventoryReportMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedInventoryReport.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedInventoryReport)
          )
      )
      .andExpect(status().isOk());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeUpdate);
    InventoryReport testInventoryReport = inventoryReportList.get(
      inventoryReportList.size() - 1
    );
    assertThat(testInventoryReport.getFormName()).isEqualTo(UPDATED_FORM_NAME);
    assertThat(testInventoryReport.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testInventoryReport.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testInventoryReport.getTableName())
      .isEqualTo(UPDATED_TABLE_NAME);
    assertThat(testInventoryReport.getDescription())
      .isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testInventoryReport.getDescriptionParameter())
      .isEqualTo(UPDATED_DESCRIPTION_PARAMETER);
    assertThat(testInventoryReport.getActualAvailable())
      .isEqualTo(UPDATED_ACTUAL_AVAILABLE);
    assertThat(testInventoryReport.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testInventoryReport.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testInventoryReport.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testInventoryReport.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testInventoryReport.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testInventoryReport.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testInventoryReport.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testInventoryReport.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void patchNonExistingInventoryReport() throws Exception {
    int databaseSizeBeforeUpdate = inventoryReportRepository.findAll().size();
    inventoryReport.setId(count.incrementAndGet());

    // Create the InventoryReport
    InventoryReportDTO inventoryReportDTO = inventoryReportMapper.toDto(
      inventoryReport
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restInventoryReportMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, inventoryReportDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(inventoryReportDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchInventoryReport() throws Exception {
    int databaseSizeBeforeUpdate = inventoryReportRepository.findAll().size();
    inventoryReport.setId(count.incrementAndGet());

    // Create the InventoryReport
    InventoryReportDTO inventoryReportDTO = inventoryReportMapper.toDto(
      inventoryReport
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restInventoryReportMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(inventoryReportDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamInventoryReport() throws Exception {
    int databaseSizeBeforeUpdate = inventoryReportRepository.findAll().size();
    inventoryReport.setId(count.incrementAndGet());

    // Create the InventoryReport
    InventoryReportDTO inventoryReportDTO = inventoryReportMapper.toDto(
      inventoryReport
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restInventoryReportMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(inventoryReportDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the InventoryReport in the database
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteInventoryReport() throws Exception {
    // Initialize the database
    inventoryReportRepository.saveAndFlush(inventoryReport);

    int databaseSizeBeforeDelete = inventoryReportRepository.findAll().size();

    // Delete the inventoryReport
    restInventoryReportMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, inventoryReport.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<InventoryReport> inventoryReportList = inventoryReportRepository.findAll();
    assertThat(inventoryReportList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
