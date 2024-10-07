package com.paa.dms.products.manage.items.model;

import lombok.Data;

import java.util.List;
@Data
public class ResponseRetrieveFilterEntity {
    /** List of products matching the filters provided. */
    private List<MongoProductsEntity> products;
}
