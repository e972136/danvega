package com.finsol.books.controller;


import com.finsol.books.entity.Empleado;
import com.finsol.books.repository.EmpleadoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoRepository repository;

//    @SchemaMapping(typeName = "Query",value = "allEmpleados")
    @QueryMapping
    public List<Empleado> allEmpleados(){
        return repository.findAll();
    }
//    allEmpleados {
//        id
//    }

    @QueryMapping
    public Empleado findOne(@Argument Integer id){
        return repository.findById(id).orElse(null);
    }
    //    findOne(id:2){
//        nombre
//    }

    @QueryMapping
    public Page<Empleado> allEmpleadosPage(@Argument int page,
                                           @Argument int size){
        PageRequest pr = PageRequest.of(page,size);
        return repository.findAll(pr);

    }
//    allEmpleadosPage(page:1, size:2){
//        nombre
//    }

}
//http://localhost:8081/graphiql?path=/graphql