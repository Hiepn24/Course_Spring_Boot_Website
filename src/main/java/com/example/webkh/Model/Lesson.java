package com.example.webkh.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    private int id;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "lesson_video")
    private String lessonVideo;

    @ManyToOne
    @JoinColumn(name="course_id",referencedColumnName = "course_id")
    private Course course;

    public Lesson() {

    }

    public Lesson(int id, String lessonName, String lessonVideo) {
        this.id = id;
        this.lessonName = lessonName;
        this.lessonVideo = lessonVideo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonVideo() {
        return lessonVideo;
    }

    public void setLessonVideo(String lessonVideo) {
        this.lessonVideo = lessonVideo;
    }

    @Override
    public String toString() {
        return "Lesson [id=" + id + ", lessonName=" + lessonName + ", lessonVideo=" + lessonVideo + "]";
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
}
