package com.paa.dms.products.manage.items.model;

import lombok.Data;

import java.util.List;

@Data
public class RequestRetrieveFilterEntity {
    private List<FilterEntity> filters;
}
