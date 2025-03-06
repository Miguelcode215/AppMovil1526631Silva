package com.example.appmovil1526631silva;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    EditText Nombre, Telefono, Email, Password;

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
        Nombre = findViewById(R.id.Nombre);
        Telefono = findViewById(R.id.Telefono);
        Email = findViewById(R.id.newEmail);
        Password = findViewById(R.id.txt_newPass);
        Button btn_registrar = findViewById(R.id.btn_Register);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sal0ir = new Intent(register.this,MainActivity.class);
                startActivity(Sal0ir);
            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombreuser = Nombre.getText().toString();
                String Telefonouser = Telefono.getText().toString();
                String Emailuser = Email.getText().toString();
                String Passworduser = Password.getText().toString();

                if(TextUtils.isEmpty(Nombreuser) || TextUtils.isEmpty(Telefonouser) || TextUtils.isEmpty(Emailuser) || TextUtils.isEmpty(Passworduser)){
                    Toast.makeText(register.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(Emailuser,Passworduser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                               Map<String, String> user = new HashMap<>();
                               user.put("username",Nombreuser);
                               user.put("usertelefono",Telefonouser);
                               user.put("email", Emailuser);

                               FirebaseDatabase.getInstance().getReference().child("usuarios").child(userid).setValue(user);

                           }

                           Toast.makeText(register.this,"REGISTRO EXITOSO",Toast.LENGTH_SHORT).show();
                        }
                    });
                    limpiarCampos();
                    finish();
                }
            }

        });
    }
    private void limpiarCampos(){
        Nombre.setText("");
        Telefono.setText("");
        Email.setText("");
        Password.setText("");
        Nombre.requestFocus();
    }
}