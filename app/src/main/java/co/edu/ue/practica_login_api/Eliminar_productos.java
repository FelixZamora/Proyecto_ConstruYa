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
import co.edu.ue.practica_login_api.model.Productos;
import co.edu.ue.practica_login_api.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Eliminar_productos extends AppCompatActivity {
    private ImageButton btnCambiar;
    private EditText etENombre;
    private EditText etESKU;
    private Button btnBorrar;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_productos);
        begin();
        this.btnCambiar.setOnClickListener(this::cambiar);
        this.btnBorrar.setOnClickListener(this::borrar);
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

    private void borrar(View view) {
        try {
            Productos producto = new Productos();
            Boolean nombre = validCampos(etENombre.getText().toString());
            Boolean sku = validCampos(etESKU.getText().toString());

            if(nombre == true && sku == true){
                producto.setUse_nombre(etENombre.getText().toString());
                producto.setUse_sku(etESKU.getText().toString());
                producto.setUse_precio("25");
                producto.setUse_imagen("KDKJCKDLK.COM");

                retrofit = ClienteRetrofit.getClient(BASE_URL);
                ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
                Call<String> call = serviceLogin.eliminarproducto(producto);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String respuesta = response.body();
                        Toast.makeText(Eliminar_productos.this, ""+respuesta, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Eliminar_productos.this, ""+t, Toast.LENGTH_LONG).show();
                    }
                });
            }
            else {
                etESKU.setError("Valida los campos anteriores");
            }


        }catch (Exception e){
            Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
        }
    }

    private void cambiar(View view) {
        cambiarPantalla(Menu.class);
    }

    private void begin() {
        this.btnCambiar = findViewById(R.id.btnCerrar);
        this.etENombre = findViewById(R.id.etNombreEP);
        this.etESKU = findViewById(R.id.etSkuE);
        this.btnBorrar = findViewById(R.id.btnBorrar);
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