package com.DigitalContentV2.DigitalContentv2.facade;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.DigitalContentV2.DigitalContentv2.dto.UsuarioRegistroS;
import com.DigitalContentV2.DigitalContentv2.modelo.Usuario;
import com.DigitalContentV2.DigitalContentv2.modelo.UsuarioNotFoundException;

public interface IUsuario extends UserDetailsService {

	public List<Usuario> encontrarTodo();
	public Usuario encontrarId(Integer idUsuario);
	public void crear(Usuario usuario);
	public void actualizar(Usuario usuario);
	public void eliminar(Usuario usuario);
	
	public Usuario save(UsuarioRegistroS uregistroS);	
	public String[] listarCorreos();
	
	public void updateResetPasswordToken(String token, String email)  throws UsuarioNotFoundException;
	public Usuario getByResetPasswordToken(String token);
	public void updatePassword(Usuario customer, String newPassword);
}
