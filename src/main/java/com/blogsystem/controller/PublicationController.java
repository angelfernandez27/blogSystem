package com.blogsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogsystem.dto.PublicationDTO;
import com.blogsystem.dto.PublicationResponse;
import com.blogsystem.service.imp.PublicationService;
import com.blogsystem.util.AppConst;

@RestController
@RequestMapping("api/publications")
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	@GetMapping("/findAll")
	public ResponseEntity<PublicationResponse> findAll(
			@RequestParam(value = "pageNo", defaultValue = AppConst.NO_PAGE_DEFAULT, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConst.SIZE_PAGE_DEFAULT, required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConst.ORDER_BY_DEFAULT,required = false) String orderBy,
			@RequestParam(value = "sortDir",defaultValue = AppConst.ORDER_DIR_DEFAULT,required = false) String sortDir) {
		return ResponseEntity.ok(publicationService.findAll(pageNo,pageSize,orderBy, sortDir));
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<PublicationDTO> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(publicationService.findById(id));
	}

	@PostMapping("/save")
	public ResponseEntity<PublicationDTO> save(@Valid @RequestBody PublicationDTO publicationDTO) {
		return new ResponseEntity<>(publicationService.createPublication(publicationDTO), HttpStatus.CREATED);
	}

	@PutMapping("/updateById/{id}")
	public ResponseEntity<PublicationDTO> updateById(@Valid @RequestBody PublicationDTO publicationDTO,
			@PathVariable(name = "id") Long id) {
		PublicationDTO publicationResponse = publicationService.update(publicationDTO, id);

		return new ResponseEntity<>(publicationResponse, HttpStatus.OK);
	}

	@DeleteMapping("deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
		publicationService.deleteById(id);
		return new ResponseEntity<>("Publication deleted", HttpStatus.OK);
	}

}
