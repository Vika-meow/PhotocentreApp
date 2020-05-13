package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Customer;
import ru.nsu.fit.DataBase.Repos.CustomerRepo;

import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    CustomerRepo customerRepo;

    @GetMapping("/insert/customer")
    public String main(Map<String, Object> model){

        putListOfAll(model);
        return "/insert/customer/customer";
    }

    @PostMapping("/insert/customer")
    public String add(@RequestParam String name, @RequestParam boolean discountCard,
                      Map<String, Object> model){

        Customer customer = new Customer(name,discountCard);
        customerRepo.save(customer);

        putListOfAll(model);
        return "/insert/customer/customer";
    }

    private void putListOfAll(Map<String, Object> model){
        Iterable<Customer> it = customerRepo.findAll();
        model.put("customers", it);
    }
}
