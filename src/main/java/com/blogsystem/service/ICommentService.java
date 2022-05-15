package com.blogsystem.service;

import java.util.List;

import com.blogsystem.dto.CommentDTO;

public interface ICommentService {
	
	public CommentDTO create(Long publicationId,CommentDTO commentDTO);
	
	public List<CommentDTO> getCommentByPublicationId(Long publicationId);
	
	public CommentDTO getCommentById(Long publicationId, Long commentId);
	
	
	public CommentDTO updateComment(Long publicationId, Long commentId, CommentDTO commentDTO);
	
	public void deleteById(Long publicationId, Long commentId);

}
