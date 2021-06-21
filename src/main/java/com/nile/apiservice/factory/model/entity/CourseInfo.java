package com.nile.apiservice.factory.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "rest", name = "t_course_info")
@Getter
@Setter
public class CourseInfo {
    @Id
    @Column(name = "content_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @JsonIgnore
    @ManyToMany(mappedBy = "courseinfos") // 변수명!!
    private Set<Course> courses = new HashSet<>();

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
