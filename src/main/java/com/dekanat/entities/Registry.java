package com.dekanat.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="registry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "registry", joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Student> students;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "registry", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Lesson> lessons;

    @Column(name = "is_student_present")
    private boolean isStudentPresent;

    @Column(name = "mark")
    private double mark;

}