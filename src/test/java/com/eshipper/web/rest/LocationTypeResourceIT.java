package com.eshipper.web.rest;

import com.eshipper.CreateWoReferenceChangesApp;
import com.eshipper.domain.LocationType;
import com.eshipper.repository.LocationTypeRepository;
import com.eshipper.service.LocationTypeService;
import com.eshipper.service.dto.LocationTypeDTO;
import com.eshipper.service.mapper.LocationTypeMapper;
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
 * Integration tests for the {@Link LocationTypeResource} REST controller.
 */
@SpringBootTest(classes = CreateWoReferenceChangesApp.class)
public class LocationTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private LocationTypeRepository locationTypeRepository;

    @Autowired
    private LocationTypeMapper locationTypeMapper;

    @Autowired
    private LocationTypeService locationTypeService;

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

    private MockMvc restLocationTypeMockMvc;

    private LocationType locationType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocationTypeResource locationTypeResource = new LocationTypeResource(locationTypeService);
        this.restLocationTypeMockMvc = MockMvcBuilders.standaloneSetup(locationTypeResource)
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
    public static LocationType createEntity(EntityManager em) {
        LocationType locationType = new LocationType()
            .name(DEFAULT_NAME);
        return locationType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LocationType createUpdatedEntity(EntityManager em) {
        LocationType locationType = new LocationType()
            .name(UPDATED_NAME);
        return locationType;
    }

    @BeforeEach
    public void initTest() {
        locationType = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocationType() throws Exception {
        int databaseSizeBeforeCreate = locationTypeRepository.findAll().size();

        // Create the LocationType
        LocationTypeDTO locationTypeDTO = locationTypeMapper.toDto(locationType);
        restLocationTypeMockMvc.perform(post("/api/location-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the LocationType in the database
        List<LocationType> locationTypeList = locationTypeRepository.findAll();
        assertThat(locationTypeList).hasSize(databaseSizeBeforeCreate + 1);
        LocationType testLocationType = locationTypeList.get(locationTypeList.size() - 1);
        assertThat(testLocationType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createLocationTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationTypeRepository.findAll().size();

        // Create the LocationType with an existing ID
        locationType.setId(1L);
        LocationTypeDTO locationTypeDTO = locationTypeMapper.toDto(locationType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationTypeMockMvc.perform(post("/api/location-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LocationType in the database
        List<LocationType> locationTypeList = locationTypeRepository.findAll();
        assertThat(locationTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLocationTypes() throws Exception {
        // Initialize the database
        locationTypeRepository.saveAndFlush(locationType);

        // Get all the locationTypeList
        restLocationTypeMockMvc.perform(get("/api/location-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locationType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getLocationType() throws Exception {
        // Initialize the database
        locationTypeRepository.saveAndFlush(locationType);

        // Get the locationType
        restLocationTypeMockMvc.perform(get("/api/location-types/{id}", locationType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(locationType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLocationType() throws Exception {
        // Get the locationType
        restLocationTypeMockMvc.perform(get("/api/location-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocationType() throws Exception {
        // Initialize the database
        locationTypeRepository.saveAndFlush(locationType);

        int databaseSizeBeforeUpdate = locationTypeRepository.findAll().size();

        // Update the locationType
        LocationType updatedLocationType = locationTypeRepository.findById(locationType.getId()).get();
        // Disconnect from session so that the updates on updatedLocationType are not directly saved in db
        em.detach(updatedLocationType);
        updatedLocationType
            .name(UPDATED_NAME);
        LocationTypeDTO locationTypeDTO = locationTypeMapper.toDto(updatedLocationType);

        restLocationTypeMockMvc.perform(put("/api/location-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationTypeDTO)))
            .andExpect(status().isOk());

        // Validate the LocationType in the database
        List<LocationType> locationTypeList = locationTypeRepository.findAll();
        assertThat(locationTypeList).hasSize(databaseSizeBeforeUpdate);
        LocationType testLocationType = locationTypeList.get(locationTypeList.size() - 1);
        assertThat(testLocationType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingLocationType() throws Exception {
        int databaseSizeBeforeUpdate = locationTypeRepository.findAll().size();

        // Create the LocationType
        LocationTypeDTO locationTypeDTO = locationTypeMapper.toDto(locationType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationTypeMockMvc.perform(put("/api/location-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LocationType in the database
        List<LocationType> locationTypeList = locationTypeRepository.findAll();
        assertThat(locationTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocationType() throws Exception {
        // Initialize the database
        locationTypeRepository.saveAndFlush(locationType);

        int databaseSizeBeforeDelete = locationTypeRepository.findAll().size();

        // Delete the locationType
        restLocationTypeMockMvc.perform(delete("/api/location-types/{id}", locationType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<LocationType> locationTypeList = locationTypeRepository.findAll();
        assertThat(locationTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocationType.class);
        LocationType locationType1 = new LocationType();
        locationType1.setId(1L);
        LocationType locationType2 = new LocationType();
        locationType2.setId(locationType1.getId());
        assertThat(locationType1).isEqualTo(locationType2);
        locationType2.setId(2L);
        assertThat(locationType1).isNotEqualTo(locationType2);
        locationType1.setId(null);
        assertThat(locationType1).isNotEqualTo(locationType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocationTypeDTO.class);
        LocationTypeDTO locationTypeDTO1 = new LocationTypeDTO();
        locationTypeDTO1.setId(1L);
        LocationTypeDTO locationTypeDTO2 = new LocationTypeDTO();
        assertThat(locationTypeDTO1).isNotEqualTo(locationTypeDTO2);
        locationTypeDTO2.setId(locationTypeDTO1.getId());
        assertThat(locationTypeDTO1).isEqualTo(locationTypeDTO2);
        locationTypeDTO2.setId(2L);
        assertThat(locationTypeDTO1).isNotEqualTo(locationTypeDTO2);
        locationTypeDTO1.setId(null);
        assertThat(locationTypeDTO1).isNotEqualTo(locationTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(locationTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(locationTypeMapper.fromId(null)).isNull();
    }
}
