package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World!(from 3002 port)") String name, Model model) {
        model.addAttribute("name", name);
        System.out.println(outerServer);
        return "greeting";
    }
    @Value("${arg}")
    private String arg;

    @Value( "${outerServer}" )
    private String outerServer;

    @RequestMapping("/outer")
    public String greeting(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://"+ outerServer + ":3001/greeting";
        System.out.println(outerServer);
        System.out.println(url);
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        String rates = response.getBody();
        model.addAttribute("rates", rates);

        return "outer";
    }



}
