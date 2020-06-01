package com.example.marcadorescategorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcadorescategorias.model.classes.MainViewModel;
import com.example.marcadorescategorias.model.classes.Usuario;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private EditText etUsuarioRegistro, etPasswordRegistro;
    private Button btRegistrarRegistro, btIniciarSesionRegistro;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        init();
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        etUsuarioRegistro = findViewById(R.id.etUsuarioRegistro);
        etPasswordRegistro = findViewById(R.id.etPasswordRegistro);
        btRegistrarRegistro = findViewById(R.id.btRegistrarRegistro);
        btIniciarSesionRegistro = findViewById(R.id.btIniciarSesionRegistro);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
    }

    private void init() {
        if (!pref.getString("correo", "").equals("")){
            Intent intentSesion = new Intent(MainActivity.this, Menu.class);
            startActivity(intentSesion);
            finish();
        }
        viewModel.getExisteUsuarioLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario.getCorreo() == null){
                    Usuario addUsuario = new Usuario(0, etUsuarioRegistro.getText().toString(), etPasswordRegistro.getText().toString());
                    viewModel.addUsuario(addUsuario);
                    etUsuarioRegistro.setText("");
                    etPasswordRegistro.setText("");
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.usuarioadd), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.usuarioexiste), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btRegistrarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuarioRegistro.getText().toString();
                String password = etPasswordRegistro.getText().toString();

                if (usuario.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.camposVacios), Toast.LENGTH_SHORT).show();
                }else{
                    viewModel.fetchExisteUsuario(usuario);
                }
            }
        });

        btIniciarSesionRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrLogin = new Intent(MainActivity.this, Login.class);
                startActivity(intentIrLogin);
            }
        });
    }
}
