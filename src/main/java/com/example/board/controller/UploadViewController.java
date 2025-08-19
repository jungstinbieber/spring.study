package com.example.board.controller;

import com.example.board.upload.ImageFile;
import com.example.board.upload.ImageFileDTO;
import com.example.board.upload.ImageFileRepository;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadViewController {

    private final ImageFileRepository imageFileRepository;

    UploadViewController(ImageFileRepository imageFileRepository) {
        this.imageFileRepository = imageFileRepository;
    }
    

	@GetMapping("/upload")
	public String upload(Model model) {
		
		List<ImageFileDTO> list = imageFileRepository.findAll()
									.stream()
									.map(ImageFileDTO::from)
									.toList();
		
		
		model.addAttribute("images", list);
		
		return "/upload/imageList";
	}
	
	
}
