package com.gaspar.jdbc.clientdemo.controller;

import com.gaspar.jdbc.clientdemo.entity.Producto;
import com.gaspar.jdbc.clientdemo.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/all")
    List<Producto> findAll(){
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    Optional<Producto> findById(
            @PathVariable Integer id
    ){
        return productoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Optional<Producto>  create(@RequestBody Producto producto){
        return productoService.create(producto);
    }

    @PutMapping("/{id}")
    void update(
            @RequestBody Producto producto,
            @PathVariable Integer id
    ){
        productoService.update(producto,id);
    }

    @DeleteMapping("/{id}")
    void delete(
            @PathVariable Integer id
    ){
        productoService.delete(id);
    }

}
