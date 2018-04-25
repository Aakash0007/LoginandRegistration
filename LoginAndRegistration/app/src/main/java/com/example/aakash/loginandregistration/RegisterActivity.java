package com.example.aakash.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aakash.loginandregistration.Common.Common;
import com.example.aakash.loginandregistration.Model.APIResponse;
import com.example.aakash.loginandregistration.Remote.ThisMyAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView tv_login;
    EditText edt_email, edt_password, edt_user;
    Button btn_register;

    ThisMyAPI mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init service
        mService = Common.getAPI();

        //Init View
        tv_login = (TextView) findViewById(R.id.tv_login);
        edt_user = (EditText) findViewById(R.id.edt_user);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_register = (Button) findViewById(R.id.btn_register);

        //event
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser(edt_user.getText().toString(), edt_email.getText().toString(), edt_password.getText().toString());
            }
        });
    }

    private void createNewUser(String name, String email, String password) {

        mService.registerUser(name, email, password).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse result = response.body();
                if (result.isError())
                    Toast.makeText(RegisterActivity.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                else startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                Toast.makeText(RegisterActivity.this, "User Created :" + result.getUid(), Toast.LENGTH_SHORT).show();
                
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
