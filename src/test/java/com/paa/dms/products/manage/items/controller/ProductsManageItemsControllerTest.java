package com.paa.dms.products.manage.items.controller;

import com.paa.dms.products.manage.items.constants.APIConstants;
import com.paa.dms.products.manage.items.model.RequestNewProductEntity;
import com.paa.dms.products.manage.items.model.RequestRetrieveFilterEntity;
import com.paa.dms.products.manage.items.model.RequestRetrieveProductEntity;
import com.paa.dms.products.manage.items.model.ResponseRetrieveFilterEntity;
import com.paa.dms.products.manage.items.service.ProductsManageItemsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductsManageItemsControllerTest {
    @InjectMocks
    private ProductsManageItemsController productsManageItemsController;
    @Mock
    private ProductsManageItemsService productsManageItemsService;
    @Mock
    private APIConstants apiConstants;
    private HttpHeaders httpHeaders;
    private RequestRetrieveProductEntity requestRetrieveProductEntity;
    private RequestRetrieveFilterEntity requestRetrieveFilterEntity;
    private RequestNewProductEntity requestNewProductEntity;
    @BeforeEach
    public void setUp() {
        httpHeaders = new HttpHeaders();
        httpHeaders.set("uid", "123456789");
        requestRetrieveProductEntity = new RequestRetrieveProductEntity();
        requestRetrieveFilterEntity = new RequestRetrieveFilterEntity();
        requestNewProductEntity = new RequestNewProductEntity();
    }
    @Test
    void createProduct_ShouldReturnOk_WhenProductIsCreated() {
        // Arrange
        when(productsManageItemsService.saveProduct(any(), any())).thenReturn(ResponseEntity.ok("Product Created"));
        // Act
        ResponseEntity<String> response = productsManageItemsController.createProduct(httpHeaders, requestNewProductEntity);
        // Assert
        verify(productsManageItemsService).saveProduct(httpHeaders, requestNewProductEntity);
        assertEquals(ResponseEntity.ok("Product Created"), response);
    }
    @Test
    void getProduct_ShouldReturnProduct_WhenProductExists() {
        // Arrange
        when(productsManageItemsService.getProduct(any(), any())).thenReturn(ResponseEntity.ok(requestNewProductEntity));
        // Act
        ResponseEntity<RequestNewProductEntity> response = productsManageItemsController.getProduct(httpHeaders, requestRetrieveProductEntity);
        // Assert
        verify(productsManageItemsService).getProduct(httpHeaders, requestRetrieveProductEntity);
        assertEquals(ResponseEntity.ok(requestNewProductEntity), response);
    }
    @Test
    void getProductFilter_ShouldReturnProductList_WhenFiltersApplied() {
        // Arrange
        ResponseRetrieveFilterEntity responseRetrieveFilterEntity = new ResponseRetrieveFilterEntity();
        when(productsManageItemsService.getProductList(any(), any())).thenReturn(ResponseEntity.ok(responseRetrieveFilterEntity));
        // Act
        ResponseEntity<ResponseRetrieveFilterEntity> response = productsManageItemsController.getProductFilter(httpHeaders, requestRetrieveFilterEntity);
        // Assert
        verify(productsManageItemsService).getProductList(httpHeaders, requestRetrieveFilterEntity);
        assertEquals(ResponseEntity.ok(responseRetrieveFilterEntity), response);
    }
    @Test
    void deleteProduct_ShouldReturnOk_WhenProductIsDeleted() {
        // Arrange
        when(productsManageItemsService.deleteProduct(any(), any())).thenReturn(ResponseEntity.ok("Product deleted successfully"));
        // Act
        ResponseEntity<String> response = productsManageItemsController.deleteProduct(requestRetrieveProductEntity, httpHeaders);
        // Assert
        verify(productsManageItemsService).deleteProduct(requestRetrieveProductEntity, httpHeaders);
        assertEquals(ResponseEntity.ok("Product deleted successfully"), response);
    }


}