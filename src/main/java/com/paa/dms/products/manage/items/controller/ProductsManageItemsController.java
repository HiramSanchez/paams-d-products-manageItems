package com.paa.dms.products.manage.items.controller;

import com.paa.dms.products.manage.items.constants.APIConstants;
import com.paa.dms.products.manage.items.model.RequestNewProductEntity;
import com.paa.dms.products.manage.items.model.RequestRetrieveFilterEntity;
import com.paa.dms.products.manage.items.model.RequestRetrieveProductEntity;
import com.paa.dms.products.manage.items.model.ResponseRetrieveFilterEntity;
import com.paa.dms.products.manage.items.service.ProductsManageItemsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(APIConstants.BASE_PATH)
public class ProductsManageItemsController {
    @Autowired
    private ProductsManageItemsService productsManageItemsService;
    @Autowired
    private APIConstants apiConstants;

    /**
     * Endpoint for creating a new product.
     *
     * @param httpHeaders Headers containing user information (uid)
     * @param userRequest Request body containing product details
     * @return ResponseEntity with status 200 OK when product is successfully created
     */
    @PostMapping(path = APIConstants.CREATE_NEW_ITEM_ENDPOINT)
    public ResponseEntity<String> createProduct(@RequestHeader HttpHeaders httpHeaders,
                                                @Valid @RequestBody RequestNewProductEntity userRequest) {
        log.debug(apiConstants.getLOG_NEW_ITEM_ENDPOINT());
        return productsManageItemsService.saveProduct(httpHeaders,userRequest);
    }

    /**
     * Endpoint for retrieving a specific product.
     *
     * @param httpHeaders Headers containing user information (uid)
     * @param userRequest Request body containing product id
     * @return ResponseEntity with the product data
     */
    @GetMapping(path = APIConstants.READ_ITEM_DATA_ENDPOINT)
    public ResponseEntity<RequestNewProductEntity> getProduct(@RequestHeader HttpHeaders httpHeaders,
                                                             @Valid @RequestBody RequestRetrieveProductEntity userRequest) {
        log.debug(apiConstants.getLOG_READ_DATA_ENDPOINT());
        return productsManageItemsService.getProduct(httpHeaders, userRequest);
    }

    /**
     * Endpoint for retrieving all products matching filters.
     *
     * @param httpHeaders Headers containing user information (uid)
     * @param userRequest Request body containing list of filters
     * @return ResponseEntity with a list products data
     */
    @GetMapping(path = APIConstants.RETRIEVE_ITEM_LIST_ENDPOINT)
    public ResponseEntity<ResponseRetrieveFilterEntity> getProductFilter(@RequestHeader HttpHeaders httpHeaders,
                                                                         @Valid @RequestBody RequestRetrieveFilterEntity userRequest) {
        log.debug(apiConstants.getLOG_READ_FILTER_ENDPOINT());
        return productsManageItemsService.getProductList(httpHeaders, userRequest);
    }

    /**
     * Endpoint for deleting a specific product permanently.
     *
     * @param httpHeaders Headers containing user information (uid)
     * @param userRequest Request body containing the productID to delete
     * @return ResponseEntity with status 200 OK upon successful deletion
     */
    @DeleteMapping(path = APIConstants.DELETE_PRODUCT_ENDPOINT)
    public ResponseEntity<String> deleteProduct(@Valid @RequestBody RequestRetrieveProductEntity userRequest,
                                                @RequestHeader HttpHeaders httpHeaders) {
        log.debug(apiConstants.getLOG_DELETE_ITEM_ENDPOINT());
        return productsManageItemsService.deleteProduct(userRequest,httpHeaders);
    }
}
