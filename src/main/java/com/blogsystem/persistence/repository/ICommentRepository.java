package com.blogsystem.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogsystem.persistence.entity.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
	
	public List<Comment> findByPublicationId(Long publicationId);

}
