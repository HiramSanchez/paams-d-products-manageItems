package com.paa.dms.products.manage.items.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class APIConstants {

    //Endpoint paths
    public static final String BASE_PATH = "${constants.api.uri.basePath}";
    public static final String CREATE_NEW_ITEM_ENDPOINT = "${constants.api.uri.productNewData.path}";
    public static final String READ_ITEM_DATA_ENDPOINT = "${constants.api.uri.productsRetrieveData.path}";
    public static final String RETRIEVE_ITEM_LIST_ENDPOINT = "${constants.api.uri.productsRetrieveFilter.path}";
    public static final String DELETE_PRODUCT_ENDPOINT = "${constants.api.uri.productsDeleteData.path}";

    //Service call log messages
    @Value("${service.api.name}")
    private String SERVICE_NAME;
    @Value("${constants.api.uri.productNewData.call}")
    private String LOG_NEW_ITEM_ENDPOINT;
    @Value("${constants.api.uri.productsRetrieveData.call}")
    private String LOG_READ_DATA_ENDPOINT;
    @Value("${constants.api.uri.productsRetrieveFilter.call}")
    private String LOG_READ_FILTER_ENDPOINT;
    @Value("${constants.api.uri.productsDeleteData.call}")
    private String LOG_DELETE_ITEM_ENDPOINT;

    //Error resolver log messages
    @Value("${constants.api.uri.errors.msg.unexpected}")
    private String EXCEPTION_MSG_UNEXPECTED;
    @Value("${constants.api.uri.errors.msg.noDataFound}")
    private String EXCEPTION_MSG_NO_DATA_FOUND;
    @Value("${constants.api.uri.errors.msg.forbidden}")
    private String EXCEPTION_MSG_FORBIDDEN;

    //Swagger Constants
    @Value("${service.api.version}")
    private String SERVICE_VERSION;
    @Value("${service.api.details}")
    private String SERVICE_DESCRIPTION;
    @Value("${service.api.dev.name}")
    private String DEV_NAME;
    @Value("${service.api.dev.web}")
    private String DEV_WEB;
    @Value("${service.api.dev.email}")
    private String DEV_EMAIL;
}
