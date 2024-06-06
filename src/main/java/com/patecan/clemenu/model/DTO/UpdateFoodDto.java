package com.patecan.clemenu.model.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record UpdateFoodDto(
        @Min(0)
        double price) {
}
