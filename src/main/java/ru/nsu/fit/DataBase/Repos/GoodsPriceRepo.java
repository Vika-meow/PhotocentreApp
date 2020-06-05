package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.GoodsPrice;

import java.util.List;

public interface GoodsPriceRepo extends CrudRepository<GoodsPrice, Integer> {
    public List<GoodsPrice> findAll();
    public GoodsPrice findByGoodsId(int goodsId);
}
