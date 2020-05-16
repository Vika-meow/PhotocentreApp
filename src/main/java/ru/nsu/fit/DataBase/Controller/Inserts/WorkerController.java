package ru.nsu.fit.DataBase.Controller.Inserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Organization;
import ru.nsu.fit.DataBase.Domain.Worker;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;
import ru.nsu.fit.DataBase.Repos.WorkerRepo;

import java.util.Map;

@Controller
public class WorkerController {
    @Autowired
    WorkerRepo workerRepo;

    @Autowired
    OrganizationRepo organizationRepo;


    @GetMapping("/insert/worker")
    public String main(Map<String, Object> model){
        putListOfAll(model);
        return "/insert/worker/worker";
    }

    @PostMapping("/insert/worker")
    public String add(@RequestParam String address, @RequestParam String name,
            @RequestParam String profile, Map<String, Object> model){
        //System.out.println(address);

        Organization organization = organizationRepo.findByAddress(address);
        Worker worker = new Worker(name, profile, organization);
        workerRepo.save(worker);

        putListOfAll(model);
        return "/insert/worker/worker";
    }

    @PostMapping("/insert/worker/delete")
    public  String delete(@RequestParam String name, Map<String, Object> model){
        /*Worker worker = workerRepo.findByName(name);
        workerRepo.delete(worker);*/
        workerRepo.deleteById(name);

        putListOfAll(model);
        return "/insert/worker/worker";
    }

    private void putListOfAll(Map<String, Object> model) {
        putListOfWorker(model);
        putListOfOrganizations(model);
    }

    private void putListOfOrganizations(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organization", it);
    }

    private void putListOfWorker(Map<String, Object> model){
        Iterable<Worker> it = workerRepo.findAll();
        model.put("workers", it);
    }

}
