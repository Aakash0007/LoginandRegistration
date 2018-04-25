package com.example.aakash.loginandregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    TextView tv_register;
    EditText edt_email, edt_password;
    Button btn_login;

    ThisMyAPI mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init service
        mService = Common.getAPI();

        //Init View
        tv_register = (TextView) findViewById(R.id.tv_register);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        //event
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser(edt_email.getText().toString(), edt_password.getText().toString());
            }
        });


    }

    private void authenticateUser(String email, String password) {

        mService.loginUser(email, password).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse result = response.body();
                if (result.isError())
                    Toast.makeText(MainActivity.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(MainActivity.this, ShowMyPage.class));
                    Toast.makeText(MainActivity.this, "Login Success !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
