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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.ue.practica_login_api.api.ServiceLogin;
import co.edu.ue.practica_login_api.model.Loger;
import co.edu.ue.practica_login_api.model.Productos;
import co.edu.ue.practica_login_api.model.ResponseCredentials;
import co.edu.ue.practica_login_api.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Agregar_productos extends AppCompatActivity {
    private ImageButton btnCambiar;
    private EditText etNombre;
    private EditText etSKU;
    private EditText etPrecio;
    private EditText etImagen;
    private Button btnAgregar;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);
        begin();
        this.btnCambiar.setOnClickListener(this::cambiar);
        this.btnAgregar.setOnClickListener(this::agregar);
    }

    public static boolean validCampos(String datos) {

        Pattern pattern = Pattern.compile("^.{4,}$");
        Matcher matcher = pattern.matcher(datos);

        if (matcher.find() == true) {
            //System.out.println("El nombre ingresado es válido.");
            return true;
        } else {
            //System.out.println("El nombre ingresado es inválido.");
            return false;
        }
    }

    private void agregar(View view) {

        Productos productos = new Productos();
        Boolean nombre = validCampos(etNombre.getText().toString());
        Boolean sku = validCampos(etSKU.getText().toString());
        Boolean precio = validCampos(etPrecio.getText().toString());
        Boolean imagen = validCampos(etImagen.getText().toString());

        if (nombre == true && sku == true && precio == true && imagen == true){
            productos.setUse_nombre(etNombre.getText().toString());
            productos.setUse_sku(etSKU.getText().toString());
            productos.setUse_precio(etPrecio.getText().toString());
            productos.setUse_imagen(etImagen.getText().toString());
            retrofit = ClienteRetrofit.getClient(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
            Call<String> call = serviceLogin.crearproducto(productos);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String respuesta = response.body();
                    Toast.makeText(Agregar_productos.this, ""+respuesta, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(Agregar_productos.this, "ERROR"+t, Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            etImagen.setError("Valida los campos anteriores");
        }

    }

    private void cambiar(View view) {
        cambiarPantalla(Menu.class);
    }

    private void begin() {
        this.btnCambiar = findViewById(R.id.btnCerrar);
        this.btnAgregar = findViewById(R.id.btnRegistrarPersona);
        this.etNombre = findViewById(R.id.etNombreProduct);
        this.etSKU = findViewById(R.id.etSKUProduct);
        this.etPrecio = findViewById(R.id.etPrecioProduct);
        this.etImagen = findViewById(R.id.etImagenProduct);

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