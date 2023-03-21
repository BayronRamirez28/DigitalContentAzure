package com.DigitalContentV2.DigitalContentv2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DigitalContentV2.DigitalContentv2.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

	@Query(
			value = "SELECT * FROM producto WHERE producto.estado = 'Activo'",
			nativeQuery = true
	)
	List<Producto> buscarEstado();
	
	@Query(
			value = "SELECT * FROM producto p WHERE p.id_genero_fk = 1",
			nativeQuery = true
	)
	List<Producto> buscarGM();
	
	@Query(
			value = "SELECT * FROM producto p WHERE p.id_genero_fk = 2",
			nativeQuery = true
	)
	List<Producto> buscarGH();
	
	@Query(
			value = "SELECT * FROM producto p WHERE p.id_genero_fk = 3",
			nativeQuery = true
	)
	List<Producto> buscarGNH();
	
	@Query(
			value = "SELECT * FROM producto p WHERE p.id_genero_fk = 4",
			nativeQuery = true
	)
	List<Producto> buscarGNM();
}
