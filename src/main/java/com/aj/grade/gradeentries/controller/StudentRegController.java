package com.aj.grade.gradeentries.controller;

import com.aj.grade.gradeentries.exception.ResourceAlreadyExistException;
import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.exception.ScetionFullException;
import com.aj.grade.gradeentries.model.StudentInfo;
import com.aj.grade.gradeentries.services.StudentRegService;
import com.aj.grade.gradeentries.services.StudentinfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/registration")
public class StudentRegController {

    private StudentRegService studentRegService;
    private StudentinfoService studentinfoService;

    public StudentRegController(StudentRegService studentRegService, StudentinfoService studentinfoService) {
        this.studentRegService = studentRegService;
        this.studentinfoService = studentinfoService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<StudentInfo>> findAllStudent() {

        return ResponseEntity.ok().body(studentRegService.findallStudent());
    }

    @PostMapping(value = "/{sectionId}")
    public ResponseEntity<StudentInfo> registrationStudent(@PathVariable String sectionId, @RequestBody StudentInfo studentInfo) {
        try {
            return ResponseEntity.ok().body(studentRegService.registrationStudent(sectionId, studentInfo));
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ScetionFullException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(value = "/{sectionId}")
    public ResponseEntity<StudentInfo> dopSection(@PathVariable String sectionId, @RequestBody StudentInfo studentInfo) {
        try {
            studentRegService.dropScetion(sectionId, studentInfo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}


