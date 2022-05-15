package com.blogsystem.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="publications", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Publication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String content;
	@OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<Comment> comments=new HashSet<>();
	
	public Publication() {
		super();
	}
	
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

	@Override
	public String toString() {
		return "Publication [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
				+ "]";
	}
	
	
}
