package com.example.miaplicacion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.205.69:3000/getPreguntes/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static APIService getApiService() {
        return retrofit.create(APIService.class);
    }
}
