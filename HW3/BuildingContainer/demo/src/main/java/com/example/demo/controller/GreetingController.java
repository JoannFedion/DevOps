package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
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
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World!(from 3001 port)") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @Value( "${outerServer}" )
    private String outerServer;

    @RequestMapping("/outer")
    public String greeting(Model model) {
        System.out.println(outerServer);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://"+ outerServer + ":3002/greeting";
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        String rates = response.getBody();
        model.addAttribute("rates", rates);
        System.out.println(rates);
        return "outer";
    }
}
