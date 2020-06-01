package com.example.marcadorescategorias.model.classes;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.marcadorescategorias.model.rest.MarcadoresClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private MarcadoresClient marcadoresClient;

    private final String url = "http://informatica.ieszaidinvergeles.org:9060/tarea3/public/api/";

    private MutableLiveData<Usuario> existeUsuarioLiveData = new MutableLiveData<>();
    private MutableLiveData<Usuario> getLoginLiveData = new MutableLiveData<>();
    private MutableLiveData<Categoria> existeCategoriaLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Categoria>> todasCategoriasLiveData = new MutableLiveData<>();
    private MutableLiveData<Categoria> categoriaUsuarioNombreLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Marcadores>> marcadoresCategoriaLiveData = new MutableLiveData<>();
    private MutableLiveData<Marcadores> marcadorPorIdLiveData = new MutableLiveData<>();
    private MutableLiveData<Categoria> categoriaPorIdLiveData = new MutableLiveData<>();


    public Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        marcadoresClient = retrofit.create(MarcadoresClient.class);
    }

    /*Función postUsuario*/

    public void postUsuario(Usuario usuario){

        Call<Long> call = marcadoresClient.postUsuario(usuario);

        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                Log.v("UsuarioAdd", response.body().toString());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.v("UsuarioAddError", t.getLocalizedMessage());
            }
        });
    }

    /*Funciones que comprueban si existe el usuario introducido*/

    public void fetchExisteUsuario(String datos){
        Call<Usuario> call = marcadoresClient.getExisteUsuario(datos);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                existeUsuarioLiveData.postValue(response.body());
                Log.v("FetchExisteUsuario", response.body().toString());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.v("FetchExisteUsuarioError", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Usuario> getExisteUsuarioLiveData(){
        return existeUsuarioLiveData;
    }

    /*Funciones Iniciar sesión*/

    public void getLogin(String datos){
        Call<Usuario> call = marcadoresClient.getLogin(datos);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.v("FetchGetLogin", response.body().toString());
                getLoginLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.v("FetchGetLoginError", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Usuario> getLoginLiveData(){
        return getLoginLiveData;
    }

    /*Función Crear categoría*/

    public void addCategoria(Categoria categoria){
        Call<Long> call = marcadoresClient.postCategoria(categoria);

        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                Log.v("addCategoria", response.body().toString());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.v("addCategoriaError", t.getLocalizedMessage());
            }
        });
    }

    /*Función que comprueba si existe categoría*/

    public void existeCategoria(String datos){
        Call<Categoria> call = marcadoresClient.getExisteCategoria(datos);

        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                Log.v("existeCategoria", response.body().toString());
                existeCategoriaLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Log.v("existeCategoriaError", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Categoria> existeCategoriaLiveData(){
        return existeCategoriaLiveData;
    }

    /*Función Crear Marcador*/

    public void addMarcador(Marcadores marcador){
        Call<Long> call = marcadoresClient.postMarcadores(marcador);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                Log.v("addMarcador", response.body().toString());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.v("addMarcadorError", t.getLocalizedMessage());
            }
        });
    }

    /*Funciones coger todas las categorías del usuario*/
    public void todasCategorias(Long datos){
        Call<List<Categoria>> call = marcadoresClient.getCategoriasUsuario(datos);

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                Log.v("todasCategorias", response.body().toString());
                todasCategoriasLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.v("todasCategoriasError", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<List<Categoria>> todasCategoriasLiveData(){
        return todasCategoriasLiveData;
    }

    /*Funciones coger la categoría del spinner del usuario*/
    public void categoriaUsuarioNombre(String datos){
        Call<Categoria> call = marcadoresClient.getCategoriaUsuarioNombre(datos);

        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                Log.v("categoriaUsuarioNombre", response.body().toString());
                categoriaUsuarioNombreLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Log.v("categoriaUsuaNombError", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Categoria> categoriaUsuarioNombreLiveData(){
        return categoriaUsuarioNombreLiveData;
    }

    /*Función editarCategoría*/

    public void editarCategoria(Categoria categoria){
        Call<Integer> call = marcadoresClient.putCategoria(categoria.getId(), categoria);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("editarCategoria", "añade");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("editarCategoriaError", t.getLocalizedMessage());
            }
        });
    }

    /*Función postMarcador*/

    public void postMarcadores(Marcadores marcador){

        Call<Long> call = marcadoresClient.postMarcadores(marcador);

        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                Log.v("postMarcadores", response.body().toString());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.v("postMarcadoresError", t.getLocalizedMessage());
            }
        });
    }

    /*Función devuelve todos los marcadores por usuario e id*/

    public void marcadoresCategoria(String datos){
        Call<List<Marcadores>> call = marcadoresClient.getMarcadoresCategoria(datos);

        call.enqueue(new Callback<List<Marcadores>>() {
            @Override
            public void onResponse(Call<List<Marcadores>> call, Response<List<Marcadores>> response) {
                Log.v("marcadoresCategoria", response.body().toString());
                marcadoresCategoriaLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Marcadores>> call, Throwable t) {
                Log.v("marcadoresCatError", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<List<Marcadores>> marcadoresCategoriaLiveData(){
        return marcadoresCategoriaLiveData;
    }

    /*Funciones coger marcador por el id*/

    public void marcadorPorId(long id){
        Call<Marcadores> call = marcadoresClient.getMarcador(id);

        call.enqueue(new Callback<Marcadores>() {
            @Override
            public void onResponse(Call<Marcadores> call, Response<Marcadores> response) {
                Log.v("marcadorPorId", response.body().toString());
                marcadorPorIdLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Marcadores> call, Throwable t) {
                Log.v("marcadorPorIdError", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Marcadores> marcadorPorIdLiveData(){
        return marcadorPorIdLiveData;
    }

    /*Funciones coger categoria por el id*/

    public void categoriaPorId(long id){
        Call<Categoria> call = marcadoresClient.getCategoria(id);

        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                Log.v("categoriaPorId", response.body().toString());
                categoriaPorIdLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Log.v("categoriaPorIdError", t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Categoria> categoriaPorIdLiveData(){
        return categoriaPorIdLiveData;
    }

    /*Función editarMarcador*/

    public void editarMarcador(Marcadores marcadores){
        Call<Integer> call = marcadoresClient.putMarcadores(marcadores.getId(), marcadores);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("editarMarcador", "añade");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("editarMarcadorError", t.getLocalizedMessage());
            }
        });
    }

    /*Función borrar categoría*/

    public void borrarCategoria(long id){
        Call<Integer> call = marcadoresClient.deleteCategoria(id);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("borrarCategoria", "Borra");
                todasCategorias(4l);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("borrarCategoriaError", t.getLocalizedMessage());
            }
        });
    }

    /*Función borrar categoría*/

    public void borrarMarcador(long id){
        Call<Integer> call = marcadoresClient.deleteMarcadores(id);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("borrarMarcador", "Borra");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("borrarMarcadorError", t.getLocalizedMessage());
            }
        });
    }
}
