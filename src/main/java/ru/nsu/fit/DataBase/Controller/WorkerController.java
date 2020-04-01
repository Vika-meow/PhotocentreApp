package ru.nsu.fit.DataBase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Organization;
import ru.nsu.fit.DataBase.Domain.Worker;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;
import ru.nsu.fit.DataBase.Repos.WorkerRepo;

import javax.persistence.ManyToOne;
import java.util.Map;
import java.util.Optional;

@Controller
public class WorkerController {
    @Autowired
    WorkerRepo workerRepo;

    @Autowired
    OrganizationRepo organizationRepo;


    @GetMapping("/insert/worker")
    public String main(Map<String, Object> model){
        putListofAll(model);
        putListofOrganizations(model);
        return "worker";
    }

    @PostMapping("/insert/worker")
    public String add(@RequestParam String address, @RequestParam String name,
            @RequestParam String profile, Map<String, Object> model){
        //System.out.println(address);

        Organization organization = organizationRepo.findByAddress(address);
        Worker worker = new Worker(name, profile, organization);
        workerRepo.save(worker);

        putListofAll(model);
        putListofOrganizations(model);
        return "worker";
    }

    private void putListofOrganizations(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organization", it);
    }

    private void putListofAll(Map<String, Object> model){
        Iterable<Worker> it = workerRepo.findAll();
        model.put("workers", it);
    }

}
