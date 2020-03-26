package ru.nsu.fit.DataBase.Controller;

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

        putListofAll(model);
        return "customer";
    }

    @PostMapping("/insert/customer")
    public String add(@RequestParam String name, @RequestParam boolean discountCard,
                      Map<String, Object> model){

        Customer customer = new Customer(name,discountCard);
        customerRepo.save(customer);

        putListofAll(model);
        return "customer";
    }

    private void putListofAll(Map<String, Object> model){
        Iterable<Customer> it = customerRepo.findAll();
        model.put("customers", it);
    }
}
