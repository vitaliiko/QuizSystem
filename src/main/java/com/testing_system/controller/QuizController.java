package com.testing_system.controller;

import com.testing_system.entity.Question;
import com.testing_system.entity.User;
import com.testing_system.exception.UserValidateException;
import com.testing_system.request_object.Result;
import com.testing_system.service.QuestionService;
import com.testing_system.service.UserService;
import com.testing_system.util.QuizUtil;
import com.testing_system.util.UserUtil;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/quiz")
@SuppressWarnings("unchecked")
public class QuizController {

    @Autowired private UserService userService;
    @Autowired private UserUtil userUtil;
    @Autowired private QuizUtil quizUtil;
    @Autowired private QuestionService questionService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signIn() {
        return new ModelAndView("signIn");
    }

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(String firstName, String lastName, String email, HttpSession session)
            throws UserValidateException, HibernateException {

        User user = userUtil.createUser(firstName, lastName, email);
        session.setAttribute("userId", user.getId());
        session.removeAttribute("questions");
        session.removeAttribute("userAnswers");
        session.removeAttribute("time");

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/quiz/home");
        return model;
    }

    @RequestMapping("/signOut")
    public ModelAndView signOut(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/quiz/signIn");
    }

    @RequestMapping("/home")
    public ModelAndView home(HttpSession session) throws HibernateException {
        ModelAndView model = new ModelAndView("home");
        User user = userService.getById((Long) session.getAttribute("userId"));
        model.addObject("user", user);
        model.addObject("questionsCount", questionService.getQuestionCount());
        model.addObject("questionsInQuiz", QuizUtil.QUESTIONS_COUNT);
        model.addObject("timeLimit", QuizUtil.TIME_LIMIT);
        model.addObject("bestUsers", userService.getFirst("bestResult", 0, 5));
        return model;
    }

    @RequestMapping(value = "/startTest", method = RequestMethod.GET)
    public ModelAndView startTest(HttpSession session) throws HibernateException {
        ModelAndView model = new ModelAndView("quiz");
        session.setAttribute("questions", quizUtil.getRandomQuestions(QuizUtil.QUESTIONS_COUNT));
        session.setAttribute("userAnswers", new HashMap<>());
        session.setAttribute("time", System.currentTimeMillis());
        userService.addAttempt((Long) session.getAttribute("userId"));
        return model;
    }

    @RequestMapping(value = "/getQuestion")
    public Question getQuestion(String answer, Integer questionId, HttpSession session) throws HibernateException {
        Set<Integer> questions = (Set<Integer>) session.getAttribute("questions");
        Map<Integer, String> userAnswers = (Map<Integer, String>) session.getAttribute("userAnswers");
        if (questionId != null && !userAnswers.containsKey(questionId)) {
            userAnswers.put(questionId, answer);
        }
        return quizUtil.getQuestion(questions);
    }

    @RequestMapping("/getLimits")
    public Integer[] getLimits() {
        return new Integer[] {QuizUtil.QUESTIONS_COUNT, QuizUtil.TIME_LIMIT};
    }

    @RequestMapping("/getResult")
    public Result getResult(String answer, Integer questionId, HttpSession session) throws HibernateException {
        User user = userService.getById((Long) session.getAttribute("userId"));
        session.removeAttribute("questions");
        Map<Integer, String> userAnswers = (Map<Integer, String>) session.getAttribute("userAnswers");
        if (questionId != null) {
            userAnswers.put(questionId, answer);
        }
        Result result = quizUtil.prepareResult(userAnswers, (Long) session.getAttribute("time"), user.getAttempts());
        userUtil.setBestResult(user, result.getPoints());
        return result;
    }

    @ExceptionHandler(UserValidateException.class)
    public ModelAndView authExceptionHandler(UserValidateException e) {
        ModelAndView model = new ModelAndView("signIn");
        model.addObject("errorMessage", e.getMessage());
        return model;
    }

    @ExceptionHandler(HibernateException.class)
    public ModelAndView hibernateExceptionHandler() {
        ModelAndView model = new ModelAndView("errorPage");
        model.addObject("errorMessage", "Data base doesn't respond");
        return model;
    }
}