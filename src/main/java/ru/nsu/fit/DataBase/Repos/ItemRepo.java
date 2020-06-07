package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.DataBase.Domain.CheckEntity;
import ru.nsu.fit.DataBase.Domain.Item;

import java.sql.Date;
import java.util.List;

public interface ItemRepo extends CrudRepository<Item, Integer> {
    public void deleteById(int itemId);

    public List<Item> findByCheck(CheckEntity check);

    public List<Item> findByOrderIdIsNotNull();
    public List<Item> findByOrderIdIsNotNull(Sort sort);

    //public List<Item> findByOrderIdIsNotNullOrderByCount();
   /* public List<Item> findByOrderIdIsNotNullOrderByCheck_Organization_Address();
    public List<Item> findByOrderIdIsNotNullOrderByCheck_Date();
    public List<Item> findByOrderIdIsNotNullOrderByOrderId_Format();
    public List<Item> findByOrderIdIsNotNullOrderByOrderId_OrderType();
    public List<Item> findByOrderIdIsNotNullOrderByOrderId_PaperType();
    public List<Item> findByOrderIdIsNotNullOrderByCheck_Customer_Name();*/
    //public List<Item> findByOrderIdIsNotNullOrderByCheck_Customer_Name();


    public List<Item> findByOrderId_OrderIdIn(List<Integer> ordersId);
    public List<Item> findByOrderId_OrderIdIn(List<Integer> ordersId, Sort sort);

    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNull();
    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNull(Sort sort);
    public List<Item> findByOrderId_OrderIdInAndCheck_Organization_BranchOfficeAdressIsNull(List<Integer> ordersId);
    public List<Item> findByOrderId_OrderIdInAndCheck_Organization_BranchOfficeAdressIsNull(List<Integer> ordersId, Sort sort);

    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNotNull();
    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNotNull(Sort sort);
    public List<Item> findByOrderId_OrderIdInAndCheck_Organization_BranchOfficeAdressIsNotNull(List<Integer> ordersId);
    public List<Item> findByOrderId_OrderIdInAndCheck_Organization_BranchOfficeAdressIsNotNull(List<Integer> ordersId, Sort sort);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetween(Date start, Date end);
    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetween(Date start, Date end, Sort sort);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetween(List<Integer> ordersId,Date start, Date end);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetween(List<Integer> ordersId,Date start, Date end, Sort sort);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(Date start, Date end);
    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(Date start, Date end, Sort sort);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(List<Integer> ordersId, Date start, Date end);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(List<Integer> ordersId, Date start, Date end, Sort sort);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(Date start, Date end);
    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(Date start, Date end, Sort sort);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(List<Integer> ordersId, Date start, Date end);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(List<Integer> ordersId, Date start, Date end, Sort sort);

    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_AddressIn(Date start, Date end, List<String> addresses);
    public List<Item> findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_AddressIn(Date start, Date end, List<String> addresses, Sort sort);

    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_AddressIn(List<String> addresses);
    public List<Item> findByOrderIdIsNotNullAndCheck_Organization_AddressIn(List<String> addresses, Sort sort);

    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_AddressIn(List<Integer> ordersId, Date start, Date end, List<String> addresses);
    public List<Item> findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_AddressIn(List<Integer> ordersId, Date start, Date end, List<String> addresses, Sort sort);
    public List<Item> findByOrderId_OrderIdInAndCheck_Organization_AddressIn(List<Integer> ordersId, List<String> addresses);
    public List<Item> findByOrderId_OrderIdInAndCheck_Organization_AddressIn(List<Integer> ordersId, List<String> addresses, Sort sort);


    public List<Item> findByOrderId_OrderTypeIsAndCheck_DateBetweenAndCheck_Organization_AddressIn(String orderType, Date start, Date end, List<String> addresses);
    public List<Item> findByOrderId_OrderTypeIs(String orderType);

    //query9
    public List<Item> findByGoodsIdNotNullAndCheck_DateBetweenAndCheck_Organization_AddressIn(Date start, Date end, List<String> addresses);
    public List<Item> findByGoodsIdNotNull();

    //query11
    @Query(value = "select name_of_goods, company, goods_model, buy_price, sell_price, sum(count) from item " +
            "natural join goods_price " +
            "natural join check_entity " +
            "natural join organization " +
            "where date between :start and :end " +
            "and address in :orgs " +
            "group by name_of_goods, company, goods_model, buy_price, sell_price " +
            "order by sum(count) desc",
    nativeQuery = true)
    public List<Object[]> findByDateAndAddress(@Param("start") Date start,
                                               @Param("end") Date end,
                                               @Param("orgs") List<String> orgs);

    @Query(value = "select name_of_goods, company, goods_model, buy_price, sell_price, sum(count) from item " +
            "natural join goods_price " +
            "group by name_of_goods, company, goods_model, buy_price, sell_price " +
            "order by sum(count) desc",
            nativeQuery = true)
    public List<Object[]> findByGoods();
}
