package ru.nsu.fit.DataBase.Controller.Selects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Item;
import ru.nsu.fit.DataBase.Repos.ItemRepo;

import java.sql.Date;
import java.util.Map;

@Controller
public class OdrerSelect {
    @Autowired
    ItemRepo itemRepo;

    @GetMapping("/select/orders")
    public String startPage(Map<String, Object> model){
        Iterable<Item> it = itemRepo.findByOrderIdIsNotNull();
        model.put("orders", it);

        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);

        return "selectOrder";
    }

    @GetMapping("/select/ordersParams")
    public String select(@RequestParam String organizationFilter,
                         @RequestParam Date afterDate, @RequestParam Date beforeDate, Map<String, Object> model){
        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);

        if((afterDate != null) && (beforeDate != null)){
            Iterable<Item> it = itemRepo.findByOrderIdIsNotNullAndCheck_DateBetween(afterDate, beforeDate);
            model.put("orders", it);
            return "selectOrder";
        }
        Iterable<Item> it = itemRepo.findByOrderIdIsNotNull();
        model.put("orders", it);
        return "selectOrder";
    }
}
