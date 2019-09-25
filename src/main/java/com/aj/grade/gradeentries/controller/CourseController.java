package com.aj.grade.gradeentries.controller;

import com.aj.grade.gradeentries.exception.ResourceAlreadyExistException;
import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.model.Course;
import com.aj.grade.gradeentries.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/course")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Course>> findAllCourse() {
        return ResponseEntity.ok().body(courseService.findAllCourse());
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<Course> findByCode(@PathVariable String code) {
        try {
            return ResponseEntity.ok().body(courseService.findById(code));
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = ("/{programName}"))
    public ResponseEntity<Course> createCourse(@PathVariable String programName, @RequestBody Course course) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.creatCourse(programName, course));
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(value = "/programName/{programName}")
    public ResponseEntity<List<Course>> findByCourseByProgram(@PathVariable String programName) {
        return ResponseEntity.ok(courseService.findAllCourseByProgram(programName));
    }

    @GetMapping(value = "/title/{courseTitle}")
    public ResponseEntity<List<Course>> findAllCourseByTitle(@PathVariable String courseTitle) {
        return ResponseEntity.ok().body(courseService.fnindByCourseTitle(courseTitle));
    }

    @PutMapping(value = "/{courseCode}")
    public ResponseEntity<Course> updateCourse(@PathVariable String courseCode, @RequestBody Course course) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(courseService.updateCourse(courseCode, course));
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{courseCode}")
    public ResponseEntity<String> deleteCourse(@PathVariable String courseCode) {
        try {
            return ResponseEntity.accepted().body(courseService.deleteCourse(courseCode));
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
