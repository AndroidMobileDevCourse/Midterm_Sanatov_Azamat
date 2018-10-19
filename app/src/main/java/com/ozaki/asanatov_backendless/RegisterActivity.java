package com.ozaki.asanatov_backendless;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;
    private Button proceedLoginButton;
    private EditText nameTextField;
    private EditText emailTextField;
    private EditText passwordTextField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = (Button) findViewById(R.id.register_button);
        nameTextField = (EditText) findViewById(R.id.editText);
        emailTextField = (EditText) findViewById(R.id.editText2);
        passwordTextField = findViewById(R.id.editText3);
        proceedLoginButton = findViewById(R.id.proceedLogin_button);
        HashMap testObject = new HashMap<>();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        proceedLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginIntent();
            }
        });
    }
    private void LoginIntent(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void register() {
        BackendlessUser user = new BackendlessUser();
        final EditText user_name = findViewById(R.id.editText);
        final EditText user_email = findViewById(R.id.editText2);
        final EditText user_password = findViewById(R.id.editText3);


        user.setEmail( user_email.getText().toString());
        user.setPassword(user_password.getText().toString());
        user.setProperty( "name", user_name.getText().toString());

        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(getContext(), "User has been registered", Toast.LENGTH_LONG).show();
                LoginIntent();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public Context getContext() {
        return this;
    }
    private void handleFault(BackendlessFault fault) {
        String msg = "Server reported an error " + fault.getMessage();
        Log.e("MYAPP", msg);
    }
}
