package org.myself.udemy.springboot.app.productos.service.impl;

import java.util.List;

import org.myself.udemy.springboot.app.productos.entity.Producto;
import org.myself.udemy.springboot.app.productos.repository.ProductoRepository;
import org.myself.udemy.springboot.app.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>)this.productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return this.productoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return this.productoRepository.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.productoRepository.deleteById(id);
	}

}
