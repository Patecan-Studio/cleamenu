package com.patecan.clemenu.service;

import com.patecan.clemenu.model.DTO.FoodDto;
import com.patecan.clemenu.model.DTO.StoreDto;
import com.patecan.clemenu.model.DTO.UpdateFoodDto;
import com.patecan.clemenu.model.Food;
import com.patecan.clemenu.model.schema.StoreSchema;
import com.patecan.clemenu.repository.StoreRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StoreServiceImplementation implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public StoreDto registerStore(StoreDto storeDto) throws Exception {
        if (storeDto != null) {

            StoreSchema res = storeRepository.save(storeDto.convertToSchema());
            return new StoreDto(res.getId(), res.getCode(), res.getItems().stream()
                    .map(item -> new FoodDto(item.getFoodId(), item.getName(), item.getPrice(), item.getVer()))
                    .collect(Collectors.toList()));
        }
        throw new Exception("Store is null");
    }

    @Override
    public List<StoreDto> getRegisteredStores() throws Exception {
        List<StoreSchema> storeSchemaList = storeRepository.findAll();
        if (!storeSchemaList.isEmpty()) {

            return storeSchemaList
                    .stream()
                    .map(storeSchema -> new StoreDto(
                            storeSchema.getId(),
                            storeSchema.getCode(),
                            storeSchema.getItems()
                                    .stream()
                                    .map(foodSchema -> new FoodDto(
                                            foodSchema.getFoodId(),
                                            foodSchema.getName(),
                                            foodSchema.getPrice(),
                                            foodSchema.getVer()))
                                    .toList())
                    ).toList();
        }
        throw new Exception("User is null");
    }

    @Override
    public List<Food> updateFoodPrice(String storeId, String itemId, UpdateFoodDto updateFoodDto) throws Exception {
        System.out.println("Store ID: " + storeId);
        System.out.println("Item ID: " + itemId);
        System.out.println("Update Food Info: " + updateFoodDto);

        Query query = new Query();
        query.addCriteria(Criteria.where("_id")
                .is(storeId)); // find the parent
        query.addCriteria(Criteria.where("items._id")
                .is(new ObjectId(itemId)));
        StoreSchema rs = mongoTemplate.findOne(query, StoreSchema.class);
        System.out.println(rs);

        Update update = new Update();
        update.set("items.$.price", updateFoodDto.price());

        StoreSchema updatedStoreSchema = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), StoreSchema.class);

        if(updatedStoreSchema!=null){
            return updatedStoreSchema.getItems().stream().filter(item -> Objects.equals(item.getFoodId(), itemId)).toList();
        } else{
            return null;
        }
    }

}
