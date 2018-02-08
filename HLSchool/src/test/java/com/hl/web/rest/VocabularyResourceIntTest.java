package com.hl.web.rest;

import com.hl.HlSchoolApp;

import com.hl.domain.Vocabulary;
import com.hl.repository.VocabularyRepository;
import com.hl.service.VocabularyService;
import com.hl.service.dto.VocabularyDTO;
import com.hl.service.mapper.VocabularyMapper;
import com.hl.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.hl.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VocabularyResource REST controller.
 *
 * @see VocabularyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HlSchoolApp.class)
public class VocabularyResourceIntTest {

    private static final String DEFAULT_JAPANESE = "AAAAAAAAAA";
    private static final String UPDATED_JAPANESE = "BBBBBBBBBB";

    private static final String DEFAULT_ENGLISH = "AAAAAAAAAA";
    private static final String UPDATED_ENGLISH = "BBBBBBBBBB";

    private static final String DEFAULT_VIETNAMESE = "AAAAAAAAAA";
    private static final String UPDATED_VIETNAMESE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_AUDIO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_AUDIO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_AUDIO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_AUDIO_CONTENT_TYPE = "image/png";

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private VocabularyMapper vocabularyMapper;

    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVocabularyMockMvc;

    private Vocabulary vocabulary;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VocabularyResource vocabularyResource = new VocabularyResource(vocabularyService);
        this.restVocabularyMockMvc = MockMvcBuilders.standaloneSetup(vocabularyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vocabulary createEntity(EntityManager em) {
        Vocabulary vocabulary = new Vocabulary()
            .japanese(DEFAULT_JAPANESE)
            .english(DEFAULT_ENGLISH)
            .vietnamese(DEFAULT_VIETNAMESE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .audio(DEFAULT_AUDIO)
            .audioContentType(DEFAULT_AUDIO_CONTENT_TYPE);
        return vocabulary;
    }

    @Before
    public void initTest() {
        vocabulary = createEntity(em);
    }

    @Test
    @Transactional
    public void createVocabulary() throws Exception {
        int databaseSizeBeforeCreate = vocabularyRepository.findAll().size();

        // Create the Vocabulary
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);
        restVocabularyMockMvc.perform(post("/api/vocabularies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isCreated());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeCreate + 1);
        Vocabulary testVocabulary = vocabularyList.get(vocabularyList.size() - 1);
        assertThat(testVocabulary.getJapanese()).isEqualTo(DEFAULT_JAPANESE);
        assertThat(testVocabulary.getEnglish()).isEqualTo(DEFAULT_ENGLISH);
        assertThat(testVocabulary.getVietnamese()).isEqualTo(DEFAULT_VIETNAMESE);
        assertThat(testVocabulary.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testVocabulary.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testVocabulary.getAudio()).isEqualTo(DEFAULT_AUDIO);
        assertThat(testVocabulary.getAudioContentType()).isEqualTo(DEFAULT_AUDIO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createVocabularyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vocabularyRepository.findAll().size();

        // Create the Vocabulary with an existing ID
        vocabulary.setId(1L);
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVocabularyMockMvc.perform(post("/api/vocabularies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkJapaneseIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyRepository.findAll().size();
        // set the field null
        vocabulary.setJapanese(null);

        // Create the Vocabulary, which fails.
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        restVocabularyMockMvc.perform(post("/api/vocabularies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnglishIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyRepository.findAll().size();
        // set the field null
        vocabulary.setEnglish(null);

        // Create the Vocabulary, which fails.
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        restVocabularyMockMvc.perform(post("/api/vocabularies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVietnameseIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyRepository.findAll().size();
        // set the field null
        vocabulary.setVietnamese(null);

        // Create the Vocabulary, which fails.
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        restVocabularyMockMvc.perform(post("/api/vocabularies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkImageIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyRepository.findAll().size();
        // set the field null
        vocabulary.setImage(null);

        // Create the Vocabulary, which fails.
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        restVocabularyMockMvc.perform(post("/api/vocabularies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVocabularies() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);

        // Get all the vocabularyList
        restVocabularyMockMvc.perform(get("/api/vocabularies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vocabulary.getId().intValue())))
            .andExpect(jsonPath("$.[*].japanese").value(hasItem(DEFAULT_JAPANESE.toString())))
            .andExpect(jsonPath("$.[*].english").value(hasItem(DEFAULT_ENGLISH.toString())))
            .andExpect(jsonPath("$.[*].vietnamese").value(hasItem(DEFAULT_VIETNAMESE.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].audioContentType").value(hasItem(DEFAULT_AUDIO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].audio").value(hasItem(Base64Utils.encodeToString(DEFAULT_AUDIO))));
    }

    @Test
    @Transactional
    public void getVocabulary() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);

        // Get the vocabulary
        restVocabularyMockMvc.perform(get("/api/vocabularies/{id}", vocabulary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vocabulary.getId().intValue()))
            .andExpect(jsonPath("$.japanese").value(DEFAULT_JAPANESE.toString()))
            .andExpect(jsonPath("$.english").value(DEFAULT_ENGLISH.toString()))
            .andExpect(jsonPath("$.vietnamese").value(DEFAULT_VIETNAMESE.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.audioContentType").value(DEFAULT_AUDIO_CONTENT_TYPE))
            .andExpect(jsonPath("$.audio").value(Base64Utils.encodeToString(DEFAULT_AUDIO)));
    }

    @Test
    @Transactional
    public void getNonExistingVocabulary() throws Exception {
        // Get the vocabulary
        restVocabularyMockMvc.perform(get("/api/vocabularies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVocabulary() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);
        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();

        // Update the vocabulary
        Vocabulary updatedVocabulary = vocabularyRepository.findOne(vocabulary.getId());
        // Disconnect from session so that the updates on updatedVocabulary are not directly saved in db
        em.detach(updatedVocabulary);
        updatedVocabulary
            .japanese(UPDATED_JAPANESE)
            .english(UPDATED_ENGLISH)
            .vietnamese(UPDATED_VIETNAMESE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .audio(UPDATED_AUDIO)
            .audioContentType(UPDATED_AUDIO_CONTENT_TYPE);
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(updatedVocabulary);

        restVocabularyMockMvc.perform(put("/api/vocabularies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isOk());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
        Vocabulary testVocabulary = vocabularyList.get(vocabularyList.size() - 1);
        assertThat(testVocabulary.getJapanese()).isEqualTo(UPDATED_JAPANESE);
        assertThat(testVocabulary.getEnglish()).isEqualTo(UPDATED_ENGLISH);
        assertThat(testVocabulary.getVietnamese()).isEqualTo(UPDATED_VIETNAMESE);
        assertThat(testVocabulary.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testVocabulary.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testVocabulary.getAudio()).isEqualTo(UPDATED_AUDIO);
        assertThat(testVocabulary.getAudioContentType()).isEqualTo(UPDATED_AUDIO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingVocabulary() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();

        // Create the Vocabulary
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVocabularyMockMvc.perform(put("/api/vocabularies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isCreated());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVocabulary() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);
        int databaseSizeBeforeDelete = vocabularyRepository.findAll().size();

        // Get the vocabulary
        restVocabularyMockMvc.perform(delete("/api/vocabularies/{id}", vocabulary.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vocabulary.class);
        Vocabulary vocabulary1 = new Vocabulary();
        vocabulary1.setId(1L);
        Vocabulary vocabulary2 = new Vocabulary();
        vocabulary2.setId(vocabulary1.getId());
        assertThat(vocabulary1).isEqualTo(vocabulary2);
        vocabulary2.setId(2L);
        assertThat(vocabulary1).isNotEqualTo(vocabulary2);
        vocabulary1.setId(null);
        assertThat(vocabulary1).isNotEqualTo(vocabulary2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VocabularyDTO.class);
        VocabularyDTO vocabularyDTO1 = new VocabularyDTO();
        vocabularyDTO1.setId(1L);
        VocabularyDTO vocabularyDTO2 = new VocabularyDTO();
        assertThat(vocabularyDTO1).isNotEqualTo(vocabularyDTO2);
        vocabularyDTO2.setId(vocabularyDTO1.getId());
        assertThat(vocabularyDTO1).isEqualTo(vocabularyDTO2);
        vocabularyDTO2.setId(2L);
        assertThat(vocabularyDTO1).isNotEqualTo(vocabularyDTO2);
        vocabularyDTO1.setId(null);
        assertThat(vocabularyDTO1).isNotEqualTo(vocabularyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vocabularyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vocabularyMapper.fromId(null)).isNull();
    }
}
