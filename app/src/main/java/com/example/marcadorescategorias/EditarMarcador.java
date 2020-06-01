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

import java.util.ArrayList;
import java.util.List;

public class EditarMarcador extends AppCompatActivity {

    private MainViewModel viewModel;

    private EditText etNombreEditarMarcador, etDescripcionEditarMarcador;
    private Spinner spnEditarMarcador;
    private Button btEditarMarcador;

    private String[] nombreMarcadorEditar;
    private long idMarcador;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_marcador);

        initComponents();
        init();
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        etNombreEditarMarcador = findViewById(R.id.etNombreEditarMarcador);
        etDescripcionEditarMarcador = findViewById(R.id.etDescripcionEditarMarcador);
        spnEditarMarcador = findViewById(R.id.spnEditarMarcador);
        btEditarMarcador = findViewById(R.id.btEditarMarcador);

        nombreMarcadorEditar = getIntent().getStringExtra("nombreMarcadorEditar").split("\\.");
        idMarcador = Long.parseLong(nombreMarcadorEditar[0]);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
    }

    private void init() {

        viewModel.marcadorPorIdLiveData().observe(this, new Observer<Marcadores>() {
            @Override
            public void onChanged(final Marcadores marcadores) {
                etNombreEditarMarcador.setText(marcadores.getUrl());
                etDescripcionEditarMarcador.setText(marcadores.getDescripcion());

                viewModel.todasCategoriasLiveData().observe(EditarMarcador.this, new Observer<List<Categoria>>() {
                    @Override
                    public void onChanged(List<Categoria> categorias) {
                        List<String> nombreCategorias = new ArrayList<>();

                        for (int i = 0; i < categorias.size(); i++){
                            nombreCategorias.add(categorias.get(i).getNombre());
                        }

                        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(EditarMarcador.this,
                                android.R.layout.simple_spinner_item, nombreCategorias);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnEditarMarcador.setAdapter(dataAdapter);

                        viewModel.categoriaPorIdLiveData().observe(EditarMarcador.this, new Observer<Categoria>() {
                            @Override
                            public void onChanged(Categoria categoria) {
                                int spinnerPosition = dataAdapter.getPosition(categoria.getNombre());
                                spnEditarMarcador.setSelection(spinnerPosition);
                            }
                        });
                        viewModel.categoriaPorId(marcadores.getIdcategoria());


                    }
                });

                viewModel.todasCategorias(pref.getLong("id", 404));
            }
        });
        viewModel.marcadorPorId(idMarcador);


        viewModel.categoriaUsuarioNombreLiveData().observe(this, new Observer<Categoria>() {
            @Override
            public void onChanged(Categoria categoria) {
                Marcadores marcadores = new Marcadores(idMarcador, pref.getLong("id", 404), categoria.getId() , etNombreEditarMarcador.getText().toString(), etDescripcionEditarMarcador.getText().toString());
                viewModel.editarMarcador(marcadores);
                System.out.println(marcadores.toString());
                Toast.makeText(EditarMarcador.this, getResources().getString(R.string.marcadoreditado), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btEditarMarcador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombreEditarMarcador.getText().toString().equals("")){
                    Toast.makeText(EditarMarcador.this, getResources().getString(R.string.camposVacios), Toast.LENGTH_SHORT).show();
                }else{
                    String itemSpinner = spnEditarMarcador.getSelectedItem().toString();

                    viewModel.categoriaUsuarioNombre(pref.getLong("id", 404) + ":" + itemSpinner);
                }
            }
        });
    }
}
