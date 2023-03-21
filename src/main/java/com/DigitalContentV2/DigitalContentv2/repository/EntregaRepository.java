package com.DigitalContentV2.DigitalContentv2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DigitalContentV2.DigitalContentv2.modelo.Entrega;
import com.DigitalContentV2.DigitalContentv2.modelo.Usuario;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer>{

	@Query(value="SELECT * FROM entrega WHERE estado = 'Pendiente'",nativeQuery = true)
	List<Entrega> estadoPendiente();
	
	@Query(value="SELECT * FROM entrega WHERE estado = 'EnCamino'",nativeQuery = true)
	List<Entrega> estadoEnCamino();

	@Query(value="SELECT * FROM entrega WHERE estado = 'Entregado'",nativeQuery = true)
	List<Entrega> estadoEntregado();
	
	@Query(value = "select * from entrega e where e.id_domiciliario = ? and e.estado = 'EnCamino'", nativeQuery = true)
	public List<Entrega> findByDom(Usuario usuario);

}
