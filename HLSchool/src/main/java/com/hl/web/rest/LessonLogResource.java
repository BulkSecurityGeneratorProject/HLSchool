package com.hl.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hl.service.LessonLogService;
import com.hl.web.rest.errors.BadRequestAlertException;
import com.hl.web.rest.util.HeaderUtil;
import com.hl.web.rest.util.PaginationUtil;
import com.hl.service.dto.LessonLogDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing LessonLog.
 */
@RestController
@RequestMapping("/api")
public class LessonLogResource {

    private final Logger log = LoggerFactory.getLogger(LessonLogResource.class);

    private static final String ENTITY_NAME = "lessonLog";

    private final LessonLogService lessonLogService;

    public LessonLogResource(LessonLogService lessonLogService) {
        this.lessonLogService = lessonLogService;
    }

    /**
     * POST  /lesson-logs : Create a new lessonLog.
     *
     * @param lessonLogDTO the lessonLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new lessonLogDTO, or with status 400 (Bad Request) if the lessonLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/lesson-logs")
    @Timed
    public ResponseEntity<LessonLogDTO> createLessonLog(@RequestBody LessonLogDTO lessonLogDTO) throws URISyntaxException {
        log.debug("REST request to save LessonLog : {}", lessonLogDTO);
        if (lessonLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new lessonLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LessonLogDTO result = lessonLogService.save(lessonLogDTO);
        return ResponseEntity.created(new URI("/api/lesson-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lesson-logs : Updates an existing lessonLog.
     *
     * @param lessonLogDTO the lessonLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated lessonLogDTO,
     * or with status 400 (Bad Request) if the lessonLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the lessonLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/lesson-logs")
    @Timed
    public ResponseEntity<LessonLogDTO> updateLessonLog(@RequestBody LessonLogDTO lessonLogDTO) throws URISyntaxException {
        log.debug("REST request to update LessonLog : {}", lessonLogDTO);
        if (lessonLogDTO.getId() == null) {
            return createLessonLog(lessonLogDTO);
        }
        LessonLogDTO result = lessonLogService.save(lessonLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, lessonLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lesson-logs : get all the lessonLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of lessonLogs in body
     */
    @GetMapping("/lesson-logs")
    @Timed
    public ResponseEntity<List<LessonLogDTO>> getAllLessonLogs(Pageable pageable) {
        log.debug("REST request to get a page of LessonLogs");
        Page<LessonLogDTO> page = lessonLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/lesson-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /lesson-logs/:id : get the "id" lessonLog.
     *
     * @param id the id of the lessonLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the lessonLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/lesson-logs/{id}")
    @Timed
    public ResponseEntity<LessonLogDTO> getLessonLog(@PathVariable Long id) {
        log.debug("REST request to get LessonLog : {}", id);
        LessonLogDTO lessonLogDTO = lessonLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(lessonLogDTO));
    }

    /**
     * DELETE  /lesson-logs/:id : delete the "id" lessonLog.
     *
     * @param id the id of the lessonLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/lesson-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteLessonLog(@PathVariable Long id) {
        log.debug("REST request to delete LessonLog : {}", id);
        lessonLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/lesson-logs?query=:query : search for the lessonLog corresponding
     * to the query.
     *
     * @param query the query of the lessonLog search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/lesson-logs")
    @Timed
    public ResponseEntity<List<LessonLogDTO>> searchLessonLogs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of LessonLogs for query {}", query);
        Page<LessonLogDTO> page = lessonLogService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/lesson-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
