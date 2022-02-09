package com.alevel.web.dto.response;

import com.alevel.persistence.entity.cart.Cart;

public class CartResponseDto extends ResponseDto {

    private Long userId;
    private Long itemId;
    private int quantity;

    public CartResponseDto() {
    }

    public CartResponseDto(Cart cart) {
        setId(cart.getId());
        setCreated(cart.getCreated());
        setUpdated(cart.getUpdated());
        setVisible(cart.getVisible());
        this.userId = cart.getUserId();
        this.itemId = cart.getItemId();
        this.quantity = cart.getQuantity();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
