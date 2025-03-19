package com.example.webkh.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webkh.Model.RegisterCourse;
import com.example.webkh.Repository.RcRepository;

@Service
public class RcService {
    @Autowired
    private RcRepository rcRepository;

    public List<Object[]> findRcAll() {
        return rcRepository.findRcAll();
    }

    public List<Object[]> findRcDetail(Integer id) {
        return rcRepository.findRcDetail(id);
    }

    public List<Object[]> findRcUser(Integer id, Integer id1) {
        return rcRepository.findRcUser(id, id1);
    }

    public RegisterCourse findById(Integer id) {
        return rcRepository.findById(id).orElse(null);
    }

    public void save(RegisterCourse registerCourse) {
        rcRepository.save(registerCourse);
    }
}
