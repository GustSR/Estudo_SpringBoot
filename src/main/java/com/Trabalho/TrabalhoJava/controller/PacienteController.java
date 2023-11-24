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


import com.Trabalho.TrabalhoJava.entity.Paciente;
import com.Trabalho.TrabalhoJava.repositories.IPacientes;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Pacientes")
public class PacienteController {
    
    @Autowired
    private IPacientes entityPaci;
    
    @GetMapping
    @Transactional
    public List<Paciente> getAllPacientes(){
        List<Paciente> result = entityPaci.findAll();
        return result;
    }
    
    @GetMapping(value = "/{id}")
    public Paciente Encontrar_Paciente(@PathVariable Long id){
        return entityPaci.findById(id).get();
    }

    @PostMapping
	public Paciente Cadastrar_Paciente(@RequestBody Paciente paciente){
		return entityPaci.save(paciente);
	}

    @PutMapping("/{id}")
    public Paciente Editar_Paciente(@PathVariable Long id,@RequestBody Paciente novoPaciente){
        Optional <Paciente> PacienteOptional = entityPaci.findById(id);
        
        if (PacienteOptional.isPresent()){
            Paciente Paciente = PacienteOptional.get();
            Paciente.setEmail(novoPaciente.getEmail());
            Paciente.setNome(novoPaciente.getNome());
            Paciente.setTelefone(novoPaciente.getTelefone());
            Paciente.setIdade(novoPaciente.getIdade());
            return entityPaci.save(Paciente);
        }else{
            return null;
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Deletar_Paciente(@PathVariable Long id){
        Optional <Paciente> Pacienteoptional = entityPaci.findById(id);
        if(Pacienteoptional.isPresent()){
            entityPaci.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
    

