package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;


@RestController
@RequestMapping("/consultas") // parte de la buena practica que solo se pueda acceder como /pacientes
public class ConsultaController {
	
	@Autowired
	private IConsultaService service;

	@GetMapping
	public ResponseEntity<List<Consulta>> listar(){
		List<Consulta> lista = service.listar();
		return new ResponseEntity<List<Consulta>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Consulta> leerPorId(@PathVariable ("id") Integer id) {
		Consulta obj = service.leerPorId(id); 
		if(obj == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);		
	}
	
	@GetMapping("/hateoas/{id}")
	public Resource<Consulta> leerPorIdHateoas(@PathVariable ("id") Integer id) {
		Consulta obj = service.leerPorId(id); 
		if(obj == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO " + id);
		}

		Resource<Consulta> 	resource = new Resource<Consulta>(obj);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).leerPorId(id));
		resource.add(linkTo.withRel("consulta-Resource"));
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> registar(@Valid @RequestBody ConsultaListaExamenDTO obj) { //@RequestBody lee el json y lo representa en un objeto java
		Consulta paciente = service.registrarTransaccional(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paciente.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Consulta pac) {
		service.modificar(pac);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Consulta obj = service.leerPorId(id);
		if(obj == null) {
			throw new ModelNotFoundException("ID NO ENCONTRADO " + id);
		}else {
			service.eliminar(id);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
