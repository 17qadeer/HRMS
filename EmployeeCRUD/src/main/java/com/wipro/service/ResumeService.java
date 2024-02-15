package com.wipro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Pdf;
import com.wipro.service.repo.ResumeRepository;

@Service
public class ResumeService {
	@Autowired
	private ResumeRepository resumeRepository;

	public Pdf createPdf(Pdf pdf) {
		return resumeRepository.save(pdf);
	}

	public List<Pdf> getAllPdf() {
		return resumeRepository.findAll();
	}
	
	public Optional<Pdf> findPdfById(int id){
		return resumeRepository.findById(id);
	}
}
