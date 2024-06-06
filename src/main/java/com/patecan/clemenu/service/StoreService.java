package com.patecan.clemenu.service;

import com.patecan.clemenu.model.DTO.StoreDto;
import com.patecan.clemenu.model.DTO.UpdateFoodDto;
import com.patecan.clemenu.model.Food;

import java.util.List;

public interface StoreService
{
    public StoreDto registerStore(StoreDto store) throws Exception;
    public List<StoreDto> getRegisteredStores() throws Exception;
    public List<Food> updateFoodPrice(String storeId, String itemId, UpdateFoodDto updateFoodDto) throws Exception;
}
