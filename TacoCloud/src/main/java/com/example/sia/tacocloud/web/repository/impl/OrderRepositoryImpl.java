//package com.example.sia.tacocloud.web.repository.impl;
//
//import com.example.sia.tacocloud.web.pojo.Order;
//import com.example.sia.tacocloud.web.pojo.Taco;
//import com.example.sia.tacocloud.web.repository.OrderRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
///**
// * @author Coulomb
// * @since 2021/11/4 14:45
// */
//@Repository
//public class OrderRepositoryImpl implements OrderRepository {
//
//    private SimpleJdbcInsert orderInserter;
//    private SimpleJdbcInsert orderTacoInserter;
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
//
//        orderInserter = new SimpleJdbcInsert(jdbcTemplate)
//                .withTableName("taco_order")
//                .usingGeneratedKeyColumns("id");
//
//        orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
//                .withTableName("taco_order_tacos");
//
//        objectMapper = new ObjectMapper();
//    }
//
//    @Override
//    public Order save(Order order) {
//
//        order.setPlacedAt(new Date());
//        long orderId = saveOrderInfo(order);
//        order.setId(orderId);
//
//        List<Taco> tacos = order.getTacos();
//        for (Taco taco : tacos) {
//            saveTacoToOrder(taco, orderId);
//        }
//
//        return order;
//    }
//
//    private long saveOrderInfo(Order order) {
//
//        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
//        values.put("placedAt", order.getPlacedAt());
//
//        long orderId = orderInserter.executeAndReturnKey(values).longValue();
//        return orderId;
//    }
//
//    private void saveTacoToOrder(Taco taco, long orderId) {
//
//        Map<String, Object> values = new HashMap<>();
//        values.put("taco_order_id", orderId);
//        values.put("taco_id", taco.getId());
//        orderTacoInserter.execute(values);
//    }
//}
