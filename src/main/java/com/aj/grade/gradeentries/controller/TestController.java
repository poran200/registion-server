package com.aj.grade.gradeentries.controller;

import com.aj.grade.gradeentries.model.Course;
import com.aj.grade.gradeentries.model.Program;
import com.aj.grade.gradeentries.model.Section;
import com.aj.grade.gradeentries.model.StudentInfo;
import com.aj.grade.gradeentries.repository.CourseRepository;
import com.aj.grade.gradeentries.repository.ProgramRepository;
import com.aj.grade.gradeentries.repository.ScetionRepository;
import com.aj.grade.gradeentries.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScetionRepository scetionRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private StudentRepository studentRepository;

    private static void accept(Program program) {
        program.getCourses().forEach(course -> course.getCode().getClass());
    }

    @RequestMapping("/course")
    public List<Course> findallcourse() {
//
        Course course = courseRepository.getOne("ACT103");
        Section section = new Section();
        section.setFaculty("kmh");
        section.setSection_number(5);
        section.setCapacity(30);
        section.setSemester_id(50);
        section.setSectionID(course.getCode() + "." + section.getSection_number() + "." + section.getSemester_id());
        //       program.addCousrse(course);
//      List<Section> sections = new ArrayList<>();
//       sections.add(section);
        course.addSection(section);
        courseRepository.save(course);
        scetionRepository.save(section);

        List<Course> list = this.courseRepository.findAll();


        return list;


    }

    @RequestMapping("/program")
    public List<Program> findall() {
        Program program = programRepository.getOne("EEE");
        System.out.println(program);
//        List<Course> courses = new ArrayList<>();
//
        Course course = new Course("EEE1022", "Electronic device", 3, "EEE");
        program.addCousrse(course);

        programRepository.save(program);
        courseRepository.save(course);
        List<Program> programList = this.programRepository.findAll();
        return programList;


    }

    @RequestMapping("/insert")
    public Section addStudent() {

//      Student student = new Student(102,"jalal");
        StudentInfo studentInfo1 = new StudentInfo("110", "ridoy");
        //  Student student = this.studentRepository.getOne(102L);
        Section section = this.scetionRepository.getOne("ACT102.8.50");
        // section.addStudent(student1);
        section.addStudent(studentInfo1);
        // studentRepository.save(student);
        scetionRepository.save(section);

        return section;
    }

    @RequestMapping("/section")
    public List<List<Section>> allStudent() {
        // return this.studentRepository.findAll().stream().map(Student::getSections).collect(Collectors.toList());
        //  Student student= studentRepository.getOne(102L);

        return studentRepository.findAll().stream().map(StudentInfo::getSections).collect(Collectors.toList());

    }

    @RequestMapping("/student")
    public List<List<StudentInfo>> all() {
        return scetionRepository.findAll().stream().map(Section::getStudentInfos).collect(Collectors.toList());
    }
//    @RequestMapping("/student")
//    public List<Student> all(){
//        return studentRepository.findAll();
//    }
}


