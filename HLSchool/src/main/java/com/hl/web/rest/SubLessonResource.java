package com.hl.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hl.service.SubLessonService;
import com.hl.web.rest.errors.BadRequestAlertException;
import com.hl.web.rest.util.HeaderUtil;
import com.hl.web.rest.util.PaginationUtil;
import com.hl.service.dto.SubLessonDTO;
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
 * REST controller for managing SubLesson.
 */
@RestController
@RequestMapping("/api")
public class SubLessonResource {

    private final Logger log = LoggerFactory.getLogger(SubLessonResource.class);

    private static final String ENTITY_NAME = "subLesson";

    private final SubLessonService subLessonService;

    public SubLessonResource(SubLessonService subLessonService) {
        this.subLessonService = subLessonService;
    }

    /**
     * POST  /sub-lessons : Create a new subLesson.
     *
     * @param subLessonDTO the subLessonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subLessonDTO, or with status 400 (Bad Request) if the subLesson has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sub-lessons")
    @Timed
    public ResponseEntity<SubLessonDTO> createSubLesson(@Valid @RequestBody SubLessonDTO subLessonDTO) throws URISyntaxException {
        log.debug("REST request to save SubLesson : {}", subLessonDTO);
        if (subLessonDTO.getId() != null) {
            throw new BadRequestAlertException("A new subLesson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubLessonDTO result = subLessonService.save(subLessonDTO);
        return ResponseEntity.created(new URI("/api/sub-lessons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-lessons : Updates an existing subLesson.
     *
     * @param subLessonDTO the subLessonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subLessonDTO,
     * or with status 400 (Bad Request) if the subLessonDTO is not valid,
     * or with status 500 (Internal Server Error) if the subLessonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sub-lessons")
    @Timed
    public ResponseEntity<SubLessonDTO> updateSubLesson(@Valid @RequestBody SubLessonDTO subLessonDTO) throws URISyntaxException {
        log.debug("REST request to update SubLesson : {}", subLessonDTO);
        if (subLessonDTO.getId() == null) {
            return createSubLesson(subLessonDTO);
        }
        SubLessonDTO result = subLessonService.save(subLessonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subLessonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sub-lessons : get all the subLessons.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of subLessons in body
     */
    @GetMapping("/sub-lessons")
    @Timed
    public ResponseEntity<List<SubLessonDTO>> getAllSubLessons(Pageable pageable) {
        log.debug("REST request to get a page of SubLessons");
        Page<SubLessonDTO> page = subLessonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sub-lessons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/sublessonsByLessonId/{id}")
    @Timed
    public ResponseEntity<List<SubLessonDTO>> getSubLessonsByLessonId(@PathVariable Long id, Pageable pageable) {
        log.debug("REST request to get a page of SubLessons");
        Page<SubLessonDTO> page = subLessonService.findSubLessonsByLessonId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sub-lessons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sub-lessons/:id : get the "id" subLesson.
     *
     * @param id the id of the subLessonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subLessonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sub-lessons/{id}")
    @Timed
    public ResponseEntity<SubLessonDTO> getSubLesson(@PathVariable Long id) {
        log.debug("REST request to get SubLesson : {}", id);
        SubLessonDTO subLessonDTO = subLessonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(subLessonDTO));
    }

    /**
     * DELETE  /sub-lessons/:id : delete the "id" subLesson.
     *
     * @param id the id of the subLessonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sub-lessons/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubLesson(@PathVariable Long id) {
        log.debug("REST request to delete SubLesson : {}", id);
        subLessonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sub-lessons?query=:query : search for the subLesson corresponding
     * to the query.
     *
     * @param query the query of the subLesson search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/sub-lessons")
    @Timed
    public ResponseEntity<List<SubLessonDTO>> searchSubLessons(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of SubLessons for query {}", query);
        Page<SubLessonDTO> page = subLessonService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sub-lessons");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
