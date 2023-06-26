package com.pucpr.atividadesomativa2.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.model.DataModel;
import com.pucpr.atividadesomativa2.model.OrderService;

import java.util.Date;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView titleLabel, textViewClient, textViewDevice,
            textViewDetails, textViewOpenedAt, textViewFinishedAt,
            textViewSolution, titleFinishedAt, titleSolution;

    private Button solutionButton, deleteButton;

    private OrderService orderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        this.titleLabel = findViewById(R.id.orderDetailsTitleId);
        this.textViewClient = findViewById(R.id.orderDetailsClientText);
        this.textViewDevice = findViewById(R.id.orderDetailsDeviceText);
        this.textViewDetails = findViewById(R.id.orderDetailsDetailsText);
        this.textViewOpenedAt = findViewById(R.id.orderDetailsOpenedAtText);
        this.textViewFinishedAt = findViewById(R.id.orderDetailsFinishedAtText);
        this.textViewSolution = findViewById(R.id.orderDetailsSolutionText);
        this.titleFinishedAt = findViewById(R.id.orderDetailsFinishedTitle);
        this.titleSolution = findViewById(R.id.orderDetailsSolutionTitle);
        this.solutionButton = findViewById(R.id.orderDetailsSolutionButton);
        this.deleteButton = findViewById(R.id.orderDetailsDeleteButton);

        this.orderService = (OrderService) getIntent().getSerializableExtra("ORDER_SERVICE");

        this.fillComponents();
    }

    private void fillComponents() {

        this.titleLabel.setText("Ordem se serviço: " + this.orderService.getId());
        this.textViewClient.setText(this.orderService.getClient());
        this.textViewDevice.setText(this.orderService.getDevice());
        this.textViewDetails.setText(this.orderService.getDetail());

        if (this.orderService.getCreatedAt() == null) {
            this.textViewOpenedAt.setVisibility(TextView.INVISIBLE);
        } else {
            String dateFormatted = (String) DateFormat.format("dd/MM/yyyy", this.orderService.getCreatedAt());
            this.textViewOpenedAt.setText(dateFormatted);
        }

        if (this.orderService.getSolution() == null) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewFinishedAt.setVisibility(TextView.INVISIBLE);
                    textViewSolution.setVisibility(TextView.INVISIBLE);
                    titleFinishedAt.setVisibility(TextView.INVISIBLE);
                    titleSolution.setVisibility(TextView.INVISIBLE);
                }
            });
        } else {

            if (this.orderService.getFinishedAt() != null) {
                String dateFormatted = (String) DateFormat.format("dd/MM/yyyy", this.orderService.getCreatedAt());
                this.textViewFinishedAt.setText(dateFormatted);
            } else {
                this.textViewFinishedAt.setText("");
            }
            this.textViewSolution.setText(this.orderService.getSolution());
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    solutionButton.setVisibility(TextView.GONE);
                }
            });
        }

        this.solutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSolutionActivity();
            }
        });

        this.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                if (orderService.getSolution() == null) {
                    DataModel.getInstance().deleteUnFinishedOrder(orderService);
                } else {
                    DataModel.getInstance().deleteFinishedOrder(orderService);
                }
            }
        });
    }

    private void openAddSolutionActivity() {
        Intent intent = new Intent(this, AddSolutionOrderServiceActivity.class);
        intent.putExtra("ORDER_SERVICE", this.orderService);
        startActivity(intent);
    }
}