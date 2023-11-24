package com.Trabalho.TrabalhoJava.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Trabalho.TrabalhoJava.entity.Consulta;
import com.Trabalho.TrabalhoJava.repositories.IConsultas;

import jakarta.transaction.Transactional;

@RestController	
@CrossOrigin("*")
@RequestMapping(value = "/Consultas")
public class ConsultaController{
	
	@Autowired
	private IConsultas entityConsul;
	
	
	@GetMapping
	@Transactional
	public List<Consulta> getAllConsultas() {
		List<Consulta> result = entityConsul.findAll();
        return result;	
	}

	@GetMapping(value = "/{id}")
	public Consulta Encontrar_Consulta(@PathVariable Long id){
		return entityConsul.findById(id).get();
	}

	@PostMapping
	public Consulta Marcar_Consulta(@RequestBody Consulta consulta){
		return entityConsul.save(consulta);
	}

	@PutMapping("/{id}")
	public Consulta Editar_Consulta(@PathVariable Long id, @RequestBody Consulta novaConsulta){
		Optional <Consulta> consultaOptional = entityConsul.findById(id);

		if (consultaOptional.isPresent()){
			Consulta consulta = consultaOptional.get();
			consulta.setData(novaConsulta.getData());
			return entityConsul.save(consulta);
		}
		else{
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> Deletar_Consulta(@PathVariable Long id){
		Optional <Consulta> consultaOptional = entityConsul.findById(id);
		if (consultaOptional.isPresent()){
			entityConsul.deleteById(id);
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.notFound().build();
		}
	}


}
