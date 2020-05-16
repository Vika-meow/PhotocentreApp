package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.*;
import ru.nsu.fit.DataBase.Repos.*;

import java.util.Map;

@Controller
public class CheckItemController {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    GoodsPriceRepo goodsPriceRepo;
    @Autowired
    ServicePriceRepo servicePriceRepo;
    @Autowired
    OrderPriceRepo orderPriceRepo;
    @Autowired
    CheckRepo checkRepo;

    CheckEntity check;
    @GetMapping("/insert/check/item")
    public String main(@RequestParam int checkId, Map<String, Object> model){
        check = checkRepo.findByCheckId(checkId);
        putListOfAll(model);
        return "/insert/check/item/checkItem";
    }

    @PostMapping("/insert/check/itemGoods")
    public String addGoods(@RequestParam int checkId, @RequestParam int goodsId, @RequestParam int count, Map<String, Object> model){
        check = checkRepo.findByCheckId(checkId);
        GoodsPrice goods = goodsPriceRepo.findByGoodsId(goodsId);
        Item item = new Item(check, goods, count);
        itemRepo.save(item);
        putListOfAll(model);
        return "/insert/check/item/checkItem";
    }

    @PostMapping("/insert/check/itemService")
    public String addService(@RequestParam int checkId, @RequestParam int serviceId, @RequestParam int count, Map<String, Object> model){
        check = checkRepo.findByCheckId(checkId);
        ServicePrice service = servicePriceRepo.findByServiceId(serviceId);
        Item item = new Item(check, service, count);
        itemRepo.save(item);
        putListOfAll(model);
        return "/insert/check/item/checkItem";
    }

    @PostMapping("/insert/check/itemOrder")
    public String addOrder(@RequestParam int checkId, @RequestParam int orderId,
                           @RequestParam int count, Map<String, Object> model){
        check = checkRepo.findByCheckId(checkId);
        OrderPrice order = orderPriceRepo.findByOrderId(orderId);
        Item item = new Item(check, order, count);
        itemRepo.save(item);
        putListOfAll(model);
        return "/insert/check/item/checkItem";
    }

    @PostMapping("/insert/check/item/delete")
    public String delete(@RequestParam int id,Map<String, Object> model){
        itemRepo.deleteById(id);
        putListOfAll(model);
        return "/insert/check/item/checkItem";
    }

    private void putListOfAll(Map<String, Object> model){
        model.put("currentCheck", check);

        Iterable<GoodsPrice> goodsIt = goodsPriceRepo.findAll();
        model.put("goods", goodsIt);

        Iterable<ServicePrice> serviceIt = servicePriceRepo.findAll();
        model.put("services", serviceIt);

        Iterable<OrderPrice> orderIt = orderPriceRepo.findAll();
        model.put("orders", orderIt);

        Iterable<Item> items = itemRepo.findByCheck(check);
        model.put("items", items);
    }
}
