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

    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNull();

    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNotNull();

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetween(Date start, Date end);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(Date start, Date end);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(Date start, Date end);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_AddressIn(Date start, Date end, List<String> addresses);

    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_AddressIn(List<String> addresses);
}
