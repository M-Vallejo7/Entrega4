package com.vallejo.entrega4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.annotation.NonNull;

public class DatabaseManager {
    private ArrayList<HashMap<String, String>> listaDeEquipos = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getListaDeEquipos() {
        return listaDeEquipos;
    }
    public void cargarEquiposDeDB(final AdaptadorEquipos adaptadorEquipos) {
        //instantiate database connection
        FirebaseDatabase baseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference referenciaEquipos = baseDeDatos.getReference("equipos");

        referenciaEquipos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaDeEquipos.clear();
                for (DataSnapshot equipoSnapshot: dataSnapshot.getChildren()) {
                    HashMap<String, String> equipo = (HashMap<String, String>) equipoSnapshot.getValue();
                    listaDeEquipos.add(equipo);
                }
                adaptadorEquipos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError errorBaseDeDatos) {
                //handle databaseError
            }
        });
    }
}
