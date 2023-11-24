package com.Trabalho.TrabalhoJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Trabalho.TrabalhoJava.entity.Paciente;

public interface IPacientes extends JpaRepository<Paciente, Long>{
    
}
