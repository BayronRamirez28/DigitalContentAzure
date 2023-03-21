package com.DigitalContentV2.DigitalContentv2.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "devolucion")
public class Devolucion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDevolucion;
	
	@Column(name = "descripcion", nullable = false, length = 200)
	private String descripcion;
	
	@Column(name = "fecha", updatable = false, nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	/*
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "detalle_Devolucion", joinColumns = @JoinColumn(name = "id_Devolucion_fk"), inverseJoinColumns = @JoinColumn(name = "id_Producto_fk"))
	private List<Producto> producto = new ArrayList<Producto>();
	*/
	
	@ManyToOne
	@JoinColumn(name = "id_Producto_fk")
	private Producto id_Producto_fk;

	public int getIdDevolucion() {
		return idDevolucion;
	}
	
	

	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public void setIdDevolucion(int idDevolucion) {
		this.idDevolucion = idDevolucion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

	public Producto getId_Producto_fk() {
		return id_Producto_fk;
	}

	public void setId_Producto_fk(Producto id_Producto_fk) {
		this.id_Producto_fk = id_Producto_fk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	}


