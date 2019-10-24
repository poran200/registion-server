package com.aj.grade.gradeentries.services;

import com.aj.grade.gradeentries.exception.ResourceAlreadyExistException;
import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.model.Course;
import com.aj.grade.gradeentries.model.Section;
import com.aj.grade.gradeentries.repository.CourseRepository;
import com.aj.grade.gradeentries.repository.ScetionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionService {

    private ScetionRepository scetionRepository;

    private CourseRepository courseRepository;

    public SectionService(ScetionRepository scetionRepository, CourseRepository courseRepository) {
        this.scetionRepository = scetionRepository;
        this.courseRepository = courseRepository;
    }

    public List<Section> findAll() {

        return scetionRepository.findAll();
    }


    public Section creatSection(String courseCode, Section section) throws ResourseNotFoundException, ResourceAlreadyExistException {
        Course course = courseRepository.getOne(courseCode);
        String sectionId= courseCode+"."+section.getSection_number()+"."+section.getSemester_id();
        Optional<Section> optionalSection= scetionRepository.findById(sectionId);
         // Section sectionTest= scetionRepository.getOne(sectionId);
        if (course == null) {
            throw new ResourseNotFoundException(courseCode + "");
        } else if (optionalSection.isPresent()){
            throw new  ResourceAlreadyExistException(sectionId+"");
        }
         else {
            section.setCourse(course);
            section.setSectionID(course.getCode() + "." + section.getSection_number() + "." + section.getSemester_id());
            course.addSection(section);
            //courseRepository.save(course);
            System.out.println("ok save");
            return scetionRepository.save(section);
        }

    }


    public Section updateSection(String sectionId, Section section) throws ResourseNotFoundException {
        Optional<Section> optionalSection = scetionRepository.findById(sectionId);
        if (optionalSection.isPresent()) {
            section.setSectionID(section.getCourse().getCode() + "." + section.getSection_number() + "." + section.getSemester_id());
            return scetionRepository.save(section);
        } else {
            throw new ResourseNotFoundException(sectionId + "");
        }

    }

    public List<Section> findallbyCourescode(String courseCode) {

        return scetionRepository.findAllByCourseCode(courseCode);
    }

    public void deleteScetionbyid(String sectionId) throws ResourseNotFoundException {
        Optional<Section> section = scetionRepository.findById(sectionId);
        if (section.isPresent()) {
            scetionRepository.deleteById(sectionId);
        } else {
            throw new ResourseNotFoundException(sectionId + "");
        }

    }

    public List<Section> findAllByProgramName(String programName){
        return scetionRepository.findAllByCourseProgram(programName);
    }

    public List<Section> findbyfaculty(String faculty) {
        return scetionRepository.findAllByFaculty(faculty);
    }


}
