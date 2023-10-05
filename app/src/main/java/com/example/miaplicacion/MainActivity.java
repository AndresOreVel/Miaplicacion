package com.example.miaplicacion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResultado;
    private ImageView[] imageViewsPregunta = new ImageView[4];
    private Button btnResponder;
    private int preguntaActual = 0; // Índice de la pregunta actual
    private List<Pregunta> preguntas; // Lista de preguntas obtenidas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultado = findViewById(R.id.textViewResultado);
        imageViewsPregunta[0] = findViewById(R.id.imageViewPregunta1);
        imageViewsPregunta[1] = findViewById(R.id.imageViewPregunta2);
        imageViewsPregunta[2] = findViewById(R.id.imageViewPregunta3);
        imageViewsPregunta[3] = findViewById(R.id.imageViewPregunta4);
        btnResponder = findViewById(R.id.btnResponder);

        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar si aún hay preguntas disponibles
                if (preguntaActual < preguntas.size()) {
                    // Obtener la siguiente pregunta
                    Pregunta siguientePregunta = preguntas.get(preguntaActual);
                    mostrarPregunta(siguientePregunta);
                    preguntaActual++; // Incrementar el contador de preguntas
                } else {
                    // Se han respondido todas las preguntas, puedes manejarlo aquí
                    Log.d("TAG", "Todas las preguntas han sido respondidas.");
                }
            }
        });

        cargarPreguntas(); // Cargar las preguntas iniciales
    }

    private void cargarPreguntas() {
        APIService apiService = RetrofitClient.getApiService();

        Call<List<Pregunta>> call = apiService.getPreguntas();
        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                if (response.isSuccessful()) {
                    preguntas = response.body();
                    if (preguntas != null && !preguntas.isEmpty()) {
                        // Mostrar la primera pregunta
                        mostrarPregunta(preguntas.get(preguntaActual));
                        preguntaActual++; // Incrementar el contador de preguntas
                    } else {
                        Log.d("TAG", "La lista de preguntas está vacía");
                    }
                } else {
                    Log.d("TAG", "No se pudo obtener la lista de preguntas");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Pregunta>> call, @NonNull Throwable t) {
                Log.d("TAG", "Error de conexión: " + t.getMessage());
            }
        });
    }

    private void mostrarPregunta(Pregunta pregunta) {
        // Actualizar el contenido del TextView con la pregunta
        textViewResultado.setText("ID: " + pregunta.getId() + "\nTexto: " + pregunta.getPregunta());

        // Cargar las 4 imágenes de la pregunta desde las URLs utilizando Picasso
        if (pregunta.getRespostes() != null && pregunta.getRespostes().size() >= 4) {
            for (int i = 0; i < 4; i++) {
                Respuesta respuesta = pregunta.getRespostes().get(i);
                cargarImagenConPicasso(respuesta.getImagenURL(), imageViewsPregunta[i]);
            }
        }
    }

    private void cargarImagenConPicasso(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }
}
