package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.OrderPrice;
import ru.nsu.fit.DataBase.Repos.OrderPriceRepo;

import java.util.Map;

@Controller
public class OrderPriceController {

    @Autowired
    private OrderPriceRepo orderPriceRepo;

    @GetMapping("/price/order")
    public String main(Map<String, Object> model){
        putListOfAll(model);
        return "/insert/price/order/priceOrder";
    }

    @PostMapping("/price/orderAdd")
    public String add(@RequestParam String orderType, @RequestParam String paperType,
                      @RequestParam String format, @RequestParam int price,
                      Map<String, Object> model){

        OrderPrice orderPrice = new OrderPrice(orderType, paperType, format, price);
        orderPriceRepo.save(orderPrice);

        putListOfAll(model);

        return "/insert/price/order/priceOrder";
    }

    @PostMapping("/price/order/delete")
    public String delete(@RequestParam int orderId, Map<String, Object> model){
        OrderPrice orderPrice = orderPriceRepo.findByOrderId(orderId);
        orderPriceRepo.delete(orderPrice);

        putListOfAll(model);

        return "/insert/price/order/tableOrder";
    }

    private void putListOfAll(Map<String, Object> model){
        Iterable<OrderPrice> it = orderPriceRepo.findAll();
        model.put("order", it);
    }
}
