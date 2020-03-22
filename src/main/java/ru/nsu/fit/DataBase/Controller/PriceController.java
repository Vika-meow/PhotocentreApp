package ru.nsu.fit.DataBase.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Page with all prices
@Controller
public class PriceController {

    @GetMapping("/price")
    public String insert(){
        return "price";
    }
}
