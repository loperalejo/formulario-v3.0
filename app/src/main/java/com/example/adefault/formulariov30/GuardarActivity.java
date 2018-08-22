package com.example.adefault.formulariov30;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GuardarActivity extends AppCompatActivity {

    private TextView tNombre, tCorreo, tSexo, tFecha, tLugar, tHobbie;
    private Button bGuardarDatos, bCorregir;
    DatosContacto contacto = new DatosContacto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);

        tNombre = findViewById(R.id.tNombre);
        tCorreo = findViewById(R.id.tCorreo);
        tSexo = findViewById(R.id.tSexo);
        tFecha = findViewById(R.id.tFecha);
        tLugar = findViewById(R.id.tLugar);
        tHobbie = findViewById(R.id.tHobbie);
        bCorregir = findViewById(R.id.bCorregir);
        bGuardarDatos = findViewById(R.id.bGuardarDatos);

        Bundle args = getIntent().getExtras();

        if(args!=null) {
            contacto = args.getParcelable("contacto");
            tNombre.setText(contacto.getName());
            tCorreo.setText(contacto.getEmail());
            tSexo.setText(contacto.getSex());
            tFecha.setText(contacto.getDate());
            tLugar.setText(contacto.getPlace());
            tHobbie.setText(contacto.getHobbies());
        }
    }

    public void guardarDatos(View view) {
        Intent intent = new Intent();
        intent.putExtra("Llegaron", "Guardado");
        intent.putExtra("persona", contacto);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void corregir(View view) {
        finish();
    }
}
