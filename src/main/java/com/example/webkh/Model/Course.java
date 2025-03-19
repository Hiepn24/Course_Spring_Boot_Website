package com.example.webkh.Model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "course_id")
    private int id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_img")
    private String courseImg;

    @Column(name = "course_description")
    private String courseDescription;

    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "category_id")
    private Category category;

    @OneToMany(mappedBy = "course")
    private Set<Lesson> lesson;

    @OneToMany(mappedBy = "course")
    private Set<RegisterCourse> registerCourse;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(Set<Lesson> lesson) {
        this.lesson = lesson;
    }

    public Set<RegisterCourse> getRegisterCourse() {
        return registerCourse;
    }

    public void setRegisterCourse(Set<RegisterCourse> registerCourse) {
        this.registerCourse = registerCourse;
    }

    public Course() {

    }

    public Course(int id, String courseName, String courseImg, String courseDescription) {
        this.id = id;
        this.courseName = courseName;
        this.courseImg = courseImg;
        this.courseDescription = courseDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", courseName=" + courseName + ", courseImg=" + courseImg + ", courseDescription="
                + courseDescription + "]";
    }
    
}
