package com.practicesoftwaretesting.product.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductsResponse {

    private int currentPage;
    private List<Product> data;
    private int from;
    private int lastPage;
    private int perPage;
    private int to;
    private int total;
}