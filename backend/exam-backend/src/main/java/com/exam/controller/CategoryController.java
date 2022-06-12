package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    // add category
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        Category category1 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    //get category
    @GetMapping("/{category_id}")
    public Category getCategory(@PathVariable("category_id") Long category_id){
        return this.categoryService.getCategory(category_id);
    }

    //get All category
    @GetMapping("/")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }


   // upDate category
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return this.categoryService.upDateCategory(category);
    }

    //delete category
    @DeleteMapping("/{category_id}")
    public void deleteCategory(@PathVariable("category_id") Long category_id){
        this.categoryService.deleteCategory(category_id);
    }
}
