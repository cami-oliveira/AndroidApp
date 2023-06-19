package com.pucpr.atividadesomativa2.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.model.DataModel;
import com.pucpr.atividadesomativa2.model.OrderService;

public class NewOrderService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_service);
    }

    public void createOrderServiceOnClick(View v) {

        DataModel.getInstance().addOrderService(new OrderService("teste",
                "4199999999",
                "iphone x",
                "nao liga"));

        Log.d("test", DataModel.getInstance().getOrderServices().toString());
    }
}