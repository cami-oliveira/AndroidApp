package com.pucpr.atividadesomativa2.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.pucpr.atividadesomativa2.R;
import com.pucpr.atividadesomativa2.model.DataModel;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    TextInputLayout usernameTextInputLayout;
    EditText passwordEditText;
    TextInputLayout passwordTextInputLayout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);

        DataModel.getInstance().createDataBase(getApplicationContext());
        DataModel.getInstance().findAllOpenedOrderServices();
        DataModel.getInstance().findAllFinishedOrderServices();
    }

    public void loginButtonOnClick(View v) {

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String usernameData = String.valueOf(username.equals(DataModel.getInstance().userDetails.getUsername()));
        String passwordData = String.valueOf(password.equals(DataModel.getInstance().userDetails.getPassword()));

        Intent intent = new Intent(MainActivity.this, MainTabActivity.class);
        startActivity(intent);

        /*if (username.equals(DataModel.getInstance().userDetails.getUsername()) &&
                password.equals(DataModel.getInstance().userDetails.getPassword())) {

            Intent intent = new Intent(MainActivity.this,MainTabActivity.class);
            startActivity(intent);

        } else {
            if ((usernameEditText.getText().toString().trim().equals(""))) {
                usernameEditText.setError(getString(R.string.errorMessage));
                usernameEditText.setText(null);
            } else {
                usernameEditText.setError(null);
            }

            if ((passwordEditText.getText().toString().trim().equals(""))) {
                passwordEditText.setError(getString(R.string.errorMessage));
                passwordEditText.setText(null);
            } else {
                passwordEditText.setError(null);
            }

            if ((usernameEditText.getText().toString().trim().equals("")) || (passwordEditText.getText().toString().trim().equals(""))) {
                Toast.makeText(this, getString(R.string.toast_coloque_nome_e_senha), Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.error));
            builder.setMessage(getString(R.string.wrong_user));
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                }
            });

            builder.create().show();
        }*/

    }
}