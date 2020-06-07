package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.DataBase.Domain.GoodsPrice;
import ru.nsu.fit.DataBase.NewDomains.GoodsAndCompany;

import java.util.List;

public interface GoodsPriceRepo extends CrudRepository<GoodsPrice, Integer> {
    public List<GoodsPrice> findAll();
    public GoodsPrice findByGoodsId(int goodsId);

    @Query(value = "select name_of_goods, company, sum(count) from goods_price " +
            "natural join item " +
            "group by name_of_goods, company " +
            "order by sum(count) desc " +
            "limit :limitCount ",
    nativeQuery = true)
    public List<Object[]> findTop(@Param("limitCount") int limitCount);

    @Query(value = "select name_of_goods, company, sum(count) from goods_price " +
            "natural join item " +
            "natural join check_entity " +
            "natural join organization " +
            "where address in :orgs " +
            "group by name_of_goods, company " +
            "order by sum(count) desc " +
            "limit :limitCount ",
            nativeQuery = true)
    public List<Object[]> findTopInOrgs(@Param("limitCount") int limitCount,
                                        @Param("orgs") List<String> orgs);

}
