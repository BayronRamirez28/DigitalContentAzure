package com.DigitalContentV2.DigitalContentv2.facade;

import java.util.List;

import com.DigitalContentV2.DigitalContentv2.modelo.Entrega;
import com.DigitalContentV2.DigitalContentv2.modelo.Usuario;

public interface IEntrega {

	public List<Entrega> encontrarTodo();
	public void crear(Entrega entrega, Integer idUsuario);
	public void actualizar(Entrega entrega, Usuario usuario);
	public void eliminar(Entrega entrega);
	public Entrega encontrarId(Integer idEntrega);
	public List<Entrega> estadoEntregado();
	public List<Entrega> estadoEnCamino();
	public List<Entrega> estadoPendiente();
	
}
