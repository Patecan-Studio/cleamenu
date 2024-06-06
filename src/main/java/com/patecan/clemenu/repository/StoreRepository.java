package com.patecan.clemenu.repository;

import com.patecan.clemenu.model.schema.StoreSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

public interface StoreRepository extends MongoRepository<StoreSchema, String>
{

}
