package com.patecan.clemenu.model.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;

public record FoodDto(
        @MongoId
        String foodId,
        @NotEmpty(message = "Food name cannot be empty")
        String name,
        @Min(0)
        double price,
        String ver
) {
}
