package com.finsol.books.controller;

import com.finsol.books.entity.Empleado;
import com.finsol.books.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmpleadoRestController {

    private final EmpleadoRepository repository;

    @GetMapping("/ver/{id}")
    public Empleado verDetalleEmpleado(@PathVariable("id") Integer id){
        Empleado empleado = repository.findById(id).orElse(null);
        return empleado;
    }


    @GetMapping({"/listar","/"})
    public List<Empleado> listarEmpleados(){

        List<Empleado> empleados = repository.findAll();
        return empleados;
    }


    @GetMapping("/paginado")
    public Page<Empleado> empleadoPaginado(@RequestParam int page,
                                           @RequestParam int size){
        PageRequest pr = PageRequest.of(page,size);
        return repository.findAll(pr);

    }

}
