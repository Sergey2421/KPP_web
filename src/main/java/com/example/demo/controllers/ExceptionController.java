package com.example.demo.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Controller
public class ExceptionController implements ErrorController{
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorCode", "Error 404");
            modelAndView.addObject("errorMessage", "Page not found");
            modelAndView.addObject("solution", "Сheck the data is correct");
        }
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}