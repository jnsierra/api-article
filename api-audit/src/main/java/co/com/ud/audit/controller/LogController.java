package co.com.ud.audit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v.1/log")
public class LogController {

    @GetMapping("/")
    public String hola(){
        return "Hola";
    }

}