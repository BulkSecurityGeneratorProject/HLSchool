package com.hl.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hl.domain.User;
import com.hl.repository.UserRepository;
import com.hl.security.SecurityUtils;
import com.hl.service.CourseLogService;
import com.hl.service.CourseService;
import com.hl.service.UserService;
import com.hl.service.dto.CourseDTO;
import com.hl.web.rest.errors.BadRequestAlertException;
import com.hl.web.rest.util.HeaderUtil;
import com.hl.web.rest.util.PaginationUtil;
import com.hl.service.dto.CourseLogDTO;
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
 * REST controller for managing CourseLog.
 */
@RestController
@RequestMapping("/api")
public class CourseLogResource {

    private final Logger log = LoggerFactory.getLogger(CourseLogResource.class);

    private static final String ENTITY_NAME = "courseLog";

    private final CourseLogService courseLogService;

    private final UserRepository userRepository;

    private final CourseService courseService;

    private final UserService userService;

    public CourseLogResource(CourseLogService courseLogService, UserRepository userRepository, CourseService courseService, UserService userService) {
        this.courseLogService = courseLogService;
        this.userRepository = userRepository;
        this.courseService = courseService;
        this.userService = userService;
    }

    /**
     * POST  /course-logs : Create a new courseLog.
     *
     * @param courseLogDTO the courseLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseLogDTO, or with status 400 (Bad Request) if the courseLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-logs")
    @Timed
    public ResponseEntity<CourseLogDTO> createCourseLog(@RequestBody CourseLogDTO courseLogDTO) throws URISyntaxException {
        log.debug("REST request to save CourseLog : {}", courseLogDTO);
        if (courseLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new courseLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<User> user = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
        User _user = user.get();
        CourseDTO courseDTO = this.courseService.findOne(courseLogDTO.getCourseId());
        if(_user != null) {
            if (_user.getCoin() >= courseDTO.getCoin()) {
                courseLogDTO.setUserId(_user.getId());
                CourseLogDTO result = courseLogService.save(courseLogDTO);
                this.userService.plusCoin(-1 * courseDTO.getCoin());

                return ResponseEntity.created(new URI("/api/course-logs/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                    .body(result);
            }
        }
        return ResponseEntity.status(404).body(courseLogDTO);
    }

    /**
     * PUT  /course-logs : Updates an existing courseLog.
     *
     * @param courseLogDTO the courseLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseLogDTO,
     * or with status 400 (Bad Request) if the courseLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the courseLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-logs")
    @Timed
    public ResponseEntity<CourseLogDTO> updateCourseLog(@RequestBody CourseLogDTO courseLogDTO) throws URISyntaxException {
        log.debug("REST request to update CourseLog : {}", courseLogDTO);
        if (courseLogDTO.getId() == null) {
            return createCourseLog(courseLogDTO);
        }
        CourseLogDTO result = courseLogService.save(courseLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-logs : get all the courseLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courseLogs in body
     */
    @GetMapping("/course-logs")
    @Timed
    public ResponseEntity<List<CourseLogDTO>> getAllCourseLogs(Pageable pageable) {
        log.debug("REST request to get a page of CourseLogs");
        Page<CourseLogDTO> page = courseLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /course-logs/:id : get the "id" courseLog.
     *
     * @param id the id of the courseLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/course-logs/{id}")
    @Timed
    public ResponseEntity<CourseLogDTO> getCourseLog(@PathVariable Long id) {
        log.debug("REST request to get CourseLog : {}", id);
        CourseLogDTO courseLogDTO = courseLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseLogDTO));
    }

    /**
     * DELETE  /course-logs/:id : delete the "id" courseLog.
     *
     * @param id the id of the courseLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseLog(@PathVariable Long id) {
        log.debug("REST request to delete CourseLog : {}", id);
        courseLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/course-logs?query=:query : search for the courseLog corresponding
     * to the query.
     *
     * @param query the query of the courseLog search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/course-logs")
    @Timed
    public ResponseEntity<List<CourseLogDTO>> searchCourseLogs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CourseLogs for query {}", query);
        Page<CourseLogDTO> page = courseLogService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/course-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
