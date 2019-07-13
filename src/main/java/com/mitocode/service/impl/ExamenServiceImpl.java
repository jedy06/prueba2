package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Examen;
import com.mitocode.repo.IExamenRepo;
import com.mitocode.service.IExamenService;

@Service
public class ExamenServiceImpl implements IExamenService{
	
	@Autowired
	private IExamenRepo repo;

	@Override
	public Examen registrar(Examen obj) {
		return repo.save(obj);
		
	}

	@Override
	public Examen modificar(Examen obj) {
		// TODO Auto-generated method stub
		return repo.save(obj); 
		
	}

	@Override
	public List<Examen> listar() {		
		return repo.findAll();  //Permite listar todos los registros
	}

	@Override
	public Examen leerPorId(Integer id) {
		return repo.findOne(id);  //Permite listar registros de a uno haciendo un select por Id
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
		
	}

}
