package com.example.marcadorescategorias.model.classes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;

    public MainViewModel(@NonNull Application application){
        super(application);
        repository = new Repository();
    }
    /*USUARIO*/

    /*Función post Usuario*/
    public void addUsuario(Usuario usuario){
        repository.postUsuario(usuario);
    }

    /*Funciones que comprueban si existe el usuario introducido*/
    public void fetchExisteUsuario(String datos){
        repository.fetchExisteUsuario(datos);
    }

    public MutableLiveData<Usuario> getExisteUsuarioLiveData(){
        return repository.getExisteUsuarioLiveData();
    }

    /*Funciones Iniciar sesión*/

    public void getLogin(String datos){
        repository.getLogin(datos);
    }

    public MutableLiveData<Usuario> getLoginLiveData(){
        return repository.getLoginLiveData();
    }

    /*Función Crear categoría*/

    public void addCategoria(Categoria categoria){
        repository.addCategoria(categoria);
    }

    /*Función que comprueba si existe*/

    public void existeCategoria(String datos){
        repository.existeCategoria(datos);
    }

    public MutableLiveData<Categoria> existeCategoriaLiveData(){
        return repository.existeCategoriaLiveData();
    }

    /*Función coger todas las categorías*/
    public void todasCategorias(Long datos){
        repository.todasCategorias(datos);
    }

    public MutableLiveData<List<Categoria>> todasCategoriasLiveData(){
        return repository.todasCategoriasLiveData();
    }

    /*Funciones coger la categoría del spinner del usuario*/
    public void categoriaUsuarioNombre(String datos){
        repository.categoriaUsuarioNombre(datos);
    }

    public MutableLiveData<Categoria> categoriaUsuarioNombreLiveData(){
        return repository.categoriaUsuarioNombreLiveData();
    }

    /*Función postMarcadores*/
    public void postMarcadores(Marcadores marcador){
        repository.addMarcador(marcador);
    }

    /*Función devuelve todos los marcadores por usuario e id*/

    public void marcadoresCategoria(String datos){
        repository.marcadoresCategoria(datos);
    }

    public MutableLiveData<List<Marcadores>> marcadoresCategoriaLiveData(){
        return repository.marcadoresCategoriaLiveData();
    }

    /*Función editarCategoría*/

    public void editarCategoria(Categoria categoria){
        repository.editarCategoria(categoria);
    }

    /*Funciones coger marcador por el id*/

    public void marcadorPorId(long id){
        repository.marcadorPorId(id);
    }

    public MutableLiveData<Marcadores> marcadorPorIdLiveData(){
        return repository.marcadorPorIdLiveData();
    }

    /*Funciones coger categoria por el id*/

    public void categoriaPorId(long id){
        repository.categoriaPorId(id);
    }

    public MutableLiveData<Categoria> categoriaPorIdLiveData(){
        return repository.categoriaPorIdLiveData();
    }

    /*Función editarMarcador*/

    public void editarMarcador(Marcadores marcadores){
        repository.editarMarcador(marcadores);
    }

    /*Función borrar categoría*/

    public void borrarCategoria(long id){
        repository.borrarCategoria(id);
    }

    /*Función borrar marcador*/

    public void borrarMarcador(long id){
        repository.borrarMarcador(id);
    }
}
