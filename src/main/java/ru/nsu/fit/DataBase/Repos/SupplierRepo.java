package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.DataBase.Domain.Supplier;

import java.sql.Date;
import java.util.List;

public interface SupplierRepo extends CrudRepository<Supplier, Integer> {
    public List<Supplier> findAll();
    public Supplier findBySupplierId(int supplierId);
    public List<Supplier> findBySupplierIdIn(List<Integer> ids);

    @Query(value = "select supplier.supplier_id from supplier " +
            " natural join delivery " +
            " natural join delivery_input " +
            "where date between  :afterDate AND :beforeDate " +
            "group by supplier.supplier_id " +
            "having sum(count) >= :volume",
            nativeQuery = true
    )
    public List<Integer> findByDateBetweenAndVolume(@Param("afterDate")Date afterDate,
                                                                @Param("beforeDate") Date beforeDate,
                                                                @Param("volume") int volume);

    @Query(value = "select supplier.supplier_id from supplier " +
            " natural join delivery " +
            " natural join delivery_input " +
            "where date between  :afterDate AND :beforeDate " +
            "and goods_id in :goodsList " +
            "group by supplier.supplier_id " +
            "having sum(count) >= :volume",
    nativeQuery = true
    )
    public List<Integer> findByDateBetweenAndVolumeAndGoodsList(@Param("afterDate")Date afterDate,
                                      @Param("beforeDate") Date beforeDate,
                                           @Param("volume") int volume,
                                                    @Param("goodsList") List<Integer> goodsList);
}
