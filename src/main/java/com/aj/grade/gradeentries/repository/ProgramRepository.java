package com.aj.grade.gradeentries.repository;

import com.aj.grade.gradeentries.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProgramRepository extends JpaRepository<Program, String> {

}
