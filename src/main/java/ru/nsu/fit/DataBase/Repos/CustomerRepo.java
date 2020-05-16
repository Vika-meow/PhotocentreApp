package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.Customer;

public interface CustomerRepo extends CrudRepository<Customer, String> {
    public Customer findByName(String name);
}
