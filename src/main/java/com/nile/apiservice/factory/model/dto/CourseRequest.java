package com.nile.apiservice.factory.model.dto;

import java.util.Set;

import com.nile.apiservice.factory.model.entity.CourseInfo;

public class CourseRequest {
    public int id;
    public String coursename;
    public Set<CourseInfo> courseinfos;
}
