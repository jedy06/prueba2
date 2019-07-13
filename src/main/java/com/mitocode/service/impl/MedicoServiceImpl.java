package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Medico;
import com.mitocode.repo.IMedicoRepo;
import com.mitocode.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService{
	
	@Autowired
	private IMedicoRepo repo;

	@Override
	public Medico registrar(Medico obj) {
		return repo.save(obj);
		
	}

	@Override
	public Medico modificar(Medico obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
		
	}

	@Override
	public List<Medico> listar() {		
		return repo.findAll();  //Permite listar todos los registros
	}

	@Override
	public Medico leerPorId(Integer id) {
		return repo.findOne(id);  //Permite listar registros de a uno haciendo un select por Id
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
		
	}

}
