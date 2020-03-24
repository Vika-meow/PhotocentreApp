package ru.nsu.fit.DataBase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.OrderPrice;
import ru.nsu.fit.DataBase.Domain.ServicePrice;
import ru.nsu.fit.DataBase.Repos.ServicePriceRepo;

import java.util.Map;

@Controller
public class ServicePriceController {
    @Autowired
    ServicePriceRepo servicePriceRepo;

    @GetMapping("/price/service")
    public String main(Map<String, Object> model){
        putListofAll(model);
        return "priceService";
    }

    @PostMapping("/price/service")
    public String add(@RequestParam String serviceType, @RequestParam int price,
                      Map<String, Object> model){
        ServicePrice servicePrice = new ServicePrice(serviceType, price);
        servicePriceRepo.save(servicePrice);

        putListofAll(model);

        return "priceService";
    }

    private void putListofAll(Map<String, Object> model){
        Iterable<ServicePrice> it = servicePriceRepo.findAll();
        model.put("service", it);
    }
}
