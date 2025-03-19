package com.example.webkh.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webkh.Model.Lesson;
import com.example.webkh.Repository.LessonRepository;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Integer id) {
        return lessonRepository.findById(id).orElse(null);
    }

    public List<Lesson> findLessonByCourseId(Integer id) {
        return lessonRepository.findLessonByCourseId(id);
    }

    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void deleteById(Integer id) {
        lessonRepository.deleteById(id);
    }
}
