package com.school.netlearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value = "/static")
public class indexController {

    @RequestMapping("/")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
