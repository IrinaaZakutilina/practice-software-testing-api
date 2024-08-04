package com.practicesoftwaretesting.cart.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AddCartItemRequest {

    private String productId;
    private int quantity;
}