package com.example.marcadorescategorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcadorescategorias.model.classes.Categoria;
import com.example.marcadorescategorias.model.classes.MainViewModel;

public class AddCategoria extends AppCompatActivity {

    private MainViewModel viewModel;

    private EditText etCategoriaAddCategoria;
    private Button btAddCategoriaAddCategoria;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_categoria);

        initComponents();
        init();
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        etCategoriaAddCategoria = findViewById(R.id.etCategoriaAddCategoria);
        btAddCategoriaAddCategoria = findViewById(R.id.btAddCategoriaAddCategoria);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);

    }

    private void init() {
        viewModel.existeCategoriaLiveData().observe(this, new Observer<Categoria>() {
            @Override
            public void onChanged(Categoria categoria) {
                if (categoria.getNombre() == null){
                    Categoria categoriaAdd = new Categoria(0, pref.getLong("id",404), etCategoriaAddCategoria.getText().toString());
                    viewModel.addCategoria(categoriaAdd);
                    Toast.makeText(AddCategoria.this, getResources().getString(R.string.categoriaAdd), Toast.LENGTH_SHORT).show();
                    etCategoriaAddCategoria.setText("");
                }else{
                    Toast.makeText(AddCategoria.this, getResources().getString(R.string.existecategoria), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btAddCategoriaAddCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreCategoria = etCategoriaAddCategoria.getText().toString();

                if (nombreCategoria.equals("")){
                    Toast.makeText(AddCategoria.this, getResources().getString(R.string.camposVacios), Toast.LENGTH_SHORT).show();
                }else{
                    viewModel.existeCategoria(nombreCategoria);
                }
            }
        });
    }
}
