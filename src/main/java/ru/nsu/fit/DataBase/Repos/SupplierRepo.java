package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Integer> {
    public Supplier findBySupplierId(int supplierId);
}
