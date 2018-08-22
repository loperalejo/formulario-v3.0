package com.example.adefault.formulariov30;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactosActivity extends AppCompatActivity {

    private TextView tNombre, tCorreo, tSexo, tFecha, tLugar, tHobbie;
    private ListView lContactos;
    private Button bRegresar;

    ArrayList<DatosContacto> Personas = new ArrayList<DatosContacto>();
    String nombre = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        tNombre = findViewById(R.id.tNombre);
        tCorreo = findViewById(R.id.tCorreo);
        tSexo = findViewById(R.id.tSexo);
        tFecha = findViewById(R.id.tFecha);
        tLugar = findViewById(R.id.tLugar);
        tHobbie = findViewById(R.id.tHobbies);
        lContactos = findViewById(R.id.lContactos);
        bRegresar = findViewById(R.id.bRegresar);

        Bundle args = getIntent().getExtras();

        if(args != null){
            Personas = args.getParcelableArrayList("contactos");
            lContactos.setAdapter(new ArrayAdapter<DatosContacto>(this, R.layout.mylist, Personas));

            lContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    nombre = lContactos.getItemAtPosition(i).toString();
                    int cont = 0;
                    int ok = 0;
                    while(ok == 0) {
                        if (Personas.get(cont).getName().equals(nombre)) {
                            ok = 1;
                        } else {
                            cont++;
                        }
                    }
                    tNombre.setText(Personas.get(cont).getName());
                    tCorreo.setText(Personas.get(cont).getEmail());
                    tSexo.setText(Personas.get(cont).getSex());
                    tFecha.setText(Personas.get(cont).getDate());
                    tLugar.setText(Personas.get(cont).getPlace());
                    tHobbie.setText(Personas.get(cont).getHobbies());
                }
            });
        }
    }

    public void regresar(View view) {
        finish();
    }
}
