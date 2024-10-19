package com.paa.dms.products.manage.items.repository;

import com.paa.dms.products.manage.items.model.FilterEntity;
import com.paa.dms.products.manage.items.model.MongoProductsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class MarketplaceProductsRepositoryImplTest {
    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private MarketplaceProductsRepositoryImpl marketplaceProductsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindProductsByFilters() {
        // Arrange
        FilterEntity filter1 = new FilterEntity();
        filter1.setFilter("category");
        filter1.setValue("electronics");
        FilterEntity filter2 = new FilterEntity();
        filter2.setFilter("brand");
        filter2.setValue("BrandX");
        List<FilterEntity> filters = Arrays.asList(filter1, filter2);
        MongoProductsEntity product1 = new MongoProductsEntity();
        MongoProductsEntity product2 = new MongoProductsEntity();
        List<MongoProductsEntity> expectedProducts = Arrays.asList(product1, product2);
        // Mock the behavior of mongoTemplate
        when(mongoTemplate.find(org.mockito.ArgumentMatchers.any(Query.class), eq(MongoProductsEntity.class)))
                .thenReturn(expectedProducts);
        // Act
        List<MongoProductsEntity> actualProducts = marketplaceProductsRepository.findProductsByFilters(filters);
        // Assert: Verify the returned products are correct
        assertEquals(expectedProducts, actualProducts);
        // Capture the Query object passed to mongoTemplate
        ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mongoTemplate).find(queryCaptor.capture(), eq(MongoProductsEntity.class));
        // Validate the captured query through its string representation
        Query capturedQuery = queryCaptor.getValue();
        String queryString = capturedQuery.toString();
        // Check if the query contains the expected filter criteria
        assertTrue(queryString.contains("electronics"));
        assertTrue(queryString.contains("BrandX"));
    }
}