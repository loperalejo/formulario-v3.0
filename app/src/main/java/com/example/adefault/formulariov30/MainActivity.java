package com.example.adefault.formulariov30;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText eNombre, eContraseña, eRepita, eCorreo;
    private RadioButton rMasculino, rFemenino;
    private CheckBox cDeporte, cMusica, cTeatro, cViaje;
    private TextView tFecha;
    private ImageButton ibFecha;
    private Spinner sLugar;
    private Button bGuardar, bBuscar, bContactos;
    Calendar calendario;
    DatePickerDialog dpd;
    int dia, mes, año;

    ArrayList <DatosContacto> Personas = new ArrayList<DatosContacto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNombre = findViewById(R.id.eNombre);
        eContraseña = findViewById(R.id.eContraseña);
        eRepita = findViewById(R.id.eRepita);
        eCorreo = findViewById(R.id.eCorreo);
        rMasculino = findViewById(R.id.rMasculino);
        rFemenino = findViewById(R.id.rFemenino);
        cDeporte = findViewById(R.id.cDeporte);
        cMusica = findViewById(R.id.cMusica);
        cTeatro = findViewById(R.id.cTeatro);
        cViaje = findViewById(R.id.cViaje);
        tFecha = findViewById(R.id.tFecha);
        ibFecha = findViewById(R.id.ibFecha);
        sLugar = findViewById(R.id.sLugar);
        bGuardar = findViewById(R.id.bGuardar);
        bBuscar = findViewById(R.id.bBuscar);
        bContactos = findViewById(R.id.bContactos);

        ibFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendario = Calendar.getInstance();
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                año = calendario.get(Calendar.YEAR);

                dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mAño, int mMes, int mDia) {
                        tFecha.setText(mDia + "/" + (mMes + 1) + "/" + mAño);
                    }
                }, dia, mes, año);
                dpd.show();
            }
        });

        String[] lugar = {"Bogotá", "Medellín", "Cali", "Barranquilla", "Bucaramanga", "Otra"};

        sLugar.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner, lugar));


    }

    public void guardar(View view) {
        String contraseña1 = eContraseña.getText().toString();
        String contraseña2 = eRepita.getText().toString();
        String fecha1, fecha2, fecha3;
        StringBuilder hb = new StringBuilder();

        if ((eNombre.getText().toString().isEmpty()) || (eContraseña.getText().toString().isEmpty()) || (eRepita.getText().toString().isEmpty()) || (eCorreo.getText().toString().isEmpty())) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        } else if ((!rMasculino.isChecked()) && (!rFemenino.isChecked())) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        } else if ((!cDeporte.isChecked()) && (!cMusica.isChecked()) && (!cTeatro.isChecked()) && (!cViaje.isChecked())) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        } else if (tFecha.getText().toString().isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        } else if (!(contraseña1.equals(contraseña2))) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else {
            int cont = 0;
            int ok = 0;
            int st = 0;
            String nombre = eNombre.getText().toString();
            while (ok == 0){
                if(cont < Personas.size()) {
                    if (Personas.get(cont).getName().equals(nombre)) {
                        Toast.makeText(this, "Ya existe un contacto con el mismo nombre, por favor cambielo", Toast.LENGTH_SHORT).show();
                        ok = 1;
                        st = 1;
                    } else {
                        cont++;
                    }
                } else {
                    ok = 1;
                }
            }
            if (st == 0) {
                Intent intent = new Intent(MainActivity.this, GuardarActivity.class);
                DatosContacto NuevoContacto = new DatosContacto();
                NuevoContacto.setName(eNombre.getText().toString());
                NuevoContacto.setPassword(eContraseña.getText().toString());
                NuevoContacto.setEmail(eCorreo.getText().toString());
                if (rMasculino.isChecked()) {
                    NuevoContacto.setSex("masculino");
                } else {
                    NuevoContacto.setSex("femenino");
                }
                fecha1 = String.valueOf(dia);
                fecha2 = String.valueOf(mes);
                fecha3 = String.valueOf(año);
                NuevoContacto.setDate(dia + "/" + mes + "/" + año);
                NuevoContacto.setPlace(sLugar.getSelectedItem().toString());
                if (cDeporte.isChecked()) {
                    hb.append("Deporte ");
                }
                if (cMusica.isChecked()) {
                    hb.append("Musica ");
                }
                if (cTeatro.isChecked()) {
                    hb.append("Teatro ");
                }
                if (cViaje.isChecked()) {
                    hb.append("Viaje ");
                }
                NuevoContacto.setHobbies(hb.toString());
                intent.putExtra("contacto", NuevoContacto);
                startActivityForResult(intent, 1234);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 1234) && (resultCode == RESULT_OK)){
            Toast.makeText(this, data.getExtras().getString("Llegaron"), Toast.LENGTH_SHORT).show();
            DatosContacto contacto = data.getParcelableExtra("persona");
            Personas.add(contacto);
        }
        if((requestCode == 5678) && (resultCode == RESULT_OK)){
            Personas.clear();
            Personas = data.getParcelableArrayListExtra("persona");
        }

    }

    public void contactos(View view) {
        Intent intent = new Intent(MainActivity.this, ContactosActivity.class);
        intent.putExtra("contactos", Personas);
        startActivity(intent);
    }

    public void buscar(View view) {
        Intent intent = new Intent(MainActivity.this, BuscaActivity.class);
        intent.putExtra("contactos", Personas);
        startActivityForResult(intent, 5678);
    }

}

