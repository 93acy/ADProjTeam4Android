package com.example.adprojteam4.OrderFunction;

public class UserOrder {
    private Long id;
    private Double orderValue;
    private String userOrderStatus;

    public Long getId() {
        return id;
    }

    public UserOrder(Double orderValue) {
        this.orderValue = orderValue;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Double orderValue) {
        this.orderValue = orderValue;
    }

    public String getUserOrderStatus() {
        return userOrderStatus;
    }

    public void setUserOrderStatus(String userOrderStatus) {
        this.userOrderStatus = userOrderStatus;
    }
}
