package com.example.marcadorescategorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcadorescategorias.model.classes.Categoria;
import com.example.marcadorescategorias.model.classes.MainViewModel;

public class EditarCategoria extends AppCompatActivity {

    private MainViewModel viewModel;

    private String nombreCategoriaEditar;
    private Long idUsuario;

    private EditText etNombreEditarCategoria;
    private Button btEditarCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_categoria);

        initComponents();
        init();
    }

    private void initComponents() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        nombreCategoriaEditar = getIntent().getStringExtra("nombreCategoriaEditar");
        idUsuario = getIntent().getLongExtra("idUsuario", 404);

        etNombreEditarCategoria = findViewById(R.id.etNombreEditarCategoria);
        btEditarCategoria = findViewById(R.id.btEditarCategoria);
    }

    private void init() {

        etNombreEditarCategoria.setText(nombreCategoriaEditar);

        btEditarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombreEditarCategoria.getText().toString().equals("")){
                    Toast.makeText(EditarCategoria.this, getResources().getString(R.string.camposVacios), Toast.LENGTH_SHORT).show();
                }else{
                    viewModel.categoriaUsuarioNombreLiveData().observe(EditarCategoria.this, new Observer<Categoria>() {
                        @Override
                        public void onChanged(Categoria categoria) {
                            Categoria editCategoria = new Categoria(categoria.getId(), idUsuario, etNombreEditarCategoria.getText().toString());
                            viewModel.editarCategoria(editCategoria);
                            System.out.println(editCategoria.toString());
                            Toast.makeText(EditarCategoria.this, getResources().getString(R.string.categoriaeditada), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    viewModel.categoriaUsuarioNombre(idUsuario + ":" + nombreCategoriaEditar);
                }
            }
        });
        viewModel.categoriaUsuarioNombre(idUsuario + ":" + nombreCategoriaEditar);
    }
}
