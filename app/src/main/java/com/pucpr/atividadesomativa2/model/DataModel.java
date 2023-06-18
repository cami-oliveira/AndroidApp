package com.pucpr.atividadesomativa2.model;

public class DataModel {

    private static DataModel instance = new DataModel();
    private DataModel() {

    }

    public static DataModel getInstance() {
        return instance;
    }

    public UserDetails userDetails =
            new UserDetails("Professor", "123456");
}
