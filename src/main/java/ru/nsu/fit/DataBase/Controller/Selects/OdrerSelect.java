package ru.nsu.fit.DataBase.Controller.Selects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Item;
import ru.nsu.fit.DataBase.Domain.Organization;
import ru.nsu.fit.DataBase.Repos.ItemRepo;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class OdrerSelect {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    OrganizationRepo organizationRepo;

    @GetMapping("/select/orders")
    public String startPage(Map<String, Object> model){
        Iterable<Item> it = itemRepo.findByOrderIdIsNotNull();
        model.put("orders", it);

        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);
        model.put("fullPrice", countSum(it));

        return "/select/orders/selectOrder";
    }

    @GetMapping("/select/orders/organizationTypeAndDate")
    public String select(@RequestParam String organizationFilter,
                         @RequestParam(required = false) List<String> orgs,
                         @RequestParam(required = false) Date afterDate,
                         @RequestParam(required = false) Date beforeDate,
                         Map<String, Object> model){
        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);

        Iterable<Item> it = null;

        if(orgs != null){
            if((afterDate!=null) && (beforeDate!=null)){
                it = itemRepo.findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_AddressIn(afterDate, beforeDate, orgs);
            } else {
                it = itemRepo.findByOrderIdIsNotNullAndCheck_Organization_AddressIn(orgs);
            }
        } else {
            if((afterDate!=null) && (beforeDate!=null)) {
                if (organizationFilter.equals("all")) {
                    it = itemRepo.findByOrderIdIsNotNullAndCheck_DateBetween(afterDate, beforeDate);
                }

                if (organizationFilter.equals("filials")) {
                    it = itemRepo.findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(afterDate, beforeDate);
                }

                if (organizationFilter.equals("kiosks")) {
                    it = itemRepo.findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(afterDate, beforeDate);
                }
            }
            else {
                if (organizationFilter.equals("all")) {
                    it = itemRepo.findByOrderIdIsNotNull();
                }

                if (organizationFilter.equals("filials")) {
                    it = itemRepo.findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNull();
                }

                if (organizationFilter.equals("kiosks")) {
                    it = itemRepo.findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNotNull();
                }
            }
        }

        model.put("orders", it);
        model.put("fullPrice", countSum(it));
        return "/select/orders/selectOrder";
    }

    @GetMapping("/select/orders/addDates")
    public String addDates(Map<String, Object> model){
        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);
        return "/select/orders/addDates";
    }
    @GetMapping("/select/orders/removeDates")
    public String removeDates(){
        return "/select/orders/removeDates";
    }

    @GetMapping("/select/orders/addOrganizations")
    public String addOrganizations(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organizations", it);
        return "/select/orders/addOrganizations";
    }
    @GetMapping("/select/orders/removeOrganizations")
    public String removeOrganizations(){
        return "/select/orders/removeOrganizations";
    }


    @GetMapping("/select/orders/putOrganizations")
    public String putOrganizations(String typeOfOrganizations, Map<String, Object> model){
        if(typeOfOrganizations.equals("all")){
            Iterable<Organization> it = organizationRepo.findAll();
            model.put("organizations", it);
        }

        if(typeOfOrganizations.equals("filials")){
            Iterable<Organization> it = organizationRepo.findByBranchOfficeAdressIsNull();
            model.put("organizations", it);
        }

        if(typeOfOrganizations.equals("kiosks")){
            Iterable<Organization> it = organizationRepo.findByBranchOfficeAdressIsNotNull();
            model.put("organizations", it);
        }
        return "/select/orders/putOrganizations";
    }

    private int countSum(Iterable<Item> it){
        int counting = 0;
        for (Item i: it) {
            counting += i.getOrderPrice();
        }
        return counting;
    }
}
