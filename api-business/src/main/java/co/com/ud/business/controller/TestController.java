package co.com.ud.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping(value = "/")
    public String test(){
        System.out.println("Hola");
        return "Hola";
    }
}
