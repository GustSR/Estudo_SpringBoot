package com.Trabalho.TrabalhoJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Trabalho.TrabalhoJava.entity.Medico;

public interface IMedicos extends JpaRepository<Medico, Long> {
  
} 
