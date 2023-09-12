package com.gaspar.jdbc.clientdemo.service;

import com.gaspar.jdbc.clientdemo.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Integer id);

    Optional<Producto> create(Producto producto);

    void update(Producto producto, Integer id);

    void delete(Integer id);
}
