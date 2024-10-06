package com.paa.dms.products.manage.items.service;

import com.paa.dms.products.manage.items.exception.custom.NoDataFoundException;
import com.paa.dms.products.manage.items.model.*;
import com.paa.dms.products.manage.items.repository.MarketplaceProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductsManageItemsService {
    @Autowired
    private MarketplaceProductsRepository marketplaceProductsRepository;


    /**
     * Create Product Service
     * Creates a new product and saves it to the database.
     * @param httpHeaders HTTP headers to retrieve the user identifier (uid)
     * @param userRequest the product details from the user's request
     * @return ResponseEntity confirming that the order was created
     */
    public ResponseEntity<String> saveProduct(HttpHeaders httpHeaders,
                                              RequestNewProductEntity userRequest) {
        String uid = httpHeaders.getFirst("uid").toString();
        log.debug("REQUEST >>> " + "User with id #" + uid + " requested creation of product #" + userRequest.getProductId().toString());

        MongoProductsEntity newProduct = new MongoProductsEntity();
        newProduct.setColor(userRequest.getColor());
        newProduct.setDescription(userRequest.getDescription());
        newProduct.setGender(userRequest.getGender());
        newProduct.setImageId(userRequest.getImageId());
        newProduct.setName(userRequest.getName());
        newProduct.setProductId(userRequest.getProductId());
        newProduct.setProductType(userRequest.getProductType());
        newProduct.setSize(userRequest.getSize());
        newProduct.setPrice(userRequest.getPrice());

        marketplaceProductsRepository.save(newProduct);
        ResponseEntity response = ResponseEntity.ok("Product Created");
        log.debug("RESPONSE >>> " + response);
        return response;
    }

    /**
     * Get Filtered Products Service
     * Retrieves a list of products from the database, matching the filters provided.
     * @param httpHeaders HTTP headers to retrieve the user identifier (uid)
     * @param userRequest with a list of the filters applied to the request
     * @return ResponseEntity containing a list of the products matching the request
     */
    public ResponseEntity<ResponseRetrieveFilterEntity> getProductList(HttpHeaders httpHeaders,
                                                                       RequestRetrieveFilterEntity userRequest) {
        String uid = httpHeaders.getFirst("uid").toString();
        List<FilterEntity> filters = userRequest.getFilters();
        log.debug("REQUEST >>> " + "User with id #" + uid + " requested data of products with filters: " + filters.toString());

        var foundItems = marketplaceProductsRepository.findProductsByFilters(filters);


        if (!foundItems.isEmpty()) {
            ResponseRetrieveFilterEntity response = new ResponseRetrieveFilterEntity();
            response.setProducts(foundItems);

            log.debug("RESPONSE >>> " + response);
            return ResponseEntity.ok(response);
        }
        throw new NoDataFoundException();

    }

    /**
     * Get Product Service
     * Retrieves a product from the database.
     * @param httpHeaders HTTP headers to retrieve the user identifier (uid)
     * @param userRequest the product id from the user's request
     * @return ResponseEntity with the data of the product requested
     */
    public ResponseEntity<RequestNewProductEntity> getProduct(HttpHeaders httpHeaders,
                                                              RequestRetrieveProductEntity userRequest) {
        String uid = httpHeaders.getFirst("uid").toString();
        String productId = userRequest.getProductId().toString();
        log.debug("REQUEST >>> " + "User with id #" + uid + " requested data of product #" + productId);

        var foundProduct = findProductByProductId(productId);

        if (!foundProduct.isEmpty()) {
            var product = foundProduct.get();
            RequestNewProductEntity response = RequestNewProductEntity.builder()
                    .productId(product.getProductId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .color(product.getColor())
                    .gender(product.getGender())
                    .imageId(product.getImageId())
                    .productType(product.getProductType())
                    .size(product.getSize())
                    .price(product.getPrice())
                    .build();

            log.debug("RESPONSE >>> " + response);
            return ResponseEntity.ok(response);
        }
        throw new NoDataFoundException();
    }

    /**
     * Finds a product from the repository.
     * @param productId the user's identifier
     * @return The product associated with the productId
     */
    public Optional<MongoProductsEntity> findProductByProductId(String productId) {
        return marketplaceProductsRepository.findByProductId(productId);
    }

    /**
     * Delete Product Service
     * Deletes a product from the DB.
     * @param userRequest containing productId
     * @param httpHeaders HTTP headers to retrieve the user identifier (uid)
     * @return ResponseEntity confirming the product deletion
     */
    public ResponseEntity<String> deleteProduct(RequestRetrieveProductEntity userRequest,
                                                HttpHeaders httpHeaders) {
        String uid = httpHeaders.getFirst("uid").toString();
        log.debug("REQUEST >>> " + uid + " requested to delete product number #" + userRequest.getProductId());
        MongoProductsEntity storedProductData = findProductByProductId(userRequest.getProductId())
                .orElseThrow(() -> new NoDataFoundException());
        marketplaceProductsRepository.deleteById(storedProductData.get_id().toString());
        ResponseEntity response = ResponseEntity.ok("Product deleted successfully");
        log.debug("RESPONSE >>> " + response);
        return response;
    }

}
