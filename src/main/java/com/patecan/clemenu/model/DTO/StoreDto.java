package com.patecan.clemenu.model.DTO;

import com.patecan.clemenu.model.Food;
import com.patecan.clemenu.model.schema.StoreSchema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.stream.Collectors;

public record StoreDto(String id,
                       @NotEmpty(message = "Store CODE is mandatory")
                       String code,
                       @NotEmpty(message = "Food cannot be empty")
                       List<FoodDto> items
) {

    public StoreSchema convertToSchema() {
        List<Food> foodList = items
                .stream()
                .map(item -> new Food(item.foodId(),item.name(), item.price(), item.ver()))
                .collect(Collectors.toList());
        StoreSchema storeSchema = new StoreSchema(this.id, this.code, foodList);
        return storeSchema;
    }

}
