package com.mitocode.service;

import java.util.List;

public interface ICRUD<T> {

	T registrar(T t);
	T modificar(T t);
	T leerPorId(Integer Id);
	List<T> listar();
	void eliminar(Integer Id);
	
}
