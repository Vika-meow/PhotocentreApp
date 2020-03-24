package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.ServicePrice;

public interface ServicePriceRepo extends CrudRepository<ServicePrice, String> {
}
