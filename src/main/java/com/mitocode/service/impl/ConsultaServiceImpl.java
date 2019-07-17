package com.mitocode.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.model.Consulta;
import com.mitocode.repo.IConsultaExamenRepo;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.service.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService{
	
	@Autowired
	private IConsultaRepo repo;

	@Autowired
	private IConsultaExamenRepo ceRepo;
	
	@Transactional
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO) {
	
		consultaDTO.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(consultaDTO.getConsulta()));
		repo.save(consultaDTO.getConsulta());
		
		consultaDTO.getListExamen().forEach(ex -> ceRepo.registrar(consultaDTO.getConsulta().getIdConsulta(), ex.getIdExamen()));
		
		return consultaDTO.getConsulta();
	}
	
	@Override
	public Consulta registrar(Consulta obj) {
		obj.getDetalleConsulta().forEach(det -> {
			det.setConsulta(obj);
		});
		
//		for(DetalleConsulta det : obj.getDetalleConsulta()) {
//		det.setConsulta(obj);
//		}
		return repo.save(obj);
		
	}

	@Override
	public Consulta modificar(Consulta obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
		
	}

	@Override
	public List<Consulta> listar() {		
		return repo.findAll();  //Permite listar todos los registros
	}

	@Override
	public Consulta leerPorId(Integer id) {
		return repo.findOne(id);  //Permite listar registros de a uno haciendo un select por Id
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
		
	}



}
