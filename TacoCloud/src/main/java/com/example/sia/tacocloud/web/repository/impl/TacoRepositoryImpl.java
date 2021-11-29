//package com.example.sia.tacocloud.web.repository.impl;
//
//import com.example.sia.tacocloud.web.pojo.Ingredient;
//import com.example.sia.tacocloud.web.pojo.Taco;
//import com.example.sia.tacocloud.web.repository.TacoRepository;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Timestamp;
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.Date;
//
///**
// * @author Coulomb
// * @since 2021/11/4 10:46
// */
//@Repository
//public class TacoRepositoryImpl implements TacoRepository {
//
//    private JdbcTemplate jdbcTemplate;
//
//    public TacoRepositoryImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public Taco save(Taco taco) {
//
//        long tacoId = saveTacoInfo(taco);
//        taco.setId(tacoId);
//
//        for (Ingredient ingredient : taco.getIngredients()) {
//            saveIngredientToTaco(ingredient, tacoId);
//        }
//
//        return taco;
//    }
//
//    private long saveTacoInfo(Taco taco) {
//
//        taco.setCreatedAt(new Date());
//        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into taco (name, created_at) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
//        pscf.setReturnGeneratedKeys(true);
//        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(psc, keyHolder);
//
//        return keyHolder.getKey().longValue();
//    }
//
//    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
//        jdbcTemplate.update("insert into taco_ingredients (taco_id, ingredient_id) values (?, ?)", tacoId, ingredient.getId());
//    }
//}
