package com.exam.service;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Quiz upDateQuiz(Quiz quiz);
    public Set <Quiz> getQuizzes();
    public Quiz getQuiz(Long quiz_id);
    public void deleteQuiz(Long quiz_id);

    public List<Quiz> getQuizzesOfCategory(Category category);

    public List<Quiz>getActiveQuizzes();

    public List<Quiz>getActiveQuizzesOfCategory(Category category);
}
