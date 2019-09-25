package com.aj.grade.gradeentries.services;

import com.aj.grade.gradeentries.exception.ResourseNotFoundException;
import com.aj.grade.gradeentries.model.Grade;
import com.aj.grade.gradeentries.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    private GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> findall(){
        return gradeRepository.findAll();
    }
    public List<Grade> finByStudentId(long studentId) throws ResourseNotFoundException {
        Optional<Grade> optionalGrade = gradeRepository.findById(studentId);
        if (optionalGrade.isPresent()){
            return gradeRepository.findAllByStudentIdContaining(studentId);
        }
        else {
            throw  new ResourseNotFoundException(optionalGrade.get()+"");
        }
    }

    public  List<Grade> insertList(List<Grade> gradeList){
           List<Grade> sveList = gradeRepository.saveAll(gradeList);
           return sveList;
    }

}
