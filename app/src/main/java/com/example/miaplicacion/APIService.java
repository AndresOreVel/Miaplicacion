package com.example.miaplicacion;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface APIService {
    @GET("http://192.168.205.69:3000/getPreguntes/")
    Call<List<Pregunta>> getPreguntas();
}
