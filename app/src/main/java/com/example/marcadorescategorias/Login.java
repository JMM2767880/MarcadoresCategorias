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

public class Login extends AppCompatActivity {

    private MainViewModel viewModel;

    private EditText etUsuarioLogin, etPasswordLogin;
    private Button btAccederLogin;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        init();
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        etUsuarioLogin = findViewById(R.id.etUsuarioLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btAccederLogin = findViewById(R.id.btAccederLogin);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
    }

    private void init() {
        viewModel.getLoginLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                System.out.println("login: " + usuario);
                if (usuario.getCorreo() == null){
                    Toast.makeText(Login.this, getResources().getString(R.string.loginIncorrecto), Toast.LENGTH_SHORT).show();
                }else{
                    editor.putLong("id", usuario.getId());
                    editor.putString("correo", usuario.getCorreo());
                    editor.putString("password", usuario.getClave());
                    editor.commit();
                    Intent intenIrMenu = new Intent(Login.this, Menu.class);
                    startActivity(intenIrMenu);
                    finish();
                    Toast.makeText(Login.this, getResources().getString(R.string.bienvenido) + " " + usuario.getCorreo(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btAccederLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuarioLogin.getText().toString();
                String password = etPasswordLogin.getText().toString();

                if (usuario.equals("")  || password.equals("")){
                    Toast.makeText(Login.this, getResources().getString(R.string.camposVacios), Toast.LENGTH_SHORT).show();
                }else{
                    viewModel.getLogin(usuario + ":" + password);
                }
            }
        });
    }
}
