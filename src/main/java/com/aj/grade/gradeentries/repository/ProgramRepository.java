package com.aj.grade.gradeentries.repository;

import com.aj.grade.gradeentries.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, String> {

}
