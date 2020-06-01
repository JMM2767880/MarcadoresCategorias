package com.example.marcadorescategorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.marcadorescategorias.model.classes.AdapterMarcadoresCategoria;
import com.example.marcadorescategorias.model.classes.Categoria;
import com.example.marcadorescategorias.model.classes.MainViewModel;
import com.example.marcadorescategorias.model.classes.Marcadores;

import java.util.List;

public class MarcadoresCategoria extends AppCompatActivity {

    private MainViewModel viewModel;

    private Long idUsuario;
    private String nombreCategoria;

    private RecyclerView rvMarcadoresCategoria;
    private AdapterMarcadoresCategoria adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcadores_categoria);

        initComponents();
        init();
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        idUsuario = getIntent().getLongExtra("id", 404);
        nombreCategoria = getIntent().getStringExtra("categoria");

        rvMarcadoresCategoria = findViewById(R.id.rvMarcadoresCategoria);
    }

    private void init() {
        viewModel.categoriaUsuarioNombreLiveData().observe(this, new Observer<Categoria>() {
            @Override
            public void onChanged(final Categoria categoria) {
                viewModel.marcadoresCategoriaLiveData().observe(MarcadoresCategoria.this, new Observer<List<Marcadores>>() {
                    @Override
                    public void onChanged(List<Marcadores> marcadores) {
                        rvMarcadoresCategoria.setHasFixedSize(true);

                        lManager = new LinearLayoutManager(MarcadoresCategoria.this);
                        rvMarcadoresCategoria.setLayoutManager(lManager);

                        adapter = new AdapterMarcadoresCategoria(marcadores, MarcadoresCategoria.this, viewModel, idUsuario, categoria.getId());
                        rvMarcadoresCategoria.setAdapter(adapter);
                    }
                });
                viewModel.marcadoresCategoria(idUsuario + ":" + categoria.getId());
            }
        });
        viewModel.categoriaUsuarioNombre(idUsuario + ":" + nombreCategoria);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        viewModel.categoriaUsuarioNombreLiveData().observe(this, new Observer<Categoria>() {
            @Override
            public void onChanged(Categoria categoria) {
                viewModel.marcadoresCategoriaLiveData().observe(MarcadoresCategoria.this, new Observer<List<Marcadores>>() {
                    @Override
                    public void onChanged(List<Marcadores> marcadores) {
                        adapter.notifyDataSetChanged();
                    }
                });
                viewModel.marcadoresCategoria(idUsuario + ":" + categoria.getId());
            }
        });
        viewModel.categoriaUsuarioNombre(idUsuario + ":" + nombreCategoria);
    }
}
