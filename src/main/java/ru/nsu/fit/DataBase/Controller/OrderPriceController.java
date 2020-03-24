package ru.nsu.fit.DataBase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.GoodsPrice;
import ru.nsu.fit.DataBase.Domain.OrderPrice;
import ru.nsu.fit.DataBase.Repos.OrderPriceRepo;

import java.util.Map;

@Controller
public class OrderPriceController {

    @Autowired
    private OrderPriceRepo orderPriceRepo;

    @GetMapping("/price/order")
    public String main(Map<String, Object> model){
        putListofAll(model);
        return "priceOrder";
    }

    @PostMapping("/price/orderAdd")
    public String add(@RequestParam String orderType, @RequestParam String paperType,
                      @RequestParam String format, @RequestParam int price,
                      Map<String, Object> model){

        OrderPrice orderPrice = new OrderPrice(orderType, paperType, format, price);
        orderPriceRepo.save(orderPrice);

        putListofAll(model);

        return "priceOrder";
    }

    private void putListofAll(Map<String, Object> model){
        Iterable<OrderPrice> it = orderPriceRepo.findAll();
        model.put("order", it);
    }
}