package ru.nsu.fit.DataBase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Organization;
import ru.nsu.fit.DataBase.Domain.ServicePrice;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;

import java.util.Map;

@Controller
public class OrganizationController {
    @Autowired
    OrganizationRepo organizationRepo;

    @GetMapping("/insert/organization")
    public String main(Map<String, Object> model){
        putListofAll(model);
        return "organization";
    }

    @PostMapping("/insert/organization")
    public String add(@RequestParam String address,
                        Map<String, Object> model){
        Organization organization = new Organization(address);
        organizationRepo.save(organization);

        putListofAll(model);
        return "organization";
    }

    private void putListofAll(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organizations", it);
    }

}
