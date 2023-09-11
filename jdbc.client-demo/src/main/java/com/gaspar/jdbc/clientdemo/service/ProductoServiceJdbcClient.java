package com.gaspar.jdbc.clientdemo.service;

import com.gaspar.jdbc.clientdemo.entity.Producto;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceJdbcClient implements ProductoService{

    private final JdbcClient jdbcClient;

    public ProductoServiceJdbcClient(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Producto> findAll() {
        return jdbcClient.sql("SELECT id_producto, descripcion, precio from producto")
                .query(Producto.class)
                .list();
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return jdbcClient
                .sql("SELECT id_producto, descripcion, precio from producto where id_producto = :id")
                .param("id",id)
                .query(Producto.class)
                .optional();
    }

    @Override
    public void create(Producto producto) {

    }

    @Override
    public void update(Producto producto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }
}
