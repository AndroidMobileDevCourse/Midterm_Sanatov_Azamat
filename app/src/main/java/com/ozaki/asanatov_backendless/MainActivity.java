package com.ozaki.asanatov_backendless;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button proceedRegisterButton;
    private EditText nameTextField;
    private EditText emailTextField;
    private EditText passwordTextField;
    private Button loginButton;
    public static final String APPLICATION_ID = "06A0FC42-468A-E380-FF3D-264F92D6E200";
    public static final String API_KEY = "18EEC773-7751-5F7F-FFA7-68E6E7799400";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Backendless.setUrl("https://api.backendless.com");
        Backendless.initApp(this, APPLICATION_ID,API_KEY);
        loginButton = findViewById(R.id.login_button);
        proceedRegisterButton = findViewById(R.id.proceedRegister_button);
        nameTextField = findViewById(R.id.editText);
        emailTextField = findViewById(R.id.editText2);
        passwordTextField = findViewById(R.id.editText3);
        proceedRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistIntent();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    public Context getContext() {
        return this;
    }
    private void RegistIntent(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    private void SignedInIntent(){
        Intent intent = new Intent(this,LogoutActivity.class);
        startActivity(intent);
    }
    private void Login() {
        final EditText user_email = findViewById(R.id.editText2);
        final EditText user_password = findViewById(R.id.editText3);
        Backendless.UserService.login(
                user_email.getText().toString(),
                user_password.getText().toString(),
                new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(getContext(), "User has been logged in", Toast.LENGTH_LONG).show();
                        SignedInIntent();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
