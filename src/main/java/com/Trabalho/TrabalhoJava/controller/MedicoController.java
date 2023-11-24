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
import com.Trabalho.TrabalhoJava.entity.Medico;
import com.Trabalho.TrabalhoJava.repositories.IMedicos;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Medicos")
public class MedicoController{
    
    @Autowired
    private IMedicos entityMed;

    @GetMapping
    @Transactional
    public List<Medico> getAllMedicos(){
        List<Medico> result = entityMed.findAll();
        return result;
    }
    
    @GetMapping(value = "/{id}")
    public Medico Encontrar_Medico(@PathVariable Long id){
        return entityMed.findById(id).get();
    }

    @PostMapping
    public Medico Cadastrar_Medico(@RequestBody Medico medico){
	return entityMed.save(medico);
	}

    @PutMapping("/{id}")
    public Medico Editar_Medico(@PathVariable Long id,@RequestBody Medico novoMedico){
        Optional <Medico> medicoOptional = entityMed.findById(id);
        
        if (medicoOptional.isPresent()){
            Medico medico = medicoOptional.get();
            medico.setEspecializacao(novoMedico.getEspecializacao());
            medico.setNome(novoMedico.getNome());
            return entityMed.save(medico);
        }else{
            return null;
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Deletar_Medico(@PathVariable Long id){
        Optional <Medico> medicOptional = entityMed.findById(id);
        if(medicOptional.isPresent()){
            entityMed.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
