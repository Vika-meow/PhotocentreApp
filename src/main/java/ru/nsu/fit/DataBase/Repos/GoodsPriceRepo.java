package ru.nsu.fit.DataBase.Repos;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.DataBase.Domain.GoodsPrice;

public interface GoodsPriceRepo extends CrudRepository<GoodsPrice, String> {
}
