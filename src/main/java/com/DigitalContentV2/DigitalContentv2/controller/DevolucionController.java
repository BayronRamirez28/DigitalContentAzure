package com.DigitalContentV2.DigitalContentv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DigitalContentV2.DigitalContentv2.facadeImp.Devoluciondao;
import com.DigitalContentV2.DigitalContentv2.modelo.Devolucion;

@Controller
@RequestMapping("/admin/")
public class DevolucionController {
	
	@Autowired
	private Devoluciondao devolucionDao;
	
	@GetMapping({"/devolucion"})
	public String allDevolucion(Model modelo) {

		List<Devolucion> lstDevolucion = this.devolucionDao.encontrarTodo();
		modelo.addAttribute("listaD", lstDevolucion);
		return "/Administration/Adevolucion/devolucion";
	}
	
	@GetMapping("/devolucion/nuevoD")
	public String formularioCrear(Model modelo) {
	
		modelo.addAttribute("devolucionN",new Devolucion());
		return "Administration/Adevolucion/formulario_cd";
	}
	
	@PostMapping("/devolucion")
	public String crearDevolucion(@ModelAttribute("devolucionN") Devolucion devolucion) {
		devolucionDao.crear(devolucion);
		return "redirect:/admin/devolucion";
	}
	
	@GetMapping("/devolucion/editar/{idDevolucion}")
	public String formularioModificarD(@PathVariable("idDevolucion") Integer idDevolucion, Model modelo){
		
		Devolucion devolucion = devolucionDao.encontrarId(idDevolucion);
		modelo.addAttribute("devolucion",devolucion);
		
		return "Administration/Adevolucion/formulario_ed";
	}
	
	@GetMapping("/devolucion/eliminar/{idDevolucion}")
	public String eliminarDevolucion(@PathVariable("idDevolucion") Integer idDevolucion){
		
		Devolucion devolucion = devolucionDao.encontrarId(idDevolucion);
		
		this.devolucionDao.actualizarEstado(devolucion);
		return "redirect:/admin/devolucion";
	}
	

}

