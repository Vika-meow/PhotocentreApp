package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.Delivery;

public interface DeliveryRepo extends CrudRepository<Delivery, Integer> {
    public Delivery findByDeliveryId(int deliveryId);
    public void deleteById(int deliveryId);
}
