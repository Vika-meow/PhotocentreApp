package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.DataBase.Domain.Worker;
import ru.nsu.fit.DataBase.NewDomains.WorkersOrgsCount;

import java.util.List;

public interface WorkerRepo extends CrudRepository<Worker, String> {
    public Worker findByName(String name);
    public void deleteById(String name);

    @Query(/*"select " +
            "new ru.nsu.fit.DataBase.NewDomains.WorkersOrgsCount(w.profile, o.address, count(w.profile)) " +
            "from Worker w " +
            "join Organization o " +
            "group by w.profile, o.address",*/
            value = "select profile as profile, address as address, count(profile) as count " +
                    "from " +
                    "worker " +
                    "natural join organization " +
                    "group by profile, address",
    nativeQuery = true)
    public List<Object[]> findWorkers();

    @Query(/*"select " +
            "new ru.nsu.fit.DataBase.NewDomains.WorkersOrgsCount(w.profile, o.address, count(w.profile)) " +
            "from Worker w " +
            "join Organization o " +
            "group by w.profile, o.address",*/
            value = "select profile as profile, address as address, count(profile) as count " +
                    "from " +
                    "worker " +
                    "natural join organization " +
                    "where profile = :profile " +
                    "group by profile, address",
            nativeQuery = true)
    public List<Object[]> findWorkersByProfile(@Param("profile") String profile);
}
