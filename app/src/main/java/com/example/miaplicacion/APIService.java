package com.example.miaplicacion;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface APIService {
    @GET("http://192.168.56.1:3000/getPreguntes/")
    Call<List<Pregunta>> getPreguntas();
}
