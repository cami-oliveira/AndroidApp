package com.pucpr.atividadesomativa2.model;

import android.content.Context;

import com.pucpr.atividadesomativa2.database.OrderServiceDataBase;

import java.util.ArrayList;

public class DataModel {

    private static DataModel instance = new DataModel();
    private DataModel() {

    }

    private ArrayList<OrderService> orderServices = new ArrayList<>();

    public static DataModel getInstance() {
        return instance;
    }

    public UserDetails userDetails =
            new UserDetails("Professor", "123456");

    private OrderServiceDataBase database;
    public void createDataBase(Context context){
        database = new OrderServiceDataBase(context);
    }

    public boolean addOrderService(OrderService orderService){
        long id = database.createDefaultOrderServiceInDB(orderService);
        if(id>0){
            orderService.setId(id);
            orderServices.add(orderService);
            return true;
        }
        return false;
    }

    public ArrayList<OrderService> getOrderServices() {
        return orderServices;
    }
}
