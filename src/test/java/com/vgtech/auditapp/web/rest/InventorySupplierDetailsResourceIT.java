package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.domain.InventorySupplierDetails;
import com.vgtech.auditapp.repository.InventorySupplierDetailsRepository;
import com.vgtech.auditapp.service.criteria.InventorySupplierDetailsCriteria;
import com.vgtech.auditapp.service.dto.InventorySupplierDetails;
import com.vgtech.auditapp.service.mapper.InventorySupplierDetailsMapper;
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
 * Integration tests for the {@link InventorySupplierDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InventorySupplierDetailsResourceIT {

  private static final String DEFAULT_FORM_NAME = "AAAAAAAAAA";
  private static final String UPDATED_FORM_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_SUB_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_SUB_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_TABLE_NAME = "AAAAAAAAAA";
  private static final String UPDATED_TABLE_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_SUPPLIER_NAME = "AAAAAAAAAA";
  private static final String UPDATED_SUPPLIER_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_SUPPLIER_ADDRESS = "AAAAAAAAAA";
  private static final String UPDATED_SUPPLIER_ADDRESS = "BBBBBBBBBB";

  private static final String DEFAULT_SUPPLIER_CONTACT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_SUPPLIER_CONTACT_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER =
    "AAAAAAAAAA";
  private static final String UPDATED_SUPPLIER_CONTACT_NAME_NUMBER =
    "BBBBBBBBBB";

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

  private static final String ENTITY_API_URL =
    "/api/inventory-supplier-details";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private InventorySupplierDetailsRepository inventorySupplierDetailsRepository;

  @Autowired
  private InventorySupplierDetailsMapper inventorySupplierDetailsMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restInventorySupplierDetailsMockMvc;

  private InventorySupplierDetails inventorySupplierDetails;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static InventorySupplierDetails createEntity(EntityManager em) {
    InventorySupplierDetails inventorySupplierDetails = new InventorySupplierDetails()
      .formName(DEFAULT_FORM_NAME)
      .type(DEFAULT_TYPE)
      .subType(DEFAULT_SUB_TYPE)
      .tableName(DEFAULT_TABLE_NAME)
      .supplierName(DEFAULT_SUPPLIER_NAME)
      .supplierAddress(DEFAULT_SUPPLIER_ADDRESS)
      .supplierContactName(DEFAULT_SUPPLIER_CONTACT_NAME)
      .supplierContactNameNumber(DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .createdDate(DEFAULT_CREATED_DATE)
      .createdBy(DEFAULT_CREATED_BY)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
    return inventorySupplierDetails;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static InventorySupplierDetails createUpdatedEntity(EntityManager em) {
    InventorySupplierDetails inventorySupplierDetails = new InventorySupplierDetails()
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .supplierName(UPDATED_SUPPLIER_NAME)
      .supplierAddress(UPDATED_SUPPLIER_ADDRESS)
      .supplierContactName(UPDATED_SUPPLIER_CONTACT_NAME)
      .supplierContactNameNumber(UPDATED_SUPPLIER_CONTACT_NAME_NUMBER)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    return inventorySupplierDetails;
  }

  @BeforeEach
  public void initTest() {
    inventorySupplierDetails = createEntity(em);
  }

  @Test
  @Transactional
  void createInventorySupplierDetails() throws Exception {
    int databaseSizeBeforeCreate = inventorySupplierDetailsRepository
      .findAll()
      .size();
    // Create the InventorySupplierDetails
    InventorySupplierDetails inventorySupplierDetails = inventorySupplierDetailsMapper.toDto(
      inventorySupplierDetails
    );
    restInventorySupplierDetailsMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventorySupplierDetails))
      )
      .andExpect(status().isCreated());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList)
      .hasSize(databaseSizeBeforeCreate + 1);
    InventorySupplierDetails testInventorySupplierDetails = inventorySupplierDetailsList.get(
      inventorySupplierDetailsList.size() - 1
    );
    assertThat(testInventorySupplierDetails.getFormName())
      .isEqualTo(DEFAULT_FORM_NAME);
    assertThat(testInventorySupplierDetails.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testInventorySupplierDetails.getSubType())
      .isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testInventorySupplierDetails.getTableName())
      .isEqualTo(DEFAULT_TABLE_NAME);
    assertThat(testInventorySupplierDetails.getSupplierName())
      .isEqualTo(DEFAULT_SUPPLIER_NAME);
    assertThat(testInventorySupplierDetails.getSupplierAddress())
      .isEqualTo(DEFAULT_SUPPLIER_ADDRESS);
    assertThat(testInventorySupplierDetails.getSupplierContactName())
      .isEqualTo(DEFAULT_SUPPLIER_CONTACT_NAME);
    assertThat(testInventorySupplierDetails.getSupplierContactNameNumber())
      .isEqualTo(DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER);
    assertThat(testInventorySupplierDetails.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testInventorySupplierDetails.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testInventorySupplierDetails.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testInventorySupplierDetails.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testInventorySupplierDetails.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testInventorySupplierDetails.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testInventorySupplierDetails.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void createInventorySupplierDetailsWithExistingId() throws Exception {
    // Create the InventorySupplierDetails with an existing ID
    inventorySupplierDetails.setId(1L);
    InventorySupplierDetails inventorySupplierDetails = inventorySupplierDetailsMapper.toDto(
      inventorySupplierDetails
    );

    int databaseSizeBeforeCreate = inventorySupplierDetailsRepository
      .findAll()
      .size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restInventorySupplierDetailsMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventorySupplierDetails))
      )
      .andExpect(status().isBadRequest());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetails() throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList
    restInventorySupplierDetailsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(inventorySupplierDetails.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
      .andExpect(
        jsonPath("$.[*].supplierName").value(hasItem(DEFAULT_SUPPLIER_NAME))
      )
      .andExpect(
        jsonPath("$.[*].supplierAddress")
          .value(hasItem(DEFAULT_SUPPLIER_ADDRESS))
      )
      .andExpect(
        jsonPath("$.[*].supplierContactName")
          .value(hasItem(DEFAULT_SUPPLIER_CONTACT_NAME))
      )
      .andExpect(
        jsonPath("$.[*].supplierContactNameNumber")
          .value(hasItem(DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER))
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
  void getInventorySupplierDetails() throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get the inventorySupplierDetails
    restInventorySupplierDetailsMockMvc
      .perform(get(ENTITY_API_URL_ID, inventorySupplierDetails.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.id").value(inventorySupplierDetails.getId().intValue())
      )
      .andExpect(jsonPath("$.formName").value(DEFAULT_FORM_NAME))
      .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
      .andExpect(jsonPath("$.subType").value(DEFAULT_SUB_TYPE))
      .andExpect(jsonPath("$.tableName").value(DEFAULT_TABLE_NAME))
      .andExpect(jsonPath("$.supplierName").value(DEFAULT_SUPPLIER_NAME))
      .andExpect(jsonPath("$.supplierAddress").value(DEFAULT_SUPPLIER_ADDRESS))
      .andExpect(
        jsonPath("$.supplierContactName").value(DEFAULT_SUPPLIER_CONTACT_NAME)
      )
      .andExpect(
        jsonPath("$.supplierContactNameNumber")
          .value(DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER)
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
  void getInventorySupplierDetailsByIdFiltering() throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    Long id = inventorySupplierDetails.getId();

    defaultInventorySupplierDetailsShouldBeFound("id.equals=" + id);
    defaultInventorySupplierDetailsShouldNotBeFound("id.notEquals=" + id);

    defaultInventorySupplierDetailsShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultInventorySupplierDetailsShouldNotBeFound("id.greaterThan=" + id);

    defaultInventorySupplierDetailsShouldBeFound("id.lessThanOrEqual=" + id);
    defaultInventorySupplierDetailsShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFormNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where formName equals to DEFAULT_FORM_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "formName.equals=" + DEFAULT_FORM_NAME
    );

    // Get all the inventorySupplierDetailsList where formName equals to UPDATED_FORM_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "formName.equals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFormNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where formName not equals to DEFAULT_FORM_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "formName.notEquals=" + DEFAULT_FORM_NAME
    );

    // Get all the inventorySupplierDetailsList where formName not equals to UPDATED_FORM_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "formName.notEquals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFormNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where formName in DEFAULT_FORM_NAME or UPDATED_FORM_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "formName.in=" + DEFAULT_FORM_NAME + "," + UPDATED_FORM_NAME
    );

    // Get all the inventorySupplierDetailsList where formName equals to UPDATED_FORM_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "formName.in=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFormNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where formName is not null
    defaultInventorySupplierDetailsShouldBeFound("formName.specified=true");

    // Get all the inventorySupplierDetailsList where formName is null
    defaultInventorySupplierDetailsShouldNotBeFound("formName.specified=false");
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFormNameContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where formName contains DEFAULT_FORM_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "formName.contains=" + DEFAULT_FORM_NAME
    );

    // Get all the inventorySupplierDetailsList where formName contains UPDATED_FORM_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "formName.contains=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFormNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where formName does not contain DEFAULT_FORM_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "formName.doesNotContain=" + DEFAULT_FORM_NAME
    );

    // Get all the inventorySupplierDetailsList where formName does not contain UPDATED_FORM_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "formName.doesNotContain=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTypeIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where type equals to DEFAULT_TYPE
    defaultInventorySupplierDetailsShouldBeFound("type.equals=" + DEFAULT_TYPE);

    // Get all the inventorySupplierDetailsList where type equals to UPDATED_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "type.equals=" + UPDATED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where type not equals to DEFAULT_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "type.notEquals=" + DEFAULT_TYPE
    );

    // Get all the inventorySupplierDetailsList where type not equals to UPDATED_TYPE
    defaultInventorySupplierDetailsShouldBeFound(
      "type.notEquals=" + UPDATED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTypeIsInShouldWork() throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where type in DEFAULT_TYPE or UPDATED_TYPE
    defaultInventorySupplierDetailsShouldBeFound(
      "type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE
    );

    // Get all the inventorySupplierDetailsList where type equals to UPDATED_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound("type.in=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where type is not null
    defaultInventorySupplierDetailsShouldBeFound("type.specified=true");

    // Get all the inventorySupplierDetailsList where type is null
    defaultInventorySupplierDetailsShouldNotBeFound("type.specified=false");
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTypeContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where type contains DEFAULT_TYPE
    defaultInventorySupplierDetailsShouldBeFound(
      "type.contains=" + DEFAULT_TYPE
    );

    // Get all the inventorySupplierDetailsList where type contains UPDATED_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "type.contains=" + UPDATED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTypeNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where type does not contain DEFAULT_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "type.doesNotContain=" + DEFAULT_TYPE
    );

    // Get all the inventorySupplierDetailsList where type does not contain UPDATED_TYPE
    defaultInventorySupplierDetailsShouldBeFound(
      "type.doesNotContain=" + UPDATED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySubTypeIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where subType equals to DEFAULT_SUB_TYPE
    defaultInventorySupplierDetailsShouldBeFound(
      "subType.equals=" + DEFAULT_SUB_TYPE
    );

    // Get all the inventorySupplierDetailsList where subType equals to UPDATED_SUB_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "subType.equals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySubTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where subType not equals to DEFAULT_SUB_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "subType.notEquals=" + DEFAULT_SUB_TYPE
    );

    // Get all the inventorySupplierDetailsList where subType not equals to UPDATED_SUB_TYPE
    defaultInventorySupplierDetailsShouldBeFound(
      "subType.notEquals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySubTypeIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where subType in DEFAULT_SUB_TYPE or UPDATED_SUB_TYPE
    defaultInventorySupplierDetailsShouldBeFound(
      "subType.in=" + DEFAULT_SUB_TYPE + "," + UPDATED_SUB_TYPE
    );

    // Get all the inventorySupplierDetailsList where subType equals to UPDATED_SUB_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "subType.in=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySubTypeIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where subType is not null
    defaultInventorySupplierDetailsShouldBeFound("subType.specified=true");

    // Get all the inventorySupplierDetailsList where subType is null
    defaultInventorySupplierDetailsShouldNotBeFound("subType.specified=false");
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySubTypeContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where subType contains DEFAULT_SUB_TYPE
    defaultInventorySupplierDetailsShouldBeFound(
      "subType.contains=" + DEFAULT_SUB_TYPE
    );

    // Get all the inventorySupplierDetailsList where subType contains UPDATED_SUB_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "subType.contains=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySubTypeNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where subType does not contain DEFAULT_SUB_TYPE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "subType.doesNotContain=" + DEFAULT_SUB_TYPE
    );

    // Get all the inventorySupplierDetailsList where subType does not contain UPDATED_SUB_TYPE
    defaultInventorySupplierDetailsShouldBeFound(
      "subType.doesNotContain=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTableNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where tableName equals to DEFAULT_TABLE_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "tableName.equals=" + DEFAULT_TABLE_NAME
    );

    // Get all the inventorySupplierDetailsList where tableName equals to UPDATED_TABLE_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "tableName.equals=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTableNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where tableName not equals to DEFAULT_TABLE_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "tableName.notEquals=" + DEFAULT_TABLE_NAME
    );

    // Get all the inventorySupplierDetailsList where tableName not equals to UPDATED_TABLE_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "tableName.notEquals=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTableNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where tableName in DEFAULT_TABLE_NAME or UPDATED_TABLE_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "tableName.in=" + DEFAULT_TABLE_NAME + "," + UPDATED_TABLE_NAME
    );

    // Get all the inventorySupplierDetailsList where tableName equals to UPDATED_TABLE_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "tableName.in=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTableNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where tableName is not null
    defaultInventorySupplierDetailsShouldBeFound("tableName.specified=true");

    // Get all the inventorySupplierDetailsList where tableName is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "tableName.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTableNameContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where tableName contains DEFAULT_TABLE_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "tableName.contains=" + DEFAULT_TABLE_NAME
    );

    // Get all the inventorySupplierDetailsList where tableName contains UPDATED_TABLE_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "tableName.contains=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByTableNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where tableName does not contain DEFAULT_TABLE_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "tableName.doesNotContain=" + DEFAULT_TABLE_NAME
    );

    // Get all the inventorySupplierDetailsList where tableName does not contain UPDATED_TABLE_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "tableName.doesNotContain=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierName equals to DEFAULT_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierName.equals=" + DEFAULT_SUPPLIER_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierName equals to UPDATED_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierName.equals=" + UPDATED_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierName not equals to DEFAULT_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierName.notEquals=" + DEFAULT_SUPPLIER_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierName not equals to UPDATED_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierName.notEquals=" + UPDATED_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierName in DEFAULT_SUPPLIER_NAME or UPDATED_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierName.in=" + DEFAULT_SUPPLIER_NAME + "," + UPDATED_SUPPLIER_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierName equals to UPDATED_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierName.in=" + UPDATED_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierName is not null
    defaultInventorySupplierDetailsShouldBeFound("supplierName.specified=true");

    // Get all the inventorySupplierDetailsList where supplierName is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierName.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierNameContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierName contains DEFAULT_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierName.contains=" + DEFAULT_SUPPLIER_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierName contains UPDATED_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierName.contains=" + UPDATED_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierName does not contain DEFAULT_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierName.doesNotContain=" + DEFAULT_SUPPLIER_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierName does not contain UPDATED_SUPPLIER_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierName.doesNotContain=" + UPDATED_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierAddressIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierAddress equals to DEFAULT_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierAddress.equals=" + DEFAULT_SUPPLIER_ADDRESS
    );

    // Get all the inventorySupplierDetailsList where supplierAddress equals to UPDATED_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierAddress.equals=" + UPDATED_SUPPLIER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierAddressIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierAddress not equals to DEFAULT_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierAddress.notEquals=" + DEFAULT_SUPPLIER_ADDRESS
    );

    // Get all the inventorySupplierDetailsList where supplierAddress not equals to UPDATED_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierAddress.notEquals=" + UPDATED_SUPPLIER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierAddressIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierAddress in DEFAULT_SUPPLIER_ADDRESS or UPDATED_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierAddress.in=" +
      DEFAULT_SUPPLIER_ADDRESS +
      "," +
      UPDATED_SUPPLIER_ADDRESS
    );

    // Get all the inventorySupplierDetailsList where supplierAddress equals to UPDATED_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierAddress.in=" + UPDATED_SUPPLIER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierAddressIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierAddress is not null
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierAddress.specified=true"
    );

    // Get all the inventorySupplierDetailsList where supplierAddress is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierAddress.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierAddressContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierAddress contains DEFAULT_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierAddress.contains=" + DEFAULT_SUPPLIER_ADDRESS
    );

    // Get all the inventorySupplierDetailsList where supplierAddress contains UPDATED_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierAddress.contains=" + UPDATED_SUPPLIER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierAddressNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierAddress does not contain DEFAULT_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierAddress.doesNotContain=" + DEFAULT_SUPPLIER_ADDRESS
    );

    // Get all the inventorySupplierDetailsList where supplierAddress does not contain UPDATED_SUPPLIER_ADDRESS
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierAddress.doesNotContain=" + UPDATED_SUPPLIER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactName equals to DEFAULT_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactName.equals=" + DEFAULT_SUPPLIER_CONTACT_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierContactName equals to UPDATED_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactName.equals=" + UPDATED_SUPPLIER_CONTACT_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactName not equals to DEFAULT_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactName.notEquals=" + DEFAULT_SUPPLIER_CONTACT_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierContactName not equals to UPDATED_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactName.notEquals=" + UPDATED_SUPPLIER_CONTACT_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactName in DEFAULT_SUPPLIER_CONTACT_NAME or UPDATED_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactName.in=" +
      DEFAULT_SUPPLIER_CONTACT_NAME +
      "," +
      UPDATED_SUPPLIER_CONTACT_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierContactName equals to UPDATED_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactName.in=" + UPDATED_SUPPLIER_CONTACT_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactName is not null
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactName.specified=true"
    );

    // Get all the inventorySupplierDetailsList where supplierContactName is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactName.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactName contains DEFAULT_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactName.contains=" + DEFAULT_SUPPLIER_CONTACT_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierContactName contains UPDATED_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactName.contains=" + UPDATED_SUPPLIER_CONTACT_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactName does not contain DEFAULT_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactName.doesNotContain=" + DEFAULT_SUPPLIER_CONTACT_NAME
    );

    // Get all the inventorySupplierDetailsList where supplierContactName does not contain UPDATED_SUPPLIER_CONTACT_NAME
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactName.doesNotContain=" + UPDATED_SUPPLIER_CONTACT_NAME
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameNumberIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber equals to DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactNameNumber.equals=" + DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER
    );

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber equals to UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactNameNumber.equals=" + UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameNumberIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber not equals to DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactNameNumber.notEquals=" +
      DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER
    );

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber not equals to UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactNameNumber.notEquals=" +
      UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameNumberIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber in DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER or UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactNameNumber.in=" +
      DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER +
      "," +
      UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    );

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber equals to UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactNameNumber.in=" + UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameNumberIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber is not null
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactNameNumber.specified=true"
    );

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactNameNumber.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameNumberContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber contains DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactNameNumber.contains=" +
      DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER
    );

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber contains UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactNameNumber.contains=" +
      UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsBySupplierContactNameNumberNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber does not contain DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldNotBeFound(
      "supplierContactNameNumber.doesNotContain=" +
      DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER
    );

    // Get all the inventorySupplierDetailsList where supplierContactNameNumber does not contain UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    defaultInventorySupplierDetailsShouldBeFound(
      "supplierContactNameNumber.doesNotContain=" +
      UPDATED_SUPPLIER_CONTACT_NAME_NUMBER
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField1IsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField1.equals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the inventorySupplierDetailsList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField1.equals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField1IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the inventorySupplierDetailsList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField1.notEquals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField1IsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the inventorySupplierDetailsList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField1.in=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField1IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField1 is not null
    defaultInventorySupplierDetailsShouldBeFound("freeField1.specified=true");

    // Get all the inventorySupplierDetailsList where freeField1 is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField1.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField1ContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField1.contains=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the inventorySupplierDetailsList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField1.contains=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField1NotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the inventorySupplierDetailsList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField2IsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField2.equals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the inventorySupplierDetailsList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField2.equals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField2IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the inventorySupplierDetailsList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField2.notEquals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField2IsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the inventorySupplierDetailsList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField2.in=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField2IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField2 is not null
    defaultInventorySupplierDetailsShouldBeFound("freeField2.specified=true");

    // Get all the inventorySupplierDetailsList where freeField2 is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField2.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField2ContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField2.contains=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the inventorySupplierDetailsList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField2.contains=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField2NotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the inventorySupplierDetailsList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField3IsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField3.equals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the inventorySupplierDetailsList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField3.equals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField3IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the inventorySupplierDetailsList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField3.notEquals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField3IsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the inventorySupplierDetailsList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField3.in=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField3IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField3 is not null
    defaultInventorySupplierDetailsShouldBeFound("freeField3.specified=true");

    // Get all the inventorySupplierDetailsList where freeField3 is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField3.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField3ContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField3.contains=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the inventorySupplierDetailsList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField3.contains=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByFreeField3NotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the inventorySupplierDetailsList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultInventorySupplierDetailsShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedDateIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdDate equals to DEFAULT_CREATED_DATE
    defaultInventorySupplierDetailsShouldBeFound(
      "createdDate.equals=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventorySupplierDetailsList where createdDate equals to UPDATED_CREATED_DATE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdDate.equals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventorySupplierDetailsList where createdDate not equals to UPDATED_CREATED_DATE
    defaultInventorySupplierDetailsShouldBeFound(
      "createdDate.notEquals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedDateIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultInventorySupplierDetailsShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the inventorySupplierDetailsList where createdDate equals to UPDATED_CREATED_DATE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdDate.in=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedDateIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdDate is not null
    defaultInventorySupplierDetailsShouldBeFound("createdDate.specified=true");

    // Get all the inventorySupplierDetailsList where createdDate is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdDate.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultInventorySupplierDetailsShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventorySupplierDetailsList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultInventorySupplierDetailsShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventorySupplierDetailsList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdDate is less than DEFAULT_CREATED_DATE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventorySupplierDetailsList where createdDate is less than UPDATED_CREATED_DATE
    defaultInventorySupplierDetailsShouldBeFound(
      "createdDate.lessThan=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the inventorySupplierDetailsList where createdDate is greater than SMALLER_CREATED_DATE
    defaultInventorySupplierDetailsShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdBy equals to DEFAULT_CREATED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "createdBy.equals=" + DEFAULT_CREATED_BY
    );

    // Get all the inventorySupplierDetailsList where createdBy equals to UPDATED_CREATED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdBy.equals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdBy not equals to DEFAULT_CREATED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdBy.notEquals=" + DEFAULT_CREATED_BY
    );

    // Get all the inventorySupplierDetailsList where createdBy not equals to UPDATED_CREATED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "createdBy.notEquals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedByIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the inventorySupplierDetailsList where createdBy equals to UPDATED_CREATED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdBy.in=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdBy is not null
    defaultInventorySupplierDetailsShouldBeFound("createdBy.specified=true");

    // Get all the inventorySupplierDetailsList where createdBy is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdBy.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedByContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdBy contains DEFAULT_CREATED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "createdBy.contains=" + DEFAULT_CREATED_BY
    );

    // Get all the inventorySupplierDetailsList where createdBy contains UPDATED_CREATED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdBy.contains=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByCreatedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where createdBy does not contain DEFAULT_CREATED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the inventorySupplierDetailsList where createdBy does not contain UPDATED_CREATED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "createdBy.doesNotContain=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModified.equals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventorySupplierDetailsList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventorySupplierDetailsList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the inventorySupplierDetailsList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModified.in=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModified is not null
    defaultInventorySupplierDetailsShouldBeFound("lastModified.specified=true");

    // Get all the inventorySupplierDetailsList where lastModified is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModified.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventorySupplierDetailsList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventorySupplierDetailsList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedIsLessThanSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventorySupplierDetailsList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModified.lessThan=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the inventorySupplierDetailsList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the inventorySupplierDetailsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the inventorySupplierDetailsList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedByIsInShouldWork()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the inventorySupplierDetailsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModifiedBy is not null
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModifiedBy.specified=true"
    );

    // Get all the inventorySupplierDetailsList where lastModifiedBy is null
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModifiedBy.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedByContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the inventorySupplierDetailsList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByLastModifiedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    // Get all the inventorySupplierDetailsList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the inventorySupplierDetailsList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultInventorySupplierDetailsShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllInventorySupplierDetailsByAuditIsEqualToSomething()
    throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);
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
    inventorySupplierDetails.setAudit(audit);
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);
    Long auditId = audit.getId();

    // Get all the inventorySupplierDetailsList where audit equals to auditId
    defaultInventorySupplierDetailsShouldBeFound("auditId.equals=" + auditId);

    // Get all the inventorySupplierDetailsList where audit equals to (auditId + 1)
    defaultInventorySupplierDetailsShouldNotBeFound(
      "auditId.equals=" + (auditId + 1)
    );
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultInventorySupplierDetailsShouldBeFound(String filter)
    throws Exception {
    restInventorySupplierDetailsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(inventorySupplierDetails.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
      .andExpect(
        jsonPath("$.[*].supplierName").value(hasItem(DEFAULT_SUPPLIER_NAME))
      )
      .andExpect(
        jsonPath("$.[*].supplierAddress")
          .value(hasItem(DEFAULT_SUPPLIER_ADDRESS))
      )
      .andExpect(
        jsonPath("$.[*].supplierContactName")
          .value(hasItem(DEFAULT_SUPPLIER_CONTACT_NAME))
      )
      .andExpect(
        jsonPath("$.[*].supplierContactNameNumber")
          .value(hasItem(DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER))
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
    restInventorySupplierDetailsMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultInventorySupplierDetailsShouldNotBeFound(String filter)
    throws Exception {
    restInventorySupplierDetailsMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restInventorySupplierDetailsMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingInventorySupplierDetails() throws Exception {
    // Get the inventorySupplierDetails
    restInventorySupplierDetailsMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewInventorySupplierDetails() throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    int databaseSizeBeforeUpdate = inventorySupplierDetailsRepository
      .findAll()
      .size();

    // Update the inventorySupplierDetails
    InventorySupplierDetails updatedInventorySupplierDetails = inventorySupplierDetailsRepository
      .findById(inventorySupplierDetails.getId())
      .get();
    // Disconnect from session so that the updates on updatedInventorySupplierDetails are not directly saved in db
    em.detach(updatedInventorySupplierDetails);
    updatedInventorySupplierDetails
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .supplierName(UPDATED_SUPPLIER_NAME)
      .supplierAddress(UPDATED_SUPPLIER_ADDRESS)
      .supplierContactName(UPDATED_SUPPLIER_CONTACT_NAME)
      .supplierContactNameNumber(UPDATED_SUPPLIER_CONTACT_NAME_NUMBER)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    InventorySupplierDetails inventorySupplierDetails = inventorySupplierDetailsMapper.toDto(
      updatedInventorySupplierDetails
    );

    restInventorySupplierDetailsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, inventorySupplierDetailsDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(
            TestUtil.convertObjectToJsonBytes(inventorySupplierDetailsDTO)
          )
      )
      .andExpect(status().isOk());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeUpdate);
    InventorySupplierDetails testInventorySupplierDetails = inventorySupplierDetailsList.get(
      inventorySupplierDetailsList.size() - 1
    );
    assertThat(testInventorySupplierDetails.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testInventorySupplierDetails.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testInventorySupplierDetails.getSubType())
      .isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testInventorySupplierDetails.getTableName())
      .isEqualTo(UPDATED_TABLE_NAME);
    assertThat(testInventorySupplierDetails.getSupplierName())
      .isEqualTo(UPDATED_SUPPLIER_NAME);
    assertThat(testInventorySupplierDetails.getSupplierAddress())
      .isEqualTo(UPDATED_SUPPLIER_ADDRESS);
    assertThat(testInventorySupplierDetails.getSupplierContactName())
      .isEqualTo(UPDATED_SUPPLIER_CONTACT_NAME);
    assertThat(testInventorySupplierDetails.getSupplierContactNameNumber())
      .isEqualTo(UPDATED_SUPPLIER_CONTACT_NAME_NUMBER);
    assertThat(testInventorySupplierDetails.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testInventorySupplierDetails.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testInventorySupplierDetails.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testInventorySupplierDetails.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testInventorySupplierDetails.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testInventorySupplierDetails.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testInventorySupplierDetails.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void putNonExistingInventorySupplierDetails() throws Exception {
    int databaseSizeBeforeUpdate = inventorySupplierDetailsRepository
      .findAll()
      .size();
    inventorySupplierDetails.setId(count.incrementAndGet());

    // Create the InventorySupplierDetails
    InventorySupplierDetails inventorySupplierDetails = inventorySupplierDetailsMapper.toDto(
      inventorySupplierDetails
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restInventorySupplierDetailsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, inventorySupplierDetails.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventorySupplierDetails))
      )
      .andExpect(status().isBadRequest());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchInventorySupplierDetails() throws Exception {
    int databaseSizeBeforeUpdate = inventorySupplierDetailsRepository
      .findAll()
      .size();
    inventorySupplierDetails.setId(count.incrementAndGet());

    // Create the InventorySupplierDetails
    InventorySupplierDetails inventorySupplierDetails = inventorySupplierDetailsMapper.toDto(
      inventorySupplierDetails
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restInventorySupplierDetailsMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventorySupplierDetails))
      )
      .andExpect(status().isBadRequest());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamInventorySupplierDetails() throws Exception {
    int databaseSizeBeforeUpdate = inventorySupplierDetailsRepository
      .findAll()
      .size();
    inventorySupplierDetails.setId(count.incrementAndGet());

    // Create the InventorySupplierDetails
    InventorySupplierDetails inventorySupplierDetails = inventorySupplierDetailsMapper.toDto(
      inventorySupplierDetails
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restInventorySupplierDetailsMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(inventorySupplierDetails))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateInventorySupplierDetailsWithPatch() throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    int databaseSizeBeforeUpdate = inventorySupplierDetailsRepository
      .findAll()
      .size();

    // Update the inventorySupplierDetails using partial update
    InventorySupplierDetails partialUpdatedInventorySupplierDetails = new InventorySupplierDetails();
    partialUpdatedInventorySupplierDetails.setId(
      inventorySupplierDetails.getId()
    );

    partialUpdatedInventorySupplierDetails
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .supplierName(UPDATED_SUPPLIER_NAME)
      .supplierAddress(UPDATED_SUPPLIER_ADDRESS)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

    restInventorySupplierDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedInventorySupplierDetails.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(
              partialUpdatedInventorySupplierDetails
            )
          )
      )
      .andExpect(status().isOk());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeUpdate);
    InventorySupplierDetails testInventorySupplierDetails = inventorySupplierDetailsList.get(
      inventorySupplierDetailsList.size() - 1
    );
    assertThat(testInventorySupplierDetails.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testInventorySupplierDetails.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testInventorySupplierDetails.getSubType())
      .isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testInventorySupplierDetails.getTableName())
      .isEqualTo(DEFAULT_TABLE_NAME);
    assertThat(testInventorySupplierDetails.getSupplierName())
      .isEqualTo(UPDATED_SUPPLIER_NAME);
    assertThat(testInventorySupplierDetails.getSupplierAddress())
      .isEqualTo(UPDATED_SUPPLIER_ADDRESS);
    assertThat(testInventorySupplierDetails.getSupplierContactName())
      .isEqualTo(DEFAULT_SUPPLIER_CONTACT_NAME);
    assertThat(testInventorySupplierDetails.getSupplierContactNameNumber())
      .isEqualTo(DEFAULT_SUPPLIER_CONTACT_NAME_NUMBER);
    assertThat(testInventorySupplierDetails.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testInventorySupplierDetails.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testInventorySupplierDetails.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testInventorySupplierDetails.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testInventorySupplierDetails.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testInventorySupplierDetails.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testInventorySupplierDetails.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void fullUpdateInventorySupplierDetailsWithPatch() throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    int databaseSizeBeforeUpdate = inventorySupplierDetailsRepository
      .findAll()
      .size();

    // Update the inventorySupplierDetails using partial update
    InventorySupplierDetails partialUpdatedInventorySupplierDetails = new InventorySupplierDetails();
    partialUpdatedInventorySupplierDetails.setId(
      inventorySupplierDetails.getId()
    );

    partialUpdatedInventorySupplierDetails
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .supplierName(UPDATED_SUPPLIER_NAME)
      .supplierAddress(UPDATED_SUPPLIER_ADDRESS)
      .supplierContactName(UPDATED_SUPPLIER_CONTACT_NAME)
      .supplierContactNameNumber(UPDATED_SUPPLIER_CONTACT_NAME_NUMBER)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

    restInventorySupplierDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedInventorySupplierDetails.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(
              partialUpdatedInventorySupplierDetails
            )
          )
      )
      .andExpect(status().isOk());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeUpdate);
    InventorySupplierDetails testInventorySupplierDetails = inventorySupplierDetailsList.get(
      inventorySupplierDetailsList.size() - 1
    );
    assertThat(testInventorySupplierDetails.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testInventorySupplierDetails.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testInventorySupplierDetails.getSubType())
      .isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testInventorySupplierDetails.getTableName())
      .isEqualTo(UPDATED_TABLE_NAME);
    assertThat(testInventorySupplierDetails.getSupplierName())
      .isEqualTo(UPDATED_SUPPLIER_NAME);
    assertThat(testInventorySupplierDetails.getSupplierAddress())
      .isEqualTo(UPDATED_SUPPLIER_ADDRESS);
    assertThat(testInventorySupplierDetails.getSupplierContactName())
      .isEqualTo(UPDATED_SUPPLIER_CONTACT_NAME);
    assertThat(testInventorySupplierDetails.getSupplierContactNameNumber())
      .isEqualTo(UPDATED_SUPPLIER_CONTACT_NAME_NUMBER);
    assertThat(testInventorySupplierDetails.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testInventorySupplierDetails.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testInventorySupplierDetails.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testInventorySupplierDetails.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testInventorySupplierDetails.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testInventorySupplierDetails.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testInventorySupplierDetails.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void patchNonExistingInventorySupplierDetails() throws Exception {
    int databaseSizeBeforeUpdate = inventorySupplierDetailsRepository
      .findAll()
      .size();
    inventorySupplierDetails.setId(count.incrementAndGet());

    // Create the InventorySupplierDetails
    InventorySupplierDetailsDTO inventorySupplierDetailsDTO = inventorySupplierDetailsMapper.toDto(
      inventorySupplierDetails
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restInventorySupplierDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, inventorySupplierDetailsDTO.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(inventorySupplierDetailsDTO)
          )
      )
      .andExpect(status().isBadRequest());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchInventorySupplierDetails() throws Exception {
    int databaseSizeBeforeUpdate = inventorySupplierDetailsRepository
      .findAll()
      .size();
    inventorySupplierDetails.setId(count.incrementAndGet());

    // Create the InventorySupplierDetails
    InventorySupplierDetailsDTO inventorySupplierDetailsDTO = inventorySupplierDetailsMapper.toDto(
      inventorySupplierDetails
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restInventorySupplierDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(inventorySupplierDetailsDTO)
          )
      )
      .andExpect(status().isBadRequest());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamInventorySupplierDetails() throws Exception {
    int databaseSizeBeforeUpdate = inventorySupplierDetailsRepository
      .findAll()
      .size();
    inventorySupplierDetails.setId(count.incrementAndGet());

    // Create the InventorySupplierDetails
    InventorySupplierDetailsDTO inventorySupplierDetailsDTO = inventorySupplierDetailsMapper.toDto(
      inventorySupplierDetails
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restInventorySupplierDetailsMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(inventorySupplierDetailsDTO)
          )
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the InventorySupplierDetails in the database
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteInventorySupplierDetails() throws Exception {
    // Initialize the database
    inventorySupplierDetailsRepository.saveAndFlush(inventorySupplierDetails);

    int databaseSizeBeforeDelete = inventorySupplierDetailsRepository
      .findAll()
      .size();

    // Delete the inventorySupplierDetails
    restInventorySupplierDetailsMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, inventorySupplierDetails.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<InventorySupplierDetails> inventorySupplierDetailsList = inventorySupplierDetailsRepository.findAll();
    assertThat(inventorySupplierDetailsList)
      .hasSize(databaseSizeBeforeDelete - 1);
  }
}
