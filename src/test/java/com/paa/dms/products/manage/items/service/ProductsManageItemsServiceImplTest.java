package com.paa.dms.products.manage.items.service;

import com.paa.dms.products.manage.items.exception.custom.NoDataFoundException;
import com.paa.dms.products.manage.items.model.*;
import com.paa.dms.products.manage.items.repository.MarketplaceProductsRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.http.HttpStatus.OK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsManageItemsServiceImplTest {
    @Mock
    private MarketplaceProductsRepository repository;
    @InjectMocks
    private ProductsManageItemsServiceImpl service;
    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.add("uid", "199705039");
    }

    @Test
    void testSaveProduct_Success() {
        // Arrange
        RequestNewProductEntity request = new RequestNewProductEntity();
        request.setProductId("prod-001");
        request.setName("Test Product");
        // Act
        ResponseEntity<String> response = service.saveProduct(headers, request);
        // Assert
        verify(repository, times(1)).save(any(MongoProductsEntity.class));
        assertEquals(OK, response.getStatusCode());
        assertEquals("Product Created", response.getBody());
    }

    @Test
    void testGetProductList_WithResults() {
        // Arrange
        RequestRetrieveFilterEntity filterRequest = new RequestRetrieveFilterEntity();
        FilterEntity filter = new FilterEntity();
        filter.setFilter("color");
        filter.setValue("blue"); // Supongamos que no hay productos de este color
        filterRequest.setFilters(List.of(filter));
        when(repository.findProductsByFilters(anyList())).thenReturn(List.of());
        // Act & Assert
        assertThrows(NoDataFoundException.class,
                () -> service.getProductList(headers, filterRequest));
    }

    @Test
    void testGetProductList_WithNoResults() {
        // Arrange
        RequestRetrieveFilterEntity filterRequest = new RequestRetrieveFilterEntity();
        FilterEntity filter = new FilterEntity();
        filter.setFilter("color");
        filter.setValue("red");
        filterRequest.setFilters(List.of(filter));
        when(repository.findProductsByFilters(anyList())).thenReturn(List.of(new MongoProductsEntity()));
        // Act
        ResponseEntity<ResponseRetrieveFilterEntity> response = service.getProductList(headers, filterRequest);
        // Assert
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getProducts().isEmpty());
    }

    @Test
    void testGetProduct_Success() {
        // Arrange
        RequestRetrieveProductEntity productRequest = new RequestRetrieveProductEntity();
        productRequest.setProductId("prod-001");
        MongoProductsEntity product = new MongoProductsEntity();
        product.setProductId("prod-001");
        product.setName("Test Product");
        when(repository.findByProductId("prod-001")).thenReturn(Optional.of(product));
        // Act
        ResponseEntity<RequestNewProductEntity> response = service.getProduct(headers, productRequest);
        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals("Test Product", response.getBody().getName());
    }

    @Test
    void testGetProduct_NotFound() {
        // Arrange
        RequestRetrieveProductEntity productRequest = new RequestRetrieveProductEntity();
        productRequest.setProductId("prod-001");
        // Act
        when(repository.findByProductId("prod-001")).thenReturn(Optional.empty());
        // Assert
        assertThrows(NoDataFoundException.class, () -> service.getProduct(headers, productRequest));
    }

    @Test
    void testDeleteProduct_Success() {
        // Arrange
        RequestRetrieveProductEntity productRequest = new RequestRetrieveProductEntity();
        productRequest.setProductId("prod-001");
        MongoProductsEntity product = new MongoProductsEntity();
        ObjectId objectId = new ObjectId();
        product.set_id(objectId);
        when(repository.findByProductId("prod-001")).thenReturn(Optional.of(product));
        // Act
        ResponseEntity<String> response = service.deleteProduct(productRequest, headers);
        // Assert
        verify(repository, times(1)).deleteById(product.get_id().toString());
        assertEquals(OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody());
    }

    @Test
    void testDeleteProduct_NotFound() {
        // Arrange
        RequestRetrieveProductEntity productRequest = new RequestRetrieveProductEntity();
        productRequest.setProductId("prod-001");
        // Act
        when(repository.findByProductId("prod-001")).thenReturn(Optional.empty());
        // Assert
        assertThrows(NoDataFoundException.class, () -> service.deleteProduct(productRequest, headers));
    }

}