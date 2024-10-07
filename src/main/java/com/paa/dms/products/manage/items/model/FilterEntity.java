package com.paa.dms.products.manage.items.model;

import lombok.Data;

@Data
public class FilterEntity {
    /** Type of filter applied (i.e. color, size, etc.)*/
    private String filter;
    /** Value of filter applied (i.e. blue, M, etc.)*/
    private String value;
}
