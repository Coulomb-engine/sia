package com.example.sia.tacocloud.web.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "taco_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 794052784161513763L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @NotBlank(message = "Name is required 请输入吃货名字")
    private String name;

    @NotBlank(message = "Street is required 请输入吃货街道")
    private String street;

    @NotBlank(message = "City is required 请输入吃货城市")
    private String city;

    @NotBlank(message = "State is required 请输入吃货所在州")
    private String state;

    @NotBlank(message = "Zip code is required 请输入吃货邮编")
    private String zip;

    @CreditCardNumber(message = "Not a valid credit card number 信用卡号不正确")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY 有效期格式应该是 MM/YY 这样的")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV 安全码不正确")
    private String ccCVV;

    @ManyToOne
    private User user;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco taco) {
        tacos.add(taco);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
