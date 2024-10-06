package com.paa.dms.products.manage.items.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestNewProductEntity {
    /** Unique identifier for the product. */
    private String productId;
    /** Name of the product. */
    private String name;
    /** Detailed description of the product. */
    private String description;
    /** Color of the product. */
    private String color;
    /** Gender of the user or product target group (e.g., "Male", "Female", "Unisex"). */
    private String gender;
    /** Unique identifier for the image associated with the product. */
    private String imageId;
    /** The type or category of the product (e.g., pants, shoes). */
    private String productType;
    /** Size of the product (if applicable). */
    private String size;
    /** Price of the product. */
    private String price;
}
