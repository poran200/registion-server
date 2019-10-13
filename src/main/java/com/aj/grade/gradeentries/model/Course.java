package com.aj.grade.gradeentries.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"code"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Program.class)

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String code;
    @NotNull
    private String title;
    @NotNull
    private int credit;
    @NotNull
    private String program;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
   // @JsonIgnoreProperties(value = "sectionList")
    private List<Section> sectionList;

    public Course(String code, String title) {
    }

    public Course(String code, @NotNull String title, @NotNull int credit, String program) {
        this.code = code;
        this.title = title;
        this.credit = credit;
        this.program = program;

    }

    //    @Transactional
//    public void addSection(Section section) {
//        if (sectionList == null)
//            sectionList = new ArrayList<>();
//            section.setCourse(new Course(code,title));
//        sectionList.add(section);
//    }
    public void addSection(Section section) {
        if (sectionList == null)
            sectionList = new ArrayList<>();
        section.setCourseCode(code);
        section.setCourseTitle(title);
        sectionList.add(section);
    }

}
