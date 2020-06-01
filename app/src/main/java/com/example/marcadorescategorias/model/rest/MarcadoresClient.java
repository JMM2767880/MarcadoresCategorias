package com.example.marcadorescategorias.model.rest;

import com.example.marcadorescategorias.model.classes.Categoria;
import com.example.marcadorescategorias.model.classes.Marcadores;
import com.example.marcadorescategorias.model.classes.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MarcadoresClient {

    /*-------------------USUARIO-----------------------*/
    @DELETE("usuario/{id}")
    Call<Integer> deleteUsuario(@Path("id") long id);

    @GET("usuario/{id}")
    Call<Usuario> getUsuario(@Path("id") long id);

    @GET("existe/{datos}")
    Call<Usuario> getExisteUsuario(@Path("datos") String datos);

    @GET("login/{datos}")
    Call<Usuario> getLogin(@Path("datos") String datos);

    @GET("usuario")
    Call<ArrayList<Usuario>> getAllUsuario();

    @POST("usuario")
    Call<Long> postUsuario(@Body Usuario equipo);

    @PUT("usuario/{id}")
    Call<Integer> putUsuario(@Path("id") long id, @Body Usuario usuario);

    /*-------------------CATEGORIA----------------------*/
    @DELETE("categoria/{id}")
    Call<Integer> deleteCategoria(@Path("id") long id);

    @GET("categoria/{id}")
    Call<Categoria> getCategoria(@Path("id") long id);

    @GET("categoria")
    Call<List<Categoria>> getAllCategoria();

    @GET("existecategoria/{datos}")
    Call<Categoria> getExisteCategoria(@Path("datos") String datos);

    @GET("categoriasusuario/{datos}")
    Call<List<Categoria>> getCategoriasUsuario(@Path("datos") long datos);

    @GET("categoriausuarionombre/{datos}")
    Call<Categoria> getCategoriaUsuarioNombre(@Path("datos") String datos);

    @POST("categoria")
    Call<Long> postCategoria(@Body Categoria categoria);

    @PUT("categoria/{id}")
    Call<Integer> putCategoria(@Path("id") long id, @Body Categoria categoria);

    /*-------------------MARCADORES----------------------*/
    @DELETE("marcadores/{id}")
    Call<Integer> deleteMarcadores(@Path("id") long id);

    @GET("marcadores/{id}")
    Call<Marcadores> getMarcador(@Path("id") long id);

    @GET("marcadores")
    Call<ArrayList<Marcadores>> getAllMarcadores();

    @GET("marcadorescategoria/{datos}")
    Call<List<Marcadores>> getMarcadoresCategoria(@Path("datos") String datos);

    @POST("marcadores")
    Call<Long> postMarcadores(@Body Marcadores marcadores);

    @PUT("marcadores/{id}")
    Call<Integer> putMarcadores(@Path("id") long id, @Body Marcadores marcadores);
}
