package com.testingSystem.controller;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class QuizInterceptor extends HandlerInterceptorAdapter {

    private final List<String> EXCLUSIONS = Arrays.asList(
            "/signIn", "/createQuestions"
    );

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String uri = req.getRequestURI();
        boolean exclusion = EXCLUSIONS.stream()
                .anyMatch(uri::endsWith);

        if (exclusion || req.getSession().getAttribute("userId") != null) {
            return true;
        } else {
            resp.sendRedirect("/quiz/signIn");
        }
        return false;
    }
}
