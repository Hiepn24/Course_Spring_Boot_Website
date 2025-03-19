package com.example.webkh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.webkh.Model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query(value = "SELECT * FROM lesson WHERE course_id = :courseId", nativeQuery = true)
    List<Lesson> findLessonByCourseId(@Param("courseId") int courseId);
}
