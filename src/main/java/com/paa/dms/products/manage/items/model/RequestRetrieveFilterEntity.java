package com.paa.dms.products.manage.items.model;

import lombok.Data;

import java.util.List;

@Data
public class RequestRetrieveFilterEntity {
    /** List of filters applied to the product search. */
    private List<FilterEntity> filters;
}
