package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.DeliveryInput;

import java.util.List;

public interface DeliveryInputRepo extends CrudRepository<DeliveryInput, Integer> {
    public List<DeliveryInput> findByDelivery_DeliveryId(int deliveryId);
}
