package com.aj.grade.gradeentries.controller;

import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.model.Section;
import com.aj.grade.gradeentries.services.StudentRegService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/student")
public class StudentController {
    private StudentRegService studentRegService;

    public StudentController(StudentRegService studentRegService) {
        this.studentRegService = studentRegService;
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<List<Section>> getSection(@PathVariable long studentId) {
        try {
            return ResponseEntity.ok(studentRegService.findById(studentId));
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


}
