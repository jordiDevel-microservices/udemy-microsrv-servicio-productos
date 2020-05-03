package org.myself.udemy.springboot.app.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.myself.udemy.springboot.app.productos.entity.Producto;
import org.myself.udemy.springboot.app.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

	@Autowired
	private Environment env;
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar() {
		return this.productoService.findAll().stream().map(prod -> {
			prod.setPort(Integer.parseInt(this.env.getProperty("local.server.port")));
			return prod;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = this.productoService.findById(id);
		producto.setPort(Integer.parseInt(this.env.getProperty("local.server.port")));
		
		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return this.productoService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Producto> editar(@PathVariable Long id, @RequestBody Producto producto) {
		Producto productoDb = this.productoService.findById(id);
		
		if (productoDb != null) {
			productoDb.setNombre(producto.getNombre());
			productoDb.setPrecio(producto.getPrecio());
			
			return new ResponseEntity<>(this.productoService.save(productoDb), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		Producto productoDb = this.productoService.findById(id);
		
		if (productoDb != null) {
			this.productoService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
