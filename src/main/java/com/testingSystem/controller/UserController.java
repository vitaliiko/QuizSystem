package com.testingSystem.controller;

import com.testingSystem.entity.*;
import com.testingSystem.exception.AuthException;
import com.testingSystem.service.AnswerService;
import com.testingSystem.service.QuestionService;
import com.testingSystem.service.UserService;
import com.testingSystem.util.QuestionsUtil;
import com.testingSystem.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
@SessionAttributes(value = {"userId"})
@SuppressWarnings("unchecked")
public class UserController {

    @Resource(name = "userServiceImpl") private UserService userService;
    @Autowired private UserUtil userUtil;
    @Autowired private QuestionsUtil questionsUtil;
    @Autowired private QuestionService questionService;
    @Autowired private AnswerService answerService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signIn() {
        return new ModelAndView("signIn");
    }

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(String firstName, String lastName, String email) throws AuthException {
        Long userId = userUtil.createUser(firstName, lastName, email);
        ModelAndView model = new ModelAndView("redirect:/user/index");
        model.addObject("userId", userId);
        return model;
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("questions", questionService.getAll("id"));
        List<User> userList = userService.getAll("id");
        if (userList.size() > 0) {
            model.addObject("users", userList);
        } else {
            model.addObject("message", "Users List is empty");
        }
        return model;
    }

    @RequestMapping(value = "ajaxtest", method = RequestMethod.GET)
    public @ResponseBody String getTime() {
        Random random = new Random();
        float r = random.nextFloat() * 100;
        return "Next random number is <b>" + r + "</b>. Generated on <b>" + Calendar.getInstance().getTime();
    }

    @RequestMapping(value = "/startTest", method = RequestMethod.POST)
    public ModelAndView test(HttpSession httpSession) {
        ModelAndView model = new ModelAndView("startTest");
        List<Test> tests = userService.getTests((Long) httpSession.getAttribute("userId"));
        if (tests.size() > 0) {
            model.addObject("tests", tests);
        } else {
            model.addObject("message", "You don't take a test yet");
        }
        return model;
    }

    @RequestMapping(value = "/startTest", method = RequestMethod.GET)
    public ModelAndView startTest(HttpSession httpSession) {
        List<String> userAnswersList = new ArrayList<>();
        httpSession.setAttribute("userAnswers", userAnswersList);
        return new ModelAndView("question");
    }

    @RequestMapping(value = "/getQuestion")
    public Question getQuestion() {
        Random random = new Random();
        return questionService.getById((long) random.nextInt(5));
    }

    @RequestMapping(value = "/getQuestions")
    public List<Question> getQuestions() {
        return questionService.getAll("id");
    }

    @RequestMapping(value = "/getAnswers")
    public List<Answer> getAnswers() {
        Random random = new Random();
        return questionService.getById((long) random.nextInt(7)).getAnswers();
    }

    @RequestMapping("/createQuestions")
    public ModelAndView createQuestions() {
        ModelAndView model = new ModelAndView("index");
        questionsUtil.createQuestions();
        return model;
    }

    @RequestMapping("/printQuestion")
    public ModelAndView question() {
        ModelAndView model = new ModelAndView("printQuestion");
        Question question = questionService.getById(1L);
        model.addObject("question", question);
        return model;
    }

    @ExceptionHandler(AuthException.class)
    public ModelAndView authExceptionHandler(AuthException e) {
        ModelAndView model = new ModelAndView("signIn");
        model.addObject("errorMessage", e.getMessage());
        return model;
    }

    @RequestMapping("/addTest")
    public ModelAndView addTest(HttpSession httpSession) {
        Test test = new Test("NEW TEST", Calendar.getInstance().getTime());
        userService.addTest((Long) httpSession.getAttribute("userId"), test);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/setUserAnswer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void getUserAnswer(String answer, HttpSession session) {
        ((List<String>) session.getAttribute("userAnswers")).add(answer);
        System.out.println("ANSWER: " + answer);
    }
}