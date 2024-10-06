package com.paa.dms.products.manage.items.model;

import lombok.Data;

import java.util.List;
@Data
public class ResponseRetrieveFilterEntity {
    private List<MongoProductsEntity> products;
}
