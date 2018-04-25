package com.example.aakash.loginandregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowMyPage extends AppCompatActivity {

    TextView showTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_page);

        showTextView = (TextView) findViewById(R.id.tv_show);
    }
}
