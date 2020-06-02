package ru.nsu.fit.DataBase.Controller.Selects;
/*5 query*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Item;
import ru.nsu.fit.DataBase.Repos.ItemRepo;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class PhotoCountSelect {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    OrganizationRepo organizationRepo;

    @GetMapping("/select/photoCount")
    public String main(Map<String, Object> model){
        putInitial(model);

        List<Item> items = itemRepo.findByOrderId_OrderTypeIs("Print photo");
        model.put("items", items);
        model.put("photoCount", count(items));

        return "/select/photoCount/photoCount";
    }

    @PostMapping("/select/photoCount")
    public String select(@RequestParam String orderType,
                         @RequestParam List<String> orgs,
                         @RequestParam Date afterDate,
                         @RequestParam Date beforeDate,
                         Map<String, Object> model){
        List<Item> items = itemRepo.findByOrderId_OrderTypeIsAndCheck_DateBetweenAndCheck_Organization_AddressIn
                (orderType, afterDate, beforeDate, orgs);
        model.put("items", items);

        model.put("photoCount", count(items));

        putInitial(model);

        return "/select/photoCount/photoCount";
    }

    private int count(List<Item> items){
        int result = 0;
        for(Item it : items){
            result += it.getCount();
        }
        return result;
    }

    private void putInitial(Map<String, Object> model){
        model.put("organizations", organizationRepo.findAll());

        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);
    }
}
