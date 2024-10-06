package com.paa.dms.products.manage.items.repository;

import com.paa.dms.products.manage.items.model.FilterEntity;
import com.paa.dms.products.manage.items.model.MongoProductsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
@Slf4j
public class MarketplaceProductsRepositoryImpl implements MarketplaceProductsRepositoryCustom{
    private final MongoTemplate mongoTemplate;

    public MarketplaceProductsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<MongoProductsEntity> findProductsByFilters(List<FilterEntity> filters) {
        Query query = new Query();
        for (FilterEntity filter : filters) {
            query.addCriteria(Criteria.where(filter.getFilter()).is(filter.getValue()));
        }
        return mongoTemplate.find(query, MongoProductsEntity.class);
    }
}
