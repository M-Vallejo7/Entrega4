package com.vallejo.entrega4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

public class AdaptadorEquipos extends ArrayAdapter<HashMap<String, String>> {
    private ArrayList<HashMap<String, String>> listaDeEquipos;

    public AdaptadorEquipos(Context context, ArrayList<HashMap<String, String>> listaDeEquipos) {
        super(context, android.R.layout.simple_list_item_2, android.R.id.text1, listaDeEquipos);
        this.listaDeEquipos = listaDeEquipos;
    }

    @NonNull
    @Override
    public View getView(int posicion, View convertView, @NonNull ViewGroup parent) {
        View vista = super.getView(posicion, convertView, parent);
        TextView texto1 = (TextView) vista.findViewById(android.R.id.text1);
        TextView texto2 = (TextView) vista.findViewById(android.R.id.text2);

        texto1.setText(listaDeEquipos.get(posicion).get("equipo"));
        texto2.setText(listaDeEquipos.get(posicion).get("presidente") + ", " + listaDeEquipos.get(posicion).get("categoria"));

        return vista;
    }
}
