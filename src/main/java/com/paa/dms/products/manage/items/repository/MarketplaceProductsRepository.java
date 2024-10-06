package com.paa.dms.products.manage.items.repository;

import com.paa.dms.products.manage.items.constants.MongoConstants;
import com.paa.dms.products.manage.items.model.MongoProductsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.Optional;

/**
 * Repository interface for managing products in MongoDB.
 * Extends MongoRepository to leverage Spring Data MongoDB functionalities.
 */
public interface MarketplaceProductsRepository extends MongoRepository<MongoProductsEntity, String>, MarketplaceProductsRepositoryCustom {
    /**
     * Custom query to find a specific products by its product ID.
     *
     * @param productId The unique identifier of the product
     * @return An Optional containing the found product, or empty if not found
     */
    @Query(value = MongoConstants.FIND_BY_PRODUCT_ID_QUERY)
    Optional<MongoProductsEntity> findByProductId(String productId);
}
