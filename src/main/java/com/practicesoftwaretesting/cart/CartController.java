package com.practicesoftwaretesting.cart;

import com.practicesoftwaretesting.cart.model.AddCartItemRequest;
import com.practicesoftwaretesting.cart.model.CartDetails;
import com.practicesoftwaretesting.cart.model.CreateCartResponse;
import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import com.practicesoftwaretesting.user.model.UpdateCartResponse;

public class CartController extends BaseController<CartController> {
    public ResponseDecorator<CreateCartResponse> createCart() {
        return new ResponseDecorator<>(
                baseClient().post("/carts"),
                CreateCartResponse.class
        );
    }

    public ResponseDecorator<UpdateCartResponse> addItemToCart(String cartId, AddCartItemRequest cartItem) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(cartItem)
                        .post("/carts/" + cartId),
                UpdateCartResponse.class
        );
    }

    public ResponseDecorator<CartDetails> getCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient()
                        .get("/carts/" + cartId),
                CartDetails.class
        );
    }

    public ResponseDecorator<Void> deleteCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient()
                        .delete("/carts/" + cartId),
                Void.class
        );
    }
}