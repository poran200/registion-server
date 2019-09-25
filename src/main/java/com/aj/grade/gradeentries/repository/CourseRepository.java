package com.aj.grade.gradeentries.repository;

import com.aj.grade.gradeentries.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findAllByTitleContaining(String courseTitle);

    List<Course> findAllByProgram(String programName);

}
