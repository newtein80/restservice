package com.nile.apiservice.factory.service;

import java.util.stream.Collectors;

import com.nile.apiservice.factory.model.dto.CourseRequest;
import com.nile.apiservice.factory.model.entity.Course;
import com.nile.apiservice.factory.model.entity.CourseInfo;
import com.nile.apiservice.factory.repository.CourseInfoRepository;
import com.nile.apiservice.factory.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseSerivce {
    @Autowired CourseRepository courseRepository;
    @Autowired CourseInfoRepository courseInfoRepository;

    public CourseSerivce() {}

    public Course addCourseWithContents(CourseRequest courseRequest) {
        Course course =  new Course();
        course.setId(courseRequest.id);
        course.setCourcename(courseRequest.coursename);
        course.setCourseinfos(
            courseRequest.courseinfos
            .stream()
            .map(
                courseinfo -> {
                    CourseInfo _courseinfo = courseinfo;
                    if (_courseinfo.getId() > 0) {
                        _courseinfo = this.courseInfoRepository.findById(_courseinfo.getId());
                    }
                    _courseinfo.addCourse(course);
                    return _courseinfo;
                }
            )
            .collect(Collectors.toSet())
        );
        return this.courseRepository.save(course);
    }
}
