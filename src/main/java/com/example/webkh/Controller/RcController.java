package com.example.webkh.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webkh.Model.RegisterCourse;
import com.example.webkh.Service.RcService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RcController {
    @Autowired
    private RcService rcService;

    @GetMapping("/rcAdmin")
    public String rcAdmin(Model theModel) {
        List<Object[]> rc = rcService.findRcAll();
        theModel.addAttribute("rc", rc);
        return "Admin/RC/index";
    }

    @GetMapping("/rcDetail")
    public String rcDetail(@RequestParam("rcId") int rcId, Model theModel) {
        List<Object[]> rc = rcService.findRcDetail(rcId);
        theModel.addAttribute("rc", rc);

        RegisterCourse registerCourse = rcService.findById(rcId);
        theModel.addAttribute("registerCourse", registerCourse);
        
        return "Admin/RC/updateRc";
    }

    @PostMapping("/saveStatus")
    public String saveStatus(
        @ModelAttribute("registerCourse") RegisterCourse registerCourse,
        HttpServletRequest request
    ) {
        rcService.save(registerCourse);
        return "redirect:/rcAdmin";
    }

}
