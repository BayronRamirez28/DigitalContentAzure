package com.DigitalContentV2.DigitalContentv2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DigitalContentV2.DigitalContentv2.modelo.Car_items;
import com.DigitalContentV2.DigitalContentv2.modelo.Producto;
import com.DigitalContentV2.DigitalContentv2.modelo.Usuario;

@Repository
public interface CarRepository extends JpaRepository<Car_items, Integer> {

	@Query(value = "select * from cart_items c where c.cliente_id = ?  and c.orden_compra is null", nativeQuery = true)
	public List<Car_items> findByCliente(Usuario usuario);

	@Query(value = "select * from cart_items where cliente_id = ? and producto_id = ? and orden_compra is null", nativeQuery = true)
	Car_items findByUsuarioAndProducto(Usuario cliente, Producto producto);

	@Modifying
	@Query(value = "UPDATE cart_items c SET c.cantidad = ?1 WHERE c.producto_id = ?2 AND  c.cliente_id = ?3", nativeQuery = true)
	public void updateCantidad(Integer cantidad, Integer idProducto, Integer idUsuario);
	
	@Modifying
	@Query(value = "DELETE FROM cart_items WHERE cliente_id = ?1 AND producto_id = ?2", nativeQuery = true)
	public void deleteByClienteAndProducto(Integer idUsuario, Integer idProducto);	

	@Query(value = "call asignarOrdenCompra(?);", nativeQuery = true)
	Car_items actOrdenCompra(Usuario idUsuario);
	
	@Modifying
	@Query(value = "call asignarOrdenCompra2(?1, ?2)", nativeQuery = true)
	public void actOrdenCompra2(Integer idUsuario, String estado);
	
}
