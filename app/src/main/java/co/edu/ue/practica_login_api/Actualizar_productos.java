package co.edu.ue.practica_login_api;

import static co.edu.ue.practica_login_api.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import co.edu.ue.practica_login_api.api.ServiceLogin;
import co.edu.ue.practica_login_api.model.Productos;
import co.edu.ue.practica_login_api.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Actualizar_productos extends AppCompatActivity {

    private ImageButton btnCambiar;
    private EditText etNombre;
    private EditText etSKU;
    private EditText etPrecio;
    private EditText etImagen;
    private Button btnActualizar;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_productos);
        begin();
        this.btnCambiar.setOnClickListener(this::cambiar);
        this.btnActualizar.setOnClickListener(this::actualizar);
    }

    private void actualizar(View view) {
        Productos productos = new Productos();
        productos.setUse_nombre(etNombre.getText().toString());
        productos.setUse_sku(etSKU.getText().toString());
        productos.setUse_precio(etPrecio.getText().toString());
        productos.setUse_imagen(etImagen.getText().toString());

        retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
        Call<String> call = serviceLogin.actualizarproducto(productos);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String respuesta = response.body();
                Toast.makeText(Actualizar_productos.this, ""+respuesta, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Actualizar_productos.this, "ERROR"+t, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void cambiar(View view) {
        cambiarPantalla(Menu.class);
    }

    private void begin() {
        this.btnCambiar = findViewById(R.id.btnCerrar);
        this.btnActualizar = findViewById(R.id.btnActualizarProducto);
        this.etNombre = findViewById(R.id.etNombreAProduct);
        this.etSKU = findViewById(R.id.etSKUAProduct);
        this.etPrecio = findViewById(R.id.etPrecioAProduct);
        this.etImagen = findViewById(R.id.etImagenAProduct);
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