package com.vallejo.entrega4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddQuoteActivity extends AppCompatActivity{
    private EditText editTextEquipo;
    private EditText editTextLiga;
    private EditText editTextCategoria;
    private Button botonAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        editTextEquipo = (EditText) findViewById(R.id.editTextEquipo);
        editTextLiga = (EditText) findViewById(R.id.editTextLiga);
        editTextCategoria = (EditText) findViewById(R.id.editTextCategoria);
        botonAgregar = (Button) findViewById(R.id.botonAgregar);

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String equipo = editTextEquipo.getText().toString();
                String liga = editTextLiga.getText().toString();
                String categoria = editTextCategoria.getText().toString();

                if (equipo.isEmpty()){
                    editTextEquipo.setError("Agregue un equipo por favor");
                    return;
                }
                if (liga.isEmpty()){
                    editTextLiga.setError("Agregue la liga en la que participa el equipo");
                    return;
                }
                if (categoria.isEmpty()){
                    editTextCategoria.setError("Agregue una categoría en donde el equipo clasificó");
                    return;
                }

                agregarEquipoABD(equipo, liga, categoria);
            }
        });
    }

    private void agregarEquipoABD(String equipo, String liga, String categoria) {

        HashMap<String, Object> equipoHashmap = new HashMap<>();
        equipoHashmap.put("equipo", equipo);
        equipoHashmap.put("liga", liga);
        equipoHashmap.put("categoria", categoria);

        FirebaseDatabase baseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference referenciaEquipos = baseDeDatos.getReference("equipos");

        String clave = referenciaEquipos.push().getKey();
        equipoHashmap.put("clave", clave);

        referenciaEquipos.child(clave).setValue(equipoHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddQuoteActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
                editTextEquipo.getText().clear();
                editTextLiga.getText().clear();
                editTextCategoria.getText().clear();
            }
        });
    }
}