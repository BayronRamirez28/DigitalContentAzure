package com.DigitalContentV2.DigitalContentv2.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DigitalContentV2.DigitalContentv2.dto.UsuarioRegistroS;
import com.DigitalContentV2.DigitalContentv2.facadeImp.Barriodao;
import com.DigitalContentV2.DigitalContentv2.facadeImp.Usuariodao;
import com.DigitalContentV2.DigitalContentv2.modelo.Barrio;
import com.DigitalContentV2.DigitalContentv2.modelo.Usuario;
import com.DigitalContentV2.DigitalContentv2.modelo.UsuarioNotFoundException;
import com.DigitalContentV2.DigitalContentv2.utility.Utility;

import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/registro")
public class UsuarioController {
	
	@Autowired
	private Usuariodao usuarioDao;
		
	@Autowired
	private Barriodao barrioDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@ModelAttribute("usuario")
	private UsuarioRegistroS retornarNuevoUsuario() {
		return new UsuarioRegistroS();
	}
	
	@GetMapping
	public String mostrarFormulario(Model modelo) {
		List<Barrio> lstBar = this.barrioDao.encontrarTodo();
		modelo.addAttribute("barrio", lstBar);
		return "Ausuario/formulario_cu";
	}
	
	@PostMapping
	public String registrarCunteaUsuario(@ModelAttribute("usuario") UsuarioRegistroS registroS) {
		usuarioDao.save(registroS);
		return "redirect:/registro?exito";
	}
	
	@GetMapping("/usuario/editar/{idUsuario}")
	public String usuarioEdit(@PathVariable("idUsuario") Integer idUsuario, Model modelo) {
		Usuario usuario = usuarioDao.encontrarId(idUsuario);
		
		List<Barrio> listaBarrio = barrioDao.encontrarTodo();
		
		modelo.addAttribute("listaBarrio", listaBarrio);
		modelo.addAttribute("usuario", usuario);
		return "users_edit";
	}
	
	@PostMapping("/actualizar")
	public String usuarioActualizar(@ModelAttribute("usuarioN") Usuario usuario) {
		usuarioDao.crear(usuario);
		return "redirect:/logout";
	}
	
	@GetMapping("/userLog")
	public String nombreUser(Model modelo, HttpSession session) {
		Usuario logueado = (Usuario) session.getAttribute("usersession");
		modelo.addAttribute("user", logueado);
		return "user";
	}
	
	@GetMapping("/forgotPassword")
	public String  vistaForgot() {
		return "Ausuario/ForgotPassword";
	}
	
	@PostMapping("/forgotPassword")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token = RandomString.make(30);

		try {
			usuarioDao.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSiteURL(request) + "/registro/reset_password?token=" + token;
			sendEmail(email, resetPasswordLink);
			model.addAttribute("message",
					"Hemos enviado un enlace para restablecer la contraseña a su correo electrónico. Por favor, compruebe");

		} catch (UsuarioNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error al enviar correo electrónico");
		}

		return "Ausuario/ForgotPassword";
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("digitialcontent@gmail.com", "Soporte Digital Content");
		helper.setTo(recipientEmail);

		String subject = "Aquí está el enlace para restablecer su contraseña";

		String content = "<p>Hola,</p>" + "<p>Ha solicitado restablecer su contraseña.</p>" + "<p>\n"
				+ "Haga clic en el siguiente enlace para cambiar su contraseña:</p>" + "<p><a href=\"" + link
				+ "\">Cambiar mi contraseña</a></p>" + "<br>"
				+ "<p>Ignora este correo electrónico si recuerdas tu contraseña, " + "o no has hecho la solicitud.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		Usuario usuario = usuarioDao.getByResetPasswordToken(token);
		model.addAttribute("token", token);

		if (usuario == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		}

		return "Ausuario/ResetPassword";
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		Usuario usuario = usuarioDao.getByResetPasswordToken(token);
		model.addAttribute("title", "Restablecer su contraseña");

		if (usuario == null) {
			model.addAttribute("message", "Token invalido");
			return "Ausuario/message";
		} else {
			usuarioDao.updatePassword(usuario, password);

			model.addAttribute("message", "Has cambiado satisfactoriamente tu contraseña.");
		}

		return "Ausuario/message";
	}
}





