package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;
    //add question
    @PostMapping("/")
    public ResponseEntity<Question>add(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //update question
    @PutMapping("/")
    public ResponseEntity<Question>upDate(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.upDateQuestion(question));
    }

    //get all question of any question id
    @GetMapping("/quiz/{quiz_id}")
    public ResponseEntity<?>getQuestionOfQuiz(@PathVariable("quiz_id") Long quiz_id){
//        Quiz quiz = new Quiz();
//        quiz.setQuiz_id(quiz_id);
//        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);
        Quiz quiz = this.quizService.getQuiz(quiz_id);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList(questions);
        if (list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
            list =list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        list.forEach((q)->{
            q.setAnswer("");
        });
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //get all question of any question id add to admin
    @GetMapping("/quiz/all/{quiz_id}")
    public ResponseEntity<?>getQuestionOfQuizAdmin(@PathVariable("quiz_id") Long quiz_id){
        Quiz quiz = new Quiz();
        quiz.setQuiz_id(quiz_id);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);
//        Quiz quiz = this.quizService.getQuiz(quiz_id);
//        Set<Question> questions = quiz.getQuestions();
//        List list = new ArrayList(questions);
//        if (list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
//            list =list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
//        }
//        Collections.shuffle(list);
//        return ResponseEntity.ok(list);
    }

    //get Single question
    @GetMapping("/{quiz_id}")
    public Question get(@PathVariable("quiz_id") Long quiz_id){
        return this.questionService.getQuestion(quiz_id);
    }

    //delete question
    @DeleteMapping("/{quiz_id}")
    public void delete(@PathVariable("quiz_id") Long quiz_id){
        this.questionService.deleteQuestion(quiz_id);
    }

    //direct Submit
    @PostMapping("/direct-quiz")
    public ResponseEntity<?> directQuiz(@RequestBody List<Question> questions){
        System.out.println(questions);
        int attempted = 0;
        double marksGot = 0;
        int correctAnswers = 0;
        for(Question q: questions){
               System.out.println(q.getGivenAnswer());
            //single questions
            Question question = this.questionService.get(q.getQuestion_id());

            if (question.getAnswer().equals(q.getGivenAnswer())){
                correctAnswers++;
                double markSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                        // this.question[0].quiz.maxMarks / this.question.length;
                     marksGot += markSingle;
            }

            if( q.getGivenAnswer()!=null||!q.getGivenAnswer().equals("")) {
                attempted++;
            }
        };

        Map<String,Object> result=new HashMap<>();

        result.put("marksGot",marksGot);
        result.put("correctAnswers",correctAnswers);
        result.put("attempted",attempted);

        return ResponseEntity.ok(result);
    }
}
