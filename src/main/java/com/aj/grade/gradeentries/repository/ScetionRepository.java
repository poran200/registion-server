package com.aj.grade.gradeentries.repository;

import com.aj.grade.gradeentries.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ScetionRepository extends JpaRepository<Section, String> {
    List<Section> findAllByCourseCodeContaining(String courseCode);

    List<Section> findAllByFaculty(String faculty);
    List<Section>findAllByCourseProgram(String program);
    List<Section>findAllByCourseCode(String courseCode);


}
