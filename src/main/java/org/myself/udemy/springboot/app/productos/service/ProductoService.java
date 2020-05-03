package org.myself.udemy.springboot.app.productos.service;

import java.util.List;

import org.myself.udemy.springboot.app.productos.entity.Producto;

public interface ProductoService {

	public List<Producto> findAll();
	
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	
	public void deleteById(Long id);
	
}
