package com.practicesoftwaretesting;

import com.practicesoftwaretesting.cart.CartController;
import com.practicesoftwaretesting.cart.model.*;
import com.practicesoftwaretesting.cart.model.AddCartItemRequest;
import com.practicesoftwaretesting.product.ProductController;
import com.practicesoftwaretesting.product.model.ProductsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {
    private String authToken;
    private String productId;

    CartController cartController = new CartController();
    ProductController productController = new ProductController();

    @BeforeEach
    void beforeEach() {
        authToken = registerAndLoginNewUser();

        var productRequest = ProductsRequest.builder()
                .page(1)
                .build();
        productId = productController.getProducts(productRequest)
                .as()
                .getData()
                .getFirst().getId();
    }

    @Test
    void createUpdateAndDeleteCart() {
        var createdCart = cartController.withToken(authToken).createCart()
                .assertStatusCode(201)
                .as();
        assertNotNull(createdCart.getId());

        var cartId = createdCart.getId();
        var updateCartResponse = cartController.addItemToCart(cartId, new AddCartItemRequest(productId, 1))
                .assertStatusCode(200)
                .as();
        assertNotNull(updateCartResponse.getResult());

        var cartDetails = cartController.getCart(cartId)
                .assertStatusCode(200)
                .as();
        var productIds = cartDetails.getCartItems().stream().map(CartItem::getProductId).toList();
        assertTrue(productIds.contains(productId));

        cartController.deleteCart(cartId)
                .assertStatusCode(204);
    }
}