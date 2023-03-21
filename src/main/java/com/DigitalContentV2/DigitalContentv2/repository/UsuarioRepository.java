package com.DigitalContentV2.DigitalContentv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DigitalContentV2.DigitalContentv2.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByCorreo(String correo);
	
	@Query(value= "call listarcorreos()", nativeQuery = true)
	String[] listaCorreos();
	
	@Query("SELECT c FROM Usuario c WHERE c.correo = ?1")
    	public Usuario findByEmail(String email); 
     
    	public Usuario findByResetPasswordToken(String token);
}
