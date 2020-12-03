package com.project.entities;

import com.project.enums.InvoiceStatus;

public class Invoice {

    private int id;
    private Order order;
    private int userId;
    private int price;
    private String message;
    private InvoiceStatus invoiceStatus;

    public Invoice() {
    }

    public Invoice(Order order, int userId, int price, String message, InvoiceStatus invoiceStatus) {
        this.order = order;
        this.userId = userId;
        this.price = price;
        this.message = message;
        this.invoiceStatus = invoiceStatus;
    }

    public Invoice(int id, Order order, int userId, int price, String message, InvoiceStatus invoiceStatus) {
        this.id = id;
        this.order = order;
        this.userId = userId;
        this.price = price;
        this.message = message;
        this.invoiceStatus = invoiceStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
