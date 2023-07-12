package com.todo.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

@RequestMapping(method = RequestMethod.GET, value = "/")
    public String render() {
        return "index";
    }
    
}
