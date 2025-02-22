package com.example.appmovil1526631silva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Inicializar los objetos que estan en el xml, llamada por id

        TextView Volver = findViewById(R.id.lbl_Salir);
        EditText Nombre = findViewById(R.id.Nombre);
        EditText Telefono = findViewById(R.id.Telefono);
        EditText Email = findViewById(R.id.newEmail);
        EditText Password = findViewById(R.id.txt_newPass);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sal0ir = new Intent(register.this,MainActivity.class);
                startActivity(Sal0ir);
            }
        });
    }
}