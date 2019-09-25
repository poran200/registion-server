package com.aj.grade.gradeentries.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentInfo {
    @Id
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {PERSIST, MERGE,
                    CascadeType.REFRESH, DETACH})
    @JoinTable(
            name = "section_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )

    private List<Section> sections;

    public StudentInfo(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
