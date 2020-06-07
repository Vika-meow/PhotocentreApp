package ru.nsu.fit.DataBase.Controller.Selects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.NewDomains.WorkersOrgsCount;
import ru.nsu.fit.DataBase.Repos.WorkerRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WorkersSelect {
    @Autowired
    WorkerRepo workerRepo;

    @GetMapping("/select/workers")
    public String main(Map<String, Object> model){
        List<Object[]> listObjects = workerRepo.findWorkers();

        List<WorkersOrgsCount> workers = new ArrayList<>();
        for(Object[] it : listObjects){
            workers.add( new WorkersOrgsCount(
                    String.valueOf(it[0]),
                    String.valueOf(it[1]),
                    Long.parseLong(it[2].toString())
                    )
            );
        }

        model.put("workers", workers);

        return "select/workers/workers";
    }

    @PostMapping("/select/workers")
    public String select(@RequestParam String profile,
                         Map<String, Object> model){
        List<Object[]> listObjects = workerRepo.findWorkersByProfile(profile);

        List<WorkersOrgsCount> workers = new ArrayList<>();
        for(Object[] it : listObjects) {
            workers.add(new WorkersOrgsCount(
                            String.valueOf(it[0]),
                            String.valueOf(it[1]),
                            Long.parseLong(it[2].toString())
                    )
            );
        }

            model.put("workers", workers);

            return "select/workers/workers";

    }
}
