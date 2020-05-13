package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Page with all prices
@Controller
public class PriceController {

    @GetMapping("/price")
    public String insert(){
        return "/insert/price/price";
    }
}
