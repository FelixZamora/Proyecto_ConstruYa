package co.edu.ue.practica_login_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Registro extends AppCompatActivity {

    private ImageButton btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        begin();
        btnCerrar.setOnClickListener(this::pantallaLogin);
    }

    private void begin() {
        this.btnCerrar = findViewById(R.id.btnCerrar);
    }

    private void pantallaLogin(View view) {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}