package com.blogsystem.dto;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.blogsystem.persistence.entity.Publication;

public class CommentDTO {
	
	private Long id;
	@NotEmpty(message = "name must not be Empty!")
	private String name;
	@NotEmpty(message = "email must not be Empty!")
	@Email(message = "Email must be correct!")
	private String email;
	@NotEmpty(message = "body must not be Empty!")
	
	private String body;
	
	private Publication publication;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public CommentDTO() {
		super();
	}
	
	

}
