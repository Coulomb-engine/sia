package com.example.sia.tacocloud.web.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Ingredient {

    @Id
    private final String id;

    private final String name;

    private final Type type;

    public static enum Type {
        WRAP, // 面饼
        PROTEIN, // 蛋白质
        VEGGIES, // 蔬菜
        CHEESE, // 奶酪
        SAUCE // 酱汁
    }
}
