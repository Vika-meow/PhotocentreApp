package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.DataBase.Domain.CheckEntity;
import ru.nsu.fit.DataBase.Domain.DeliveryInput;
import ru.nsu.fit.DataBase.Domain.Item;

import java.sql.Date;
import java.util.List;

public interface ItemRepo extends CrudRepository<Item, Integer> {
    public List<Item> findByCheck(CheckEntity check);

    public List<Item> findByOrderIdIsNotNull();

    /*Query("select  from item natural join check" +
            "where order_id != null" +
            "and date between :after and :before")*/
    //public List<Item> findByOrderIdIsNotNullAndDateBetween(@Param("after")Date after, @Param("before") Date before);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetween(Date start, Date end);
}
