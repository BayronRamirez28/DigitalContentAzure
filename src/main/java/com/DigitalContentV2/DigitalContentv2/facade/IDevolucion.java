package com.DigitalContentV2.DigitalContentv2.facade;

import java.util.List;

import com.DigitalContentV2.DigitalContentv2.modelo.Devolucion;

public interface IDevolucion {

	public List<Devolucion> encontrarTodo();
	public List<Devolucion> encontrarporEstado();
	public Devolucion encontrarId(Integer idDevolucion);
	public void crear(Devolucion devolucion);
	public void actualizar(Devolucion devolucion);
	public void eliminar(Integer idDevolucion);
	public void actualizarEstado(Devolucion devolucion);
	
}
