package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;
    //add quiz service
    @PostMapping("/")
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update Quiz
    @PutMapping("/")

    public ResponseEntity<Quiz> update(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.upDateQuiz(quiz));
    }

    //get all quiz
    @GetMapping("/")
    public ResponseEntity<?> quizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }
    //get quiz
    @GetMapping("/{quiz_id}")
    public Quiz quiz(@PathVariable("quiz_id") Long quiz_id){
        return this.quizService.getQuiz(quiz_id);
    }

    //delete quiz
    @DeleteMapping("/{quiz_id}")
    public void deleteQuiz(@PathVariable("quiz_id") Long quiz_id){
        this.quizService.deleteQuiz(quiz_id);
    }

    @GetMapping("/category/{category_id}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("category_id") Long category_id){
        Category category = new Category();
        category.setCategory_id(category_id);
        return this.quizService.getQuizzesOfCategory(category);
    }

    //get active quizzes
    @GetMapping("/active")
    public List<Quiz>getActiveQuizzes()
    {
        return this.quizService.getActiveQuizzes();
    }

    //get active quizzes Of Category
    @GetMapping("/category/active/{category_id}")
    public List<Quiz>getActiveQuizzesOfCategory(@PathVariable ("category_id") Long category_id)
    {
        Category category = new Category();
        category.setCategory_id(category_id);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }


}
