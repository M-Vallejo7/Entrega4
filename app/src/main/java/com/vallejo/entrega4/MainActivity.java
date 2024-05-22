package com.vallejo.entrega4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    private ListView listaEquipos;
    private FloatingActionButton botonFlotante;
    private AdaptadorEquipos adaptadorEquipos;
    private DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEquipos = (ListView) findViewById(R.id.listaEquipos);
        botonFlotante = (FloatingActionButton) findViewById(R.id.botonFlotante);

        databaseManager = new DatabaseManager();
        adaptadorEquipos = new AdaptadorEquipos(this, databaseManager.getListaDeEquipos());

        listaEquipos.setAdapter(adaptadorEquipos);

        databaseManager.cargarEquiposDeDB(adaptadorEquipos);
        botonFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MainActivity.this, AddQuoteActivity.class);
                startActivity(intento);
            }
        });
    }
}