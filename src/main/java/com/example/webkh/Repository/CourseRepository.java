package com.example.webkh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.webkh.Model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "SELECT * FROM course WHERE category_id = :categoryId", nativeQuery = true)
    List<Course> findCourseByCategoryId(@Param("categoryId") int categoryId);

    @Query(value = "SELECT * FROM course WHERE course_name like CONCAT('%', :courseName, '%')", nativeQuery = true)
    List<Course> findCourseByKey(@Param("courseName") String courseName);
}
