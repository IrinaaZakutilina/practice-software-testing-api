package com.practicesoftwaretesting.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetails {

    private String id;
    private Double additionalDiscountPercentage;
    private Double lat;
    private Double lng;
    private List<CartItem> cartItems;
}