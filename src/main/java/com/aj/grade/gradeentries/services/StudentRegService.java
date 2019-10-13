package com.aj.grade.gradeentries.services;

import com.aj.grade.gradeentries.exception.ResourceAlreadyExistException;
import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.exception.ScetionFullException;
import com.aj.grade.gradeentries.model.Section;
import com.aj.grade.gradeentries.model.StudentInfo;
import com.aj.grade.gradeentries.repository.ScetionRepository;
import com.aj.grade.gradeentries.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentRegService {

    private final StudentRepository studentRepository;
    private final ScetionRepository scetionRepository;

    public StudentRegService(StudentRepository studentRepository, ScetionRepository scetionRepository) {
        this.studentRepository = studentRepository;
        this.scetionRepository = scetionRepository;
    }

    public List<StudentInfo> findallStudent() {
        return studentRepository.findAll();
    }


    public StudentInfo create(StudentInfo studentInfo) throws ResourceAlreadyExistException {
        Optional<StudentInfo> optionalStudent = studentRepository.findById(studentInfo.getId());
        if (optionalStudent.isPresent()) {
            throw new ResourceAlreadyExistException(optionalStudent.get().getId() + "");
        }
        return studentRepository.save(studentInfo);
    }


    public List<Section> findById( String id) throws ResourseNotFoundException {
        Optional<StudentInfo> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get().getSections();
        } else {
            throw new ResourseNotFoundException(id + "");
        }
    }

    public StudentInfo registrationStudent(String sectionId, StudentInfo studentInfo) throws ResourseNotFoundException, ScetionFullException, ResourceAlreadyExistException {

        Optional<StudentInfo> optionalStudent = studentRepository.findById(studentInfo.getId());
        Section section = this.scetionRepository.getOne(sectionId);
        if (section == null) throw new ResourseNotFoundException(sectionId + "");
        if (section.getStudentInfos().size() <= section.getCapacity()) {
            if (!optionalStudent.isPresent()) {
                StudentInfo studentInfo1 = create(studentInfo);
                section.addStudent(studentInfo1);
                scetionRepository.save(section);
            } else {
                section.addStudent(optionalStudent.get());
                scetionRepository.save(section);
            }
        } else {
            throw new ScetionFullException(sectionId + "");
        }

        return studentRepository.findById(studentInfo.getId()).get();

//          student = new Student(student.getId(),student.getName());
//          Section section= this.scetionRepository.getOne(sectionId);
//          section.addStudent(student);
//          scetionRepository.save(section);

        // return   studentRepository.findById(student.getId()).get();


    }

    public String dropScetion(String scetionId, StudentInfo studentInfo) {
        studentInfo = studentRepository.getOne(studentInfo.getId());
        Section scetion = scetionRepository.getOne(scetionId);
        studentInfo.getSections().remove(scetion);
        return "section dropped";
    }


}
