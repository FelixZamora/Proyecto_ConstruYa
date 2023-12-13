package co.edu.ue.practica_login_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnBorrar;
    private Button btnCerrarSesion;
    private ImageButton btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        begin();
        this.btnInsertar.setOnClickListener(this::cambiarInsertar);
        this.btnActualizar.setOnClickListener(this::cambiarActualizar);
        this.btnBorrar.setOnClickListener(this::cambiarBorrar);
        this.btnCerrarSesion.setOnClickListener(this::cambiarCerrar);
        this.btnAtras.setOnClickListener(this::cambiarAtras);
    }

    private void cambiarAtras(View view) {
        cambiarPantalla(Inicio.class);
    }

    private void cambiarCerrar(View view) {
        cambiarPantalla(MainActivity.class);
    }

    private void cambiarBorrar(View view) {
        cambiarPantalla(Eliminar_productos.class);
    }

    private void cambiarActualizar(View view) {
        cambiarPantalla(Actualizar_productos.class);
    }

    private void cambiarInsertar(View view) {
        cambiarPantalla(Agregar_productos.class);
    }

    private void begin() {
        this.btnInsertar = findViewById(R.id.btnInsertar);
        this.btnActualizar = findViewById(R.id.btnActualizar);
        this.btnBorrar = findViewById(R.id.btnDelete);
        this.btnCerrarSesion = findViewById(R.id.btnSalir);
        this.btnAtras = findViewById(R.id.btnCerrar);
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