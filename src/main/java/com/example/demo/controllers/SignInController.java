package com.example.demo.controllers;

import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SignInController {

    Map<String, String> account = new HashMap<String, String>();

    @GetMapping("/")
    public String signIn(Model model) {
        return "signIn";
    }

    @GetMapping("/Main")
    public String page1(Model model) {
        return "Main";
    }

    @GetMapping("registrated")
    public String reg(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password, Model model){
        String temp_pas = account.get(login);
        if (temp_pas != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "registration";
        }
        if (login == "") {
            model.addAttribute("message", "Введите логин");
            return "registration";
        }
        if (password == "") {
            model.addAttribute("message", "Введите пароль");
            return "registration";
        }
        account.putIfAbsent(login, password);
        return "signIn";
    }

    @GetMapping("entrance")
    public String entrance(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password, Model model){
        String temp_pas = account.get(login);
        if (temp_pas == null) {
            model.addAttribute("message", "Нет пользователя с таким логином");
            return "signIn";
        }
        if (!temp_pas.equals(password)) {
            model.addAttribute("message", "Неправильно введен пароль");
            return "signIn";
        }
        if (temp_pas.equals(password)) {
            return "Main";
        }
        return "signIn";
    }

    @GetMapping("/registration.html")
    public String registration(Model model) {
        return "registration";
    }

}