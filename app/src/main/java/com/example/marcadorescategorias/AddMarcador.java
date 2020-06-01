package com.example.marcadorescategorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marcadorescategorias.model.classes.Categoria;
import com.example.marcadorescategorias.model.classes.MainViewModel;
import com.example.marcadorescategorias.model.classes.Marcadores;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddMarcador extends AppCompatActivity {

    private MainViewModel viewModel;

    private EditText etMarcadorAddMarcador, etDescripcionAddMarcador;
    private Button btAddMarcador;
    private Spinner spnAddMarcador;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marcador);

        initComponents();
        init();
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        etMarcadorAddMarcador = findViewById(R.id.etMarcadorAddMarcador);
        etDescripcionAddMarcador = findViewById(R.id.etDescripcionAddMarcador);
        btAddMarcador = findViewById(R.id.btAddMarcador);
        spnAddMarcador = findViewById(R.id.spnAddMarcador);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
    }

    private void init() {
        viewModel.todasCategoriasLiveData().observe(this, new Observer<List<Categoria>>() {
            @Override
            public void onChanged(List<Categoria> categorias) {
                List<String> nombreCategorias = new ArrayList<>();

                for (int i = 0; i < categorias.size(); i++){
                    nombreCategorias.add(categorias.get(i).getNombre());
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddMarcador.this,
                        android.R.layout.simple_spinner_item, nombreCategorias);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnAddMarcador.setAdapter(dataAdapter);
            }
        });

        viewModel.todasCategorias(pref.getLong("id", 404));

        viewModel.categoriaUsuarioNombreLiveData().observe(this, new Observer<Categoria>() {
            @Override
            public void onChanged(Categoria categoria) {
                String itemSpinner = spnAddMarcador.getSelectedItem().toString();

                Marcadores marcadores = new Marcadores(0, pref.getLong("id", 404), categoria.getId() , etMarcadorAddMarcador.getText().toString(), etDescripcionAddMarcador.getText().toString());
                viewModel.postMarcadores(marcadores);

                Toast.makeText(AddMarcador.this, getResources().getString(R.string.marcadoradd), Toast.LENGTH_SHORT).show();

                etMarcadorAddMarcador.setText("");
                etDescripcionAddMarcador.setText("");
            }
        });

        btAddMarcador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMarcadorAddMarcador.getText().toString().equals("") || etDescripcionAddMarcador.getText().toString().equals("")){
                    Toast.makeText(AddMarcador.this, getResources().getString(R.string.camposVacios), Toast.LENGTH_SHORT).show();
                }else{
                    String itemSpinner = spnAddMarcador.getSelectedItem().toString();

                    viewModel.categoriaUsuarioNombre(pref.getLong("id", 404) + ":" + itemSpinner);
                }
            }
        });
    }
}
