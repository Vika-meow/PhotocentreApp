package ru.nsu.fit.DataBase.Controller.Selects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Customer;
import ru.nsu.fit.DataBase.Repos.CustomerRepo;

import java.util.List;
import java.util.Map;

@Controller
public class CustomerSelect {
    @Autowired
    CustomerRepo customerRepo;

    @GetMapping("/select/query8")
    public String main(Map<String, Object> model){
        List<Customer> customers = customerRepo.findAll();
        model.put("customers", customers);

        return "/select/customerSelect/customerSelect";
    }

    @PostMapping("/select/query8")
    public String select(@RequestParam int discountCard,
            @RequestParam int volume,
            Map<String, Object> model){
        boolean card = false;
        if(discountCard == 1){
            card = true;
        }
        List<String> names = customerRepo.findByVolume(card, volume);
        List<Customer> customers = customerRepo.findByNameIn(names);
        model.put("customers", customers);

        return "/select/customerSelect/customerSelect";
    }
}
