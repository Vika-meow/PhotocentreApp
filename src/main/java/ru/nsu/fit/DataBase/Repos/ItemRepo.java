package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.CheckEntity;
import ru.nsu.fit.DataBase.Domain.Item;

import java.sql.Date;
import java.util.List;

public interface ItemRepo extends CrudRepository<Item, Integer> {
    public List<Item> findByCheck(CheckEntity check);

    public List<Item> findByOrderIdIsNotNull();
    public List<Item> findByOrderId_OrderIdIn(List<Integer> ordersId);

    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNull();
    public List<Item> findByOrderId_OrderIdInAndCheck_Organization_BranchOfficeAdressIsNull(List<Integer> ordersId);

    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNotNull();
    public List<Item> findByOrderId_OrderIdInAndCheck_Organization_BranchOfficeAdressIsNotNull(List<Integer> ordersId);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetween(Date start, Date end);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetween(List<Integer> ordersId,Date start, Date end);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(Date start, Date end);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(List<Integer> ordersId, Date start, Date end);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(Date start, Date end);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(List<Integer> ordersId, Date start, Date end);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_AddressIn(Date start, Date end, List<String> addresses);

    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_AddressIn(List<String> addresses);

    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_AddressIn(List<Integer> ordersId, Date start, Date end, List<String> addresses);
    public List<Item> findByOrderId_OrderIdInAndCheck_Organization_AddressIn(List<Integer> ordersId, List<String> addresses);
}
