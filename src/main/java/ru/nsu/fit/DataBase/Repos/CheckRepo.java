package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.CheckEntity;

public interface CheckRepo extends CrudRepository<CheckEntity, Integer> {
    public CheckEntity findByCheckId(int checkId);
}
