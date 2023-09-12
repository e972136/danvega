package com.gaspar.jdbc.clientdemo.service;

import com.gaspar.jdbc.clientdemo.entity.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceJdbcClient implements ProductoService{
    private final Logger logger = LoggerFactory.getLogger(getClass());
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
    public Optional<Producto> create(Producto producto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            jdbcClient
                    .sql("INSERT INTO producto(descripcion, precio) VALUES(:des,:pre) returning id_producto")
                    .param("des",producto.descripcion())
                    .param("pre",producto.precio())
                    .update(keyHolder);
        }catch (Exception e){
            logger.info(e.toString());
        }

        Integer keyAs = keyHolder.getKeyAs(Integer.class);
        return findById(keyAs);
    }

    @Override
    public void update(Producto producto, Integer id) {
        int update = jdbcClient
                .sql("UPDATE producto SET descripcion=?, precio=? WHERE id_producto=?")
                .params(List.of(producto.descripcion(), producto.precio(), id))
                .update();
        Assert.state(update==1,"Fail to update "+id);
    }

    @Override
    public void delete(Integer id) {
        int update = jdbcClient
                .sql("DELETE FROM producto WHERE id_producto=:id")
                .param("id",id)
                .update();
        Assert.state(update==1,"Fail to delete "+id);
    }
}
