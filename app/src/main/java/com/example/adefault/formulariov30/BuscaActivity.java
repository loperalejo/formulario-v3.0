package com.example.adefault.formulariov30;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuscaActivity extends AppCompatActivity {

    private EditText eNombre;
    private TextView tNombre, tCorreo, tSexo, tFecha, tLugar, tHobbie;
    private Button bBuscar, bRegresar, bEliminar;

    ArrayList<DatosContacto> Personas = new ArrayList<DatosContacto>();

    int el = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        eNombre = findViewById(R.id.eNombre);
        tNombre = findViewById(R.id.tNombre);
        tCorreo = findViewById(R.id.tCorreo);
        tSexo = findViewById(R.id.tSexo);
        tFecha = findViewById(R.id.tFecha);
        tLugar = findViewById(R.id.tLugar);
        tHobbie = findViewById(R.id.tHobbies);
        bBuscar = findViewById(R.id.bBuscar);
        bRegresar = findViewById(R.id.bRegresar);
        bEliminar = findViewById(R.id.bEliminar);

        Bundle args = getIntent().getExtras();

        if (args != null) {
            Personas = args.getParcelableArrayList("contactos");
        }
    }

    public void busca(View view) {
        if(eNombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingresa el nombre completo del contacto", Toast.LENGTH_SHORT).show();
        } else {
            int cont = 0;
            int ok = 0;
            String nombre = eNombre.getText().toString();
            while(ok == 0) {
                if(cont < Personas.size()) {
                    if (Personas.get(cont).getName().equals(nombre)) {
                        tNombre.setText(Personas.get(cont).getName());
                        tCorreo.setText(Personas.get(cont).getEmail());
                        tSexo.setText(Personas.get(cont).getSex());
                        tFecha.setText(Personas.get(cont).getDate());
                        tLugar.setText(Personas.get(cont).getPlace());
                        tHobbie.setText(Personas.get(cont).getHobbies());
                        ok = 1;
                        el = 1;
                    } else {
                        cont++;
                    }
                } else {
                    Toast.makeText(this, "El contacto ingresado no existe", Toast.LENGTH_SHORT).show();
                    ok = 1;
                }
            }
        }
    }

    public void atras(View view) {
        Intent intent = new Intent();
        intent.putExtra("persona", Personas);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void eliminar(View view) {
        if(el == 0){
            Toast.makeText(this, "Busca el contacto que deseas eliminar", Toast.LENGTH_SHORT).show();
        } else {
            String eliminar = tNombre.getText().toString();
            el = 0;
            int ok = 0;
            int cont = 0;
            while(ok == 0) {
                if (Personas.get(cont).getName().equals(eliminar)) {
                    Personas.remove(cont);
                    tNombre.setText("");
                    tCorreo.setText("");
                    tSexo.setText("");
                    tFecha.setText("");
                    tLugar.setText("");
                    tHobbie.setText("");
                    Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
                    ok = 1;
                } else {
                    cont++;
                }
            }
        }
    }
}
