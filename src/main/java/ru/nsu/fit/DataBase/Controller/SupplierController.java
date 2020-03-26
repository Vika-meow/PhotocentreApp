package ru.nsu.fit.DataBase.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Supplier;
import ru.nsu.fit.DataBase.Repos.SupplierRepo;

import java.util.Map;

//page with inserts for supplier
@Controller
public class SupplierController {

    @Autowired
    private SupplierRepo supplierRepo;

   @GetMapping("/insert/supplier")
    public String main(Map<String, Object> model) {
        Iterable<Supplier> it = supplierRepo.findAll();

        model.put("suppliers", it);

        return "supplier";
    }

    @PostMapping("/insert/supplier")
    public String add(@RequestParam String organization, Map<String, Object> model) {
        Supplier supplier = new Supplier(organization);

        supplierRepo.save(supplier);

        Iterable<Supplier> it = supplierRepo.findAll();

        model.put("suppliers", it);

        return "supplier";
    }


}
