package com.blogsystem.dto;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.blogsystem.persistence.entity.Comment;

public class PublicationDTO {
	
	private Long id;
	@NotEmpty(message = "title must not be Empty!")
	private String title;
	@NotEmpty(message = "description must not be Empty!")
	private String description;
	@NotEmpty(message = "content must not be Empty!")
	private String content;
	
	private Set<Comment> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	
	
	

}
