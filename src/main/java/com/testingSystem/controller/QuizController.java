package com.testingSystem.controller;

import com.testingSystem.entity.Question;
import com.testingSystem.entity.User;
import com.testingSystem.exception.AuthException;
import com.testingSystem.service.AnswerService;
import com.testingSystem.service.QuestionService;
import com.testingSystem.service.UserService;
import com.testingSystem.util.QuestionUtil;
import com.testingSystem.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signIn() {
        return new ModelAndView("signIn");
    }

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(String firstName, String lastName, String email, HttpSession session) throws AuthException {
        User user = userUtil.createUser(firstName, lastName, email);
        session.setAttribute("userId", user.getId());

        ModelAndView model = new ModelAndView();
        model.setViewName(user.isAdmin() ? "adminHome" : "home");
        model.addObject("user", user);
        model.addObject("questionsCount", questionService.getQuestionCount());
        return model;
    }

    @RequestMapping(value = "/startTest", method = RequestMethod.GET)
    public ModelAndView startTest(HttpSession session) {
        ModelAndView model = new ModelAndView("makeTest");
        session.setAttribute("questions", questionUtil.getRandomQuestions(QuestionUtil.QUESTIONS_COUNT));
        session.setAttribute("userAnswers", new HashMap<>());
        return model;
    }

    @RequestMapping(value = "/setUserAnswer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void setUserAnswer(@RequestBody String answer, HttpSession session) {
        List<String> answerList = ((List<String>) session.getAttribute("userAnswers"));
        answerList.add(answer);
        System.out.println("ANSWERS : ");
        answerList.forEach(System.out::println);
    }

    @RequestMapping(value = "/getQuestion")
    public Question getQuestion(HttpSession session) {
        Set<Integer> idSet = (Set<Integer>) session.getAttribute("questions");
        if (idSet.size() > 0) {
            Integer questionId = idSet.stream().findFirst().get();
            idSet.remove(questionId);
            return questionService.getById(questionId);
        }
        return null;
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
    public ModelAndView createQuestions() {
        ModelAndView model = new ModelAndView("home");
        questionUtil.createQuestions();
        return model;
    }

    @RequestMapping("/printQuestion")
    public ModelAndView question() {
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


}