package com.controller;

import com.model.Blog;
import com.model.Category;
import com.service.BlogService;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
@RestController
public class APICategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/category")
    public ModelAndView listCategory() {
        Iterable<Category> categories = categoryService.findAll();
        return new ModelAndView("/category/list", "categories", categories);
    }

    @GetMapping("/create-category")
    public ModelAndView showCreateForm() {
        return new ModelAndView("/category/create", "category", new Category());
    }

    @PostMapping("/create-category")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);

        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Category> category = Optional.ofNullable(categoryService.findById(id));
        if (category != null) {
            return new ModelAndView("/category/edit", "category", category);
        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/edit-category")
    public ModelAndView editCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);

        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Update category successfully");
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Category> category = Optional.ofNullable(categoryService.findById(id));
        if (category != null) {
            return new ModelAndView("/category/delete", "category", category);
        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/delete-category")
    public String deleteCategory(@ModelAttribute("category") Category category){
        categoryService.remove(category.getId());
        return "redirect:category";
    }

    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ModelAndView("/error");
        }
        Iterable<Blog> blogs = blogService.findAllByCategory(category);
        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
}
