package ru.nsu.fit.DataBase.Controller.Selects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.GoodsPrice;
import ru.nsu.fit.DataBase.Domain.Item;
import ru.nsu.fit.DataBase.NewDomains.GoodsPriceMore;
import ru.nsu.fit.DataBase.Repos.ItemRepo;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsRevenue {
    @Autowired
    ItemRepo itemRepo;

    @Autowired
    OrganizationRepo organizationRepo;

    @GetMapping("/select/query9")
    public String main(Map<String, Object> model){
        List<GoodsPriceMore> items = new ArrayList<>();
        List<Object[]> objects = itemRepo.findByGoods();

        for(Object[] it : objects){
            items.add(new GoodsPriceMore(
                    String.valueOf(it[0]),
                    String.valueOf(it[1]),
                    String.valueOf(it[2]),
                    Integer.parseInt(it[3].toString()),
                    Integer.parseInt(it[4].toString()),
                    Integer.parseInt(it[5].toString())));
        }

        putInitial(model, items);
        return "/select/goodsRevenue/goodsRevenue";
    }

    @PostMapping("/select/query9")
    public String select(@RequestParam List<String> orgs,
            @RequestParam Date afterDate,
            @RequestParam Date beforeDate,
            Map<String, Object> model){
        /*List<Item> items = itemRepo.findByGoodsIdNotNullAndCheck_DateBetweenAndCheck_Organization_AddressIn
                (afterDate, beforeDate, orgs);*/

        List<GoodsPriceMore> items = new ArrayList<>();
        List<Object[]> objects = itemRepo.findByDateAndAddress(afterDate, beforeDate, orgs);
        for(Object[] it : objects){
            items.add(new GoodsPriceMore(
                    String.valueOf(it[0]),
                    String.valueOf(it[1]),
                    String.valueOf(it[2]),
                    Integer.parseInt(it[3].toString()),
                    Integer.parseInt(it[4].toString()),
                    Integer.parseInt(it[5].toString())));
        }


        putInitial(model, items);
        return "/select/goodsRevenue/goodsRevenue";
    }

    private void putInitial(Map<String, Object> model, List<GoodsPriceMore> items) {
        model.put("items", items);

        int revenue = 0;
        for(GoodsPriceMore it : items){
            revenue = it.getSellPrice() - it.getBuyPrice();
        }
        model.put("revenue", revenue);

        model.put("organizations", organizationRepo.findAll());
        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);
    }
}
