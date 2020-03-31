package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.Worker;

public interface WorkerRepo extends CrudRepository<Worker, String> {
}
