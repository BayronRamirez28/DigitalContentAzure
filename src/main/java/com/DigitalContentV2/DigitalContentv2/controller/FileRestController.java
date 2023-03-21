package com.DigitalContentV2.DigitalContentv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DigitalContentV2.DigitalContentv2.facadeImp.FileServicedao;

@RequestMapping("/admin")
@Controller
public class FileRestController {

	@Autowired
	private FileServicedao fileServicedao;
	
	@PostMapping("/upload")	
	public String uploadFile(@Validated @RequestParam(value = "files") List<MultipartFile> files) {
		try {
			fileServicedao.save(files);
			return "redirect:/admin/color?exito";
		} catch (Exception e) {
			return "redirect:/admin/color?fallo";
		}
	}
}
