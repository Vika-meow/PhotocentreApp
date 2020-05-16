package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Organization;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;

import java.util.Map;

@Controller
public class OrganizationController {
    @Autowired
    OrganizationRepo organizationRepo;

    @GetMapping("/insert/organization")
    public String main(Map<String, Object> model){
        putListOfAll(model);
        return "/insert/organization/organization";
    }

    @PostMapping("/insert/organization")
    public String add(@RequestParam String address, @RequestParam(required = false) String branchOfficeAdress,
                        Map<String, Object> model){
        Organization organization;
        if(branchOfficeAdress != null) {
            Organization branchOrganization = organizationRepo.findByAddress(branchOfficeAdress);
            organization = new Organization(address, branchOrganization);
        } else {
            organization = new Organization(address);
        }
        organizationRepo.save(organization);

        putListOfAll(model);
        return "/insert/organization/organization";
    }

    @GetMapping("/insert/organizationShowAll")
    public String showAll(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organizationsShow", it);
        return "/insert/organization/adressesList";
    }

    @GetMapping("/insert/organizationShowFilials")
    public String showFilials(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findByBranchOfficeAdressIsNull();
        model.put("organizationsShow", it);
        return "/insert/organization/adressesList";
    }

    @GetMapping("/insert/organizationShowKiosks")
    public String showKiosks(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findByBranchOfficeAdressIsNotNull();
        model.put("organizationsShow", it);
        return "/insert/organization/adressesList";
    }

    @PostMapping("/insert/organization/delete")
    public String delete(@RequestParam String address,
                         Map<String, Object> model){
        Organization organization = organizationRepo.findByAddress(address);
        organizationRepo.delete(organization);

        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organizationsShow", it);
        return "/insert/organization/adressesList";
    }

    private void putListOfAll(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organizations", it);
        it = organizationRepo.findAll();
        Integer price = 0;
        model.put("organizationsShow", it);
    }
}
