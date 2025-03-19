package com.example.webkh.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.webkh.Model.Category;
import com.example.webkh.Model.Course;
import com.example.webkh.Service.CategoryService;
import com.example.webkh.Service.CourseService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryService categoryService;

    // Đường dẫn thư mục lưu ảnh vào folder category
    private static String UPLOADED_FOLDER = "src/main/resources/static/uploads/course/";

    @GetMapping("/courseAdmin")
    public String courseAdmin(Model theModel) {
        List<Course> course = courseService.findAll();
        theModel.addAttribute("course", course);

        return "Admin/Course/index";
    }

    @GetMapping("/formAddCourseAdmin") 
    public String formAddCourseAdmin(Model theModel) {
        Course course = new Course();
        theModel.addAttribute("course", course);

        List<Category> category = categoryService.findAll();
        theModel.addAttribute("category", category);

        return "Admin/Course/addCourse";
    }

    @PostMapping("/addCourse")
    public String addCourse(
        @ModelAttribute("course") Course course,
        @RequestParam("file") MultipartFile file,
        HttpServletRequest request
    ) {
        if (!file.isEmpty()) {
            // Lấy tên tệp gốc và thiết lập đường dẫn upload
            String fileName = file.getOriginalFilename();
            String uploadDir = UPLOADED_FOLDER; // Đường dẫn cố định đến thư mục lưu ảnh trong dự án

            try {
                // Lưu tệp vào đường dẫn dự án
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadDir + fileName);
                Files.write(path, bytes);

                // Lưu đường dẫn của file ảnh mới vào database (đường dẫn tương đối)
                course.setCourseImg("/uploads/course/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/courseAdmin";
            }
        }
        courseService.save(course);
        return "redirect:/courseAdmin";
    }

    @GetMapping("/formUpdateCourseAdmin") 
    public String formUpdateCourse(@RequestParam("courseId") int courseId, Model theModel) {
        Course course = courseService.findById(courseId);
        theModel.addAttribute("course", course);

        List<Category> category = categoryService.findAll();
        theModel.addAttribute("category", category);

        return "Admin/Course/updateCourse";
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") int courseId) {
        courseService.deleteById(courseId);
        return "redirect:/courseAdmin";
    }
}
