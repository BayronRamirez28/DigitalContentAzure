package com.DigitalContentV2.DigitalContentv2.facadeImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DigitalContentV2.DigitalContentv2.facade.IEntrega;
import com.DigitalContentV2.DigitalContentv2.modelo.Entrega;
import com.DigitalContentV2.DigitalContentv2.modelo.Usuario;
import com.DigitalContentV2.DigitalContentv2.repository.CarRepository;
import com.DigitalContentV2.DigitalContentv2.repository.EntregaRepository;

@Transactional
@Service
public class Entregadao implements IEntrega{

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	public List<Entrega> listEntregasDom(Usuario cliente) {
		return entregaRepository.findByDom(cliente);
	}
	
	@Override
	public List<Entrega> encontrarTodo() {
		return this.entregaRepository.findAll();
	}
	
	@Override
	public List<Entrega> estadoEntregado() {
		return this.entregaRepository.estadoEntregado();
	}
	
	@Override
	public List<Entrega> estadoEnCamino() {
		return this.entregaRepository.estadoEnCamino();
	}
	
	@Override
	public List<Entrega> estadoPendiente() {
		return this.entregaRepository.estadoPendiente();
	}
	
	@Override
	public Entrega encontrarId(Integer idEntrega) {
		return this.entregaRepository.getReferenceById(idEntrega);
	}

	@Override
	public void crear(Entrega entrega, Integer idUsuario) {
		carRepository.actOrdenCompra2(idUsuario, "Pendiente");
	}
 
	@Override
	public void actualizar(Entrega entrega, Usuario usuario) {
		Entrega entrega2 = new Entrega(entrega.getIdEntrega(), 
				entrega.getFecha(), entrega.getEstado(), usuario);
		this.entregaRepository.save(entrega2);
	}

	@Override
	public void eliminar(Entrega entrega) {
		// TODO Auto-generated method stub
		
	}

}
