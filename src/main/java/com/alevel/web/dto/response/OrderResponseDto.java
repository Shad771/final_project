package com.alevel.web.dto.response;

import com.alevel.persistence.entity.order.Order;

public class OrderResponseDto extends ResponseDto {

    private Long cartId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;

    public OrderResponseDto() {

    }

    public OrderResponseDto(Order order) {
        setId(order.getId());
        setCreated(order.getCreated());
        setUpdated(order.getUpdated());
        setVisible(order.getVisible());
        this.cartId = order.getCartId();
        this.customerName = order.getCustomerName();
        this.customerAddress = order.getCustomerAddress();
        this.customerEmail = order.getCustomerEmail();
        this.customerPhone = order.getCustomerPhone();
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
