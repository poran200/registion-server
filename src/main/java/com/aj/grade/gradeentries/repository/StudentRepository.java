package com.aj.grade.gradeentries.repository;

import com.aj.grade.gradeentries.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentInfo,  String> {


}
