package com.gringolito.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {
    @Value("${app.name}")
    private String name;

    @Value("${app.version}")
    private String version;

    @GetMapping
    @RequestMapping("/")
    public Map getInfo() {
        return new HashMap<String, String>() {{
            put("name", name);
            put("version", version);
        }};
    }
}
