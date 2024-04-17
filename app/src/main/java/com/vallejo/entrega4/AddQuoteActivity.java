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
    private EditText editTextPresidente;
    private EditText editTextCategoria;
    private Button botonAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        //bind views
        editTextEquipo = (EditText) findViewById(R.id.editTextEquipo);
        editTextPresidente = (EditText) findViewById(R.id.editTextPresidente);
        editTextCategoria = (EditText) findViewById(R.id.editTextCategoria);
        botonAgregar = (Button) findViewById(R.id.botonAgregar);

        //listener
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get text
                String equipo = editTextEquipo.getText().toString();
                String presidente = editTextPresidente.getText().toString();
                String categoria = editTextCategoria.getText().toString();

                //check if empty
                if (equipo.isEmpty()){
                    editTextEquipo.setError("Agregue un equipo por favor");
                    return;
                }
                if (presidente.isEmpty()){
                    editTextPresidente.setError("Agregue un presidente por favor");
                    return;
                }
                if (categoria.isEmpty()){
                    editTextCategoria.setError("Agregue una categoría por favor");
                    return;
                }

                //add to database
                agregarEquipoABD(equipo, presidente, categoria);
            }
        });
    }

    private void agregarEquipoABD(String equipo, String presidente, String categoria) {
        //create a hashmap
        HashMap<String, Object> equipoHashmap = new HashMap<>();
        equipoHashmap.put("equipo", equipo);
        equipoHashmap.put("presidente", presidente);
        equipoHashmap.put("categoria", categoria);

        //Instanciar la conexión con la base de datos
        FirebaseDatabase baseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference referenciaEquipos = baseDeDatos.getReference("equipos");

        String clave = referenciaEquipos.push().getKey();
        equipoHashmap.put("clave", clave);

        referenciaEquipos.child(clave).setValue(equipoHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddQuoteActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
                editTextEquipo.getText().clear();
                editTextPresidente.getText().clear();
                editTextCategoria.getText().clear();
            }
        });
    }
}