package com.patecan.clemenu.model.schema;

import com.patecan.clemenu.model.Food;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Setter
@Getter
@Document(collection = "stores")
public class StoreSchema
{
    @Id
    private String id;
    private String code;
    private List<Food> items;

    public StoreSchema(String id, String code, List<Food> items) {
        this.id = id;
        this.code = code;
        this.items = items;
    }

    public StoreSchema() {}
}

