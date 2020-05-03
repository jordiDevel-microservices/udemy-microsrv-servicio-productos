package org.myself.udemy.springboot.app.productos.repository;

import org.myself.udemy.springboot.app.productos.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
