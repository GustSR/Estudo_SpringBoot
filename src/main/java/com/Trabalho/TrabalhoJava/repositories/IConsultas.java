package com.Trabalho.TrabalhoJava.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.Trabalho.TrabalhoJava.entity.Consulta;


public interface IConsultas extends JpaRepository<Consulta, Long>{
	
}
