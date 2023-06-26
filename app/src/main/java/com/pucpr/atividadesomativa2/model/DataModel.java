package com.pucpr.atividadesomativa2.model;

import android.content.Context;

import com.pucpr.atividadesomativa2.database.OrderServiceDataBase;

import java.util.ArrayList;
import java.util.Date;

public class DataModel {

    private static DataModel instance = new DataModel();
    private DataModel() {

    }

    private ArrayList<OrderService> orderServices = new ArrayList<>();
    private ArrayList<OrderService> finishedOrderServices = new ArrayList<>();

    public static DataModel getInstance() {
        return instance;
    }

    public UserDetails userDetails =
            new UserDetails("Professor", "123456");

    private OrderServiceDataBase database;
    public void createDataBase(Context context){
        database = new OrderServiceDataBase(context);
    }

    public boolean addOrderService(OrderService orderService) {
        long id = database.createDefaultOrderServiceInDB(orderService);
        if(id>0){
            orderService.setId(id);
            orderService.setCreatedAt(new Date());
            orderServices.add(orderService);
            return true;
        }
        return false;
    }

    public void findAllOrderServices() {
        this.orderServices = database.getOrdersServicesFromDB(true);
    }

    public void findAllOpenedOrderServices() {
        this.orderServices = database.getOrdersServicesFromDB(true);
    }

    public void findAllFinishedOrderServices() {
        this.finishedOrderServices = database.getOrdersServicesFromDB(false);
    }
    public ArrayList<OrderService> getOrderServices() {
        return orderServices;
    }

    public ArrayList<OrderService> getFinishedOrderServices() {
        return finishedOrderServices;
    }

    public void finishOrder(OrderService orderService) {
        database.finishOrderServiceInDB(orderService);
        OrderService orderServiceFilter = findInOpenedOrders(orderService);
        if (orderServiceFilter != null) {
            this.orderServices.remove(orderServiceFilter);
        }

        orderService.setFinishedAt(new Date());
        this.finishedOrderServices.add(orderService);
    }

    public void deleteUnFinishedOrder(OrderService orderService) {
        database.removeOrderServiceInDB(orderService);

        OrderService orderServiceFilter = findInOpenedOrders(orderService);
        if (orderServiceFilter != null) {
            this.orderServices.remove(orderServiceFilter);
        }
    }

    public void deleteFinishedOrder(OrderService orderService) {
        database.removeOrderServiceInDB(orderService);

        OrderService orderServiceFilter = findInFinishedOrders(orderService);
        if (orderServiceFilter != null) {
            this.finishedOrderServices.remove(orderServiceFilter);
        }
    }

    public OrderService findInOpenedOrders(OrderService orderService) {
        OrderService orderServiceFilter = null;
        for (OrderService os : this.orderServices) {
            if (os.getId() == orderService.getId()) {
                orderServiceFilter = os;
            }
        }

        return orderServiceFilter;
    }

    public OrderService findInFinishedOrders(OrderService orderService) {
        OrderService orderServiceFilter = null;
        for (OrderService os : this.finishedOrderServices) {
            if (os.getId() == orderService.getId()) {
                orderServiceFilter = os;
            }
        }

        return orderServiceFilter;
    }
}
