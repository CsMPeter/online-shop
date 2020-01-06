package com.fasttrackit.onlineshop;

import com.fasttrackit.onlineshop.domain.Cart;
import com.fasttrackit.onlineshop.domain.Customer;
import com.fasttrackit.onlineshop.domain.Product;
import com.fasttrackit.onlineshop.service.CartService;
import com.fasttrackit.onlineshop.steps.CustomerSteps;
import com.fasttrackit.onlineshop.steps.ProductSteps;
import com.fasttrackit.onlineshop.transfer.AddProductToCartRequest;
import com.fasttrackit.onlineshop.transfer.CartResponse;
import com.fasttrackit.onlineshop.transfer.ProductInCartResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceIntegrationTests {


    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerSteps customerSteps;

    @Autowired
    private ProductSteps productSteps;


    @Test
    public void testAddProductToCart_whenNewCartForExistingCustomer_thenCartIsSaved() {

        Customer customer = customerSteps.createCustomer();
        Product product = productSteps.createProduct();

        AddProductToCartRequest request = new AddProductToCartRequest();
        request.setCustomerId(customer.getId());
        request.setProductId(product.getId());

        cartService.addProductToCart(request);

        CartResponse cart = cartService.getCart(customer.getId());

        assertThat(cart.getId(), is(customer.getId()));

        Iterator<ProductInCartResponse> iterator = cart.getProducts().iterator();
        assertThat(iterator.hasNext(), is(true));

        ProductInCartResponse productFromCart = iterator.next();

        assertThat(productFromCart, notNullValue());
        assertThat(productFromCart.getId(), is(product.getId()));
        assertThat(productFromCart.getName(), is(product.getName()));
        assertThat(productFromCart.getPrice(), is(product.getPrice()));
    }

}
