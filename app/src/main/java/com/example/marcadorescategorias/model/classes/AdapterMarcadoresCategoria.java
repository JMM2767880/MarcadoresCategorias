package com.example.marcadorescategorias.model.classes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marcadorescategorias.EditarCategoria;
import com.example.marcadorescategorias.EditarMarcador;
import com.example.marcadorescategorias.R;

import java.util.List;

public class AdapterMarcadoresCategoria extends RecyclerView.Adapter<AdapterMarcadoresCategoria.MarcadoresCategoriaViewHolder> {
    private List<Marcadores> items;
    private Context context;
    private MainViewModel viewModel;
    private long idUsuario;
    private long nombreCategoria;

    public static class MarcadoresCategoriaViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public LinearLayout llMarcadoresCategoria;

        public MarcadoresCategoriaViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.etMarcadoresCategoria_card);
            llMarcadoresCategoria = v.findViewById(R.id.llMarcadoresCategoriaCard);
        }
    }

    public AdapterMarcadoresCategoria(List<Marcadores> items, Context context, MainViewModel viewModel, long idUsuario, long nombreCategoria) {
        this.items = items;
        this.context = context;
        this.viewModel = viewModel;
        this.idUsuario = idUsuario;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MarcadoresCategoriaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.marcadorescategoria_card, viewGroup, false);
        return new MarcadoresCategoriaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MarcadoresCategoriaViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getId() + ". " + items.get(i).getUrl());
        viewHolder.llMarcadoresCategoria.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Editar");
                builder.setMessage("¿Quieres editar o borrar este marcador?");

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentEditarCategoria = new Intent(context, EditarMarcador.class);
                        intentEditarCategoria.putExtra("nombreMarcadorEditar", viewHolder.nombre.getText().toString());
                        context.startActivity(intentEditarCategoria);
                    }
                });
                builder.setNegativeButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] idMarcador = viewHolder.nombre.getText().toString().split("\\.");
                        viewModel.borrarMarcador(Long.parseLong(idMarcador[0]));

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
    }
}
