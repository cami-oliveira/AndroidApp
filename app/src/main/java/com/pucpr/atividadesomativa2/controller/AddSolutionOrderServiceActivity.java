package com.pucpr.atividadesomativa2.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.model.Constants;
import com.pucpr.atividadesomativa2.model.DataModel;
import com.pucpr.atividadesomativa2.model.OrderService;

public class AddSolutionOrderServiceActivity extends BaseActivity {

    private TextInputEditText solutionTextField;
    private Button solutionButton;
    private OrderService orderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_solution_order_service);

        solutionTextField = findViewById(R.id.solutionEditText);
        solutionButton = findViewById(R.id.addSolutionButton);

        orderService = (OrderService) getIntent().getSerializableExtra(Constants.ORDER_SERVICE);

        solutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateFields()) {
                    return;
                }

                orderService.setSolution(solutionTextField.getText().toString());
                DataModel.getInstance().finishOrder(orderService);
                openTabBar();
            }
        });
    }

    private void openTabBar() {
        Intent intent = new Intent(this, MainTabActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        hideKeyboard();
    }

    public boolean validateFields() {

        boolean isValid = true;
        if ((solutionTextField.getText().toString().trim().equals(""))) {
            solutionTextField.setError(getString(R.string.errorMessage));
            solutionTextField.setText(null);
            isValid = false;
        } else {
            solutionTextField.setError(null);
        }

        return isValid;
    }
}