package com.ecommerce.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private ShippingAddressDto shippingAddress;
    private List<CartItemDto> items;

    @Data
    public static class ShippingAddressDto {
        private String fullName;
        private String addressLine1;
        private String city;
        private String postalCode;
        private String country;
    }

    @Data
    public static class CartItemDto {
        private Long productId;
        private int quantity;
    }
}