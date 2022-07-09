package com.finsol.books.repository;


import com.finsol.books.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
}
