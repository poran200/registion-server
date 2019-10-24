package com.aj.grade.gradeentries.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"sectionID"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Course.class)

public class Section {

    private static final long serialVersionUID = 1L;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {PERSIST, MERGE,
                    CascadeType.REFRESH, DETACH})
    @JoinColumn(name = "course_id")
     @JsonIgnoreProperties("sectionList")
     private Course course;
//    private String courseCode;
//    private String courseTitle;
    private String faculty;
    private int section_number;
    private int capacity;
    private int semester_id;
    @Id
    private String sectionID;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "section_student",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnoreProperties("sections")
    private List<StudentInfo> studentInfos;


    public void addStudent(StudentInfo tempStudentInfo) {
        if (studentInfos == null) {
            studentInfos = new ArrayList<>();
        }
        studentInfos.add(tempStudentInfo);

    }


//    public Section(Course course, String faculty, int section_number, int capacity, int semester_id) {
//        this.course = course;
//        this.faculty = faculty;
//        this.section_number = section_number;
//        this.capacity = capacity;
//        this.semester_id = semester_id;
//    }


    public Section( Course course,String faculty, int section_number, int capacity, int semester_id, String sectionID) {
        this.course = course;
        this.faculty = faculty;
        this.section_number = section_number;
        this.capacity = capacity;
        this.semester_id = semester_id;
        this.sectionID = sectionID;
    }


}
