package co.edu.ue.practica_login_api;

import static co.edu.ue.practica_login_api.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.edu.ue.practica_login_api.adapter.ProductosAdapter;
import co.edu.ue.practica_login_api.api.ServiceLogin;
import co.edu.ue.practica_login_api.model.Productos;
import co.edu.ue.practica_login_api.model.ResponseCredentials;
import co.edu.ue.practica_login_api.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Inicio extends AppCompatActivity {

    private List<Productos> productos;
    private TextView tvNombre;
    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;
    private Retrofit retrofit;
    private ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        begin();
        showProductos();
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        enviarNombre(nombre);
        btnMenu.setOnClickListener(this::cambiar);
    }

    private void enviarNombre(String nombre) {

        this.tvNombre.setText("Hola, "+nombre);
    }

    private void cambiar(View view) {
        cambiarPantalla(Menu.class);
    }

    private void alertView(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Productos");
        builder.setMessage(mensaje);
        builder.setPositiveButton("ACEPTAR", null);
        builder.create();
        builder.show();
    }

    private void begin() {
        recyclerView = findViewById(R.id.rvProductos);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        this.btnMenu = findViewById(R.id.imageButton);
        this.tvNombre = findViewById(R.id.textView2);
    }

    private void showProductos(){
        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
        Call<List<Productos>> call = serviceLogin.obtenerproductos();

        call.enqueue(new Callback<List<Productos>>() {
            @Override
            public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                productos = response.body();
                productosAdapter = new ProductosAdapter(productos, getApplicationContext());
                recyclerView.setAdapter(productosAdapter);
            }

            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {
                alertView("Error en servicio" + t);
            }
        });
    }

    private void cambiarPantalla(Class <?> cls){
        try {
            Intent intent = new Intent(this, cls);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}