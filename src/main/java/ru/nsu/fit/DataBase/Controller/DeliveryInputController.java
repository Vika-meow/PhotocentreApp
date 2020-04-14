package ru.nsu.fit.DataBase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Delivery;
import ru.nsu.fit.DataBase.Domain.DeliveryInput;
import ru.nsu.fit.DataBase.Domain.GoodsPrice;
import ru.nsu.fit.DataBase.Domain.Supplier;
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
    int deliveryId;

    @GetMapping("/insert/delivery/Input")
    public String main(@RequestParam int deliveryId, Map<String, Object> model){
        currentDelivery = deliveryRepo.findByDeliveryId(deliveryId);
        this.deliveryId = deliveryId;

        model.put("delivery", currentDelivery);

        Iterable<GoodsPrice> it = goodsPriceRepo.findAll();

        model.put("goods", it);

        Iterable<DeliveryInput> deliveryInputs
                = deliveryInputRepo.findByDelivery_DeliveryId(currentDelivery.getDeliveryId());

        model.put("deliveryInput", deliveryInputs);

        return "deliveryInput";
    }

    @PostMapping("/insert/delivery/Input")
    public String add(@RequestParam int deliveryId,
            @RequestParam int goodsId, @RequestParam int count, Map<String, Object> model){
        currentDelivery = deliveryRepo.findByDeliveryId(deliveryId);
        model.put("delivery", currentDelivery);

        Iterable<GoodsPrice> it = goodsPriceRepo.findAll();

        model.put("goods", it);

        GoodsPrice goods = goodsPriceRepo.findByGoodsId(goodsId);

        DeliveryInput deliveryInput = new DeliveryInput(currentDelivery, goods, count);

        deliveryInputRepo.save(deliveryInput);


        Iterable<DeliveryInput> deliveryInputs
                = deliveryInputRepo.findByDelivery_DeliveryId(currentDelivery.getDeliveryId());

        model.put("deliveryInput", deliveryInputs);

        return "deliveryInput";
    }
/*{{#deliveryInput}}
    <div>
        <b>{{nameOfGoods}}</b>
        <span>{{goodsComapany}}</span>
        <span>{{goodsModel}}</span>
        <span>{{count}}</span>
    </div>
{{/deliveryInput}}*/
}
