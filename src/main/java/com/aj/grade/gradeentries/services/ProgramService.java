package com.aj.grade.gradeentries.services;

import com.aj.grade.gradeentries.exception.ResourceAlreadyExistException;
import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.model.Program;
import com.aj.grade.gradeentries.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;

    public List<Program> findall() {
        return programRepository.findAll();
    }

    public Program createProgram(Program program) throws ResourceAlreadyExistException {
        Optional<Program> optionalProgram = programRepository.findById(program.getProgram_title());
        if (optionalProgram.isPresent()) {
            throw new ResourceAlreadyExistException(program.getProgram_title() + "");
        }
        return programRepository.save(program);
    }

    public Program findById(String programName) throws ResourseNotFoundException {
        Optional<Program> optionalProgram = programRepository.findById(programName);
        if (optionalProgram.isPresent()) {
            return optionalProgram.get();
        } else {
            throw new ResourseNotFoundException(programName + "");
        }
    }

    public Program updateProgram(String programName, Program program) throws ResourseNotFoundException {
        Optional<Program> optionalProgram = programRepository.findById(programName);
        if (optionalProgram.isPresent()) {
            program.setProgram_title(programName);
            return programRepository.save(program);
        } else {
            throw new ResourseNotFoundException(programName + "");
        }
    }

    public void deleteProgram(String programName) throws ResourseNotFoundException {
        Optional<Program> optionalProgram = programRepository.findById(programName);
        if (optionalProgram.isPresent()) {
            programRepository.deleteById(programName);
        } else {
            throw new ResourseNotFoundException(programName + "");
        }
    }
}

