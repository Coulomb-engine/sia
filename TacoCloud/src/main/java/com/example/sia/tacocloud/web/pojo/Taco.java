package com.example.sia.tacocloud.web.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long 商品名称至少5个字")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @NotNull(message = "配料表不能为空")
    @Size(min = 2, message = "You must choose at least 2 ingredient 至少选择2种配料")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
