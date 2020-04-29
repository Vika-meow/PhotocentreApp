package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public String main(){
        return "testing";
    }

}
