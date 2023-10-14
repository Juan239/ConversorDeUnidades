package com.example.conversordeunidades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_contacto extends AppCompatActivity {
    TextView correoP;
    EditText correoElectronico;
    Button btnSubir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        correoP = findViewById(R.id.correoP);
        btnSubir = findViewById(R.id.btnCorreo);
        correoElectronico = findViewById(R.id.correoS);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("correos");
        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = correoElectronico.getText().toString();
                referencia.orderByValue().equalTo(texto).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            // El contenido no existe en la base de datos, así que lo subimos
                            try {
                                DatabaseReference nuevaEntrada = referencia.push();
                                nuevaEntrada.setValue(texto);
                                correoElectronico.setText("");
                                Toast.makeText(activity_contacto.this, "Su correo ha sido subido correctamente", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                Toast.makeText(activity_contacto.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(activity_contacto.this, "Ese correo ya existe en la base de datos", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        correoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = correoP.getText().toString();

                // Copia el texto al portapapeles
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Texto copiado", texto);
                clipboard.setPrimaryClip(clip);

                // Muestra un mensaje de confirmación
                Toast.makeText(getApplicationContext(), "Texto copiado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}