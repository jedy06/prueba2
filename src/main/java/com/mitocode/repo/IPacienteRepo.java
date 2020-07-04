package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Paciente;

public interface IPacienteRepo extends JpaRepository<Paciente, Integer> {

//	@Modifying
	@Query(value = "SELECT id_paciente, dni ,nombres, apellidos, direccion, telefono,email FROM PACIENTE", nativeQuery = true)
	List<Paciente> findDescription();
}
