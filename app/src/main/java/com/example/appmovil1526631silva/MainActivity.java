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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnLogin = findViewById(R.id.btn_Login);
        TextView lblRegister = findViewById(R.id.lbl_register);
        TextView lblResetpass = findViewById(R.id.lbl_Recetpass);
        EditText txtName = findViewById(R.id.txt_User);
        EditText txtPass = findViewById(R.id.txt_Password);

        lblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conecto = new Intent(MainActivity.this, register.class);
                startActivity(conecto);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaillog = txtName.getText().toString();
                String passlog = txtPass.getText().toString();

                if(TextUtils.isEmpty(emaillog) || TextUtils.isEmpty(passlog)){
                    Toast.makeText(MainActivity.this, "SE REQUIERE DE LOS DOS CAMPOS",Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emaillog, passlog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent ingresar = new Intent(MainActivity.this,Panelinicio.class);
                                startActivity(ingresar);
                                finish();

                                Toast.makeText(MainActivity.this, "LOGIN EXITOSO... BIENBENIDO",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}