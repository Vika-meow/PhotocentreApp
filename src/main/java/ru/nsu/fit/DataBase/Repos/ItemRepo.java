package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.CheckEntity;
import ru.nsu.fit.DataBase.Domain.DeliveryInput;
import ru.nsu.fit.DataBase.Domain.Item;

import java.util.List;

public interface ItemRepo extends CrudRepository<Item, Integer> {
    public List<Item> findByCheck(CheckEntity check);
}
