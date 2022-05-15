package com.blogsystem.service;



import org.springframework.stereotype.Service;

import com.blogsystem.dto.PublicationDTO;
import com.blogsystem.dto.PublicationResponse;


public interface IPublicationService {
	
	public PublicationDTO createPublication(PublicationDTO publicationDTO);
	public PublicationResponse findAll(int pageNo, int pageSize, String orderBy, String sortDir);
	public PublicationDTO findById(Long id);
	public PublicationDTO update(PublicationDTO publicationDTO, Long id);
	public void deleteById(Long id);

}
