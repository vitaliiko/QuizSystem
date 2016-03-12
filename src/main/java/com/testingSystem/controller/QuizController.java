package com.testingSystem.controller;

import com.testingSystem.entity.Question;
import com.testingSystem.entity.User;
import com.testingSystem.exception.AuthException;
import com.testingSystem.model.Result;
import com.testingSystem.service.AnswerService;
import com.testingSystem.service.QuestionService;
import com.testingSystem.service.UserService;
import com.testingSystem.util.QuestionUtil;
import com.testingSystem.util.TestUtil;
import com.testingSystem.util.UserUtil;
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
    @Autowired private QuestionUtil questionUtil;
    @Autowired private QuestionService questionService;
    @Autowired private AnswerService answerService;
    @Autowired private TestUtil testUtil;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signIn() {
        return new ModelAndView("signIn");
    }

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(String firstName, String lastName, String email, HttpSession session)
            throws AuthException, HibernateException {

        User user = userUtil.createUser(firstName, lastName, email);
        session.setAttribute("userId", user.getId());

        ModelAndView model = new ModelAndView();
        model.setViewName(user.isAdmin() ? "adminHome" : "home");
        model.addObject("user", user);
        model.addObject("questionsCount", questionService.getQuestionCount());
        return model;
    }

    @RequestMapping(value = "/startTest", method = RequestMethod.GET)
    public ModelAndView startTest(HttpSession session) throws HibernateException {
        ModelAndView model = new ModelAndView("makeTest");
        session.setAttribute("questions", questionUtil.getRandomQuestions(QuestionUtil.QUESTIONS_COUNT));
        session.setAttribute("userAnswers", new HashMap<>());
        session.setAttribute("time", System.currentTimeMillis());
        userService.addAttempt((Long) session.getAttribute("userId"));
        return model;
    }

    @RequestMapping(value = "/getQuestion")
    public Question getQuestion(String answer, Integer questionId, HttpSession session) throws HibernateException {
        Set<Integer> idSet = (Set<Integer>) session.getAttribute("questions");
        Map<Integer, String> userAnswers = (Map<Integer, String>) session.getAttribute("userAnswers");
        if (questionId != null) {
            userAnswers.put(questionId, answer);
        }
        return questionUtil.getQuestion(idSet);
    }

    @RequestMapping(value = "/getQuestion/{questionId}")
    public Question getQuestion(@PathVariable Integer questionId) throws HibernateException {
        return questionService.getById(questionId);
    }

    @RequestMapping("/getMaxQuestionsCount")
    public Integer getQuestionsCount() {
        return QuestionUtil.QUESTIONS_COUNT;
    }

    @RequestMapping("/getResult")
    public Result getResult(HttpSession session) {
        User user = userService.getById((Long) session.getAttribute("userId"));
        int rightAnswersCount = testUtil.countRightAnswers((Map<Integer, String>) session.getAttribute("userAnswers"));
        int spentTime = testUtil.countSpentTime((Long) session.getAttribute("time"));
        float result = testUtil.countResult(QuestionUtil.QUESTIONS_COUNT, rightAnswersCount);
        String message = testUtil.generateMessage(result);
        return new Result(spentTime, QuestionUtil.QUESTIONS_COUNT, rightAnswersCount, result, user.getAttempts(), message);
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
    public ModelAndView addQuestion() {
        return new ModelAndView("addQuestion");
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public String addQuestion(@RequestBody String answers) {
        return answers;
    }

    @RequestMapping("/createQuestions")
    public ModelAndView createQuestions() throws HibernateException {
        ModelAndView model = new ModelAndView("home");
        questionUtil.createQuestions();
        return model;
    }

    @RequestMapping("/printQuestion")
    public ModelAndView question() throws HibernateException {
        ModelAndView model = new ModelAndView("printQuestion");
        Question question = questionService.getById(1);
        model.addObject("question", question);
        return model;
    }

    @ExceptionHandler(AuthException.class)
    public ModelAndView authExceptionHandler(AuthException e) {
        ModelAndView model = new ModelAndView("signIn");
        model.addObject("errorMessage", e.getMessage());
        return model;
    }

    @ExceptionHandler(HibernateException.class)
    public ModelAndView hibernateExceptionHandler(HibernateException e) {
        ModelAndView model = new ModelAndView("errorPages/errorPage");
        model.addObject("errorMessage", "Data base doesn't respond");
        return model;
    }


}