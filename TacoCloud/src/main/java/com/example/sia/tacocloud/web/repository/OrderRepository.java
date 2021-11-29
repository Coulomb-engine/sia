package com.example.sia.tacocloud.web.repository;

import com.example.sia.tacocloud.web.pojo.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Coulomb
 * @since 2021/11/4 9:41
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

//    Order save(Order order);

    List<Order> findByZip(String deliveryZip);

    /**
     * Spring Data会将get、read、find视为同义词，都是用来获取一个或多个实体的。
     * @param zip
     * @param startDate
     * @param endDate
     * @return
     */
    List<Order> readOrdersByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);
}
