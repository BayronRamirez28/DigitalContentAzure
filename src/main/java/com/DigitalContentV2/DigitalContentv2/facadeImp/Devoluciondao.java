package com.DigitalContentV2.DigitalContentv2.facadeImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DigitalContentV2.DigitalContentv2.facade.IDevolucion;
import com.DigitalContentV2.DigitalContentv2.modelo.Devolucion;
import com.DigitalContentV2.DigitalContentv2.repository.DevolucionRepository;

@Service
public class Devoluciondao implements IDevolucion {
	
	@Autowired
	private DevolucionRepository devolucionRepository; 

	@Override
	public List<Devolucion> encontrarTodo() {
		return this.devolucionRepository.findAll();
	}

	@Override
	public List<Devolucion> encontrarporEstado() {
		return this.devolucionRepository.buscarEstado();
	}

	@Override
	public Devolucion encontrarId(Integer idDevolucion) {
		return this.devolucionRepository.getReferenceById(idDevolucion);
	}

	@Override
	public void crear(Devolucion devolucion) {
		this.devolucionRepository.save(devolucion);
		
	}

	@Override
	public void actualizar(Devolucion devolucion) {
		this.devolucionRepository.save(devolucion);
		
	}

	@Override
	public void eliminar(Integer idDevolucion) {
		devolucionRepository.deleteById(idDevolucion);
		
	}

	@Override
	public void actualizarEstado(Devolucion devolucion) {
		this.devolucionRepository.save(devolucion);
		
	}


	

}
