package com.aj.grade.gradeentries.controller;

import com.aj.grade.gradeentries.exception.ResourceAlreadyExistException;
import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.model.Section;
import com.aj.grade.gradeentries.services.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/section")
public class SectionController {
    private SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping(value = "")
    public List<Section> findallSection() {
        return sectionService.findAll();
    }
    @GetMapping(value = "/program/{programName}")
    public ResponseEntity<List<Section>> findByProgramName(@PathVariable String programName){
        return ResponseEntity.ok().body(sectionService.findAllByProgramName(programName));
    }

    @GetMapping(value = "/coursecode/{courseCode}")
    public ResponseEntity<List<Section>> findBycoursecode(@PathVariable String courseCode) {
        return ResponseEntity.ok().body(sectionService.findallbyCourescode(courseCode));
    }

    @PostMapping(value = "/{courseCode}")
    public ResponseEntity<Section> createSection(@PathVariable String courseCode, @RequestBody Section section) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(sectionService.creatSection(courseCode, section));
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ResourceAlreadyExistException e) {
             return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(value = "/faulty/{faculty}")
    public List<Section> findAllByfaculty(@PathVariable String faculty) {
        return sectionService.findbyfaculty(faculty);
    }

    @PutMapping(value = "/id/{sectionId}")
    public ResponseEntity<Section> updateSection(@PathVariable String sectionId, @RequestBody Section section) {
        try {
            Section section1 = sectionService.updateSection(sectionId, section);
            return ResponseEntity.accepted().body(section1);
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping(value = "/{sectionId}")
    public ResponseEntity<String> deleteSection(@PathVariable String sectionId) {
        try {
            sectionService.deleteScetionbyid(sectionId);
            return ResponseEntity.accepted().body(sectionId + "deleted !");
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
