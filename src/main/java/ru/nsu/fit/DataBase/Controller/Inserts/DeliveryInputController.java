package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Delivery;
import ru.nsu.fit.DataBase.Domain.DeliveryInput;
import ru.nsu.fit.DataBase.Domain.GoodsPrice;
import ru.nsu.fit.DataBase.Repos.DeliveryInputRepo;
import ru.nsu.fit.DataBase.Repos.DeliveryRepo;
import ru.nsu.fit.DataBase.Repos.GoodsPriceRepo;

import java.util.Map;

@Controller
public class DeliveryInputController {
    @Autowired
    DeliveryInputRepo deliveryInputRepo;
    @Autowired
    DeliveryRepo deliveryRepo;
    @Autowired
    GoodsPriceRepo goodsPriceRepo;

    Delivery currentDelivery;


    @GetMapping("/insert/delivery/input")
    public String main(@RequestParam int deliveryId, Map<String, Object> model){

        putCurrentDelivery(deliveryId, model);
        putListOfGoods(model);
        putListOfCurrentDeliveryInput(model);

        return "/insert/delivery/input/deliveryInput";
    }

    @PostMapping("/insert/delivery/input")
    public String add(@RequestParam int deliveryId,
            @RequestParam int goodsId, @RequestParam int count, Map<String, Object> model){

        putCurrentDelivery(deliveryId, model);
        putListOfGoods(model);

        GoodsPrice goods = goodsPriceRepo.findByGoodsId(goodsId);
        DeliveryInput deliveryInput = new DeliveryInput(currentDelivery, goods, count);
        deliveryInputRepo.save(deliveryInput);

        putListOfCurrentDeliveryInput(model);
        return "/insert/delivery/input/deliveryInput";
    }

    private void putCurrentDelivery(int deliveryId, Map<String, Object> model){
        this.currentDelivery = deliveryRepo.findByDeliveryId(deliveryId);
        model.put("delivery", currentDelivery);
    }

    private void putListOfGoods(Map<String,Object> model){
        Iterable<GoodsPrice> it = goodsPriceRepo.findAll();
        model.put("goods", it);
    }

    private void putListOfCurrentDeliveryInput(Map<String, Object> model){
        Iterable<DeliveryInput> deliveryInputs
                = deliveryInputRepo.findByDelivery_DeliveryId(currentDelivery.getDeliveryId());
        model.put("deliveryInput", deliveryInputs);
    }

}
