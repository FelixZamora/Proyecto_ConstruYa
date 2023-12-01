package co.edu.ue.practica_login_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Olvide_pss extends AppCompatActivity {

    private ImageButton btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide_pss);
        begin();
        btnCerrar.setOnClickListener(this::pantallaLogin);
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

    private void begin() {
        this.btnCerrar = findViewById(R.id.btnCerrar);
    }
}