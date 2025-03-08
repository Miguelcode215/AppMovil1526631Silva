package com.example.appmovil1526631silva;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registrar_cliente extends AppCompatActivity {
    //Inicializar las variables del registrar cliente(boton y campos de texto)
   private EditText txt_dni_cl, txt_apellidos_cl, txt_nombre_cl, txt_telefono_cl, txt_direccion_cl, txt_email_cl;
   private Button btn_guardar_cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_guardar_cl = findViewById(R.id.btn_guardar_cliente); //inicializamos con el botn de nuestro xml y los demas componentes de xml
        txt_dni_cl = findViewById(R.id.txt_DNI_cliente);
        txt_apellidos_cl = findViewById(R.id.txt_apellido_cliente);
        txt_nombre_cl = findViewById(R.id.txt_nombre_cliente);
        txt_telefono_cl = findViewById(R.id.txt_telefono_cliente);
        txt_direccion_cl = findViewById(R.id.txt_direccion_cliente);
        txt_email_cl = findViewById(R.id.txt_email_cliente);
        String idusuarioAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //inicializamos la accion de el boton de guardar clientes
        btn_guardar_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni_cliente = txt_dni_cl.getText().toString();
                String apellido_cliente = txt_apellidos_cl.getText().toString();
                String nombre_cliente = txt_nombre_cl.getText().toString();
                String telefono_cliente = txt_telefono_cl.getText().toString();
                String direccion_cliente = txt_direccion_cl.getText().toString();
                String email_user = txt_email_cl.getText().toString();

                if (TextUtils.isEmpty(dni_cliente) || TextUtils.isEmpty(apellido_cliente) || TextUtils.isEmpty(nombre_cliente) || TextUtils.isEmpty(telefono_cliente) || TextUtils.isEmpty(direccion_cliente)){

                    Toast.makeText(registrar_cliente.this, "SE REQUIERE TODOS LOS CAMPOS, EL UNICO NO OBLIGATORIO ES EL EMAIL", Toast.LENGTH_SHORT).show();
                }else{
                    DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
                     String idcliente = dr.child("clientes").push().getKey();
                    Map<String, String> cliente = new HashMap<>();
                    cliente.put("dni", dni_cliente);
                    cliente.put("Apellido", apellido_cliente);
                    cliente.put("Nombre", nombre_cliente);
                    cliente.put("Telefono", telefono_cliente);
                    cliente.put("Direccion", direccion_cliente);
                    cliente.put("Email", email_user);

                    dr.child("clientes").child(idusuarioAuth).child(idcliente).setValue(cliente).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(registrar_cliente.this, "SE REGUISTRO CORRECTAMNETE", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}