package ru.nsu.fit.DataBase.Controller.Selects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.GoodsPrice;
import ru.nsu.fit.DataBase.Domain.Supplier;
import ru.nsu.fit.DataBase.Repos.GoodsPriceRepo;
import ru.nsu.fit.DataBase.Repos.SupplierRepo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class SupplierSelect {
    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    GoodsPriceRepo goodsPriceRepo;

    @GetMapping("/select/query7")
    public String main(Map<String, Object> model){
        List<Supplier> suppliers = supplierRepo.findAll();
        model.put("suppliers", suppliers);

        putInfo(model);

        return "/select/supplierSelect/supplierSelect";
    }

    private void putInfo(Map<String, Object> model) {
        model.put("currentDate", new Date(System.currentTimeMillis()));
        model.put("goodsPrice", goodsPriceRepo.findAll());
    }

    @PostMapping("/select/query7")
    public String select(@RequestParam Date afterDate,
                         @RequestParam Date beforeDate,
                         @RequestParam int volume,
                         @RequestParam List<Integer> goodsList,
                         Map<String, Object> model){
        List<Integer> supplierIds =  supplierRepo.findByDateBetweenAndVolume
                (afterDate, beforeDate, volume, goodsList);
        List<Supplier> suppliers = supplierRepo.findBySupplierIdIn(supplierIds);
        model.put("suppliers", suppliers);

        putInfo(model);

        return "/select/supplierSelect/supplierSelect";
    }
}
