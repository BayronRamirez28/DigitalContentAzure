package com.DigitalContentV2.DigitalContentv2.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.DigitalContentV2.DigitalContentv2.enums.estadosEntrega;

@Entity
@Table(name = "entrega")
public class Entrega implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEntrega;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	@OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL)
	private List<Car_items> ordenCompra;
	
	@ManyToMany(mappedBy = "entrega")
	private List<Venta> venta = new ArrayList<Venta>();
	
	@Column(name = "estado", length = 30)
	@Enumerated(EnumType.STRING)
	private estadosEntrega estado;
	
	@ManyToOne
	@JoinColumn(name = "idDomiciliario")
	private Usuario idCliente;

	public Integer getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(Integer idEntrega) {
		this.idEntrega = idEntrega;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Usuario idCliente) {
		this.idCliente = idCliente;
	}

	public List<Car_items> getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(List<Car_items> ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public List<Venta> getVenta() {
		return venta;
	}

	public void setVenta(List<Venta> venta) {
		this.venta = venta;
	}

	public estadosEntrega getEstado() {
		return estado;
	}

	public void setEstado(estadosEntrega estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Entrega() {
		super();
	}

	public Entrega(Integer idEntrega, Date fecha, estadosEntrega estado, Usuario idCliente) {
		super();
		this.idEntrega = idEntrega;
		this.fecha = fecha;
		this.estado = estado;
		this.idCliente = idCliente;
	}
	
}
