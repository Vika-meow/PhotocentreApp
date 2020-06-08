package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.DataBase.Domain.Customer;

import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer, String> {
    public List<Customer> findAll();
    public Customer findByName(String name);
    public List<Customer> findByNameIn(List<String> name);

    @Query(value="select name from customer " +
            "natural join check_entity " +
            "natural join item " +
            "natural join order_price " +
            "where discount_card = :discountCard " +
            "group by name " +
            "having sum(count) >= :volume ",
            nativeQuery = true
    )
    public List<String> findByVolume(@Param("discountCard") boolean discountCard,
            @Param("volume") int volume);
}
