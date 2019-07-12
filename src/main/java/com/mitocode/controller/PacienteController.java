package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;



@RestController
@RequestMapping("/pacientes") // parte de la buena practica que solo se pueda acceder como /pacientes
public class PacienteController {
	
	@Autowired
	private IPacienteService service;

	@GetMapping
	public ResponseEntity<List<Paciente>> listar(){
		List<Paciente> lista = service.listar();
		return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> leerPorId(@PathVariable ("id") Integer id) {
		Paciente obj = service.leerPorId(id); 
		if(obj == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Paciente>(obj, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<Object> registar(@Valid @RequestBody Paciente pac) { //@RequestBody lee el json y lo representa en un objeto java
		Paciente paciente = service.registrar(pac);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paciente.getIdPaciente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Paciente pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Paciente obj = service.leerPorId(id);
		if(obj == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO " + id);
		}else {
			service.eliminar(id);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
