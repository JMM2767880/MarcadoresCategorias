package com.example.marcadorescategorias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button btAddCategoriaMenu, btAddMarcadorMenu, btVerMarcadoresMenu, btCerrarSesionMenu;
    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initComponents();
        init();
    }

    private void initComponents() {
        btAddCategoriaMenu = findViewById(R.id.btAddCategoriaMenu);
        btAddMarcadorMenu = findViewById(R.id.btAddMarcadorMenu);
        btVerMarcadoresMenu = findViewById(R.id.btVerMarcadorMenu);
        btCerrarSesionMenu = findViewById(R.id.btCerrarSesionMenu);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
    }

    private void init() {
        btAddCategoriaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddCategoria = new Intent(Menu.this, AddCategoria.class);
                startActivity(intentAddCategoria);
            }
        });

        btAddMarcadorMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddMarcador = new Intent(Menu.this, AddMarcador.class);
                startActivity(intentAddMarcador);
            }
        });

        btVerMarcadoresMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVerMarcadores = new Intent(Menu.this, VerMarcadores.class);
                startActivity(intentVerMarcadores);
            }
        });

        btCerrarSesionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putLong("id", 404);
                editor.commit();
                Intent intentLogin = new Intent(Menu.this, Login.class);
                startActivity(intentLogin);
                finish();
            }
        });
    }
}
