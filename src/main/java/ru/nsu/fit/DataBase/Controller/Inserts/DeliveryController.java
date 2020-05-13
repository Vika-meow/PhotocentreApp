package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Delivery;
import ru.nsu.fit.DataBase.Domain.Organization;
import ru.nsu.fit.DataBase.Domain.Supplier;
import ru.nsu.fit.DataBase.Repos.DeliveryRepo;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;
import ru.nsu.fit.DataBase.Repos.SupplierRepo;

import java.sql.Date;
import java.util.Map;

@Controller
public class DeliveryController {
    @Autowired
    DeliveryRepo deliveryRepo;
    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    OrganizationRepo organizationRepo;

    @GetMapping("/insert/delivery")
    public String main(Map<String, Object> model){
        putListOfOrganizations(model);
        putListOfSuppliers(model);
        putListOfDelivery(model);
        return "/insert/delivery/delivery";
    }

    @PostMapping("/insert/delivery")
    public String add(@RequestParam String address, @RequestParam int supplierId,
                      @RequestParam Date date, Map<String, Object> model){
        Organization organization = organizationRepo.findByAddress(address);
        Supplier supplier = supplierRepo.findBySupplierId(supplierId);
        Delivery delivery = new Delivery(organization,supplier,date);
        deliveryRepo.save(delivery);

        putListOfOrganizations(model);
        putListOfSuppliers(model);
        putListOfDelivery(model);

        return "/insert/delivery/delivery";
    }

    private void putListOfOrganizations(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organization", it);
    }

    private void putListOfSuppliers(Map<String, Object> model){
        Iterable<Supplier> it = supplierRepo.findAll();
        model.put("supplier", it);
    }

    private void putListOfDelivery(Map<String, Object> model){
        Iterable<Delivery> it = deliveryRepo.findAll();
        model.put("delivery", it);
    }
}
