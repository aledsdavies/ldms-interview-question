package com.aledsdavies.ldms.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialController {

    @GetMapping("/hello")
    @ResponseBody
    public String handler() {
        return "Hello world!";
    }
}
