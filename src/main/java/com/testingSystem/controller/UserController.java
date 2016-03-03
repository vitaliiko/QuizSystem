package com.testingSystem.controller;

import com.testingSystem.entity.Answer;
import com.testingSystem.entity.Question;
import com.testingSystem.entity.Test;
import com.testingSystem.exception.AuthException;
import com.testingSystem.service.QuestionService;
import com.testingSystem.service.UserService;
import com.testingSystem.util.QuestionsUtil;
import com.testingSystem.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"userEmail"})
public class UserController {

    @Autowired private UserService userService;
    @Autowired private UserUtil userUtil;
    @Autowired private QuestionsUtil questionsUtil;
    @Autowired private QuestionService questionService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signIn() {
        return new ModelAndView("signIn");
    }

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(String firstName, String lastName, String email) throws AuthException {
        if (userUtil.validateUserInfo(firstName, lastName, email)) {
            userService.create(firstName, lastName, email);
        }
        ModelAndView model = new ModelAndView("/index");
        model.addObject("userEmail", email);
        return model;
    }

    @RequestMapping(value = "/startTest", method = RequestMethod.GET)
    public ModelAndView test(@ModelAttribute String userEmail) {
        ModelAndView model = new ModelAndView();
        List<Test> tests = userService.getTests(userEmail);
        if (tests.size() > 0) {
            model.addObject("tests", tests);
        } else {
            model.addObject("message", "You don't take a test yet");
        }
        return model;
    }

    @RequestMapping(value = "/startTest", method = RequestMethod.POST)
    public ModelAndView startTest(@ModelAttribute String userEmail) {

        return new ModelAndView("index");
    }

    @RequestMapping("/createQuestions")
    public ModelAndView createQuestions() {
        ModelAndView model = new ModelAndView("index");
        questionsUtil.createQuestions();
        return model;
    }

    @RequestMapping("/question")
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
}