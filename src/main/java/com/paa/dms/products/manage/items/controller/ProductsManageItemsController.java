package com.paa.dms.products.manage.items.controller;

import com.paa.dms.products.manage.items.constants.APIConstants;
import com.paa.dms.products.manage.items.model.RequestNewProductEntity;
import com.paa.dms.products.manage.items.model.RequestRetrieveFilterEntity;
import com.paa.dms.products.manage.items.model.RequestRetrieveProductEntity;
import com.paa.dms.products.manage.items.model.ResponseRetrieveFilterEntity;
import com.paa.dms.products.manage.items.service.ProductsManageItemsService;
import com.paa.dms.products.manage.items.service.ProductsManageItemsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation( //swagger config
            summary = "Create a new item",
            description = "Creates a new product based on the provided details.",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,name = "uid", description = "Header containing user id, string of 9 digit number", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product details for the new item", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product Created"),
                    @ApiResponse(responseCode = "400", description = "{field} + {validation error details}"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred")
            }
    )
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
     * @return ResponseEntity with the product's data
     */
    @Operation( //swagger config
            summary = "Retrieve specific product",
            description = "Fetches an specific product using the prouctId provided.",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,name = "uid", description = "Header containing user id, string of 9 digit number", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product data retrieve"),
                    @ApiResponse(responseCode = "404", description = "Resource not found in DB"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred")
            }
    )
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
    @Operation( //swagger config
            summary = "Retrieve list of products",
            description = "Fetches a list of products using the filter(s) provided (any parameter can be used as a filter).",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,name = "uid", description = "Header containing user id, string of 9 digit number", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product list data retrieve"),
                    @ApiResponse(responseCode = "404", description = "Resource not found in DB"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred")
            }
    )
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
    @Operation( //swagger config
            summary = "Delete product",
            description = "Permanently deletes a product based on the productId provided.",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,name = "uid", description = "Header containing user id, string of 9 digit number", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Id of the order to delete", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "{field} + {validation error details}"),
                    @ApiResponse(responseCode = "404", description = "Resource not found in DB"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred")
            }
    )
    @DeleteMapping(path = APIConstants.DELETE_PRODUCT_ENDPOINT)
    public ResponseEntity<String> deleteProduct(@Valid @RequestBody RequestRetrieveProductEntity userRequest,
                                                @RequestHeader HttpHeaders httpHeaders) {
        log.debug(apiConstants.getLOG_DELETE_ITEM_ENDPOINT());
        return productsManageItemsService.deleteProduct(userRequest,httpHeaders);
    }
}
