package com.example.marcadorescategorias.model.classes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.MotionEventCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marcadorescategorias.EditarCategoria;
import com.example.marcadorescategorias.MarcadoresCategoria;
import com.example.marcadorescategorias.R;

import java.util.List;

public class AdapterVerMarcadores extends RecyclerView.Adapter<AdapterVerMarcadores.VerMarcadoresViewHolder> {
    private List<Categoria> items;
    private Context context;
    private Long id;
    private MainViewModel viewModel;

    public static class VerMarcadoresViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public LinearLayout linearLayout;

        public VerMarcadoresViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.etNombreCategoriaCard);
            linearLayout = v.findViewById(R.id.llCategoriaCard);
        }
    }

    public AdapterVerMarcadores(List<Categoria> items, Context context, Long id, MainViewModel viewModel) {
        this.items = items;
        this.context = context;
        this.id = id;
        this.viewModel = viewModel;
    }

    public void update(List<Categoria> lista)
    {
        items.clear();
        items.addAll(lista);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public VerMarcadoresViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.categoria_card, viewGroup, false);
        return new VerMarcadoresViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final VerMarcadoresViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MarcadoresCategoria.class);
                intent.putExtra("id", id);
                intent.putExtra("categoria", viewHolder.nombre.getText().toString());
                context.startActivity(intent);
            }
        });

        viewModel.todasCategoriasLiveData().observe((LifecycleOwner) context, new Observer<List<Categoria>>() {
            @Override
            public void onChanged(List<Categoria> categorias) {

            }
        });

        viewModel.categoriaUsuarioNombreLiveData().observe((LifecycleOwner) context, new Observer<Categoria>() {
            @Override
            public void onChanged(Categoria categoria) {
                viewModel.borrarCategoria(categoria.getId());

                //viewModel.todasCategorias(id);
            }
        });

        viewHolder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Editar categoría");
                builder.setMessage("¿Quieres borrar o editar la categoría?");

                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentEditarCategoria = new Intent(context, EditarCategoria.class);
                        intentEditarCategoria.putExtra("nombreCategoriaEditar", viewHolder.nombre.getText().toString());
                        intentEditarCategoria.putExtra("idUsuario", id);
                        context.startActivity(intentEditarCategoria);
                    }
                });
                builder.setNegativeButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.categoriaUsuarioNombre(id + ":" + viewHolder.nombre.getText().toString());

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
    }
}