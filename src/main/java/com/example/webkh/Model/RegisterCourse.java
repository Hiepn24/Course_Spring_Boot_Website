package com.example.webkh.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "register_courses")
public class RegisterCourse {
    @Id
    @Column(name = "rc_id")
    private int id;

    @Column(name = "rc_status")
    private int rcStatus;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="course_id",referencedColumnName = "course_id")
    private Course course;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public RegisterCourse() {

    }

    public RegisterCourse(int id, int rcStatus) {
        this.id = id;
        this.rcStatus = rcStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRcStatus() {
        return rcStatus;
    }

    public void setRcStatus(int rcStatus) {
        this.rcStatus = rcStatus;
    }

    @Override
    public String toString() {
        return "RegisterCourse [id=" + id + ", rcStatus=" + rcStatus + "]";
    }
    
}
