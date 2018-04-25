package com.example.aakash.loginandregistration.Common;

import com.example.aakash.loginandregistration.Remote.RetrofitClient;
import com.example.aakash.loginandregistration.Remote.ThisMyAPI;

public class Common {

    public static final String BASE_URL = "http://192.168.0.100/my_db/";

    public static ThisMyAPI getAPI() {

        return RetrofitClient.getClient(BASE_URL).create(ThisMyAPI.class);

    }
}
