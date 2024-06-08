package com.patecan.clemenu.controller;

import com.patecan.clemenu.model.DTO.StoreDto;
import com.patecan.clemenu.model.DTO.UpdateFoodDto;
import com.patecan.clemenu.model.DTO.response.FoodResponseDto;
import com.patecan.clemenu.model.Food;
import com.patecan.clemenu.model.schema.StoreSchema;
import com.patecan.clemenu.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController
{
    @Autowired
    private StoreService storeService;



    //Registers the users
    @PostMapping("/register")
    public ResponseEntity<StoreDto> register(@RequestBody StoreDto storeDto) throws Exception
    {
        if(storeDto!=null)
        {
            storeService.registerStore(storeDto);
            return new ResponseEntity<>(storeDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{storeId}/items/{itemId}")
    public ResponseEntity<List<Food>> updateItem(@PathVariable String storeId,
                                                 @PathVariable String itemId,
                                                 @RequestBody UpdateFoodDto updateFoodDto) throws Exception {

        List<Food> updatedFood = storeService.updateFoodPrice(storeId, itemId, updateFoodDto);
        if(updatedFood != null && !updatedFood.isEmpty()) {
            return new ResponseEntity<>(updatedFood, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("stores/{storeId}")
    public ResponseEntity<List<FoodResponseDto>> getMenuByStoreId(@PathVariable String storeId) throws Exception {

        List<Food> foods = storeService.getMenuByStoreId(storeId);
        if(!foods.isEmpty()) {
            List<FoodResponseDto> result = foods.stream().map(food -> new FoodResponseDto(food.getFoodId(), food.getName(), food.getPrice(), food.getVer())).toList();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //Get all the registered users.
    @GetMapping("/stores")
    public ResponseEntity<List<StoreDto>> getRegisteredUser() throws Exception
    {
        List<StoreDto> storeList =storeService.getRegisteredStores();
        if(!storeList.isEmpty()) {
            return new ResponseEntity<>(storeList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
