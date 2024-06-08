package com.patecan.clemenu.model.DTO.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.MongoId;

public record FoodResponseDto(
                                          String id,
                                          String name,
                                          double price,
                                          String ver) {
}
