package com.example.webkh.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webkh.Model.Category;
import com.example.webkh.Service.CategoryService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin")
    public String admin() {
        return "Admin/index";
    }

    @GetMapping("/categoryAdmin")
    public String cateAdmin(Model theModel) {
        List<Category> category = categoryService.findAll();
        theModel.addAttribute("category", category);
        return "Admin/Category/index";
    }

    @GetMapping("/formAddCateAdmin")
    public String formAddCateAdmin(Model theModel) {
        Category category = new Category();
        theModel.addAttribute("category", category);
        return "Admin/Category/addCate";
    }

    @PostMapping("/addCate")
    public String addCate(
        @ModelAttribute("category") Category category,
        HttpServletRequest request
    ) {
        categoryService.save(category);
        return "redirect:/categoryAdmin";
    }

    @GetMapping("/formUpdateCateAdmin")
    public String formUpdateCateAdmin(@RequestParam("categoryId") int categoryId, Model theModel) {
        Category category = categoryService.findById(categoryId);
        theModel.addAttribute("category", category);
        return "Admin/Category/updateCate";
    }

    @GetMapping("/deleteCate")
    public String delete(@RequestParam("categoryId") int categoryId) {
        categoryService.deleteById(categoryId);
        return "redirect:/categoryAdmin";
    }
}
