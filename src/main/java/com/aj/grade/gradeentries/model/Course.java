package com.aj.grade.gradeentries.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Section.class)

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


    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("course")
    private List<Section> sectionList;



    public Course(String code, @NotNull String title, @NotNull int credit, String program) {
        this.code = code;
        this.title = title;
        this.credit = credit;
        this.program = program;

    }


    public void addSection(Section section) {
        if (sectionList == null)
            sectionList = new ArrayList<>();
             sectionList.add(section);
    }

}
