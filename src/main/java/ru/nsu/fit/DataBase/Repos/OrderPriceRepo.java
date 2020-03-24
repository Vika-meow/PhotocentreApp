package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.OrderPrice;

public interface OrderPriceRepo extends CrudRepository<OrderPrice, String> {
}
