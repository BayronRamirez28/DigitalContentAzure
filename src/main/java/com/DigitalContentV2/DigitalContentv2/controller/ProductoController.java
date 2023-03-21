package com.DigitalContentV2.DigitalContentv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DigitalContentV2.DigitalContentv2.facadeImp.Categoriadao;
import com.DigitalContentV2.DigitalContentv2.facadeImp.Colordao;
import com.DigitalContentV2.DigitalContentv2.facadeImp.Generodao;
import com.DigitalContentV2.DigitalContentv2.facadeImp.Productodao;
import com.DigitalContentV2.DigitalContentv2.facadeImp.Talladao;
import com.DigitalContentV2.DigitalContentv2.facadeImp.UploadFielddao;
import com.DigitalContentV2.DigitalContentv2.modelo.Categoria;
import com.DigitalContentV2.DigitalContentv2.modelo.Color;
import com.DigitalContentV2.DigitalContentv2.modelo.Genero;
import com.DigitalContentV2.DigitalContentv2.modelo.Producto;
import com.DigitalContentV2.DigitalContentv2.modelo.Talla;

@RequestMapping("/admin")
@Controller
public class ProductoController {

	@Autowired
	private Productodao productoDao;

	@Autowired
	private UploadFielddao fielddao;

	@Autowired
	private Colordao colorDao;

	@Autowired
	private Categoriadao categoriaDao;

	@Autowired
	private Talladao tallaDao;

	@Autowired
	private Generodao generoDao;

	@GetMapping("/producto")
	private String listarProductos(Model modelo) {
		List<Producto> lstPro = productoDao.encontrarEstado();
		modelo.addAttribute("producto", lstPro);
		return "Administration/Aproducto/producto";
	}

	@GetMapping("/producto/nuevoP")
	private String formularioCrear(Model modelo) {
		List<Categoria> lstCat = categoriaDao.encontrarTodo();
		List<Talla> lstTalla = tallaDao.encontrarTodo();
		List<Color> lstCol = colorDao.encontrarTodo();
		List<Genero> lstGen = generoDao.encontrarTodo();

		modelo.addAttribute("lstCat", lstCat);
		modelo.addAttribute("lstTalla", lstTalla);
		modelo.addAttribute("lstCol", lstCol);
		modelo.addAttribute("lstGen", lstGen);
		modelo.addAttribute("productoN", new Producto());
		return "Administration/Aproducto/formulario_cp";
	}

	@PostMapping("/producto")
	private String crearProducto(@ModelAttribute("productoN") Producto producto) {
		productoDao.crear(producto);
		return "redirect:/admin/producto";
	}

	@GetMapping("/producto/editar/{idProducto}")
	private String formularioEditarP(@PathVariable("idProducto") Integer idProducto, Model modelo) {

		Producto producto = productoDao.encontrarId(idProducto);
		modelo.addAttribute("producto", producto);

		List<Categoria> lstCat = categoriaDao.encontrarTodo();
		List<Talla> lstTalla = tallaDao.encontrarTodo();
		List<Color> lstCol = colorDao.encontrarTodo();
		List<Genero> lstGen = generoDao.encontrarTodo();

		modelo.addAttribute("lstCat", lstCat);
		modelo.addAttribute("lstTalla", lstTalla);
		modelo.addAttribute("lstCol", lstCol);
		modelo.addAttribute("lstGen", lstGen);

		return "Administration/Aproducto/formulario_ep";
	}

	@GetMapping("/producto/eliminar/{idProducto}")
	private String eliminarProducto(@PathVariable("idProducto") Integer idProducto) {

		Producto producto = productoDao.encontrarId(idProducto);

		producto.setEstado("Inactivo");
		this.productoDao.eliminar(producto);
		return "redirect:/admin/producto";
	}

	@GetMapping("producto/catalogo")
	private String catalogo() {

		return "Administration/Aproducto/catalogo";
	}

	@PostMapping("/save")
	public String saveMeme(@Validated @ModelAttribute("meme") Producto prod, BindingResult result, Model model,
			@RequestParam(value = "file") MultipartFile image, RedirectAttributes flash, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "meme/meme";
		} else {
			if (!image.isEmpty()) {
				if (prod.getFoto() != null && prod.getFoto().length() > 0) {
					fielddao.delete(prod.getFoto());
				}
				String uniqueFileName = fielddao.copy(image);
				prod.setFoto(uniqueFileName);
			}
			productoDao.crear(prod);
			status.setComplete();
		}
		return "redirect:/admin/producto";
	}
	
	@GetMapping("/editfoto")
	public String editfot() {
		return "Administration/Aproducto/ef";
	} 
	
	@GetMapping("/editfoto/{idProducto}")
	private String editfot(@PathVariable("idProducto") Integer idProducto, Model modelo) {

		Producto producto = productoDao.encontrarId(idProducto);
		modelo.addAttribute("producto", producto);

		List<Categoria> lstCat = categoriaDao.encontrarTodo();
		List<Talla> lstTalla = tallaDao.encontrarTodo();
		List<Color> lstCol = colorDao.encontrarTodo();
		List<Genero> lstGen = generoDao.encontrarTodo();

		modelo.addAttribute("lstCat", lstCat);
		modelo.addAttribute("lstTalla", lstTalla);
		modelo.addAttribute("lstCol", lstCol);
		modelo.addAttribute("lstGen", lstGen);

		return "Administration/Aproducto/ef";
	}
}
