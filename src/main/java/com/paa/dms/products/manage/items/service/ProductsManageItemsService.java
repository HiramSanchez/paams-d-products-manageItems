package com.paa.dms.products.manage.items.service;

import com.paa.dms.products.manage.items.model.RequestNewProductEntity;
import com.paa.dms.products.manage.items.model.RequestRetrieveFilterEntity;
import com.paa.dms.products.manage.items.model.RequestRetrieveProductEntity;
import com.paa.dms.products.manage.items.model.ResponseRetrieveFilterEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface ProductsManageItemsService {
    ResponseEntity<String> saveProduct(HttpHeaders httpHeaders, RequestNewProductEntity userRequest);
    ResponseEntity<ResponseRetrieveFilterEntity> getProductList(HttpHeaders httpHeaders, RequestRetrieveFilterEntity userRequest);
    ResponseEntity<RequestNewProductEntity> getProduct(HttpHeaders httpHeaders, RequestRetrieveProductEntity userRequest);
    ResponseEntity<String> deleteProduct(RequestRetrieveProductEntity userRequest, HttpHeaders httpHeaders);
}
