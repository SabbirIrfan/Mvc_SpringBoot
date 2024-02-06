package com.dsi.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class test {

    @GetMapping(value = "/")
//    @ResponseBody
    public String home(){
        System.out.println("this is home");

        return "home";
    }
}
