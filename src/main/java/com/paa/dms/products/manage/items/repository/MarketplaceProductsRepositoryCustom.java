package com.paa.dms.products.manage.items.repository;

import com.paa.dms.products.manage.items.model.FilterEntity;
import com.paa.dms.products.manage.items.model.MongoProductsEntity;

import java.util.List;
/**
 * Custom Repository interface for managing dynamic querys in MongoDB.
 */
public interface MarketplaceProductsRepositoryCustom {
    List<MongoProductsEntity> findProductsByFilters(List<FilterEntity> filters);

}
