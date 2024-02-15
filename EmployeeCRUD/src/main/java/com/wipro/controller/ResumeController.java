package com.wipro.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.entity.Pdf;
import com.wipro.service.EmployeeCRUDService;
import com.wipro.service.ResumeService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ResumeController {

	@Autowired
	private ResumeService resumeService;
	@Autowired
	private EmployeeCRUDService employeeCRUDService;

	@GetMapping("/getUpload/{id}")
	public String getUpload(Model model,@PathVariable Long id) {
		model.addAttribute("emp",employeeCRUDService.findById(id));
		return "upload";
	}

	@PostMapping("/upload/{id}")
	public String fileUpload(@RequestParam MultipartFile file, Model model,@PathVariable Long id) throws IOException {
		Pdf pdf = new Pdf();
		String fileName = file.getOriginalFilename();
		pdf.setEmployeeid(id);
		pdf.setPdfName(fileName);
		pdf.setContent(file.getBytes());
		pdf.setSize(file.getSize());

		resumeService.createPdf(pdf);
		model.addAttribute("emp",employeeCRUDService.findById(id));
		
		model.addAttribute("message", "File Uploaded SucessFully");
		return "upload";
	}

	@GetMapping("/downloadFile")
	public void downloadFile(@Param("id") int id, Model model, HttpServletResponse response) throws IOException {
		Optional<Pdf> findPdfById = resumeService.findPdfById(id);
		if (findPdfById != null) {
			Pdf pdf = findPdfById.get();
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = "attachement; filename = " + pdf.getPdfName();

			response.setHeader(headerKey, headerValue);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(pdf.getContent());
			outputStream.close();

		}
	}

	@GetMapping("/image")
	public void showImage(@Param("id") int id, HttpServletResponse response, Optional<Pdf> pdf) throws IOException {
		pdf = resumeService.findPdfById(id);

		response.setContentType("image/jpeg,image/jpg,image/png,image/gif,image/pdf");
		response.getOutputStream().write(pdf.get().getContent());
		response.getOutputStream().close();

	}

	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadPdf(@PathVariable int id) {
		Optional<Pdf> pdfOptional = resumeService.findPdfById(id);
		if (pdfOptional.isPresent()) {
			Pdf pdf = pdfOptional.get();
			ByteArrayResource resource = new ByteArrayResource(pdf.getContent());

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdf.getPdfName() + "\"")
					.contentType(MediaType.APPLICATION_PDF).body(resource);
		} else {
			// Handle PDF not found
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/pdfs")
	public String listPdfs(Model model) {
		List<Pdf> pdfs = resumeService.getAllPdf();
		model.addAttribute("pdfs", pdfs);
		return "pdfList";
	}

}
