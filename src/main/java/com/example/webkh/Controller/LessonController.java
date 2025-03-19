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

import com.example.webkh.Model.Course;
import com.example.webkh.Model.Lesson;
import com.example.webkh.Service.CourseService;
import com.example.webkh.Service.LessonService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;

    private static String UPLOADED_FOLDER = "src/main/resources/static/uploads/lesson/";

    @GetMapping("/lessonAdmin")
    public String lessonAdmin(Model theModel) {
        List<Lesson> lesson = lessonService.findAll();
        theModel.addAttribute("lesson", lesson);
        return "Admin/Lesson/index";
    }

    @GetMapping("/formAddLessonAdmin") 
    public String formAddLessonAdmin(Model theModel) {
        Lesson lesson = new Lesson();
        theModel.addAttribute("lesson", lesson);

        List<Course> course = courseService.findAll();
        theModel.addAttribute("course", course);
        return "Admin/Lesson/addLesson";
    }

    @PostMapping("/addLesson")
    public String addLesson(
        @ModelAttribute("lesson") Lesson lesson,
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
                lesson.setLessonVideo("/uploads/lesson/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/lessonAdmin";
            }
        }
        lessonService.save(lesson);
        return "redirect:/lessonAdmin";
    }

    @GetMapping("/formUpdateLessonAdmin")
    public String formUpdateLessonAdmin(@RequestParam("lessonId") int lessonId, Model theModel) {
        Lesson lesson = lessonService.findById(lessonId);
        theModel.addAttribute("lesson", lesson);

        List<Course> course = courseService.findAll();
        theModel.addAttribute("course", course);
        return "Admin/Lesson/updateLesson";
    }

    @GetMapping("/deleteLesson")
    public String deleteLesson(@RequestParam("lessonId") int lessonId) {
        lessonService.deleteById(lessonId);
        return "redirect:/lessonAdmin";
    }
}
