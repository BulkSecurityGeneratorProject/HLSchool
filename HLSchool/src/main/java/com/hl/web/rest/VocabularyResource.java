package com.hl.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hl.service.VocabularyService;
import com.hl.web.rest.errors.BadRequestAlertException;
import com.hl.web.rest.util.HeaderUtil;
import com.hl.web.rest.util.PaginationUtil;
import com.hl.service.dto.VocabularyDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Vocabulary.
 */
@RestController
@RequestMapping("/api")
public class VocabularyResource {

    private final Logger log = LoggerFactory.getLogger(VocabularyResource.class);

    private static final String ENTITY_NAME = "vocabulary";

    private final VocabularyService vocabularyService;

    public VocabularyResource(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }

    /**
     * POST  /vocabularies : Create a new vocabulary.
     *
     * @param vocabularyDTO the vocabularyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vocabularyDTO, or with status 400 (Bad Request) if the vocabulary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vocabularies")
    @Timed
    public ResponseEntity<VocabularyDTO> createVocabulary(@Valid @RequestBody VocabularyDTO vocabularyDTO) throws URISyntaxException {
        log.debug("REST request to save Vocabulary : {}", vocabularyDTO);
        if (vocabularyDTO.getId() != null) {
            throw new BadRequestAlertException("A new vocabulary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VocabularyDTO result = vocabularyService.save(vocabularyDTO);
        return ResponseEntity.created(new URI("/api/vocabularies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vocabularies : Updates an existing vocabulary.
     *
     * @param vocabularyDTO the vocabularyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vocabularyDTO,
     * or with status 400 (Bad Request) if the vocabularyDTO is not valid,
     * or with status 500 (Internal Server Error) if the vocabularyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vocabularies")
    @Timed
    public ResponseEntity<VocabularyDTO> updateVocabulary(@Valid @RequestBody VocabularyDTO vocabularyDTO) throws URISyntaxException {
        log.debug("REST request to update Vocabulary : {}", vocabularyDTO);
        if (vocabularyDTO.getId() == null) {
            return createVocabulary(vocabularyDTO);
        }
        VocabularyDTO result = vocabularyService.save(vocabularyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vocabularyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vocabularies : get all the vocabularies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vocabularies in body
     */
    @GetMapping("/vocabularies")
    @Timed
    public ResponseEntity<List<VocabularyDTO>> getAllVocabularies(Pageable pageable) {
        log.debug("REST request to get a page of Vocabularies");
        Page<VocabularyDTO> page = vocabularyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vocabularies");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /vocabularies/:id : get the "id" vocabulary.
     *
     * @param id the id of the vocabularyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vocabularyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vocabularies/{id}")
    @Timed
    public ResponseEntity<VocabularyDTO> getVocabulary(@PathVariable Long id) {
        log.debug("REST request to get Vocabulary : {}", id);
        VocabularyDTO vocabularyDTO = vocabularyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(vocabularyDTO));
    }

    /**
     * DELETE  /vocabularies/:id : delete the "id" vocabulary.
     *
     * @param id the id of the vocabularyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vocabularies/{id}")
    @Timed
    public ResponseEntity<Void> deleteVocabulary(@PathVariable Long id) {
        log.debug("REST request to delete Vocabulary : {}", id);
        vocabularyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/vocabularies?query=:query : search for the vocabulary corresponding
     * to the query.
     *
     * @param query the query of the vocabulary search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/vocabularies")
    @Timed
    public ResponseEntity<List<VocabularyDTO>> searchVocabularies(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Vocabularies for query {}", query);
        Page<VocabularyDTO> page = vocabularyService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/vocabularies");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
