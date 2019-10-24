package com.aj.grade.gradeentries.repository;

import com.aj.grade.gradeentries.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<StudentInfo,  String> {


}
