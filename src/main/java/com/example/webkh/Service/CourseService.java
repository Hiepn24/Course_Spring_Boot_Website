package com.example.webkh.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webkh.Model.Course;
import com.example.webkh.Repository.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }

    public List<Course> findCourseByCateId(Integer id) {
        return courseRepository.findCourseByCategoryId(id);
    }

    public List<Course> findCourseByKey(String key) {
        return courseRepository.findCourseByKey(key);
    }
}
