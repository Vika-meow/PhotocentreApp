package ru.nsu.fit.DataBase.Controller.Selects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Organization;
import ru.nsu.fit.DataBase.NewDomains.GoodsAndCompany;
import ru.nsu.fit.DataBase.Repos.GoodsPriceRepo;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FindTopGoods {
    @Autowired
    GoodsPriceRepo goodsPriceRepo;
    @Autowired
    OrganizationRepo organizationRepo;

    @GetMapping("/select/query10")
    public String main(Map<String, Object> model){

        List<Object[]> list = goodsPriceRepo.findTop(10);

        List<GoodsAndCompany> goodsAndCompanies = new ArrayList<>();
        for(Object[] it : list){
            goodsAndCompanies.add(new GoodsAndCompany
                    (String.valueOf(it[0]), String.valueOf(it[1]), Integer.parseInt(it[2].toString())));
        }

        model.put("list", goodsAndCompanies);

        model.put("organizations", organizationRepo.findAll());

        return "/select/findTopGoods/findTopGoods";
    }

    @PostMapping("/select/query10")
    public String select(@RequestParam int limitCount,
            @RequestParam(required = false) List<String> orgs,
            Map<String, Object> model){

        List<Object[]> list = new ArrayList<>();
        if(orgs == null) {
            list = goodsPriceRepo.findTop(limitCount);
        } else {
            list = goodsPriceRepo.findTopInOrgs(limitCount, orgs);
        }

        List<GoodsAndCompany> goodsAndCompanies = new ArrayList<>();
        for(Object[] it : list){
            goodsAndCompanies.add(new GoodsAndCompany
                    (String.valueOf(it[0]), String.valueOf(it[1]), Integer.parseInt(it[2].toString())));
        }

        model.put("list", goodsAndCompanies);

        model.put("organizations", organizationRepo.findAll());
        return "/select/findTopGoods/findTopGoods";
    }
}
