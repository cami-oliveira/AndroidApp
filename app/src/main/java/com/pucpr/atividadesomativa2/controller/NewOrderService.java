package com.pucpr.atividadesomativa2.controller;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.model.Constants;
import com.pucpr.atividadesomativa2.model.DataModel;
import com.pucpr.atividadesomativa2.model.OrderService;

public class NewOrderService extends BaseActivity {

    private TextInputEditText clientText, phoneText, detailText, deviceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_service);

        clientText = findViewById(R.id.clientEditText);
        phoneText = findViewById(R.id.phoneEditText);
        detailText = findViewById(R.id.detailEditText);
        deviceText = findViewById(R.id.deviceEditText);

    }

    public void createOrderServiceOnClick(View v) {

        if (!this.validateFields()) {
            return;
        }

        DataModel.getInstance().addOrderService(
                new OrderService(this.clientText.getText().toString(),
                this.phoneText.getText().toString(),
                deviceText.getText().toString(),
                detailText.getText().toString()));

        Log.d("ADD ITEM", DataModel.getInstance().getOrderServices().toString());

        hideKeyboard();

        finish();
    }

    public boolean validateFields() {

        boolean isValid = true;
        if ((clientText.getText().toString().trim().equals(""))) {
            clientText.setError(getString(R.string.errorMessage));
            clientText.setText(null);
            isValid = false;
        } else {
            clientText.setError(null);
        }

        if ((phoneText.getText().toString().trim().equals(""))) {
            phoneText.setError(getString(R.string.errorMessage));
            phoneText.setText(null);
            isValid = false;
        } else {
            phoneText.setError(null);
        }

        if ((deviceText.getText().toString().trim().equals(""))) {
            deviceText.setError(getString(R.string.errorMessage));
            deviceText.setText(null);
            isValid = false;
        } else {
            deviceText.setError(null);
        }

        if ((detailText.getText().toString().trim().equals(""))) {
            detailText.setError(getString(R.string.errorMessage));
            detailText.setText(null);
            isValid = false;
        } else {
            detailText.setError(null);
        }

        return isValid;
    }
}