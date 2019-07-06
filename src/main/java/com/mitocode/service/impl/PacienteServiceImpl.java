package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Paciente;
import com.mitocode.repo.IPacienteRepo;
import com.mitocode.service.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService{
	
	@Autowired
	private IPacienteRepo repo;

	@Override
	public void registrar(Paciente pac) {
		repo.save(pac);
		
	}

	@Override
	public void modificar(Paciente pac) {
		// TODO Auto-generated method stub
		repo.save(pac);
		
	}

	@Override
	public List<Paciente> listar() {		
		return repo.findAll();  //Permite listar todos los registros
	}

	@Override
	public Paciente leerPorId(Integer id) {
		return repo.findOne(id);  //Permite listar registros de a uno haciendo un select por Id
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
		
	}

}
