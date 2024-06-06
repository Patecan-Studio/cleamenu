package com.patecan.clemenu.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
public class Food {
    @MongoId
    private String foodId;
    private String name;
    private double price;
    private String ver;

    public Food() {
    }

    public Food(String foodId, String name, double price, String ver) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.ver = ver;
    }
}
