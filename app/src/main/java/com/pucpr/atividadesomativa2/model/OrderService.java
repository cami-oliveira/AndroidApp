package com.pucpr.atividadesomativa2.model;

import java.io.Serializable;
import java.util.Date;

public class OrderService implements Serializable {

    private long id;

    private String client, phone, device, detail, solution;

    private Date createdAt, finishedAt, removedAt;

    public OrderService(long id,
                        String client,
                        String phone,
                        String device,
                        String detail,
                        String solution,
                        Date createdAt,
                        Date finishedAt,
                        Date removedAt) {
        this.id = id;
        this.client = client;
        this.phone = phone;
        this.device = device;
        this.detail = detail;
        this.solution = solution;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.removedAt = removedAt;
    }

    public OrderService(String client,
                        String phone,
                        String device,
                        String detail) {
        this.client = client;
        this.phone = phone;
        this.device = device;
        this.detail = detail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Date getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(Date removedAt) {
        this.removedAt = removedAt;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
