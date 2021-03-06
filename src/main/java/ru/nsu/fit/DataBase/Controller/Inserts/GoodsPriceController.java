package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.GoodsPrice;
import ru.nsu.fit.DataBase.Repos.GoodsPriceRepo;

import java.util.Map;

@Controller
public class GoodsPriceController {

    @Autowired
    private GoodsPriceRepo goodsPriceRepo;

    @GetMapping("/price/goods")
    public String main(Map<String, Object> model) {
        putListOfAll(model);
        return "/insert/price/goods/priceGoods";
    }

    @PostMapping("/price/goodsAdd")
    public String add(@RequestParam String nameOfGoods, @RequestParam String company,
                      @RequestParam String goodsModel, @RequestParam int buyPrice,
                      @RequestParam int sellPrice, Map<String, Object> model){

        GoodsPrice goodsPrice = new GoodsPrice(nameOfGoods, company, goodsModel,
                buyPrice, sellPrice);

        goodsPriceRepo.save(goodsPrice);

        putListOfAll(model);

        return "/insert/price/goods/priceGoods";
    }

    @PostMapping("/insert/price/goods/delete")
    public String delete(@RequestParam int goodsId, Map<String, Object> model){
        GoodsPrice goodsPrice = goodsPriceRepo.findByGoodsId(goodsId);
        goodsPriceRepo.delete(goodsPrice);

        putListOfAll(model);

        return "/insert/price/goods/tableGoods";
    }

    private void putListOfAll(Map<String, Object> model){
        Iterable<GoodsPrice> it = goodsPriceRepo.findAll();
        model.put("goods", it);
    }

}
