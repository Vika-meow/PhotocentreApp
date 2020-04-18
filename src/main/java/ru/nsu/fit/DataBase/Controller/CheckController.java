package ru.nsu.fit.DataBase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.CheckEntity;
import ru.nsu.fit.DataBase.Domain.Customer;
import ru.nsu.fit.DataBase.Domain.Organization;
import ru.nsu.fit.DataBase.Repos.CheckRepo;
import ru.nsu.fit.DataBase.Repos.CustomerRepo;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;

import java.sql.Date;
import java.util.Map;

@Controller
public class CheckController {
    @Autowired
    CheckRepo checkRepo;
    @Autowired
    OrganizationRepo organizationRepo;
    @Autowired
    CustomerRepo customerRepo;

    @GetMapping("/insert/check")
    public String main(Map<String, Object> model){
        putListOfAll(model);
        return "check";
    }

    @PostMapping("/insert/check")
    public String add(@RequestParam String address, @RequestParam String customerName,
                      @RequestParam Date date, Map<String, Object> model){
        Organization organization = organizationRepo.findByAddress(address);
        Customer customer = new Customer(customerName);
        if(!customerRepo.existsById(customerName)){
            customerRepo.save(customer);
        }
        CheckEntity check = new CheckEntity(organization, customer, date);
        checkRepo.save(check);
        putListOfAll(model);
        return "check";
    }

    private void putListOfAll(Map<String, Object> model) {
        putListOfOrganizations(model);
        putListOfCheck(model);
    }

    private void putListOfOrganizations(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organization", it);
    }

    private void putListOfCheck(Map<String, Object> model){
        Iterable<CheckEntity> it = checkRepo.findAll();
        model.put("check", it);
    }

}
