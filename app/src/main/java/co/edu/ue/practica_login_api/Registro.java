package co.edu.ue.practica_login_api;

import static co.edu.ue.practica_login_api.api.ValuesApi.BASE_URL;
import static co.edu.ue.practica_login_api.remote.ClienteRetrofit.retrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.ue.practica_login_api.api.ServiceLogin;
import co.edu.ue.practica_login_api.model.Credentials;
import co.edu.ue.practica_login_api.model.Register;
import co.edu.ue.practica_login_api.model.ResponseCredentials;
import co.edu.ue.practica_login_api.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Registro extends AppCompatActivity {

    private Retrofit retrofit;

    private EditText etCorreo;
    private EditText etNombre;
    private EditText etPss;
    private EditText etPss1;


    private ImageButton btnCerrar;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        begin();
        btnCerrar.setOnClickListener(this::pantallaLogin);
        btnRegistrar.setOnClickListener(this::processRegister);
    }

    public boolean validEmail(String data){
        Pattern pattern =
                Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~\\-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher mather = pattern.matcher(data);
        if (mather.find() == true) {
            //System.out.println("El email ingresado es válido.");
            return true;
        } else {
            //System.out.println("El email ingresado es inválido.");
            return false;
        }

    }

    public static boolean validFullName(String fullName) {

        Pattern pattern = Pattern.compile("^[a-zA-ZÁ-ÿ\\s']+([a-zA-ZÁ-ÿ\\s']+)+([a-zA-ZÁ-ÿ\\s']+)*$");
        Matcher matcher = pattern.matcher(fullName);

        if (matcher.find() == true) {
            //System.out.println("El nombre ingresado es válido.");
            return true;
        } else {
            //System.out.println("El nombre ingresado es inválido.");
            return false;
        }
    }

    public String fechaActual() {
        // Obtener la fecha actual
        Date currentDate = new Date();

        // Formatear la fecha según tus necesidades
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(currentDate);

        return formattedDate;
    }

    public static boolean validPss(String password1, String password2) {
        // Verifica que las contraseñas sean iguales
        if (!password1.equals(password2)) {
            System.out.println("Las contraseñas no coinciden.");
            return false;
        }

        // Verifica que la contraseña cumpla con los requisitos
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        Matcher matcher = pattern.matcher(password1);

        if (matcher.matches()) {
            //System.out.println("La contraseña cumple con los requisitos.");
            return true;
        } else {
            //System.out.println("La contraseña no cumple con los requisitos.");
            return false;
        }
    }

    private void processRegister(View view) {
        Boolean mail = validEmail(this.etCorreo.getText().toString());
        Boolean fullName = validFullName(this.etNombre.getText().toString());
        Boolean pss = validPss(this.etPss.getText().toString(), this.etPss1.getText().toString());

        if (mail == true && fullName == true && pss == true){
            Register register = new Register();
            String fechaActual = fechaActual();
            register.setUse_mail(etCorreo.getText().toString());
            register.setUse_pss(etPss.getText().toString());
            register.setUse_dateCreate(fechaActual.toString());
            register.setUse_name(etNombre.getText().toString());
            //VAMOS BIEN
            retrofit = ClienteRetrofit.getClient(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
            Call<String> llamada = serviceLogin.accessregister(register);
            llamada.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()) {
                        String body = response.body();
                        String mensaje = body.toString();
                        Toast.makeText(Registro.this, "" + mensaje, Toast.LENGTH_LONG).show();
                        if (mensaje == "Se ha registrado exitosamente"){
                            cambiarPantalla(MainActivity.class);
                        }

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    alertView("ERROR EN LA CONEXION");
                }
            });

        }
        else{
            if(mail == false){
                etCorreo.setError("El nombre de usuario de la dirección de correo electrónico debe comenzar con una letra");
            }
            else if (fullName == false) {
                etNombre.setError("El nombre completo debe comenzar y terminar con una letra o una tilde, y puede contener cualquier combinación de letras, tildes, espacios en blanco y apóstrofes, y debe tener al menos dos palabras");
            }
            else{
                etPss.setError("La contraseña debe tener al menos 8 caracteres, una letra minúscula, una letra mayúscula y un dígito");
                etPss1.setError("Revisa la coincidencia con la contraseña anterior");
            }
        }

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



    private void alertView(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registro");
        builder.setMessage(mensaje);
        builder.setPositiveButton("ACEPTAR", null);
        builder.create();
        builder.show();
    }
    public static boolean isNullOrEmpty(Object obj){
        if(obj==null)return true;
        if(obj instanceof String) return ((String)obj).trim().equals("") || ((String)obj).equalsIgnoreCase("NULL");
        if(obj instanceof Integer) return ((Integer)obj)==0;
        if(obj instanceof Long) return ((Long)obj).equals(new Long(0));
        if(obj instanceof Double) return ((Double)obj).equals(0.0);
        if(obj instanceof Collection) return (((Collection)obj).isEmpty());
        return false;
    }

    private void begin() {
        //EDIT TEXT
        this.etCorreo = findViewById(R.id.etCorreo);
        this.etNombre = findViewById(R.id.etFull_name);
        this.etPss = findViewById(R.id.etPassword);
        this.etPss1 = findViewById(R.id.etPassword2);
        //BOTONES
        this.btnCerrar = findViewById(R.id.btnCerrar);
        this.btnRegistrar = findViewById(R.id.btnRegistrarPersona);
    }

    private void pantallaLogin(View view) {
        cambiarPantalla(MainActivity.class);
    }
}