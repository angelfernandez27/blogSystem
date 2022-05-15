package com.blogsystem.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogsystem.dto.PublicationDTO;
import com.blogsystem.dto.PublicationResponse;
import com.blogsystem.exception.ResourceNotFoundException;
import com.blogsystem.persistence.entity.Publication;
import com.blogsystem.persistence.repository.IPublicatinRepository;
import com.blogsystem.service.IPublicationService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Service
public class PublicationService implements IPublicationService{
	@Autowired
	private IPublicatinRepository publicatinRepository;
	
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public PublicationDTO createPublication(PublicationDTO publicationDTO) {
		Publication publication=mapperEntity(publicationDTO);
		
		Publication newPublication= publicatinRepository.save(publication);
		
		PublicationDTO publicationResponse=mapperDto(newPublication);
		
		return publicationDTO;
	}


	@Override
	public PublicationResponse findAll(int pageNo, int pageSize, String orderBy, String sortDir) {
		//paginación y clasificación
		Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
		Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
		Page<Publication> publications=publicatinRepository.findAll(pageable);
		
		List<Publication> publicationList=publications.getContent();
		
		List<PublicationDTO> content= publicationList.stream().map(publication -> mapperDto(publication) ).collect(Collectors.toList());
		
		PublicationResponse publicationResponse=new PublicationResponse();
		publicationResponse.setContent(content);
		publicationResponse.setPageNumb(pageNo);
		publicationResponse.setPageSize(pageSize);
		publicationResponse.setTotalElements(publications.getTotalElements());
		publicationResponse.setTotalPages(publications.getTotalPages());
		publicationResponse.setLastPage(publications.isLast());
		
		return publicationResponse;
		
	}
	@Override
	public PublicationDTO findById(Long id) {
		Publication publication=publicatinRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Publication", "id", id));
		return mapperDto(publication);
	}
	@Override
	public PublicationDTO update(PublicationDTO publicationDTO, Long id) {
		Publication publication=publicatinRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Publication", "id", id));
		publication.setTitle(publicationDTO.getTitle());
		publication.setDescription(publicationDTO.getDescription());
		publication.setContent(publicationDTO.getContent());
		Publication publicationUpdate=publicatinRepository.save(publication);
		return mapperDto(publicationUpdate);
	}
	@Override
	public void deleteById(Long id) {
		Publication publication=publicatinRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Publication", "id", id));
		publicatinRepository.deleteById(publication.getId());
		
	}
	
	//concierte una entity a dto
	private PublicationDTO mapperDto(Publication publication) {
		PublicationDTO publicationDTO=mapper.map(publication, PublicationDTO.class);
		return publicationDTO;
		
	}
	
	//convierte dto a entity
	
	private Publication mapperEntity(PublicationDTO publicationDTO) {
		Publication publication=mapper.map(publicationDTO, Publication.class);
		return publication;
		
	}


	


	


	

}
