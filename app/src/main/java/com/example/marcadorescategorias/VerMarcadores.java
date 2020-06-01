package com.example.marcadorescategorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.marcadorescategorias.model.classes.AdapterVerMarcadores;
import com.example.marcadorescategorias.model.classes.Categoria;
import com.example.marcadorescategorias.model.classes.MainViewModel;

import java.util.List;

public class VerMarcadores extends AppCompatActivity {

    private MainViewModel viewModel;

    private SharedPreferences pref;

    private RecyclerView rvVerMarcadores;

    private AdapterVerMarcadores adapterVerMarcadores;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_marcadores);

        initComponents();
        init();
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        rvVerMarcadores = findViewById(R.id.rvVerMarcadores);
    }

    private void init() {
        viewModel.todasCategoriasLiveData().observe(this, new Observer<List<Categoria>>() {
            @Override
            public void onChanged(List<Categoria> categorias) {
                rvVerMarcadores.setHasFixedSize(true);

                lManager = new LinearLayoutManager(VerMarcadores.this);
                rvVerMarcadores.setLayoutManager(lManager);

                adapterVerMarcadores = new AdapterVerMarcadores(categorias, VerMarcadores.this, pref.getLong("id", 404), viewModel);
                rvVerMarcadores.setAdapter(adapterVerMarcadores);
            }
        });
        viewModel.todasCategorias(pref.getLong("id", 404));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        viewModel.todasCategoriasLiveData().observe(VerMarcadores.this, new Observer<List<Categoria>>() {
            @Override
            public void onChanged(List<Categoria> categorias) {
                System.out.println(categorias.toString());
                adapterVerMarcadores.notifyDataSetChanged();
            }
        });
        viewModel.todasCategorias(pref.getLong("id", 404));
    }
}
