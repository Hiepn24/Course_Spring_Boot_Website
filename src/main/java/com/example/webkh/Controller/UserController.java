package com.example.webkh.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webkh.Model.Category;
import com.example.webkh.Model.Course;
import com.example.webkh.Model.Lesson;
import com.example.webkh.Model.RegisterCourse;
import com.example.webkh.Model.User;
import com.example.webkh.Service.CategoryService;
import com.example.webkh.Service.CourseService;
import com.example.webkh.Service.LessonService;
import com.example.webkh.Service.RcService;
import com.example.webkh.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private RcService rcService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model theModel) {
        List<Category> categories = categoryService.findAll();
        theModel.addAttribute("category", categories);

        List<Course> course = courseService.findAll();
        theModel.addAttribute("course", course);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model theModel) {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("userSession", user.getEmail());
            session.setAttribute("userIdSession", user.getId());
            return "redirect:/home";
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("userSession");
        return "redirect:/login";
    }

    @GetMapping("/listCourse")
    public String listCourse(
        @RequestParam(value = "categoryId", required = false) int categoryId, 
        Model theModel
    ) {
        List<Course> courses = courseService.findCourseByCateId(categoryId);
        theModel.addAttribute("course", courses);
        return "listCourse";
    }

    @GetMapping("/findCourse") 
    public String findCourse(
        @RequestParam(value = "key", required = false) String key,
        Model theModel
    ) {
        List<Course> courses = courseService.findCourseByKey(key);
        theModel.addAttribute("course", courses);
        return "findCourse";
    }
    // @GetMapping("/listCourse")
    // public String listCourse(
    //     @RequestParam(value = "key", required = false) String key,
    //     @RequestParam(value = "categoryId", required = false) int categoryId, 
    //     Model theModel
    // ) {
    //     List<Course> courses = null;
    //     if (null != key) {
    //         courses = courseService.findCourseByKey(key);
    //         theModel.addAttribute("message", "Tìm kiếm khóa học với từ khóa là: " +key);
    //     } else {
    //         courses = courseService.findCourseByCateId(categoryId);
    //     }
    //     theModel.addAttribute("course", courses);
    //     return "listCourse";
    // }

    @GetMapping("/detailCourse") 
    public String detailCourse(@RequestParam("courseId") int courseId, Model theModel, HttpSession session) {
        Course course = courseService.findById(courseId);
        theModel.addAttribute("course", course);

        // Lấy userId từ session
        Integer userId = (Integer) session.getAttribute("userIdSession");
        List<Object[]> rc = rcService.findRcUser(userId, courseId);
        theModel.addAttribute("rc", rc);
        return "detailCourse";
    }

    @GetMapping("/lesson") 
    public String lesson(@RequestParam("courseId") int courseId, Model theModel) {
        List<Lesson> lessons = lessonService.findLessonByCourseId(courseId);
        theModel.addAttribute("lesson", lessons);
        return "lesson";
    }

    @PostMapping("/registerCourse")
    public String registerCourse(
        @RequestParam("courseId") int courseId,
        Model theModel,
        HttpSession session
    ) {
        Integer userId = (Integer) session.getAttribute("userIdSession");
        User user = userService.findById(userId);
        Course course = courseService.findById(courseId);
        RegisterCourse newRegistration = new RegisterCourse();
        newRegistration.setCourse(course);  // Gán course_id
        newRegistration.setUser(user);     // Gán user_id
        newRegistration.setRcStatus(0);        // Trạng thái ban đầu là "Chờ xác nhận"
        // newRegistration.setRcStatus(rcId);        // Trạng thái ban đầu là "Chờ xác nhận"
        rcService.save(newRegistration);
        theModel.addAttribute("course", course);
        return "redirect:/detailCourse?courseId=" + courseId;
    }

}
