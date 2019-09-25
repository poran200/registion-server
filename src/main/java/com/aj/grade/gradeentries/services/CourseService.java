package com.aj.grade.gradeentries.services;

import com.aj.grade.gradeentries.exception.ResourceAlreadyExistException;
import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.model.Course;
import com.aj.grade.gradeentries.model.Program;
import com.aj.grade.gradeentries.repository.CourseRepository;
import com.aj.grade.gradeentries.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProgramRepository programRepository;

    public Course creatCourse(String programId, Course course) throws ResourseNotFoundException, ResourceAlreadyExistException {
        Program program = programRepository.getOne(programId);
        Optional<Course> tempCourse = courseRepository.findById(course.getCode());
        if (program == null) {
            throw new ResourseNotFoundException(programId + "");
        } else if (tempCourse.isPresent()) {
            throw new ResourceAlreadyExistException(course.getCode() + "");

        }
        program.addCousrse(course);
        programRepository.save(program);
        return courseRepository.save(course);

    }

    public Course findById(String code) throws ResourseNotFoundException {
        Optional<Course> optionalCourse = courseRepository.findById(code);
        if (!optionalCourse.isPresent()) {
            throw new ResourseNotFoundException(code + "");
        }
        return optionalCourse.get();
    }

    public List<Course> findAllCourseByProgram(String programName) {
        return courseRepository.findAllByProgram(programName);
    }


    public List<Course> fnindByCourseTitle(String courseTitle) {
        return courseRepository.findAllByTitleContaining(courseTitle);
    }

    public Course updateCourse(String courseCode, Course course) throws ResourseNotFoundException {
        Optional<Course> optionalCourse = courseRepository.findById(courseCode);
        if (optionalCourse.isPresent()) {
            return courseRepository.save(course);
        }
        throw new ResourseNotFoundException(courseCode + "");
    }


    public String deleteCourse(String code) throws ResourseNotFoundException {
        Optional<Course> course = courseRepository.findById(code);
        if (course.isPresent()) {
            courseRepository.deleteById(code);
            return code;
        }
        throw new ResourseNotFoundException(code + "");
    }

    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }

}
