package com.DigitalContentV2.DigitalContentv2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DigitalContentV2.DigitalContentv2.facadeImp.Entregadao;
import com.DigitalContentV2.DigitalContentv2.modelo.Entrega;
import com.DigitalContentV2.DigitalContentv2.modelo.Usuario;

@RequestMapping("/dom")
@Controller
public class EntregaController {

	@Autowired
	private Entregadao entregadao;

	@GetMapping("/entregas")
	public String allEntregas(Model modelo, HttpSession session) {
		Usuario logueado = (Usuario) session.getAttribute("usersession");
		List<Entrega> lstEn = this.entregadao.listEntregasDom(logueado);
		modelo.addAttribute("listaEn", lstEn);
		return "ADomicilios/pedidos";
	}

	@GetMapping("/pendiente")
	public String allPendiente(Model modelo) {
		List<Entrega> lstEn = this.entregadao.estadoPendiente();
		modelo.addAttribute("listaEn", lstEn);
		return "ADomicilios/pedidos";
	}
	
	
	@GetMapping("/entrega/{idEntrega}")
	public String registrarVentaForm(Model model, @PathVariable("idEntrega") Integer idEntrega) {
		Entrega entrega = entregadao.encontrarId(idEntrega);
		model.addAttribute("Entrega", entrega);
		return "ADomicilios/EntregaU";
	}
		
	@PostMapping("/entrega")
	public String updateEntrega(@ModelAttribute("entregaU") Entrega entrega, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usersession");
		this.entregadao.actualizar(entrega, usuario);
		return "redirect:/dom/entregas";
	}
	
	
}
