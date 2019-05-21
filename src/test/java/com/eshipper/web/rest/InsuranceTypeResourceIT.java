package com.eshipper.web.rest;

import com.eshipper.CreateWoReferenceChangesApp;
import com.eshipper.domain.InsuranceType;
import com.eshipper.repository.InsuranceTypeRepository;
import com.eshipper.service.InsuranceTypeService;
import com.eshipper.service.dto.InsuranceTypeDTO;
import com.eshipper.service.mapper.InsuranceTypeMapper;
import com.eshipper.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.eshipper.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link InsuranceTypeResource} REST controller.
 */
@SpringBootTest(classes = CreateWoReferenceChangesApp.class)
public class InsuranceTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private InsuranceTypeRepository insuranceTypeRepository;

    @Autowired
    private InsuranceTypeMapper insuranceTypeMapper;

    @Autowired
    private InsuranceTypeService insuranceTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restInsuranceTypeMockMvc;

    private InsuranceType insuranceType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InsuranceTypeResource insuranceTypeResource = new InsuranceTypeResource(insuranceTypeService);
        this.restInsuranceTypeMockMvc = MockMvcBuilders.standaloneSetup(insuranceTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceType createEntity(EntityManager em) {
        InsuranceType insuranceType = new InsuranceType()
            .name(DEFAULT_NAME);
        return insuranceType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceType createUpdatedEntity(EntityManager em) {
        InsuranceType insuranceType = new InsuranceType()
            .name(UPDATED_NAME);
        return insuranceType;
    }

    @BeforeEach
    public void initTest() {
        insuranceType = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsuranceType() throws Exception {
        int databaseSizeBeforeCreate = insuranceTypeRepository.findAll().size();

        // Create the InsuranceType
        InsuranceTypeDTO insuranceTypeDTO = insuranceTypeMapper.toDto(insuranceType);
        restInsuranceTypeMockMvc.perform(post("/api/insurance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the InsuranceType in the database
        List<InsuranceType> insuranceTypeList = insuranceTypeRepository.findAll();
        assertThat(insuranceTypeList).hasSize(databaseSizeBeforeCreate + 1);
        InsuranceType testInsuranceType = insuranceTypeList.get(insuranceTypeList.size() - 1);
        assertThat(testInsuranceType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createInsuranceTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insuranceTypeRepository.findAll().size();

        // Create the InsuranceType with an existing ID
        insuranceType.setId(1L);
        InsuranceTypeDTO insuranceTypeDTO = insuranceTypeMapper.toDto(insuranceType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceTypeMockMvc.perform(post("/api/insurance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceType in the database
        List<InsuranceType> insuranceTypeList = insuranceTypeRepository.findAll();
        assertThat(insuranceTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInsuranceTypes() throws Exception {
        // Initialize the database
        insuranceTypeRepository.saveAndFlush(insuranceType);

        // Get all the insuranceTypeList
        restInsuranceTypeMockMvc.perform(get("/api/insurance-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getInsuranceType() throws Exception {
        // Initialize the database
        insuranceTypeRepository.saveAndFlush(insuranceType);

        // Get the insuranceType
        restInsuranceTypeMockMvc.perform(get("/api/insurance-types/{id}", insuranceType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInsuranceType() throws Exception {
        // Get the insuranceType
        restInsuranceTypeMockMvc.perform(get("/api/insurance-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsuranceType() throws Exception {
        // Initialize the database
        insuranceTypeRepository.saveAndFlush(insuranceType);

        int databaseSizeBeforeUpdate = insuranceTypeRepository.findAll().size();

        // Update the insuranceType
        InsuranceType updatedInsuranceType = insuranceTypeRepository.findById(insuranceType.getId()).get();
        // Disconnect from session so that the updates on updatedInsuranceType are not directly saved in db
        em.detach(updatedInsuranceType);
        updatedInsuranceType
            .name(UPDATED_NAME);
        InsuranceTypeDTO insuranceTypeDTO = insuranceTypeMapper.toDto(updatedInsuranceType);

        restInsuranceTypeMockMvc.perform(put("/api/insurance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceTypeDTO)))
            .andExpect(status().isOk());

        // Validate the InsuranceType in the database
        List<InsuranceType> insuranceTypeList = insuranceTypeRepository.findAll();
        assertThat(insuranceTypeList).hasSize(databaseSizeBeforeUpdate);
        InsuranceType testInsuranceType = insuranceTypeList.get(insuranceTypeList.size() - 1);
        assertThat(testInsuranceType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingInsuranceType() throws Exception {
        int databaseSizeBeforeUpdate = insuranceTypeRepository.findAll().size();

        // Create the InsuranceType
        InsuranceTypeDTO insuranceTypeDTO = insuranceTypeMapper.toDto(insuranceType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceTypeMockMvc.perform(put("/api/insurance-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insuranceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceType in the database
        List<InsuranceType> insuranceTypeList = insuranceTypeRepository.findAll();
        assertThat(insuranceTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInsuranceType() throws Exception {
        // Initialize the database
        insuranceTypeRepository.saveAndFlush(insuranceType);

        int databaseSizeBeforeDelete = insuranceTypeRepository.findAll().size();

        // Delete the insuranceType
        restInsuranceTypeMockMvc.perform(delete("/api/insurance-types/{id}", insuranceType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<InsuranceType> insuranceTypeList = insuranceTypeRepository.findAll();
        assertThat(insuranceTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceType.class);
        InsuranceType insuranceType1 = new InsuranceType();
        insuranceType1.setId(1L);
        InsuranceType insuranceType2 = new InsuranceType();
        insuranceType2.setId(insuranceType1.getId());
        assertThat(insuranceType1).isEqualTo(insuranceType2);
        insuranceType2.setId(2L);
        assertThat(insuranceType1).isNotEqualTo(insuranceType2);
        insuranceType1.setId(null);
        assertThat(insuranceType1).isNotEqualTo(insuranceType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceTypeDTO.class);
        InsuranceTypeDTO insuranceTypeDTO1 = new InsuranceTypeDTO();
        insuranceTypeDTO1.setId(1L);
        InsuranceTypeDTO insuranceTypeDTO2 = new InsuranceTypeDTO();
        assertThat(insuranceTypeDTO1).isNotEqualTo(insuranceTypeDTO2);
        insuranceTypeDTO2.setId(insuranceTypeDTO1.getId());
        assertThat(insuranceTypeDTO1).isEqualTo(insuranceTypeDTO2);
        insuranceTypeDTO2.setId(2L);
        assertThat(insuranceTypeDTO1).isNotEqualTo(insuranceTypeDTO2);
        insuranceTypeDTO1.setId(null);
        assertThat(insuranceTypeDTO1).isNotEqualTo(insuranceTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(insuranceTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(insuranceTypeMapper.fromId(null)).isNull();
    }
}
