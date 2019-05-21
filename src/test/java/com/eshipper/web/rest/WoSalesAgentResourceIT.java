package com.eshipper.web.rest;

import com.eshipper.CreateWoReferenceChangesApp;
import com.eshipper.domain.WoSalesAgent;
import com.eshipper.repository.WoSalesAgentRepository;
import com.eshipper.service.WoSalesAgentService;
import com.eshipper.service.dto.WoSalesAgentDTO;
import com.eshipper.service.mapper.WoSalesAgentMapper;
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
 * Integration tests for the {@Link WoSalesAgentResource} REST controller.
 */
@SpringBootTest(classes = CreateWoReferenceChangesApp.class)
public class WoSalesAgentResourceIT {

    private static final String DEFAULT_AGENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROMO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_CODE = "BBBBBBBBBB";

    @Autowired
    private WoSalesAgentRepository woSalesAgentRepository;

    @Autowired
    private WoSalesAgentMapper woSalesAgentMapper;

    @Autowired
    private WoSalesAgentService woSalesAgentService;

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

    private MockMvc restWoSalesAgentMockMvc;

    private WoSalesAgent woSalesAgent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WoSalesAgentResource woSalesAgentResource = new WoSalesAgentResource(woSalesAgentService);
        this.restWoSalesAgentMockMvc = MockMvcBuilders.standaloneSetup(woSalesAgentResource)
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
    public static WoSalesAgent createEntity(EntityManager em) {
        WoSalesAgent woSalesAgent = new WoSalesAgent()
            .agentName(DEFAULT_AGENT_NAME)
            .promoCode(DEFAULT_PROMO_CODE);
        return woSalesAgent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoSalesAgent createUpdatedEntity(EntityManager em) {
        WoSalesAgent woSalesAgent = new WoSalesAgent()
            .agentName(UPDATED_AGENT_NAME)
            .promoCode(UPDATED_PROMO_CODE);
        return woSalesAgent;
    }

    @BeforeEach
    public void initTest() {
        woSalesAgent = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoSalesAgent() throws Exception {
        int databaseSizeBeforeCreate = woSalesAgentRepository.findAll().size();

        // Create the WoSalesAgent
        WoSalesAgentDTO woSalesAgentDTO = woSalesAgentMapper.toDto(woSalesAgent);
        restWoSalesAgentMockMvc.perform(post("/api/wo-sales-agents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDTO)))
            .andExpect(status().isCreated());

        // Validate the WoSalesAgent in the database
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeCreate + 1);
        WoSalesAgent testWoSalesAgent = woSalesAgentList.get(woSalesAgentList.size() - 1);
        assertThat(testWoSalesAgent.getAgentName()).isEqualTo(DEFAULT_AGENT_NAME);
        assertThat(testWoSalesAgent.getPromoCode()).isEqualTo(DEFAULT_PROMO_CODE);
    }

    @Test
    @Transactional
    public void createWoSalesAgentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woSalesAgentRepository.findAll().size();

        // Create the WoSalesAgent with an existing ID
        woSalesAgent.setId(1L);
        WoSalesAgentDTO woSalesAgentDTO = woSalesAgentMapper.toDto(woSalesAgent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoSalesAgentMockMvc.perform(post("/api/wo-sales-agents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesAgent in the database
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoSalesAgents() throws Exception {
        // Initialize the database
        woSalesAgentRepository.saveAndFlush(woSalesAgent);

        // Get all the woSalesAgentList
        restWoSalesAgentMockMvc.perform(get("/api/wo-sales-agents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woSalesAgent.getId().intValue())))
            .andExpect(jsonPath("$.[*].agentName").value(hasItem(DEFAULT_AGENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].promoCode").value(hasItem(DEFAULT_PROMO_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getWoSalesAgent() throws Exception {
        // Initialize the database
        woSalesAgentRepository.saveAndFlush(woSalesAgent);

        // Get the woSalesAgent
        restWoSalesAgentMockMvc.perform(get("/api/wo-sales-agents/{id}", woSalesAgent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(woSalesAgent.getId().intValue()))
            .andExpect(jsonPath("$.agentName").value(DEFAULT_AGENT_NAME.toString()))
            .andExpect(jsonPath("$.promoCode").value(DEFAULT_PROMO_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWoSalesAgent() throws Exception {
        // Get the woSalesAgent
        restWoSalesAgentMockMvc.perform(get("/api/wo-sales-agents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoSalesAgent() throws Exception {
        // Initialize the database
        woSalesAgentRepository.saveAndFlush(woSalesAgent);

        int databaseSizeBeforeUpdate = woSalesAgentRepository.findAll().size();

        // Update the woSalesAgent
        WoSalesAgent updatedWoSalesAgent = woSalesAgentRepository.findById(woSalesAgent.getId()).get();
        // Disconnect from session so that the updates on updatedWoSalesAgent are not directly saved in db
        em.detach(updatedWoSalesAgent);
        updatedWoSalesAgent
            .agentName(UPDATED_AGENT_NAME)
            .promoCode(UPDATED_PROMO_CODE);
        WoSalesAgentDTO woSalesAgentDTO = woSalesAgentMapper.toDto(updatedWoSalesAgent);

        restWoSalesAgentMockMvc.perform(put("/api/wo-sales-agents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDTO)))
            .andExpect(status().isOk());

        // Validate the WoSalesAgent in the database
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeUpdate);
        WoSalesAgent testWoSalesAgent = woSalesAgentList.get(woSalesAgentList.size() - 1);
        assertThat(testWoSalesAgent.getAgentName()).isEqualTo(UPDATED_AGENT_NAME);
        assertThat(testWoSalesAgent.getPromoCode()).isEqualTo(UPDATED_PROMO_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingWoSalesAgent() throws Exception {
        int databaseSizeBeforeUpdate = woSalesAgentRepository.findAll().size();

        // Create the WoSalesAgent
        WoSalesAgentDTO woSalesAgentDTO = woSalesAgentMapper.toDto(woSalesAgent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoSalesAgentMockMvc.perform(put("/api/wo-sales-agents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(woSalesAgentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoSalesAgent in the database
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoSalesAgent() throws Exception {
        // Initialize the database
        woSalesAgentRepository.saveAndFlush(woSalesAgent);

        int databaseSizeBeforeDelete = woSalesAgentRepository.findAll().size();

        // Delete the woSalesAgent
        restWoSalesAgentMockMvc.perform(delete("/api/wo-sales-agents/{id}", woSalesAgent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<WoSalesAgent> woSalesAgentList = woSalesAgentRepository.findAll();
        assertThat(woSalesAgentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesAgent.class);
        WoSalesAgent woSalesAgent1 = new WoSalesAgent();
        woSalesAgent1.setId(1L);
        WoSalesAgent woSalesAgent2 = new WoSalesAgent();
        woSalesAgent2.setId(woSalesAgent1.getId());
        assertThat(woSalesAgent1).isEqualTo(woSalesAgent2);
        woSalesAgent2.setId(2L);
        assertThat(woSalesAgent1).isNotEqualTo(woSalesAgent2);
        woSalesAgent1.setId(null);
        assertThat(woSalesAgent1).isNotEqualTo(woSalesAgent2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoSalesAgentDTO.class);
        WoSalesAgentDTO woSalesAgentDTO1 = new WoSalesAgentDTO();
        woSalesAgentDTO1.setId(1L);
        WoSalesAgentDTO woSalesAgentDTO2 = new WoSalesAgentDTO();
        assertThat(woSalesAgentDTO1).isNotEqualTo(woSalesAgentDTO2);
        woSalesAgentDTO2.setId(woSalesAgentDTO1.getId());
        assertThat(woSalesAgentDTO1).isEqualTo(woSalesAgentDTO2);
        woSalesAgentDTO2.setId(2L);
        assertThat(woSalesAgentDTO1).isNotEqualTo(woSalesAgentDTO2);
        woSalesAgentDTO1.setId(null);
        assertThat(woSalesAgentDTO1).isNotEqualTo(woSalesAgentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(woSalesAgentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(woSalesAgentMapper.fromId(null)).isNull();
    }
}
