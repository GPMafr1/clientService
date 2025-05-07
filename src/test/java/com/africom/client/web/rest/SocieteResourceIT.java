package com.africom.client.web.rest;

import com.africom.client.ClientServiceApp;
import com.africom.client.domain.Societe;
import com.africom.client.repository.SocieteRepository;
import com.africom.client.service.SocieteService;
import com.africom.client.service.dto.SocieteDTO;
import com.africom.client.service.mapper.SocieteMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SocieteResource} REST controller.
 */
@SpringBootTest(classes = ClientServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SocieteResourceIT {

    private static final String DEFAULT_RAISON_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIALE = "BBBBBBBBBB";

    private static final String DEFAULT_RAISON_SOCIALE_ABREGE = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIALE_ABREGE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIANT_UNIQUE = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIANT_UNIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRE_COMMERCE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRE_COMMERCE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_ARTICLE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ARTICLE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_PAYS = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "1.(@bHE\\A1";
    private static final String UPDATED_EMAIL = "N+@y4|'<\\=>'";

    @Autowired
    private SocieteRepository societeRepository;

    @Autowired
    private SocieteMapper societeMapper;

    @Autowired
    private SocieteService societeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSocieteMockMvc;

    private Societe societe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Societe createEntity(EntityManager em) {
        Societe societe = new Societe()
            .raisonSociale(DEFAULT_RAISON_SOCIALE)
            .raisonSocialeAbrege(DEFAULT_RAISON_SOCIALE_ABREGE)
            .identifiantUnique(DEFAULT_IDENTIFIANT_UNIQUE)
            .registreCommerce(DEFAULT_REGISTRE_COMMERCE)
            .codeArticle(DEFAULT_CODE_ARTICLE)
            .adresse(DEFAULT_ADRESSE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .pays(DEFAULT_PAYS)
            .telephone(DEFAULT_TELEPHONE)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL);
        return societe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Societe createUpdatedEntity(EntityManager em) {
        Societe societe = new Societe()
            .raisonSociale(UPDATED_RAISON_SOCIALE)
            .raisonSocialeAbrege(UPDATED_RAISON_SOCIALE_ABREGE)
            .identifiantUnique(UPDATED_IDENTIFIANT_UNIQUE)
            .registreCommerce(UPDATED_REGISTRE_COMMERCE)
            .codeArticle(UPDATED_CODE_ARTICLE)
            .adresse(UPDATED_ADRESSE)
            .codePostal(UPDATED_CODE_POSTAL)
            .pays(UPDATED_PAYS)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL);
        return societe;
    }

    @BeforeEach
    public void initTest() {
        societe = createEntity(em);
    }

    @Test
    @Transactional
    public void createSociete() throws Exception {
        int databaseSizeBeforeCreate = societeRepository.findAll().size();
        // Create the Societe
        SocieteDTO societeDTO = societeMapper.toDto(societe);
        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isCreated());

        // Validate the Societe in the database
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeCreate + 1);
        Societe testSociete = societeList.get(societeList.size() - 1);
        assertThat(testSociete.getRaisonSociale()).isEqualTo(DEFAULT_RAISON_SOCIALE);
        assertThat(testSociete.getRaisonSocialeAbrege()).isEqualTo(DEFAULT_RAISON_SOCIALE_ABREGE);
        assertThat(testSociete.getIdentifiantUnique()).isEqualTo(DEFAULT_IDENTIFIANT_UNIQUE);
        assertThat(testSociete.getRegistreCommerce()).isEqualTo(DEFAULT_REGISTRE_COMMERCE);
        assertThat(testSociete.getCodeArticle()).isEqualTo(DEFAULT_CODE_ARTICLE);
        assertThat(testSociete.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testSociete.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testSociete.getPays()).isEqualTo(DEFAULT_PAYS);
        assertThat(testSociete.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testSociete.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testSociete.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createSocieteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = societeRepository.findAll().size();

        // Create the Societe with an existing ID
        societe.setId(1L);
        SocieteDTO societeDTO = societeMapper.toDto(societe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Societe in the database
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRaisonSocialeIsRequired() throws Exception {
        int databaseSizeBeforeTest = societeRepository.findAll().size();
        // set the field null
        societe.setRaisonSociale(null);

        // Create the Societe, which fails.
        SocieteDTO societeDTO = societeMapper.toDto(societe);


        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRaisonSocialeAbregeIsRequired() throws Exception {
        int databaseSizeBeforeTest = societeRepository.findAll().size();
        // set the field null
        societe.setRaisonSocialeAbrege(null);

        // Create the Societe, which fails.
        SocieteDTO societeDTO = societeMapper.toDto(societe);


        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdentifiantUniqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = societeRepository.findAll().size();
        // set the field null
        societe.setIdentifiantUnique(null);

        // Create the Societe, which fails.
        SocieteDTO societeDTO = societeMapper.toDto(societe);


        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRegistreCommerceIsRequired() throws Exception {
        int databaseSizeBeforeTest = societeRepository.findAll().size();
        // set the field null
        societe.setRegistreCommerce(null);

        // Create the Societe, which fails.
        SocieteDTO societeDTO = societeMapper.toDto(societe);


        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeArticleIsRequired() throws Exception {
        int databaseSizeBeforeTest = societeRepository.findAll().size();
        // set the field null
        societe.setCodeArticle(null);

        // Create the Societe, which fails.
        SocieteDTO societeDTO = societeMapper.toDto(societe);


        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = societeRepository.findAll().size();
        // set the field null
        societe.setAdresse(null);

        // Create the Societe, which fails.
        SocieteDTO societeDTO = societeMapper.toDto(societe);


        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodePostalIsRequired() throws Exception {
        int databaseSizeBeforeTest = societeRepository.findAll().size();
        // set the field null
        societe.setCodePostal(null);

        // Create the Societe, which fails.
        SocieteDTO societeDTO = societeMapper.toDto(societe);


        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = societeRepository.findAll().size();
        // set the field null
        societe.setPays(null);

        // Create the Societe, which fails.
        SocieteDTO societeDTO = societeMapper.toDto(societe);


        restSocieteMockMvc.perform(post("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSocietes() throws Exception {
        // Initialize the database
        societeRepository.saveAndFlush(societe);

        // Get all the societeList
        restSocieteMockMvc.perform(get("/api/societes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(societe.getId().intValue())))
            .andExpect(jsonPath("$.[*].raisonSociale").value(hasItem(DEFAULT_RAISON_SOCIALE)))
            .andExpect(jsonPath("$.[*].raisonSocialeAbrege").value(hasItem(DEFAULT_RAISON_SOCIALE_ABREGE)))
            .andExpect(jsonPath("$.[*].identifiantUnique").value(hasItem(DEFAULT_IDENTIFIANT_UNIQUE)))
            .andExpect(jsonPath("$.[*].registreCommerce").value(hasItem(DEFAULT_REGISTRE_COMMERCE)))
            .andExpect(jsonPath("$.[*].codeArticle").value(hasItem(DEFAULT_CODE_ARTICLE)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL)))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    @Transactional
    public void getSociete() throws Exception {
        // Initialize the database
        societeRepository.saveAndFlush(societe);

        // Get the societe
        restSocieteMockMvc.perform(get("/api/societes/{id}", societe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(societe.getId().intValue()))
            .andExpect(jsonPath("$.raisonSociale").value(DEFAULT_RAISON_SOCIALE))
            .andExpect(jsonPath("$.raisonSocialeAbrege").value(DEFAULT_RAISON_SOCIALE_ABREGE))
            .andExpect(jsonPath("$.identifiantUnique").value(DEFAULT_IDENTIFIANT_UNIQUE))
            .andExpect(jsonPath("$.registreCommerce").value(DEFAULT_REGISTRE_COMMERCE))
            .andExpect(jsonPath("$.codeArticle").value(DEFAULT_CODE_ARTICLE))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    @Transactional
    public void getNonExistingSociete() throws Exception {
        // Get the societe
        restSocieteMockMvc.perform(get("/api/societes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSociete() throws Exception {
        // Initialize the database
        societeRepository.saveAndFlush(societe);

        int databaseSizeBeforeUpdate = societeRepository.findAll().size();

        // Update the societe
        Societe updatedSociete = societeRepository.findById(societe.getId()).get();
        // Disconnect from session so that the updates on updatedSociete are not directly saved in db
        em.detach(updatedSociete);
        updatedSociete
            .raisonSociale(UPDATED_RAISON_SOCIALE)
            .raisonSocialeAbrege(UPDATED_RAISON_SOCIALE_ABREGE)
            .identifiantUnique(UPDATED_IDENTIFIANT_UNIQUE)
            .registreCommerce(UPDATED_REGISTRE_COMMERCE)
            .codeArticle(UPDATED_CODE_ARTICLE)
            .adresse(UPDATED_ADRESSE)
            .codePostal(UPDATED_CODE_POSTAL)
            .pays(UPDATED_PAYS)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL);
        SocieteDTO societeDTO = societeMapper.toDto(updatedSociete);

        restSocieteMockMvc.perform(put("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isOk());

        // Validate the Societe in the database
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeUpdate);
        Societe testSociete = societeList.get(societeList.size() - 1);
        assertThat(testSociete.getRaisonSociale()).isEqualTo(UPDATED_RAISON_SOCIALE);
        assertThat(testSociete.getRaisonSocialeAbrege()).isEqualTo(UPDATED_RAISON_SOCIALE_ABREGE);
        assertThat(testSociete.getIdentifiantUnique()).isEqualTo(UPDATED_IDENTIFIANT_UNIQUE);
        assertThat(testSociete.getRegistreCommerce()).isEqualTo(UPDATED_REGISTRE_COMMERCE);
        assertThat(testSociete.getCodeArticle()).isEqualTo(UPDATED_CODE_ARTICLE);
        assertThat(testSociete.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testSociete.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testSociete.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testSociete.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testSociete.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testSociete.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingSociete() throws Exception {
        int databaseSizeBeforeUpdate = societeRepository.findAll().size();

        // Create the Societe
        SocieteDTO societeDTO = societeMapper.toDto(societe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSocieteMockMvc.perform(put("/api/societes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(societeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Societe in the database
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSociete() throws Exception {
        // Initialize the database
        societeRepository.saveAndFlush(societe);

        int databaseSizeBeforeDelete = societeRepository.findAll().size();

        // Delete the societe
        restSocieteMockMvc.perform(delete("/api/societes/{id}", societe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Societe> societeList = societeRepository.findAll();
        assertThat(societeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
