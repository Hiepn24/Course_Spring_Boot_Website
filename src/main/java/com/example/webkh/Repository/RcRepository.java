package com.example.webkh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.webkh.Model.RegisterCourse;

public interface RcRepository extends JpaRepository<RegisterCourse, Integer> {
    @Query(
        value = "SELECT register_courses.rc_id, register_courses.rc_status, user.user_id, user.email, course.course_name\n" +//
                "FROM register_courses\n" +//
                "JOIN user ON register_courses.user_id = user.user_id\n" +//
                "JOIN course ON register_courses.course_id = course.course_id", nativeQuery = true)
    List<Object[]> findRcAll();

    @Query(
        value = "SELECT register_courses.rc_id, register_courses.rc_status, user.user_id, user.email, course.course_name\n" +//
                "FROM register_courses\n" +//
                "JOIN user ON register_courses.user_id = user.user_id\n" +//
                "JOIN course ON register_courses.course_id = course.course_id\n" +//
                "WHERE register_courses.rc_id = :rcId", nativeQuery = true)
    List<Object[]> findRcDetail(@Param("rcId") int rcId);

    @Query(
        value = "SELECT register_courses.rc_id, register_courses.rc_status, user.user_id, user.email, course.course_name, course.course_id\n" +//
                "FROM register_courses\n" +//
                "JOIN user ON register_courses.user_id = user.user_id\n" +//
                "JOIN course ON register_courses.course_id = course.course_id\n" +//
                "WHERE user.user_id = :userId AND course.course_id = :courseId", nativeQuery = true)
    List<Object[]> findRcUser(@Param("userId") int userId, @Param("courseId") int courseId);
}
