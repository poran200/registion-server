package com.aj.grade.gradeentries.controller;

import com.aj.grade.gradeentries.exception.ResourceAlreadyExistException;
import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.model.Program;
import com.aj.grade.gradeentries.services.ProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/program")
public class ProgramController {

    private ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Program>> findAll() {
        return ResponseEntity.ok().body(programService.findall());
    }

    @PostMapping(value = "")
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(programService.createProgram(program));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

   @GetMapping(value = "/{programName}")
    public ResponseEntity<Program> getProgramById(@PathVariable String programName) {
        try {
            return ResponseEntity.ok().body(programService.findById(programName));
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{programName}")
    public ResponseEntity<String> deleteProgramById(@PathVariable String programName) throws ResourseNotFoundException {

        try {
            programService.deleteProgram(programName);
            return ResponseEntity.ok(programName);
        } catch (ResourseNotFoundException e) {
            throw new ResourseNotFoundException(programName + "");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{programName}")
    public ResponseEntity<Program> updateProgram(@PathVariable String programName, @RequestBody Program program) {
        try {
            return ResponseEntity.accepted().body(programService.updateProgram(programName, program));
        } catch (ResourseNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
