package com.pucpr.atividadesomativa2.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.model.DataModel;
import com.pucpr.atividadesomativa2.model.OrderService;

public class AddSolutionOrderServiceActivity extends AppCompatActivity {

    private TextInputEditText solutionTextField;
    private Button solutionButton;
    private OrderService orderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_solution_order_service);

        solutionTextField = findViewById(R.id.solutionEditText);
        solutionButton = findViewById(R.id.addSolutionButton);

        orderService = (OrderService) getIntent().getSerializableExtra("ORDER_SERVICE");

        solutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                orderService.setSolution("Deu boa");
                DataModel.getInstance().finishOrder(orderService);

            }
        });
    }
}