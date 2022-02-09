package com.alevel.service;

import com.alevel.persistence.entity.cart.Cart;

import java.util.List;

public interface CartService extends BaseCrudService<Cart> {

    List<Cart> findCartIdByUserIdAndProductId(Long userId, Long productId);
}
